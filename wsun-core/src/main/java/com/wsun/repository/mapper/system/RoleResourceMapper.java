package com.wsun.repository.mapper.system;

import com.wsun.entity.system.RoleResource;
import com.wsun.repository.MyBatisRepository;

@MyBatisRepository
public interface RoleResourceMapper {

	int deleteByPrimaryKey(Long id);

	int insert(RoleResource record);

	int insertSelective(RoleResource record);

	RoleResource selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(RoleResource record);

	int updateByPrimaryKey(RoleResource record);

	int deleteByRoleId(Long roleId);

	int deleteByResourceId(Long resourceId);
}