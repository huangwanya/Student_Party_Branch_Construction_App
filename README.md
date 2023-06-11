# 
# ### 本实践旨在通过一个美食分类的案列，让大家理解和掌握如何使用飞桨动态图搭建一个**卷积神经网络**。
# 
# ### 特别提示：本实践所用数据集均来自互联网，请勿用于商务用途。

# In[19]:


import os
import zipfile
import random
import json
import paddle
import sys
import numpy as np
from PIL import Image
from PIL import ImageEnhance
import paddle.fluid as fluid
import matplotlib.pyplot as plt
import cv2
# import numpy as np
# import paddle.fluid as fluid
import os
os.environ["CUDA_VISIBLE_DEVICES"] = "0"



import paddle.fluid as fluid

fluid.default_startup_program().random_seed = 1
fluid.default_main_program().random_seed = 1
place = fluid.CUDAPlace(0) 
exe = fluid.Executor(place)





import paddle.fluid as fluid

# 创建设备上下文池
fluid.device_guard("cuda")

# 设置GPU设备ID
device_id = 0  # 根据你的需求选择合适的设备ID

# 创建设备上下文
place = fluid.CUDAPlace(device_id)
device_ctx = fluid.Executor(place)



# In[20]:


class Compose(object):
    def __init__(self, transforms):
        self.transforms = transforms
    def __call__(self, image):
        for t in self.transforms:
            image = t(image)
        return image


class Normalize(object):
    def __init__(self, mean_val, std_val, val_scale=1):
        # set val_scale = 1 if mean and std are in range (0,1)
        # set val_scale to other value, if mean and std are in range (0,255)
        self.mean = np.array(mean_val, dtype=np.float32)
        self.std = np.array(std_val, dtype=np.float32)
        self.val_scale = 1/255.0 if val_scale==1 else 1
    def __call__(self, image, label=None):
        image = image.astype(np.float32)
        image = image * self.val_scale
        image = image - self.mean
        image = image * (1 / self.std)
        return image, label


class ConvertDataType(object):
    def __call__(self, image, label=None):
        if label is not None:
            label = label.astype(np.int64)
        return image.astype(np.float32), label


class Pad(object):
    def __init__(self, size, ignore_label=255, mean_val=0, val_scale=1):
        # set val_scale to 1 if mean_val is in range (0, 1)
        # set val_scale to 255 if mean_val is in range (0, 255) 
        factor = 255 if val_scale == 1 else 1

        self.size = size
        self.ignore_label = ignore_label
        self.mean_val=mean_val
        # from 0-1 to 0-255
        if isinstance(self.mean_val, (tuple,list)):
            self.mean_val = [int(x* factor) for x in self.mean_val]
        else:
            self.mean_val = int(self.mean_val * factor)


    def __call__(self, image, label=None):
        h, w, c = image.shape
        pad_h = max(self.size - h, 0)
        pad_w = max(self.size - w, 0)

        pad_h_half = int(pad_h / 2)
        pad_w_half = int(pad_w / 2)

        if pad_h > 0 or pad_w > 0:

            image = cv2.copyMakeBorder(image,
                                       top=pad_h_half,
                                       left=pad_w_half,
                                       bottom=pad_h - pad_h_half,
                                       right=pad_w - pad_w_half,
                                       borderType=cv2.BORDER_CONSTANT,
                                       value=self.mean_val)
            
        return image


