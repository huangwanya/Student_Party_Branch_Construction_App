package com.entity.view;

import com.entity.DangshijiaoyuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 党史教育
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-05-03 22:14:17
 */
@TableName("dangshijiaoyu")
public class DangshijiaoyuView  extends DangshijiaoyuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DangshijiaoyuView(){
	}
 
 	public DangshijiaoyuView(DangshijiaoyuEntity dangshijiaoyuEntity){
 	try {
			BeanUtils.copyProperties(this, dangshijiaoyuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
