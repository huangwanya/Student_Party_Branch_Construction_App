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

import com.entity.DangfeijiaojiaoEntity;
import com.entity.view.DangfeijiaojiaoView;

import com.service.DangfeijiaojiaoService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;

/**
 * 党费缴交
 * 后端接口
 * @author 
 * @email 
 * @date 2022-05-03 22:14:17
 */
@RestController
@RequestMapping("/dangfeijiaojiao")
public class DangfeijiaojiaoController {
    @Autowired
    private DangfeijiaojiaoService dangfeijiaojiaoService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DangfeijiaojiaoEntity dangfeijiaojiao, 
		HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("dangyuan")) {
			dangfeijiaojiao.setDangyuanzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<DangfeijiaojiaoEntity> ew = new EntityWrapper<DangfeijiaojiaoEntity>();
		PageUtils page = dangfeijiaojiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dangfeijiaojiao), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DangfeijiaojiaoEntity dangfeijiaojiao, 
		HttpServletRequest request){
        EntityWrapper<DangfeijiaojiaoEntity> ew = new EntityWrapper<DangfeijiaojiaoEntity>();
		PageUtils page = dangfeijiaojiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dangfeijiaojiao), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DangfeijiaojiaoEntity dangfeijiaojiao){
       	EntityWrapper<DangfeijiaojiaoEntity> ew = new EntityWrapper<DangfeijiaojiaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( dangfeijiaojiao, "dangfeijiaojiao")); 
        return R.ok().put("data", dangfeijiaojiaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DangfeijiaojiaoEntity dangfeijiaojiao){
        EntityWrapper< DangfeijiaojiaoEntity> ew = new EntityWrapper< DangfeijiaojiaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( dangfeijiaojiao, "dangfeijiaojiao")); 
		DangfeijiaojiaoView dangfeijiaojiaoView =  dangfeijiaojiaoService.selectView(ew);
		return R.ok("查询党费缴交成功").put("data", dangfeijiaojiaoView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DangfeijiaojiaoEntity dangfeijiaojiao = dangfeijiaojiaoService.selectById(id);
        return R.ok().put("data", dangfeijiaojiao);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DangfeijiaojiaoEntity dangfeijiaojiao = dangfeijiaojiaoService.selectById(id);
        return R.ok().put("data", dangfeijiaojiao);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DangfeijiaojiaoEntity dangfeijiaojiao, HttpServletRequest request){
    	dangfeijiaojiao.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(dangfeijiaojiao);

        dangfeijiaojiaoService.insert(dangfeijiaojiao);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DangfeijiaojiaoEntity dangfeijiaojiao, HttpServletRequest request){
    	dangfeijiaojiao.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(dangfeijiaojiao);

        dangfeijiaojiaoService.insert(dangfeijiaojiao);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DangfeijiaojiaoEntity dangfeijiaojiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dangfeijiaojiao);
        dangfeijiaojiaoService.updateById(dangfeijiaojiao);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        dangfeijiaojiaoService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<DangfeijiaojiaoEntity> wrapper = new EntityWrapper<DangfeijiaojiaoEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("dangyuan")) {
			wrapper.eq("dangyuanzhanghao", (String)request.getSession().getAttribute("username"));
		}

		int count = dangfeijiaojiaoService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}