# TODO
class CenterCrop(object):
    
        
    def __call__(self,data):
        #self.data = np.array(self,data)
        w,h,_ = data.shape
        
        target_w = int(w/2)
        target_h = int(h/2)
        start_x = int((w-target_w)//2)
        start_y = int((h-target_h)//2)

        crop_data = self.data[start_y:start_y+target_h,start_x:start_x+target_w,:]
        crop_data = cv2.resize(crop_data ,(w,h))
        return crop_data







# TODO
class Resize(object):
    def __init__(self,h,w):
        
        self.w = w
        self.h = h
    def __call__(self,data):
        #self.data = np.array(self.data)
        
        resized_data = cv2.resize(data,(self.w,self.h))
        #print(type(resized_data))
        return resized_data
        




# TODO
class RandomFlip(object):
    def __init__(self,rate=0.5,h2v=1):
        #self.data = data
        self.rate = rate
        self.h2v = h2v
    def __call__(self,data):
        
        if np.random.random()>self.rate:
            # 沿垂直方向翻转
            if self.h2v==0:
                data = cv2.flip(data,1)
            # 沿水平方向翻转
            else:
                data = cv2.flip(data,0)
        return data

        


# TODO
class RandomCrop(object):
    def __init__(self,rate=0.8):
        #self.data = data
        self.rate = rate
    def __call__(self,data):
        #print(data)
        # print(type(data))
        w,h,_ = data.shape
        target_w = int(w*self.rate)
        target_h = int(h*self.rate)

        start_x = int((w-target_w)//2)
        start_y = int((h-target_h)//2)

        zeros = data[start_y:start_y+target_w,start_x:start_x+target_w,:]
        zeros = cv2.resize(zeros,(w,h,))
        # print(zeros)
        return zeros


# TODO
class Scale(object):
    def __init__(self,target_w,target_h):
        #self.data = data
        self.target_w = target_w
        self.target_h = target_h
    def __call__(self,data):
        w,h,_ =  data.shape
        start_x = w-self.target_w
        start_y = h-self.target_h
        zeros = data[start_y:start_y+self.target_h,start_x:start_x+self.target_w,:]
        
        zeros = cv2.resize(zeros,(w,h))
        return zeros



# TODO
class RandomScale(object):
    def __init__(self,ratio=0.8,rand=True):
        #self.data = data
        self.ratio= ratio
        self.rand = rand
    def __call__(self,data):
        w,h,_ = data.shape
        target_w = int(w*self.ratio)
        target_h = int(h*self.ratio)

        if self.rand:
            
            start_x = np.random.randint(0, w - target_w)
            start_y = np.random.randint(0, h - target_h)
        else:
            start_x = ( w - target_w ) // 2
            start_y = ( h - target_h ) // 2

        zeros = data[start_y:start_y+target_h,start_x:start_x+target_w,:]
        zeros = cv2.resize(zeros ,(w,h))
        return zeros


# In[28]:


'''
参数配置
'''
train_parameters = {
    "input_size": [3, 64, 64],                                #输入图片的shape
    "class_dim": -1,                                          #分类数
    "src_path":"data/data42610/foods.zip",                    #原始数据集路径
    "target_path":"/home/aistudio/data/",                     #要解压的路径
    "train_list_path": "/home/aistudio/data/train.txt",       #train.txt路径
    "eval_list_path": "/home/aistudio/data/eval.txt",         #eval.txt路径
    "readme_path": "/home/aistudio/data/readme.json",         #readme.json路径
    "label_dict":{},                                          #标签字典
    "num_epochs": 200,                                          #训练轮数
    "train_batch_size": 100,                                   #训练时每个批次的大小
    "learning_strategy": {                                    #优化函数相关的配置
        "lr": 0.0001                                          #超参数学习率
    } 
}


# # **一、数据准备**
# 
# ### （1）解压原始数据集
# 
# ### （2）按照比例划分训练集与验证集
# 
# ### （3）乱序，生成数据列表
# 
# ### （4）构造训练数据集提供器和验证数据集提供器

# In[22]:



def unzip_data(src_path,target_path):
    '''
    解压原始数据集，将src_path路径下的zip包解压至target_path目录下
    '''
    if(not os.path.isdir(target_path + "foods")):     
        z = zipfile.ZipFile(src_path, 'r')
        z.extractall(path=target_path)
        z.close()


# In[23]:



def get_data_list(target_path,train_list_path,eval_list_path):
    '''
    生成数据列表
    '''
    #存放所有类别的信息
    class_detail = []
    #获取所有类别保存的文件夹名称
    data_list_path=target_path+"foods/"
    class_dirs = os.listdir(data_list_path)  
    #总的图像数量
    all_class_images = 0
    #存放类别标签
    class_label=0
    #存放类别数目
    class_dim = 0
    #存储要写进eval.txt和train.txt中的内容
    trainer_list=[]
    eval_list=[]
    #读取每个类别
    for class_dir in class_dirs:
        if class_dir != ".DS_Store":
            class_dim += 1
            #每个类别的信息
            class_detail_list = {}
            eval_sum = 0
            trainer_sum = 0
            #统计每个类别有多少张图片
            class_sum = 0
            #获取类别路径 
            path = data_list_path  + class_dir
            # 获取所有图片
            img_paths = os.listdir(path)
            for img_path in img_paths:                                  # 遍历文件夹下的每个图片
                name_path = path + '/' + img_path                       # 每张图片的路径
                if class_sum % 8 == 0:                                  # 每8张图片取一个做验证数据
                    eval_sum += 1                                       # test_sum为测试数据的数目
                    eval_list.append(name_path + "\t%d" % class_label + "\n")
                else:
                    trainer_sum += 1 
                    trainer_list.append(name_path + "\t%d" % class_label + "\n")#trainer_sum测试数据的数目
                class_sum += 1                                          #每类图片的数目
                all_class_images += 1                                   #所有类图片的数目
             
            # 说明的json文件的class_detail数据
            class_detail_list['class_name'] = class_dir             #类别名称
            class_detail_list['class_label'] = class_label          #类别标签
            class_detail_list['class_eval_images'] = eval_sum       #该类数据的测试集数目
            class_detail_list['class_trainer_images'] = trainer_sum #该类数据的训练集数目
            class_detail.append(class_detail_list)  
            #初始化标签列表
            train_parameters['label_dict'][str(class_label)] = class_dir
            class_label += 1 
            
    #初始化分类数
    train_parameters['class_dim'] = class_dim
    
    #乱序  
    random.shuffle(eval_list)
    with open(eval_list_path, 'a') as f:
        for eval_image in eval_list:
            f.write(eval_image) 
            
    random.shuffle(trainer_list)
    with open(train_list_path, 'a') as f2:
        for train_image in trainer_list:
            f2.write(train_image) 

    # 说明的json文件信息
    readjson = {}
    readjson['all_class_name'] = data_list_path                  #文件父目录
    readjson['all_class_images'] = all_class_images
    readjson['class_detail'] = class_detail
    jsons = json.dumps(readjson, sort_keys=True, indent=4, separators=(',', ': '))
    with open(train_parameters['readme_path'],'w') as f:
        f.write(jsons)
    print ('生成数据列表完成！')


# In[24]:



def custom_reader(file_list):
    '''
    自定义reader
    '''
    def reader():
        with open(file_list, 'r') as f:
            lines = [line.strip() for line in f]
            for line in lines:
                img_path, lab = line.strip().split('\t')
                img = Image.open(img_path) 
                if img.mode != 'RGB': 
                    img = img.convert('RGB') 
                img = img.resize((64, 64), Image.BILINEAR)
                img = np.array(img).astype('float32') 
                img = img.transpose((2, 0, 1))  # HWC to CHW 
                img = img/255                   # 像素值归一化 
                yield img, int(lab) 
    return reader


# In[25]:


def fix(path):
    image = cv2.imread(path)
    # label = cv2.imread('work/infer_apple_pie.png')
    # print(image.shape)
    # TODO: crop_size
    #crop_size = (256,256)
    # TODO: Transform: RandomSacle, RandomFlip, Pad, RandomCrop
    name=path.split('.')[0]+'_增强.png'

    augment = Compose([
            Resize(256,256),
            RandomScale(),
            RandomFlip(),
            Pad(256),
            RandomCrop()])
    new_image = augment(image)
    cv2.imwrite(name,new_image)


# In[26]:


def zengqiang(target_path):
    '''
    生成数据列表
    '''
    #存放所有类别的信息
    class_detail = []
    #获取所有类别保存的文件夹名称
    data_list_path=target_path+"foods/"
    class_dirs = os.listdir(data_list_path)  
    #总的图像数量
    all_class_images = 0
    #存放类别标签
    class_label=0
    #存放类别数目
    class_dim = 0
    # #存储要写进eval.txt和train.txt中的内容
    # trainer_list=[]
    # eval_list=[]
    #读取每个类别
    for class_dir in class_dirs:
        if class_dir != ".DS_Store":
            class_dim += 1
            #每个类别的信息
            class_detail_list = {}
            eval_sum = 0
            trainer_sum = 0
            #统计每个类别有多少张图片
            class_sum = 0
            #获取类别路径 
            path = data_list_path  + class_dir
            # 获取所有图片
            img_paths = os.listdir(path)
            for img_path in img_paths:                                  # 遍历文件夹下的每个图片
                name_path = path + '/' + img_path                       # 每张图片的路径
                fix(name_path)
                # if class_sum % 8 == 0:                                  # 每8张图片取一个做验证数据
                #     eval_sum += 1                                       # test_sum为测试数据的数目
                #     eval_list.append(name_path + "\t%d" % class_label + "\n")
                # else:
                #     trainer_sum += 1 
                #     trainer_list.append(name_path + "\t%d" % class_label + "\n")#trainer_sum测试数据的数目
                class_sum += 1                                          #每类图片的数目
                all_class_images += 1                                   #所有类图片的数目
             
            # # 说明的json文件的class_detail数据
            # class_detail_list['class_name'] = class_dir             #类别名称
            # class_detail_list['class_label'] = class_label          #类别标签
            # class_detail_list['class_eval_images'] = eval_sum       #该类数据的测试集数目
            # class_detail_list['class_trainer_images'] = trainer_sum #该类数据的训练集数目
            # class_detail.append(class_detail_list)  
            # #初始化标签列表
            # train_parameters['label_dict'][str(class_label)] = class_dir
            class_label += 1 
            
    #初始化分类数
    # train_parameters['class_dim'] = class_dim
    
    #乱序  
    # random.shuffle(eval_list)
    # with open(eval_list_path, 'a') as f:
    #     for eval_image in eval_list:
    #         f.write(eval_image) 
            
    # random.shuffle(trainer_list)
    # with open(train_list_path, 'a') as f2:
    #     for train_image in trainer_list:
    #         f2.write(train_image) 

    # # 说明的json文件信息
    # readjson = {}
    # readjson['all_class_name'] = data_list_path                  #文件父目录
    # readjson['all_class_images'] = all_class_images
    # readjson['class_detail'] = class_detail
    # jsons = json.dumps(readjson, sort_keys=True, indent=4, separators=(',', ': '))
    # with open(train_parameters['readme_path'],'w') as f:
    #     f.write(jsons)
    print ('图像增强完成！')


# In[ ]:





# In[ ]:


# path='work/infer_apple_pie.png'
# name=path.split('.')[0]+'_增强.png'
# name


# In[27]:


'''
参数初始化
'''
src_path=train_parameters['src_path']
target_path=train_parameters['target_path']
train_list_path=train_parameters['train_list_path']
eval_list_path=train_parameters['eval_list_path']
batch_size=train_parameters['train_batch_size']

'''
解压原始数据到指定路径
'''
unzip_data(src_path,target_path)

zengqiang(target_path)


# In[29]:


data_list_path=target_path+"foods/"
class_dirs = os.listdir(data_list_path)  
#总的图像数量
all_class_images = 0
for class_dir in class_dirs:
        if class_dir != ".DS_Store":
            # class_dim += 1
            # #每个类别的信息
            # class_detail_list = {}
            # eval_sum = 0
            # trainer_sum = 0
            # #统计每个类别有多少张图片
            # class_sum = 0
            # #获取类别路径 
            path = data_list_path  + class_dir
            # 获取所有图片
            img_paths = os.listdir(path)
            for img_path in img_paths:                                  # 遍历文件夹下的每个图片
                # name_path = path + '/' + img_path                       # 每张图片的路径
                # fix(name_path)
                # if class_sum % 8 == 0:                                  # 每8张图片取一个做验证数据
                #     eval_sum += 1                                       # test_sum为测试数据的数目
                #     eval_list.append(name_path + "\t%d" % class_label + "\n")
                # else:
                #     trainer_sum += 1 
                #     trainer_list.append(name_path + "\t%d" % class_label + "\n")#trainer_sum测试数据的数目
                # class_sum += 1                                          #每类图片的数目
                all_class_images += 1                                   #所有类图片的数目
             
            # # 说明的json文件的class_detail数据
            # class_detail_list['class_name'] = class_dir             #类别名称
            # class_detail_list['class_label'] = class_label          #类别标签
            # class_detail_list['class_eval_images'] = eval_sum       #该类数据的测试集数目
            # class_detail_list['class_trainer_images'] = trainer_sum #该类数据的训练集数目
            # class_detail.append(class_detail_list)  
            # #初始化标签列表
            # train_parameters['label_dict'][str(class_label)] = class_dir
            # class_label += 1 


# In[30]:


all_class_images


# In[31]:




'''
划分训练集与验证集，乱序，生成数据列表
'''
#每次生成数据列表前，首先清空train.txt和eval.txt
with open(train_list_path, 'w') as f: 
    f.seek(0)
    f.truncate() 
with open(eval_list_path, 'w') as f: 
    f.seek(0)
    f.truncate() 
    
#生成数据列表   
get_data_list(target_path,train_list_path,eval_list_path)
# train=[]
# with open(train_list_path, 'r') as f: 
#     for i in range(1000):
#         lines = f.readline().split('\t')[0]
#         # print(lines)
#         train.append(lines)

# for line in train:
#     fix(line)

'''
构造数据提供器
'''
train_reader = paddle.batch(custom_reader(train_list_path),
                            batch_size=batch_size,
                            drop_last=True)      #若设置为True，则当最后一个batch不等于batch_size时，丢弃最后一个batch；若设置为False，则不会。默认值为False。
eval_reader = paddle.batch(custom_reader(eval_list_path),
                            batch_size=batch_size,
                            drop_last=True)


# In[ ]:





# # **二、模型配置**
# 
# 

# In[32]:



#定义卷积网络
class MyCNN(fluid.dygraph.Layer):
    # def __init__(self):
    #     super(MyCNN,self).__init__()
    #     self.hidden1 = fluid.dygraph.Conv2D(num_channels=3,       #通道数
    #                                         num_filters=64,       #卷积核个数
    #                                         filter_size=3,        #卷积核大小
    #                                         stride=1)             #步长
    #     self.hidden2 = fluid.dygraph.Conv2D(num_channels=64,
    #                                         num_filters = 128,
    #                                         filter_size=3,
    #                                         stride=1)
    #     self.hidden3 = fluid.dygraph.Pool2D(pool_size=2,          #池化核大小
    #                                         pool_type='max',      #池化类型
    #                                         pool_stride=2)        #池化步长
    #     self.hidden4 = fluid.dygraph.Linear(128*30*30,5,act='softmax')
    # #网络的前向计算过程
    # def forward(self,input):
    #     x = self.hidden1(input)
    #     #print(x.shape)
    #     x = self.hidden2(x)
    #     #print(x.shape)
    #     x = self.hidden3(x)
    #     #print(x.shape)
    #     #卷积层的输出特征图如何当作全连接层的输入使用呢？
    #     #卷积层的输出数据格式是[N,C,H,W],在输入全连接层的时候，会自动将数据拉平.
    #     #也就是对每个样本，自动将其转化为长度为K的向量，其中K=C×H×W，一个mini-batch的数据维度变成了N×K的二维向量。
    #     x = fluid.layers.reshape(x, shape=[-1, 128*30*30])
    #     y = self.hidden4(x)
    #     return y
    def __init__(self):
        super(MyCNN,self).__init__()
        self.conv1 = fluid.dygraph.Conv2D(num_channels=1, num_filters=64, filter_size=3, padding=2, act='relu')
        self.pool1 = fluid.dygraph.Pool2D(pool_size=2, pool_stride=2, pool_type='max')
        self.conv2 = fluid.dygraph.Conv2D(num_channels=64, num_filters=128, filter_size=3, padding=2, act='relu')
        self.pool2 = fluid.dygraph.Pool2D(pool_size=2, pool_stride=2, pool_type='max')
        self.conv3 = fluid.dygraph.Conv2D(num_channels=128, num_filters=256, filter_size=3, padding=2, act='relu')
        self.pool3 = fluid.dygraph.Pool2D(pool_size=2, pool_stride=2, pool_type='max')
        self.conv4 = fluid.dygraph.Conv2D(num_channels=256, num_filters=512, filter_size=3, padding=2, act='relu')
        self.fc1 = fluid.dygraph.Linear(input_dim=512*6*6, output_dim=100, act='relu');
        self.fc2 = fluid.dygraph.Linear(input_dim=100, output_dim=4096, act='relu');
        self.fc3 = fluid.dygraph.Linear(input_dim=4096, output_dim=train_parameters['class_dim'], act='softmax')

    def forward(self,input):
        x = self.conv1(input)
        x = self.pool1(x)
        x = self.conv2(x)
        x = self.pool2(x)
        x = self.conv3(x)
        x = self.pool3(x)
        x = self.conv4(x)
        x = fluid.layers.reshape(x, [x.shape[0], -1])
        x = self.fc1(x)
        x = fluid.layers.dropout(x, 0.5)
        x = self.fc2(x)
        x = fluid.layers.dropout(x, 0.5)
        x = self.fc3(x)
        return x


# # **三、模型训练 && 四、模型评估**

# In[33]:


all_train_iter=0
all_train_iters=[]
all_train_costs=[]
all_train_accs=[]

def draw_train_process(title,iters,costs,accs,label_cost,lable_acc):
    plt.title(title, fontsize=24)
    plt.xlabel("iter", fontsize=20)
    plt.ylabel("loss/acc", fontsize=20)
    plt.plot(iters, costs,color='red',label=label_cost) 
    plt.plot(iters, accs,color='green',label=lable_acc) 
    plt.legend()
    plt.grid()
    plt.show()


def draw_process(title,color,iters,data,label):
    plt.title(title, fontsize=24)
    plt.xlabel("iter", fontsize=20)
    plt.ylabel(label, fontsize=20)
    plt.plot(iters, data,color=color,label=label) 
    plt.legend()
    plt.grid()
    plt.show()


# In[35]:




'''
模型训练
'''
#place=fluid.CUDAPlace(0)表示使用GPU进行训练；不设置表示使用CPU进行训练
with fluid.dygraph.guard(place = fluid.CUDAPlace(0)):  
# with fluid.dygraph.guard():   
                           
   print(train_parameters['class_dim'])
   print(train_parameters['label_dict'])

   cnn = MyCNN()
   optimizer=fluid.optimizer.AdamOptimizer(learning_rate=train_parameters['learning_strategy']['lr'],
                                               parameter_list=cnn.parameters()) 
   for epoch_num in range(train_parameters['num_epochs']):
       for batch_id, data in enumerate(train_reader()):
           dy_x_data = np.array([item[0] for item in data], dtype='float32').reshape(-1, 3, 64, 64)
           y_data = np.array([item[1] for item in data], dtype='int64').reshape(-1, 1)

           #将Numpy转换为DyGraph接收的输入
           img = fluid.dygraph.to_variable(dy_x_data)
           label = fluid.dygraph.to_variable(y_data)

           out = cnn(img)
           #计算精度和损失
           acc=fluid.layers.accuracy(out,label)
           loss = fluid.layers.cross_entropy(out, label)
           avg_loss = fluid.layers.mean(loss)

           #使用backward()方法可以执行反向网络
           avg_loss.backward()
           optimizer.minimize(avg_loss)
            
           #将参数梯度清零以保证下一轮训练的正确性
           cnn.clear_gradients()
           
           all_train_iter=all_train_iter+train_parameters['train_batch_size']
           all_train_iters.append(all_train_iter)
           all_train_costs.append(loss.numpy()[0])
           all_train_accs.append(acc.numpy()[0])
               
           if batch_id % 30 == 0:
               print("Loss at epoch {} step {}: {}, acc: {}".format(epoch_num, batch_id, avg_loss.numpy(), acc.numpy()))
   draw_train_process("training",all_train_iters,all_train_costs,all_train_accs,"trainning loss","trainning acc")  
   draw_process("trainning loss","red",all_train_iters,all_train_costs,"trainning loss")
   draw_process("trainning acc","green",all_train_iters,all_train_accs,"trainning acc")
   #保存模型参数
   fluid.save_dygraph(cnn.state_dict(), "cnn")   
   print("Final loss: {}".format(avg_loss.numpy()))


# In[ ]:


'''
模型校验
'''
with fluid.dygraph.guard():
    model, _ = fluid.load_dygraph("cnn")
    cnn = MyCNN()
    cnn.load_dict(model)
    cnn.eval()
    accs = []
    for batch_id, data in enumerate(eval_reader()):
        # dy_x_data = np.array([x[0] for x in data]).astype('float32')
        # y_data = np.array([x[1] for x in data]).astype('int')
        # y_data = y_data[:, np.newaxis]
        
        dy_x_data = np.array([item[0] for item in data], dtype='float32').reshape(-1, 3, 64, 64)
        y_data = np.array([item[1] for item in data], dtype='int64').reshape(-1, 1)
        
        img = fluid.dygraph.to_variable(dy_x_data)
        label = fluid.dygraph.to_variable(y_data)

        out = cnn(img)
        acc=fluid.layers.accuracy(out,label)#计算精度
        lab = np.argsort(out.numpy())
        accs.append(acc.numpy()[0])
print(np.mean(accs))
print(0.60012778)


# # **五、模型预测**

# In[ ]:


import os
import zipfile

def unzip_infer_data(src_path,target_path):
    '''
    解压预测数据集
    '''
    if(not os.path.isdir(target_path + "foods")):     
        z = zipfile.ZipFile(src_path, 'r')
        z.extractall(path=target_path)
        z.close()


def load_image(img_path):
    '''
    预测图片预处理
    '''
    img = Image.open(img_path) 
    if img.mode != 'RGB': 
        img = img.convert('RGB') 
    img = img.resize((64, 64), Image.BILINEAR)
    img = np.array(img).astype('float32') 
    img = img.transpose((2, 0, 1))  # HWC to CHW 
    img = img/255                   # 像素值归一化 
    return img


infer_src_path = '/home/aistudio/data/data42610/foods.zip'
infer_dst_path = '/home/aistudio/data/'
unzip_infer_data(infer_src_path,infer_dst_path)

label_dic = train_parameters['label_dict']

'''
模型预测
'''
with fluid.dygraph.guard():
    model, _ = fluid.dygraph.load_dygraph("cnn")
    cnn = MyCNN()
    cnn.load_dict(model)
    cnn.eval()
    
    #展示预测图片
    infer_path='work/infer_apple_pie.png'
    img = Image.open(infer_path)
    plt.imshow(img)          #根据数组绘制图像
    plt.show()               #显示图像

    #对预测图片进行预处理
    infer_imgs = []
    infer_imgs.append(load_image(infer_path))
    infer_imgs = np.array(infer_imgs)
   
    for  i in range(len(infer_imgs)):
        data = infer_imgs[i]
        dy_x_data = np.array(data).astype('float32')
        dy_x_data=dy_x_data[np.newaxis,:, : ,:]
        img = fluid.dygraph.to_variable(dy_x_data)
        out = cnn(img)
        lab = np.argmax(out.numpy())  #argmax():返回最大数的索引
        print("第{}个样本,被预测为：{}".format(i+1,label_dic[str(lab)]))
        
print("结束")

