package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussremenpaixingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussremenpaixingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussremenpaixingView;


/**
 * 热门排行评论表
 *
 * @author 
 * @email 
 * @date 2021-03-25 16:50:55
 */
public interface DiscussremenpaixingService extends IService<DiscussremenpaixingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussremenpaixingVO> selectListVO(Wrapper<DiscussremenpaixingEntity> wrapper);
   	
   	DiscussremenpaixingVO selectVO(@Param("ew") Wrapper<DiscussremenpaixingEntity> wrapper);
   	
   	List<DiscussremenpaixingView> selectListView(Wrapper<DiscussremenpaixingEntity> wrapper);
   	
   	DiscussremenpaixingView selectView(@Param("ew") Wrapper<DiscussremenpaixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussremenpaixingEntity> wrapper);
   	
}

