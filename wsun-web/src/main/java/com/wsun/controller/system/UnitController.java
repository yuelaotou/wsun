package com.wsun.controller.system;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wsun.entity.system.Unit;
import com.wsun.service.system.UnitService;

/**
 * 机构控制器
 * 
 * @author: dbyangguang
 * @date: 2013年8月20日 下午9:13:36
 * @version: 1.0
 */
@Controller
@RequestMapping(value = "/system/unit")
public class UnitController {

	private static Logger logger = LoggerFactory.getLogger(UnitController.class);

	@Autowired
	private UnitService unitService;

	/**
	 * 进入机构管理页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "")
	public String init(ModelMap modelMap) {
		return "system/unit";
	}

	/**
	 * 查询所有机构列表
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<Unit> getAll() {
		return unitService.getAll();
	}

	/**
	 * 保存机构信息
	 * @param resource
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(Unit unit) {
		unitService.saveUnit(unit);
		return "true";
	}

	/**
	 * 根据主键id查询机构信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Unit getUnit(Long id) {
		return unitService.getUnitById(id);
	}

	/**
	 * 删除机构信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(Long id) {
		unitService.delete(id);
		return "true";
	}

	/**
	 * 通过拖拽更新机构信息
	 * @param sourceId
	 * @param targetId
	 * @param action
	 * @param point
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(Long sourceId, Long targetId, String action, String point) {
		logger.info(String.format("通过拖拽更新机构信息，源机构id：{0}，目标机构id：{1}，动作：{2}，位置：{3}", sourceId, targetId, action, point));
		unitService.updateUnit(sourceId, targetId, action, point);
		return "true";
	}
}
