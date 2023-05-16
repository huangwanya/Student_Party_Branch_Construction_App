package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DangfeijiaojiaoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DangfeijiaojiaoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DangfeijiaojiaoView;


/**
 * 党费缴交
 *
 * @author 
 * @email 
 * @date 2022-05-03 22:14:17
 */
public interface DangfeijiaojiaoService extends IService<DangfeijiaojiaoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DangfeijiaojiaoVO> selectListVO(Wrapper<DangfeijiaojiaoEntity> wrapper);
   	
   	DangfeijiaojiaoVO selectVO(@Param("ew") Wrapper<DangfeijiaojiaoEntity> wrapper);
   	
   	List<DangfeijiaojiaoView> selectListView(Wrapper<DangfeijiaojiaoEntity> wrapper);
   	
   	DangfeijiaojiaoView selectView(@Param("ew") Wrapper<DangfeijiaojiaoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DangfeijiaojiaoEntity> wrapper);
   	

}

