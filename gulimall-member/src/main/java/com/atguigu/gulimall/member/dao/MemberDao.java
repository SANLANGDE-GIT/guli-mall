package com.atguigu.gulimall.member.dao;

import com.atguigu.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author cr
 * @email sunlightcs@gmail.com
 * @date 2021-01-26 19:59:03
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
