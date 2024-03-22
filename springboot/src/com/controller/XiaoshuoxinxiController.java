package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.XiaoshuoxinxiEntity;
import com.entity.view.XiaoshuoxinxiView;

import com.service.XiaoshuoxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;


/**
 * 图书信息
 * 后端接口
 * @author 
 * @email 
 * @date 2021-03-25 16:50:55
 */
@RestController
@RequestMapping("/xiaoshuoxinxi")
public class XiaoshuoxinxiController {
    @Autowired
    private XiaoshuoxinxiService xiaoshuoxinxiService;
    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XiaoshuoxinxiEntity xiaoshuoxinxi, 
		HttpServletRequest request){

        EntityWrapper<XiaoshuoxinxiEntity> ew = new EntityWrapper<XiaoshuoxinxiEntity>();
		PageUtils page = xiaoshuoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiaoshuoxinxi), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XiaoshuoxinxiEntity xiaoshuoxinxi, HttpServletRequest request){
        EntityWrapper<XiaoshuoxinxiEntity> ew = new EntityWrapper<XiaoshuoxinxiEntity>();
		PageUtils page = xiaoshuoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiaoshuoxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XiaoshuoxinxiEntity xiaoshuoxinxi){
       	EntityWrapper<XiaoshuoxinxiEntity> ew = new EntityWrapper<XiaoshuoxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xiaoshuoxinxi, "xiaoshuoxinxi")); 
        return R.ok().put("data", xiaoshuoxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XiaoshuoxinxiEntity xiaoshuoxinxi){
        EntityWrapper< XiaoshuoxinxiEntity> ew = new EntityWrapper< XiaoshuoxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xiaoshuoxinxi, "xiaoshuoxinxi")); 
		XiaoshuoxinxiView xiaoshuoxinxiView =  xiaoshuoxinxiService.selectView(ew);
		return R.ok("查询图书信息成功").put("data", xiaoshuoxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XiaoshuoxinxiEntity xiaoshuoxinxi = xiaoshuoxinxiService.selectById(id);
		xiaoshuoxinxi.setClicknum(xiaoshuoxinxi.getClicknum()+1);
		xiaoshuoxinxi.setClicktime(new Date());
		xiaoshuoxinxiService.updateById(xiaoshuoxinxi);
        return R.ok().put("data", xiaoshuoxinxi);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XiaoshuoxinxiEntity xiaoshuoxinxi = xiaoshuoxinxiService.selectById(id);
		xiaoshuoxinxi.setClicknum(xiaoshuoxinxi.getClicknum()+1);
		xiaoshuoxinxi.setClicktime(new Date());
		xiaoshuoxinxiService.updateById(xiaoshuoxinxi);
        return R.ok().put("data", xiaoshuoxinxi);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R thumbsup(@PathVariable("id") String id,String type){
        XiaoshuoxinxiEntity xiaoshuoxinxi = xiaoshuoxinxiService.selectById(id);
        if(type.equals("1")) {
        	xiaoshuoxinxi.setThumbsupnum(xiaoshuoxinxi.getThumbsupnum()+1);
        } else {
        	xiaoshuoxinxi.setCrazilynum(xiaoshuoxinxi.getCrazilynum()+1);
        }
        xiaoshuoxinxiService.updateById(xiaoshuoxinxi);
        return R.ok();
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XiaoshuoxinxiEntity xiaoshuoxinxi, HttpServletRequest request){
    	xiaoshuoxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(xiaoshuoxinxi);

        xiaoshuoxinxiService.insert(xiaoshuoxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
	@IgnoreAuth
    @RequestMapping("/add")
    public R add(@RequestBody XiaoshuoxinxiEntity xiaoshuoxinxi, HttpServletRequest request){
    	xiaoshuoxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(xiaoshuoxinxi);

        xiaoshuoxinxiService.insert(xiaoshuoxinxi);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody XiaoshuoxinxiEntity xiaoshuoxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiaoshuoxinxi);
        xiaoshuoxinxiService.updateById(xiaoshuoxinxi);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xiaoshuoxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<XiaoshuoxinxiEntity> wrapper = new EntityWrapper<XiaoshuoxinxiEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = xiaoshuoxinxiService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,XiaoshuoxinxiEntity xiaoshuoxinxi, HttpServletRequest request,String pre){
        EntityWrapper<XiaoshuoxinxiEntity> ew = new EntityWrapper<XiaoshuoxinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicknum");
        
        params.put("order", "desc");
		PageUtils page = xiaoshuoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiaoshuoxinxi), params), params));
        return R.ok().put("data", page);
    }


}
