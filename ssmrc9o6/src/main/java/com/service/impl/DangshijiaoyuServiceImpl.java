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


import com.dao.DangshijiaoyuDao;
import com.entity.DangshijiaoyuEntity;
import com.service.DangshijiaoyuService;
import com.entity.vo.DangshijiaoyuVO;
import com.entity.view.DangshijiaoyuView;

@Service("dangshijiaoyuService")
public class DangshijiaoyuServiceImpl extends ServiceImpl<DangshijiaoyuDao, DangshijiaoyuEntity> implements DangshijiaoyuService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DangshijiaoyuEntity> page = this.selectPage(
                new Query<DangshijiaoyuEntity>(params).getPage(),
                new EntityWrapper<DangshijiaoyuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DangshijiaoyuEntity> wrapper) {
		  Page<DangshijiaoyuView> page =new Query<DangshijiaoyuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DangshijiaoyuVO> selectListVO(Wrapper<DangshijiaoyuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DangshijiaoyuVO selectVO(Wrapper<DangshijiaoyuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DangshijiaoyuView> selectListView(Wrapper<DangshijiaoyuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DangshijiaoyuView selectView(Wrapper<DangshijiaoyuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
