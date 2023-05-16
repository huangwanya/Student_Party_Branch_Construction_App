package com.dao;

import com.entity.DiscussdangshijiaoyuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussdangshijiaoyuVO;
import com.entity.view.DiscussdangshijiaoyuView;


/**
 * 党史教育评论表
 * 
 * @author 
 * @email 
 * @date 2022-05-03 22:14:18
 */
public interface DiscussdangshijiaoyuDao extends BaseMapper<DiscussdangshijiaoyuEntity> {
	
	List<DiscussdangshijiaoyuVO> selectListVO(@Param("ew") Wrapper<DiscussdangshijiaoyuEntity> wrapper);
	
	DiscussdangshijiaoyuVO selectVO(@Param("ew") Wrapper<DiscussdangshijiaoyuEntity> wrapper);
	
	List<DiscussdangshijiaoyuView> selectListView(@Param("ew") Wrapper<DiscussdangshijiaoyuEntity> wrapper);

	List<DiscussdangshijiaoyuView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussdangshijiaoyuEntity> wrapper);
	
	DiscussdangshijiaoyuView selectView(@Param("ew") Wrapper<DiscussdangshijiaoyuEntity> wrapper);
	

}
