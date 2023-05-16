package com.entity.view;

import com.entity.XuexidakaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 学习打卡
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-05-03 22:14:17
 */
@TableName("xuexidaka")
public class XuexidakaView  extends XuexidakaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public XuexidakaView(){
	}
 
 	public XuexidakaView(XuexidakaEntity xuexidakaEntity){
 	try {
			BeanUtils.copyProperties(this, xuexidakaEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
