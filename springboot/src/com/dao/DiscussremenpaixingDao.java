package com.dao;

import com.entity.DiscussremenpaixingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussremenpaixingVO;
import com.entity.view.DiscussremenpaixingView;


/**
 * 热门排行评论表
 * 
 * @author 
 * @email 
 * @date 2021-03-25 16:50:55
 */
public interface DiscussremenpaixingDao extends BaseMapper<DiscussremenpaixingEntity> {
	
	List<DiscussremenpaixingVO> selectListVO(@Param("ew") Wrapper<DiscussremenpaixingEntity> wrapper);
	
	DiscussremenpaixingVO selectVO(@Param("ew") Wrapper<DiscussremenpaixingEntity> wrapper);
	
	List<DiscussremenpaixingView> selectListView(@Param("ew") Wrapper<DiscussremenpaixingEntity> wrapper);

	List<DiscussremenpaixingView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussremenpaixingEntity> wrapper);
	
	DiscussremenpaixingView selectView(@Param("ew") Wrapper<DiscussremenpaixingEntity> wrapper);
	
}
