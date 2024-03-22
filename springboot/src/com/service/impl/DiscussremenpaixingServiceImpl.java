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


import com.dao.DiscussremenpaixingDao;
import com.entity.DiscussremenpaixingEntity;
import com.service.DiscussremenpaixingService;
import com.entity.vo.DiscussremenpaixingVO;
import com.entity.view.DiscussremenpaixingView;

@Service("discussremenpaixingService")
public class DiscussremenpaixingServiceImpl extends ServiceImpl<DiscussremenpaixingDao, DiscussremenpaixingEntity> implements DiscussremenpaixingService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussremenpaixingEntity> page = this.selectPage(
                new Query<DiscussremenpaixingEntity>(params).getPage(),
                new EntityWrapper<DiscussremenpaixingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussremenpaixingEntity> wrapper) {
		  Page<DiscussremenpaixingView> page =new Query<DiscussremenpaixingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DiscussremenpaixingVO> selectListVO(Wrapper<DiscussremenpaixingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussremenpaixingVO selectVO(Wrapper<DiscussremenpaixingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussremenpaixingView> selectListView(Wrapper<DiscussremenpaixingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussremenpaixingView selectView(Wrapper<DiscussremenpaixingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
