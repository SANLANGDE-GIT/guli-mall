package com.atguigu.gulimall.ware.dao;

import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author cr
 * @email sunlightcs@gmail.com
 * @date 2021-01-26 20:18:02
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
