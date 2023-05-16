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


import com.dao.DangfeijiaojiaoDao;
import com.entity.DangfeijiaojiaoEntity;
import com.service.DangfeijiaojiaoService;
import com.entity.vo.DangfeijiaojiaoVO;
import com.entity.view.DangfeijiaojiaoView;

@Service("dangfeijiaojiaoService")
public class DangfeijiaojiaoServiceImpl extends ServiceImpl<DangfeijiaojiaoDao, DangfeijiaojiaoEntity> implements DangfeijiaojiaoService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DangfeijiaojiaoEntity> page = this.selectPage(
                new Query<DangfeijiaojiaoEntity>(params).getPage(),
                new EntityWrapper<DangfeijiaojiaoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DangfeijiaojiaoEntity> wrapper) {
		  Page<DangfeijiaojiaoView> page =new Query<DangfeijiaojiaoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DangfeijiaojiaoVO> selectListVO(Wrapper<DangfeijiaojiaoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DangfeijiaojiaoVO selectVO(Wrapper<DangfeijiaojiaoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DangfeijiaojiaoView> selectListView(Wrapper<DangfeijiaojiaoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DangfeijiaojiaoView selectView(Wrapper<DangfeijiaojiaoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
