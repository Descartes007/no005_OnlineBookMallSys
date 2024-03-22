package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.XiaoshuoleibieEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.XiaoshuoleibieVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.XiaoshuoleibieView;


/**
 * 图书类别
 *
 * @author 
 * @email 
 * @date 2021-03-25 16:50:55
 */
public interface XiaoshuoleibieService extends IService<XiaoshuoleibieEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XiaoshuoleibieVO> selectListVO(Wrapper<XiaoshuoleibieEntity> wrapper);
   	
   	XiaoshuoleibieVO selectVO(@Param("ew") Wrapper<XiaoshuoleibieEntity> wrapper);
   	
   	List<XiaoshuoleibieView> selectListView(Wrapper<XiaoshuoleibieEntity> wrapper);
   	
   	XiaoshuoleibieView selectView(@Param("ew") Wrapper<XiaoshuoleibieEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XiaoshuoleibieEntity> wrapper);
   	
}

