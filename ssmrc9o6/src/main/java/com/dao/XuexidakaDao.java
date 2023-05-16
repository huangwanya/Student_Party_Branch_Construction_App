package com.dao;

import com.entity.XuexidakaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.XuexidakaVO;
import com.entity.view.XuexidakaView;


/**
 * 学习打卡
 * 
 * @author 
 * @email 
 * @date 2022-05-03 22:14:17
 */
public interface XuexidakaDao extends BaseMapper<XuexidakaEntity> {
	
	List<XuexidakaVO> selectListVO(@Param("ew") Wrapper<XuexidakaEntity> wrapper);
	
	XuexidakaVO selectVO(@Param("ew") Wrapper<XuexidakaEntity> wrapper);
	
	List<XuexidakaView> selectListView(@Param("ew") Wrapper<XuexidakaEntity> wrapper);

	List<XuexidakaView> selectListView(Pagination page,@Param("ew") Wrapper<XuexidakaEntity> wrapper);
	
	XuexidakaView selectView(@Param("ew") Wrapper<XuexidakaEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params")Map<String, Object> params,@Param("ew") Wrapper<XuexidakaEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<XuexidakaEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<XuexidakaEntity> wrapper);
}
