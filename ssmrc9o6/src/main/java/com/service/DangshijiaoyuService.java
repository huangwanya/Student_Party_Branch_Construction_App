package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DangshijiaoyuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DangshijiaoyuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DangshijiaoyuView;


/**
 * 党史教育
 *
 * @author 
 * @email 
 * @date 2022-05-03 22:14:17
 */
public interface DangshijiaoyuService extends IService<DangshijiaoyuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DangshijiaoyuVO> selectListVO(Wrapper<DangshijiaoyuEntity> wrapper);
   	
   	DangshijiaoyuVO selectVO(@Param("ew") Wrapper<DangshijiaoyuEntity> wrapper);
   	
   	List<DangshijiaoyuView> selectListView(Wrapper<DangshijiaoyuEntity> wrapper);
   	
   	DangshijiaoyuView selectView(@Param("ew") Wrapper<DangshijiaoyuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DangshijiaoyuEntity> wrapper);
   	

}

