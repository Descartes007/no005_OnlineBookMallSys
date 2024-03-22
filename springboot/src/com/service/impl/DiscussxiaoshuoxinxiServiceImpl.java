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


import com.dao.DiscussxiaoshuoxinxiDao;
import com.entity.DiscussxiaoshuoxinxiEntity;
import com.service.DiscussxiaoshuoxinxiService;
import com.entity.vo.DiscussxiaoshuoxinxiVO;
import com.entity.view.DiscussxiaoshuoxinxiView;

@Service("discussxiaoshuoxinxiService")
public class DiscussxiaoshuoxinxiServiceImpl extends ServiceImpl<DiscussxiaoshuoxinxiDao, DiscussxiaoshuoxinxiEntity> implements DiscussxiaoshuoxinxiService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussxiaoshuoxinxiEntity> page = this.selectPage(
                new Query<DiscussxiaoshuoxinxiEntity>(params).getPage(),
                new EntityWrapper<DiscussxiaoshuoxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussxiaoshuoxinxiEntity> wrapper) {
		  Page<DiscussxiaoshuoxinxiView> page =new Query<DiscussxiaoshuoxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DiscussxiaoshuoxinxiVO> selectListVO(Wrapper<DiscussxiaoshuoxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussxiaoshuoxinxiVO selectVO(Wrapper<DiscussxiaoshuoxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussxiaoshuoxinxiView> selectListView(Wrapper<DiscussxiaoshuoxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussxiaoshuoxinxiView selectView(Wrapper<DiscussxiaoshuoxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
