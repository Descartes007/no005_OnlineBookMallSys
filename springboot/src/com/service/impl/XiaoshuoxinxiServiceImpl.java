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


import com.dao.XiaoshuoxinxiDao;
import com.entity.XiaoshuoxinxiEntity;
import com.service.XiaoshuoxinxiService;
import com.entity.vo.XiaoshuoxinxiVO;
import com.entity.view.XiaoshuoxinxiView;

@Service("xiaoshuoxinxiService")
public class XiaoshuoxinxiServiceImpl extends ServiceImpl<XiaoshuoxinxiDao, XiaoshuoxinxiEntity> implements XiaoshuoxinxiService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XiaoshuoxinxiEntity> page = this.selectPage(
                new Query<XiaoshuoxinxiEntity>(params).getPage(),
                new EntityWrapper<XiaoshuoxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<XiaoshuoxinxiEntity> wrapper) {
		  Page<XiaoshuoxinxiView> page =new Query<XiaoshuoxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<XiaoshuoxinxiVO> selectListVO(Wrapper<XiaoshuoxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XiaoshuoxinxiVO selectVO(Wrapper<XiaoshuoxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XiaoshuoxinxiView> selectListView(Wrapper<XiaoshuoxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XiaoshuoxinxiView selectView(Wrapper<XiaoshuoxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
