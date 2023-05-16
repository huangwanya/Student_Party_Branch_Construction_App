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


import com.dao.XuexidakaDao;
import com.entity.XuexidakaEntity;
import com.service.XuexidakaService;
import com.entity.vo.XuexidakaVO;
import com.entity.view.XuexidakaView;

@Service("xuexidakaService")
public class XuexidakaServiceImpl extends ServiceImpl<XuexidakaDao, XuexidakaEntity> implements XuexidakaService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XuexidakaEntity> page = this.selectPage(
                new Query<XuexidakaEntity>(params).getPage(),
                new EntityWrapper<XuexidakaEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<XuexidakaEntity> wrapper) {
		  Page<XuexidakaView> page =new Query<XuexidakaView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<XuexidakaVO> selectListVO(Wrapper<XuexidakaEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XuexidakaVO selectVO(Wrapper<XuexidakaEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XuexidakaView> selectListView(Wrapper<XuexidakaEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XuexidakaView selectView(Wrapper<XuexidakaEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<XuexidakaEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<XuexidakaEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<XuexidakaEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }

}
