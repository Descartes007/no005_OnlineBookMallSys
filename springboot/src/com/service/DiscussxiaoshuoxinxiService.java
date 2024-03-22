package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussxiaoshuoxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussxiaoshuoxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussxiaoshuoxinxiView;


/**
 * 图书信息评论表
 *
 * @author 
 * @email 
 * @date 2021-03-25 16:50:55
 */
public interface DiscussxiaoshuoxinxiService extends IService<DiscussxiaoshuoxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussxiaoshuoxinxiVO> selectListVO(Wrapper<DiscussxiaoshuoxinxiEntity> wrapper);
   	
   	DiscussxiaoshuoxinxiVO selectVO(@Param("ew") Wrapper<DiscussxiaoshuoxinxiEntity> wrapper);
   	
   	List<DiscussxiaoshuoxinxiView> selectListView(Wrapper<DiscussxiaoshuoxinxiEntity> wrapper);
   	
   	DiscussxiaoshuoxinxiView selectView(@Param("ew") Wrapper<DiscussxiaoshuoxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussxiaoshuoxinxiEntity> wrapper);
   	
}

