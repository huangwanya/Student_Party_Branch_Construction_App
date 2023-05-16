package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussdangshijiaoyuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussdangshijiaoyuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussdangshijiaoyuView;


/**
 * 党史教育评论表
 *
 * @author 
 * @email 
 * @date 2022-05-03 22:14:18
 */
public interface DiscussdangshijiaoyuService extends IService<DiscussdangshijiaoyuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussdangshijiaoyuVO> selectListVO(Wrapper<DiscussdangshijiaoyuEntity> wrapper);
   	
   	DiscussdangshijiaoyuVO selectVO(@Param("ew") Wrapper<DiscussdangshijiaoyuEntity> wrapper);
   	
   	List<DiscussdangshijiaoyuView> selectListView(Wrapper<DiscussdangshijiaoyuEntity> wrapper);
   	
   	DiscussdangshijiaoyuView selectView(@Param("ew") Wrapper<DiscussdangshijiaoyuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussdangshijiaoyuEntity> wrapper);
   	

}

