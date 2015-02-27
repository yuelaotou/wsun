/**
 * 
 */
package com.wsun.controller.system;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wsun.common.constants.Consts.Sex;
import com.wsun.common.constants.Consts.Status;
import com.wsun.common.dao.page.Page;
import com.wsun.common.dao.page.PageBean;
import com.wsun.common.dao.page.PageUtil;
import com.wsun.common.utils.JsonUtil;
import com.wsun.entity.system.User;
import com.wsun.service.system.UserService;

/**
 * @author Administrator
 * 
 */
@Controller
@RequestMapping(value = "/system/user")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "")
	public String init(ModelMap modelMap) {
		// 性别下拉框
		modelMap.put("sexList", Sex.list());
		modelMap.put("statusList", Status.list());
		return "system/user";
	}

	// 特别设定多个ReuireRoles之间为Or关系，而不是默认的And.
	// @RequiresRoles(value = { "Admin", "User" }, logical = Logical.OR)
	@RequestMapping(value = "/list")
	@ResponseBody
	public PageBean<User> list(Page page, User user) {

		Map<String, Object> parameterMap = PageUtil.getParameters(page, user);
		logger.info("用户管理查询条件：" + JsonUtil.toJson(parameterMap));

		int totalCount = userService.getUserListCount(user);
		PageBean<User> pageResponse = new PageBean<User>(page, totalCount);
		if (totalCount > 0) {
			pageResponse.addAll(userService.getUserList(parameterMap));
		}
		return pageResponse;
	}

	/**
	 * 保存用户信息，同时保存用户的角色信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequiresRoles(value = { "admin", "s" }, logical = Logical.OR)
	public String save(User user) {
		logger.info("保存用户信息：" + JsonUtil.toJson(user));
		userService.saveUser(user);
		return "true";
	}

	/**
	 * 根据主键id查询带角色的用户信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public User getUser(Long id) {
		return userService.getUserDetailById(id);
	}

	/**
	 * 删除用户信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(Long id) {
		userService.deleteUser(id);
		return "true";
	}

	/**
	 * 更改用户状态，启用还是废弃
	 * 
	 * @param id
	 * @param status
	 */
	@RequestMapping(value = "/status/{status}", method = RequestMethod.POST)
	@ResponseBody
	public void status(Long id, @PathVariable Integer status) {
		userService.updateUserStatus(id, status);
	}
}
