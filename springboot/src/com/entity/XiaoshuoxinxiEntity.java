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
 * 图书信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2021-03-25 16:50:55
 */
@TableName("xiaoshuoxinxi")
public class XiaoshuoxinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public XiaoshuoxinxiEntity() {
		
	}
	
	public XiaoshuoxinxiEntity(T t) {
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
	 * 图书编号
	 */
					
	private String xiaoshuobianhao;
	
	/**
	 * 图书名称
	 */
					
	private String xiaoshuomingcheng;
	
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
	 * 赞
	 */
					
	private Integer thumbsupnum;
	
	/**
	 * 踩
	 */
					
	private Integer crazilynum;
	
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
	 * 设置：图书编号
	 */
	public void setXiaoshuobianhao(String xiaoshuobianhao) {
		this.xiaoshuobianhao = xiaoshuobianhao;
	}
	/**
	 * 获取：图书编号
	 */
	public String getXiaoshuobianhao() {
		return xiaoshuobianhao;
	}
	/**
	 * 设置：图书名称
	 */
	public void setXiaoshuomingcheng(String xiaoshuomingcheng) {
		this.xiaoshuomingcheng = xiaoshuomingcheng;
	}
	/**
	 * 获取：图书名称
	 */
	public String getXiaoshuomingcheng() {
		return xiaoshuomingcheng;
	}
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
	 * 设置：赞
	 */
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
	}
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
	/**
	 * 设置：踩
	 */
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
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
