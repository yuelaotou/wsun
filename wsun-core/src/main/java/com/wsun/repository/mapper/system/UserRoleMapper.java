package com.wsun.repository.mapper.system;

import com.wsun.entity.system.UserRole;
import com.wsun.repository.MyBatisRepository;

@MyBatisRepository
public interface UserRoleMapper {

	int deleteByPrimaryKey(Long id);

	/**
	 * 根据用户id删除和角色关联内容
	 * 
	 * @param userId
	 * @return
	 */
	int deleteByUserId(Long userId);

	/**
	 * 根据角色id删除和用户关联内容
	 * 
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(Long roleId);

	int insert(UserRole record);

	int insertSelective(UserRole record);

	UserRole selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserRole record);

	int updateByPrimaryKey(UserRole record);
}