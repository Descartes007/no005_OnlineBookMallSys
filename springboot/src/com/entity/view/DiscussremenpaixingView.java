package com.entity.view;

import com.entity.DiscussremenpaixingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 热门排行评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2021-03-25 16:50:55
 */
@TableName("discussremenpaixing")
public class DiscussremenpaixingView  extends DiscussremenpaixingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussremenpaixingView(){
	}
 
 	public DiscussremenpaixingView(DiscussremenpaixingEntity discussremenpaixingEntity){
 	try {
			BeanUtils.copyProperties(this, discussremenpaixingEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
