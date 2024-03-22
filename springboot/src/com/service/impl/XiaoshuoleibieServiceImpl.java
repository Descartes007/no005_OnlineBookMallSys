package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.XiaoshuoleibieDao;
import com.entity.XiaoshuoleibieEntity;
import com.service.XiaoshuoleibieService;
import com.entity.vo.XiaoshuoleibieVO;
import com.entity.view.XiaoshuoleibieView;

@Service("xiaoshuoleibieService")
public class XiaoshuoleibieServiceImpl extends ServiceImpl<XiaoshuoleibieDao, XiaoshuoleibieEntity> implements XiaoshuoleibieService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XiaoshuoleibieEntity> page = this.selectPage(
                new Query<XiaoshuoleibieEntity>(params).getPage(),
                new EntityWrapper<XiaoshuoleibieEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<XiaoshuoleibieEntity> wrapper) {
		  Page<XiaoshuoleibieView> page =new Query<XiaoshuoleibieView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<XiaoshuoleibieVO> selectListVO(Wrapper<XiaoshuoleibieEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XiaoshuoleibieVO selectVO(Wrapper<XiaoshuoleibieEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XiaoshuoleibieView> selectListView(Wrapper<XiaoshuoleibieEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XiaoshuoleibieView selectView(Wrapper<XiaoshuoleibieEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
