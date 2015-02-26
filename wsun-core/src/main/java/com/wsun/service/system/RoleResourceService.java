/*
 * File : RoleResourceService.java
 * date : 2013年8月27日 下午3:22:56
 */
package com.wsun.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsun.entity.system.RoleResource;
import com.wsun.repository.mapper.system.RoleResourceMapper;

/**
 * 角色和菜单服务类
 * 
 * @author: dbyangguang
 * @date: 2013年8月27日 下午3:22:56
 * @version: 1.0
 */
@Service
public class RoleResourceService {

	@Autowired
	private RoleResourceMapper roleResourceMapper;

	public int insert(RoleResource roleResource) {
		// logger.info("新增角色授权资源信息" + JsonMapper.nonEmptyMapper().toJson(roleResource));
		return roleResourceMapper.insert(roleResource);
	}

	public int update(RoleResource roleResource) {
		return roleResourceMapper.updateByPrimaryKey(roleResource);
	}

	public int delete(Long id) {
		return roleResourceMapper.deleteByPrimaryKey(id);
	}

	public int deleteByRoleId(Long roleId) {
		return roleResourceMapper.deleteByRoleId(roleId);
	}
}
