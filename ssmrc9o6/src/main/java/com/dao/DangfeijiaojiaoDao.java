package com.dao;

import com.entity.DangfeijiaojiaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DangfeijiaojiaoVO;
import com.entity.view.DangfeijiaojiaoView;


/**
 * 党费缴交
 * 
 * @author 
 * @email 
 * @date 2022-05-03 22:14:17
 */
public interface DangfeijiaojiaoDao extends BaseMapper<DangfeijiaojiaoEntity> {
	
	List<DangfeijiaojiaoVO> selectListVO(@Param("ew") Wrapper<DangfeijiaojiaoEntity> wrapper);
	
	DangfeijiaojiaoVO selectVO(@Param("ew") Wrapper<DangfeijiaojiaoEntity> wrapper);
	
	List<DangfeijiaojiaoView> selectListView(@Param("ew") Wrapper<DangfeijiaojiaoEntity> wrapper);

	List<DangfeijiaojiaoView> selectListView(Pagination page,@Param("ew") Wrapper<DangfeijiaojiaoEntity> wrapper);
	
	DangfeijiaojiaoView selectView(@Param("ew") Wrapper<DangfeijiaojiaoEntity> wrapper);
	

}
