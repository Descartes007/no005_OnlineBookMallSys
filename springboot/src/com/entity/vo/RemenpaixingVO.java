package com.entity.vo;

import com.entity.RemenpaixingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
 

/**
 * 热门排行
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2021-03-25 16:50:55
 */
public class RemenpaixingVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 图书类别
	 */
	
	private String xiaoshuoleibie;
		
	/**
	 * 图片
	 */
	
	private String tupian;
		
	/**
	 * 作者
	 */
	
	private String zuozhe;
		
	/**
	 * 出版社
	 */
	
	private String chubanshe;
		
	/**
	 * 总章节
	 */
	
	private String zongzhangjie;
		
	/**
	 * 字数
	 */
	
	private String zishu;
		
	/**
	 * 内容
	 */
	
	private String neirong;
		
	/**
	 * 热度
	 */
	
	private Integer redu;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 点击次数
	 */
	
	private Integer clicknum;
		
	/**
	 * 价格
	 */
	
	private Float price;
				
	
	/**
	 * 设置：图书类别
	 */
	 
	public void setXiaoshuoleibie(String xiaoshuoleibie) {
		this.xiaoshuoleibie = xiaoshuoleibie;
	}
	
	/**
	 * 获取：图书类别
	 */
	public String getXiaoshuoleibie() {
		return xiaoshuoleibie;
	}
				
	
	/**
	 * 设置：图片
	 */
	 
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
				
	
	/**
	 * 设置：作者
	 */
	 
	public void setZuozhe(String zuozhe) {
		this.zuozhe = zuozhe;
	}
	
	/**
	 * 获取：作者
	 */
	public String getZuozhe() {
		return zuozhe;
	}
				
	
	/**
	 * 设置：出版社
	 */
	 
	public void setChubanshe(String chubanshe) {
		this.chubanshe = chubanshe;
	}
	
	/**
	 * 获取：出版社
	 */
	public String getChubanshe() {
		return chubanshe;
	}
				
	
	/**
	 * 设置：总章节
	 */
	 
	public void setZongzhangjie(String zongzhangjie) {
		this.zongzhangjie = zongzhangjie;
	}
	
	/**
	 * 获取：总章节
	 */
	public String getZongzhangjie() {
		return zongzhangjie;
	}
				
	
	/**
	 * 设置：字数
	 */
	 
	public void setZishu(String zishu) {
		this.zishu = zishu;
	}
	
	/**
	 * 获取：字数
	 */
	public String getZishu() {
		return zishu;
	}
				
	
	/**
	 * 设置：内容
	 */
	 
	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}
	
	/**
	 * 获取：内容
	 */
	public String getNeirong() {
		return neirong;
	}
				
	
	/**
	 * 设置：热度
	 */
	 
	public void setRedu(Integer redu) {
		this.redu = redu;
	}
	
	/**
	 * 获取：热度
	 */
	public Integer getRedu() {
		return redu;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
				
	
	/**
	 * 设置：价格
	 */
	 
	public void setPrice(Float price) {
		this.price = price;
	}
	
	/**
	 * 获取：价格
	 */
	public Float getPrice() {
		return price;
	}
			
}
