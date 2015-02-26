package com.wsun.repository.mapper.system;

import java.util.List;
import java.util.Map;

import com.wsun.entity.system.User;
import com.wsun.repository.MyBatisRepository;

@MyBatisRepository
public interface UserMapper {

	/**
	 * 通过主键ID删除User
	 * 
	 * @param id
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * 新增User
	 * 
	 * @param user
	 */
	int insert(User record);

	/**
	 * 选择性新增User
	 * 
	 * @param user
	 */
	int insertSelective(User record);

	/**
	 * 通过主键ID查询User
	 * 
	 * @param id
	 * @return
	 */
	User selectByPrimaryKey(Long id);

	/**
	 * 通过登录用户名查询User
	 * 
	 * @param loginName
	 * @return
	 */
	User selectByLoginName(String loginName);

	/**
	 * 选择性修改User
	 * 
	 * @param user
	 */
	int updateByPrimaryKeySelective(User record);

	/**
	 * 修改User
	 * 
	 * @param user
	 */
	int updateByPrimaryKey(User record);

	/**
	 * 通过条件查询UserList
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<User> getUserList(Map<String, Object> parameterMap);

	/**
	 * 通过条件查询UserListCount
	 * 
	 * @param user
	 * @return
	 */
	int getUserListCount(User user);

	/**
	 * 根据主键id查询带角色的用户信息
	 * 
	 * @param id
	 * @return
	 */
	User getUserDetailById(Long id);

	/**
	 * 根据登录名查询角色和资源列表
	 * 
	 * @param loginName
	 * @return
	 */
	User getRoleAndResourceByLoginName(String loginName);

	/**
	 * 更新用户的组织机构到根组织机构中
	 * 
	 * @param unitId
	 */
	int updateUnit(Long unitId);

}
