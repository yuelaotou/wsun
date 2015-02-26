package com.wsun.repository.mapper.system;

import java.util.List;
import java.util.Map;

import com.wsun.entity.system.Role;
import com.wsun.repository.MyBatisRepository;

@MyBatisRepository
public interface RoleMapper {

	/**
	 * 通过主键ID删除Role
	 * 
	 * @param id
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * 新增Role
	 * 
	 * @param role
	 */
	int insert(Role role);

	/**
	 * 选择性新增Role
	 * 
	 * @param role
	 */
	int insertSelective(Role role);

	/**
	 * 通过主键ID查询Role
	 * 
	 * @param id
	 * @return
	 */
	Role selectByPrimaryKey(Long id);

	/**
	 * 通过角色编码查询Role
	 * 
	 * @param code
	 * @return
	 */
	int selectByCode(String code);

	/**
	 * 选择性修改Role
	 * 
	 * @param role
	 */
	int updateByPrimaryKeySelective(Role role);

	/**
	 * 修改Role
	 * 
	 * @param role
	 */
	int updateByPrimaryKey(Role role);

	/**
	 * 查询所有带资源信息的角色信息
	 * 
	 * @return
	 */
	List<Role> getRoleList(Map<String, Object> parameterMap);

	/**
	 * 查询所有带资源信息的角色信息
	 * 
	 * @return
	 */
	int getRoleListCount(Role role);

	/**
	 * 根据主键id查询带资源的角色信息
	 * 
	 * @param id
	 * @return
	 */
	Role getRoleDetailById(Long id);

	/**
	 * 查询所有角色
	 * 
	 * @return
	 */
	List<Role> getAll();
}