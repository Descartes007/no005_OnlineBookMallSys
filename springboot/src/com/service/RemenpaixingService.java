package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.RemenpaixingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.RemenpaixingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.RemenpaixingView;


/**
 * 热门排行
 *
 * @author 
 * @email 
 * @date 2021-03-25 16:50:55
 */
public interface RemenpaixingService extends IService<RemenpaixingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<RemenpaixingVO> selectListVO(Wrapper<RemenpaixingEntity> wrapper);
   	
   	RemenpaixingVO selectVO(@Param("ew") Wrapper<RemenpaixingEntity> wrapper);
   	
   	List<RemenpaixingView> selectListView(Wrapper<RemenpaixingEntity> wrapper);
   	
   	RemenpaixingView selectView(@Param("ew") Wrapper<RemenpaixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<RemenpaixingEntity> wrapper);
   	
}

