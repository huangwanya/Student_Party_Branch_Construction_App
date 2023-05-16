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


import com.dao.DiscussdangshijiaoyuDao;
import com.entity.DiscussdangshijiaoyuEntity;
import com.service.DiscussdangshijiaoyuService;
import com.entity.vo.DiscussdangshijiaoyuVO;
import com.entity.view.DiscussdangshijiaoyuView;

@Service("discussdangshijiaoyuService")
public class DiscussdangshijiaoyuServiceImpl extends ServiceImpl<DiscussdangshijiaoyuDao, DiscussdangshijiaoyuEntity> implements DiscussdangshijiaoyuService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussdangshijiaoyuEntity> page = this.selectPage(
                new Query<DiscussdangshijiaoyuEntity>(params).getPage(),
                new EntityWrapper<DiscussdangshijiaoyuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussdangshijiaoyuEntity> wrapper) {
		  Page<DiscussdangshijiaoyuView> page =new Query<DiscussdangshijiaoyuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DiscussdangshijiaoyuVO> selectListVO(Wrapper<DiscussdangshijiaoyuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussdangshijiaoyuVO selectVO(Wrapper<DiscussdangshijiaoyuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussdangshijiaoyuView> selectListView(Wrapper<DiscussdangshijiaoyuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussdangshijiaoyuView selectView(Wrapper<DiscussdangshijiaoyuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
