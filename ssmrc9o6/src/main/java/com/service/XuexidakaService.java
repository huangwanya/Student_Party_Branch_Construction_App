package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.XuexidakaEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.XuexidakaVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.XuexidakaView;


/**
 * 学习打卡
 *
 * @author 
 * @email 
 * @date 2022-05-03 22:14:17
 */
public interface XuexidakaService extends IService<XuexidakaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XuexidakaVO> selectListVO(Wrapper<XuexidakaEntity> wrapper);
   	
   	XuexidakaVO selectVO(@Param("ew") Wrapper<XuexidakaEntity> wrapper);
   	
   	List<XuexidakaView> selectListView(Wrapper<XuexidakaEntity> wrapper);
   	
   	XuexidakaView selectView(@Param("ew") Wrapper<XuexidakaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XuexidakaEntity> wrapper);
   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<XuexidakaEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<XuexidakaEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<XuexidakaEntity> wrapper);
}

