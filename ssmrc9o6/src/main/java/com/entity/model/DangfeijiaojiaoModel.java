package com.entity.model;

import com.entity.DangfeijiaojiaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
 

/**
 * 党费缴交
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2022-05-03 22:14:17
 */
public class DangfeijiaojiaoModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date tongzhishijian;
		
	/**
	 * 是否支付
	 */
	
	private String ispay;
				
	
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
