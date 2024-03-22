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


import com.dao.RemenpaixingDao;
import com.entity.RemenpaixingEntity;
import com.service.RemenpaixingService;
import com.entity.vo.RemenpaixingVO;
import com.entity.view.RemenpaixingView;

@Service("remenpaixingService")
public class RemenpaixingServiceImpl extends ServiceImpl<RemenpaixingDao, RemenpaixingEntity> implements RemenpaixingService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RemenpaixingEntity> page = this.selectPage(
                new Query<RemenpaixingEntity>(params).getPage(),
                new EntityWrapper<RemenpaixingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<RemenpaixingEntity> wrapper) {
		  Page<RemenpaixingView> page =new Query<RemenpaixingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<RemenpaixingVO> selectListVO(Wrapper<RemenpaixingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public RemenpaixingVO selectVO(Wrapper<RemenpaixingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<RemenpaixingView> selectListView(Wrapper<RemenpaixingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public RemenpaixingView selectView(Wrapper<RemenpaixingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
