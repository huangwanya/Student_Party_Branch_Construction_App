import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import adminexam from '@/views/modules/exampaperlist/exam'
    import dangfeijiaojiao from '@/views/modules/dangfeijiaojiao/list'
    import news from '@/views/modules/news/list'
    import examfailrecord from '@/views/modules/examfailrecord/list'
    import xuexidaka from '@/views/modules/xuexidaka/list'
    import dangjianhuodong from '@/views/modules/dangjianhuodong/list'
    import examquestion from '@/views/modules/examquestion/list'
    import dangshijiaoyu from '@/views/modules/dangshijiaoyu/list'
    import exampaper from '@/views/modules/exampaper/list'
    import forum from '@/views/modules/forum/list'
    import dangyuan from '@/views/modules/dangyuan/list'
    import huodongbaoming from '@/views/modules/huodongbaoming/list'
    import chat from '@/views/modules/chat/list'
    import exampaperlist from '@/views/modules/exampaperlist/list'
    import messages from '@/views/modules/messages/list'
    import discussdangshijiaoyu from '@/views/modules/discussdangshijiaoyu/list'
    import config from '@/views/modules/config/list'
    import examrecord from '@/views/modules/examrecord/list'
    import huodongleixing from '@/views/modules/huodongleixing/list'


//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
      ,{
	path: '/dangfeijiaojiao',
        name: '党费缴交',
        component: dangfeijiaojiao
      }
      ,{
	path: '/news',
        name: '通知公告',
        component: news
      }
      ,{
	path: '/examfailrecord',
        name: '错题本',
        component: examfailrecord
      }
      ,{
	path: '/xuexidaka',
        name: '学习打卡',
        component: xuexidaka
      }
      ,{
	path: '/dangjianhuodong',
        name: '党建活动',
        component: dangjianhuodong
      }
      ,{
	path: '/examquestion',
        name: '试题内容管理',
        component: examquestion
      }
      ,{
	path: '/dangshijiaoyu',
        name: '党史教育',
        component: dangshijiaoyu
      }
      ,{
	path: '/exampaper',
        name: '在线考试管理',
        component: exampaper
      }
      ,{
	path: '/forum',
        name: '党内交流',
        component: forum
      }
      ,{
	path: '/dangyuan',
        name: '党员',
        component: dangyuan
      }
      ,{
	path: '/huodongbaoming',
        name: '活动报名',
        component: huodongbaoming
      }
      ,{
	path: '/chat',
        name: '线上咨询',
        component: chat
      }
      ,{
	path: '/exampaperlist',
        name: '在线考试列表',
        component: exampaperlist
      }
      ,{
	path: '/messages',
        name: '投诉建议',
        component: messages
      }
      ,{
	path: '/discussdangshijiaoyu',
        name: '党史教育评论',
        component: discussdangshijiaoyu
      }
      ,{
	path: '/config',
        name: '轮播图管理',
        component: config
      }
      ,{
	path: '/examrecord',
        name: '考试记录',
        component: examrecord
      }
      ,{
	path: '/huodongleixing',
        name: '活动类型',
        component: huodongleixing
      }
    ]
  },
  {
    path: '/adminexam',
    name: 'adminexam',
    component: adminexam,
    meta: {icon:'', title:'adminexam'}
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
