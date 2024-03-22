package com.dao;

import com.entity.XiaoshuoleibieEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.XiaoshuoleibieVO;
import com.entity.view.XiaoshuoleibieView;


/**
 * 图书类别
 * 
 * @author 
 * @email 
 * @date 2021-03-25 16:50:55
 */
public interface XiaoshuoleibieDao extends BaseMapper<XiaoshuoleibieEntity> {
	
	List<XiaoshuoleibieVO> selectListVO(@Param("ew") Wrapper<XiaoshuoleibieEntity> wrapper);
	
	XiaoshuoleibieVO selectVO(@Param("ew") Wrapper<XiaoshuoleibieEntity> wrapper);
	
	List<XiaoshuoleibieView> selectListView(@Param("ew") Wrapper<XiaoshuoleibieEntity> wrapper);

	List<XiaoshuoleibieView> selectListView(Pagination page,@Param("ew") Wrapper<XiaoshuoleibieEntity> wrapper);
	
	XiaoshuoleibieView selectView(@Param("ew") Wrapper<XiaoshuoleibieEntity> wrapper);
	
}
