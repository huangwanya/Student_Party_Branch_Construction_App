package com.dao;

import com.entity.DangshijiaoyuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DangshijiaoyuVO;
import com.entity.view.DangshijiaoyuView;


/**
 * 党史教育
 * 
 * @author 
 * @email 
 * @date 2022-05-03 22:14:17
 */
public interface DangshijiaoyuDao extends BaseMapper<DangshijiaoyuEntity> {
	
	List<DangshijiaoyuVO> selectListVO(@Param("ew") Wrapper<DangshijiaoyuEntity> wrapper);
	
	DangshijiaoyuVO selectVO(@Param("ew") Wrapper<DangshijiaoyuEntity> wrapper);
	
	List<DangshijiaoyuView> selectListView(@Param("ew") Wrapper<DangshijiaoyuEntity> wrapper);

	List<DangshijiaoyuView> selectListView(Pagination page,@Param("ew") Wrapper<DangshijiaoyuEntity> wrapper);
	
	DangshijiaoyuView selectView(@Param("ew") Wrapper<DangshijiaoyuEntity> wrapper);
	

}
