package com.wsun.service.system;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsun.common.exception.BusinessException;
import com.wsun.entity.system.Role;
import com.wsun.entity.system.RoleResource;
import com.wsun.repository.mapper.system.RoleMapper;
import com.wsun.repository.mapper.system.RoleResourceMapper;
import com.wsun.repository.mapper.system.UserRoleMapper;

/**
 * 菜单管理业务类.
 * 
 */
@Service
public class RoleService {

	private static final Logger logger = LogManager.getLogger(RoleService.class);
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	/**
	 * 查询所有带资源信息的角色信息
	 * 
	 * @return
	 */
	public List<Role> getRoleList(Map<String, Object> parameterMap) {
		logger.info("查询所有带资源信息的角色信息");
		return roleMapper.getRoleList(parameterMap);
	}

	/**
	 * 查询所有带资源信息的角色信息
	 * 
	 * @return
	 */
	public int getRoleListCount(Role role) {
		logger.info("查询所有带资源信息的角色信息");
		return roleMapper.getRoleListCount(role);
	}

	/**
	 * 根据主键id查询带资源的角色信息
	 * 
	 * @param id
	 * @return
	 */
	public Role getRoleDetailById(Long id) {
		logger.info("根据主键id查询带资源的角色信息，id：" + id);
		return roleMapper.getRoleDetailById(id);
	}

	/**
	 * 保存角色信息，同时保存角色的资源信息
	 * 
	 * @param role
	 */
	public void saveRole(Role role) {
		if (role.getId() == null) {
			int count = roleMapper.selectByCode(role.getCode());
			if (count > 0) {
				throw new BusinessException("系统存在角色编码为[" + role.getCode() + "]的角色，不允许重复添加！");
			}
			// logger.info("新增角色：" + JsonMapper.nonEmptyMapper().toJson(role));
			roleMapper.insert(role);
		} else {
			// logger.info("修改角色：" + JsonMapper.nonEmptyMapper().toJson(role));
			roleMapper.updateByPrimaryKey(role);
		}

		// 删除角色已经授权的资源信息
		roleResourceMapper.deleteByRoleId(role.getId());

		// 插入授权的资源信息
		if (StringUtils.isNotEmpty(role.getResources())) {
			String[] resources = StringUtils.split(role.getResources(), ",");
			Arrays.sort(resources);
			logger.info(String.format("角色id：{0}，插入资源信息：{1}", role.getId(), resources));
			RoleResource roleResource = null;
			for (String res : resources) {
				roleResource = new RoleResource();
				roleResource.setRoleId(role.getId());
				roleResource.setResourceId(Long.parseLong(res));
				roleResourceMapper.insert(roleResource);
			}
		}
	}

	/**
	 * 删除角色，同时删除角色对应的资源信息。并且删除用户已经绑定的角色
	 * 
	 * @param id
	 */
	public void deleteRole(Long id) {
		// 删除角色
		roleMapper.deleteByPrimaryKey(id);
		// 删除角色对应的资源信息
		roleResourceMapper.deleteByRoleId(id);
		// 删除使用此角色的用户关联信息
		userRoleMapper.deleteByRoleId(id);
	}

	/**
	 * 查询所有角色列表
	 * 
	 * @return
	 */
	public List<Role> getAll() {
		return roleMapper.getAll();
	}
}
