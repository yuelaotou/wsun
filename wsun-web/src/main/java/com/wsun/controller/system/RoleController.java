/*
 * File : RoleController.java
 * date : 2013年8月20日 下午9:13:10
 */
package com.wsun.controller.system;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wsun.common.dao.page.Page;
import com.wsun.common.dao.page.PageBean;
import com.wsun.common.dao.page.PageUtil;
import com.wsun.common.utils.JsonUtil;
import com.wsun.entity.system.Role;
import com.wsun.service.system.RoleService;

/**
 * 角色管理Controller
 * 
 * @author: dbyangguang
 * @date: 2013年8月20日 下午9:13:10
 * @version: 1.0
 */
@Controller
@RequestMapping(value = "/system/role")
public class RoleController {

	private static final Logger logger = LogManager.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	/**
	 * 进入角色管理页面
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "")
	public String init(ModelMap modelMap) {
		return "system/role";
	}

	/**
	 * 查询所有带资源信息的角色信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public PageBean<Role> list(Page page, Role role) {

		Map<String, Object> parameterMap = PageUtil.getParameters(page, role);
		logger.info("角色管理查询条件：" + JsonUtil.toJson(parameterMap));

		int totalCount = roleService.getRoleListCount(role);
		PageBean<Role> pageResponse = new PageBean<Role>(page, totalCount);
		if (totalCount > 0) {
			pageResponse.addAll(roleService.getRoleList(parameterMap));
		}
		return pageResponse;
	}

	/**
	 * 保存角色信息，同时保存角色的资源信息
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(Role role) {
		roleService.saveRole(role);
		return "true";
	}

	/**
	 * 根据主键id查询带资源的角色信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Role getRole(Long id) {
		return roleService.getRoleDetailById(id);
	}

	/**
	 * 删除角色信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(Long id) {
		roleService.deleteRole(id);
		return "true";
	}

	/**
	 * 查询所有角色列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/all")
	@ResponseBody
	public List<Role> getAll() {
		return roleService.getAll();
	}
}
