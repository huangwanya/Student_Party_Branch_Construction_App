package com.entity.view;

import com.entity.DangfeijiaojiaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 党费缴交
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-05-03 22:14:17
 */
@TableName("dangfeijiaojiao")
public class DangfeijiaojiaoView  extends DangfeijiaojiaoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DangfeijiaojiaoView(){
	}
 
 	public DangfeijiaojiaoView(DangfeijiaojiaoEntity dangfeijiaojiaoEntity){
 	try {
			BeanUtils.copyProperties(this, dangfeijiaojiaoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
