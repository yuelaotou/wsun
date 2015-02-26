package com.wsun.controller.system;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wsun.common.constants.Consts.ResourceType;
import com.wsun.entity.system.Resource;
import com.wsun.service.ShiroDbRealm.ShiroUser;
import com.wsun.service.system.ResourceService;

/**
 * 资源控制器
 * 
 * @author: dbyangguang
 * @date: 2013年8月20日 下午9:13:36
 * @version: 1.0
 */
@Controller
@RequestMapping(value = "/system/resource")
public class ResourceController {

	private static final Logger logger = LogManager.getLogger(ResourceController.class);

	@Autowired
	private ResourceService resourceService;

	/**
	 * 进入资源管理页面
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "")
	public String init(ModelMap modelMap) {
		// 资源类型下拉框
		modelMap.put("resourceTypeList", ResourceType.list());
		return "system/resource";
	}

	/**
	 * 获取父资源列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/parent")
	@ResponseBody
	public List<Resource> getParents() {
		ShiroUser currentUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return resourceService.getParents(currentUser.loginName);
	}

	/**
	 * 获取子资源列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/child")
	@ResponseBody
	public List<Resource> getChildrenById(@RequestParam("id") Long id) {
		ShiroUser currentUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return resourceService.getChildrenById(currentUser.loginName, id);
	}

	/**
	 * 查询所有资源列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<Resource> getAll() {
		return resourceService.getAll();
	}

	/**
	 * 保存资源信息
	 * 
	 * @param resource
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(Resource resource) {
		resourceService.saveResource(resource);
		return "true";
	}

	/**
	 * 根据主键id查询资源信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Resource getResource(Long id) {
		return resourceService.getResourceById(id);
	}

	/**
	 * 删除资源信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(Long id) {
		resourceService.delete(id);
		return "true";
	}

	/**
	 * 通过拖拽更新资源信息
	 * 
	 * @param sourceId
	 * @param targetId
	 * @param action
	 * @param point
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(Long sourceId, Long targetId, String action, String point) {
		logger.info(String.format("通过拖拽更新资源信息，源资源id：{0}，目标资源id：{1}，动作：{2}，位置：{3}", sourceId, targetId, action, point));
		resourceService.updateResource(sourceId, targetId, action, point);
		return "true";
	}
}
