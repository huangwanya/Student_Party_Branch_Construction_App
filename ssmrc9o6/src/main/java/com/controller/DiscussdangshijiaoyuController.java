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
import java.io.IOException;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DiscussdangshijiaoyuEntity;
import com.entity.view.DiscussdangshijiaoyuView;

import com.service.DiscussdangshijiaoyuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;

/**
 * 党史教育评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2022-05-03 22:14:18
 */
@RestController
@RequestMapping("/discussdangshijiaoyu")
public class DiscussdangshijiaoyuController {
    @Autowired
    private DiscussdangshijiaoyuService discussdangshijiaoyuService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussdangshijiaoyuEntity discussdangshijiaoyu, 
		HttpServletRequest request){

        EntityWrapper<DiscussdangshijiaoyuEntity> ew = new EntityWrapper<DiscussdangshijiaoyuEntity>();
		PageUtils page = discussdangshijiaoyuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussdangshijiaoyu), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussdangshijiaoyuEntity discussdangshijiaoyu, 
		HttpServletRequest request){
        EntityWrapper<DiscussdangshijiaoyuEntity> ew = new EntityWrapper<DiscussdangshijiaoyuEntity>();
		PageUtils page = discussdangshijiaoyuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussdangshijiaoyu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussdangshijiaoyuEntity discussdangshijiaoyu){
       	EntityWrapper<DiscussdangshijiaoyuEntity> ew = new EntityWrapper<DiscussdangshijiaoyuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussdangshijiaoyu, "discussdangshijiaoyu")); 
        return R.ok().put("data", discussdangshijiaoyuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussdangshijiaoyuEntity discussdangshijiaoyu){
        EntityWrapper< DiscussdangshijiaoyuEntity> ew = new EntityWrapper< DiscussdangshijiaoyuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussdangshijiaoyu, "discussdangshijiaoyu")); 
		DiscussdangshijiaoyuView discussdangshijiaoyuView =  discussdangshijiaoyuService.selectView(ew);
		return R.ok("查询党史教育评论表成功").put("data", discussdangshijiaoyuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussdangshijiaoyuEntity discussdangshijiaoyu = discussdangshijiaoyuService.selectById(id);
        return R.ok().put("data", discussdangshijiaoyu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussdangshijiaoyuEntity discussdangshijiaoyu = discussdangshijiaoyuService.selectById(id);
        return R.ok().put("data", discussdangshijiaoyu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussdangshijiaoyuEntity discussdangshijiaoyu, HttpServletRequest request){
    	discussdangshijiaoyu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discussdangshijiaoyu);

        discussdangshijiaoyuService.insert(discussdangshijiaoyu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussdangshijiaoyuEntity discussdangshijiaoyu, HttpServletRequest request){
    	discussdangshijiaoyu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discussdangshijiaoyu);

        discussdangshijiaoyuService.insert(discussdangshijiaoyu);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DiscussdangshijiaoyuEntity discussdangshijiaoyu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussdangshijiaoyu);
        discussdangshijiaoyuService.updateById(discussdangshijiaoyu);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussdangshijiaoyuService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<DiscussdangshijiaoyuEntity> wrapper = new EntityWrapper<DiscussdangshijiaoyuEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = discussdangshijiaoyuService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}
