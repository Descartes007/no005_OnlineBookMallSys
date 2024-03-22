package com.dao;

import com.entity.RemenpaixingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.RemenpaixingVO;
import com.entity.view.RemenpaixingView;


/**
 * 热门排行
 * 
 * @author 
 * @email 
 * @date 2021-03-25 16:50:55
 */
public interface RemenpaixingDao extends BaseMapper<RemenpaixingEntity> {
	
	List<RemenpaixingVO> selectListVO(@Param("ew") Wrapper<RemenpaixingEntity> wrapper);
	
	RemenpaixingVO selectVO(@Param("ew") Wrapper<RemenpaixingEntity> wrapper);
	
	List<RemenpaixingView> selectListView(@Param("ew") Wrapper<RemenpaixingEntity> wrapper);

	List<RemenpaixingView> selectListView(Pagination page,@Param("ew") Wrapper<RemenpaixingEntity> wrapper);
	
	RemenpaixingView selectView(@Param("ew") Wrapper<RemenpaixingEntity> wrapper);
	
}
