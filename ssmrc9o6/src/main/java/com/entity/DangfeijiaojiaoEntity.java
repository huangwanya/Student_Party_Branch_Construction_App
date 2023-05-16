package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 党费缴交
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-05-03 22:14:17
 */
@TableName("dangfeijiaojiao")
public class DangfeijiaojiaoEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public DangfeijiaojiaoEntity() {
		
	}
	
	public DangfeijiaojiaoEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 缴费标题
	 */
					
	private String jiaofeibiaoti;
	
	/**
	 * 封面
	 */
					
	private String fengmian;
	
	/**
	 * 党费金额
	 */
					
	private Float dangfeijine;
	
	/**
	 * 党员账号
	 */
					
	private String dangyuanzhanghao;
	
	/**
	 * 党员姓名
	 */
					
	private String dangyuanxingming;
	
	/**
	 * 通知时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date tongzhishijian;
	
	/**
	 * 是否支付
	 */
					
	private String ispay;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：缴费标题
	 */
	public void setJiaofeibiaoti(String jiaofeibiaoti) {
		this.jiaofeibiaoti = jiaofeibiaoti;
	}
	/**
	 * 获取：缴费标题
	 */
	public String getJiaofeibiaoti() {
		return jiaofeibiaoti;
	}
	/**
	 * 设置：封面
	 */
	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}
	/**
	 * 获取：封面
	 */
	public String getFengmian() {
		return fengmian;
	}
	/**
	 * 设置：党费金额
	 */
	public void setDangfeijine(Float dangfeijine) {
		this.dangfeijine = dangfeijine;
	}
	/**
	 * 获取：党费金额
	 */
	public Float getDangfeijine() {
		return dangfeijine;
	}
	/**
	 * 设置：党员账号
	 */
	public void setDangyuanzhanghao(String dangyuanzhanghao) {
		this.dangyuanzhanghao = dangyuanzhanghao;
	}
	/**
	 * 获取：党员账号
	 */
	public String getDangyuanzhanghao() {
		return dangyuanzhanghao;
	}
	/**
	 * 设置：党员姓名
	 */
	public void setDangyuanxingming(String dangyuanxingming) {
		this.dangyuanxingming = dangyuanxingming;
	}
	/**
	 * 获取：党员姓名
	 */
	public String getDangyuanxingming() {
		return dangyuanxingming;
	}
	/**
	 * 设置：通知时间
	 */
	public void setTongzhishijian(Date tongzhishijian) {
		this.tongzhishijian = tongzhishijian;
	}
	/**
	 * 获取：通知时间
	 */
	public Date getTongzhishijian() {
		return tongzhishijian;
	}
	/**
	 * 设置：是否支付
	 */
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	/**
	 * 获取：是否支付
	 */
	public String getIspay() {
		return ispay;
	}

}
