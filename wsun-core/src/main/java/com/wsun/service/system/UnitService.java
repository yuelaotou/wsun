package com.wsun.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsun.entity.system.Unit;
import com.wsun.repository.mapper.system.UnitMapper;
import com.wsun.repository.mapper.system.UserMapper;

/**
 * 组织机构管理业务类.
 * 
 */
@Service
public class UnitService {

	@Autowired
	private UnitMapper unitMapper;

	@Autowired
	private UserMapper userMapper;

	/**
	 * 查询所有机构列表
	 * 
	 * @return
	 */
	public List<Unit> getAll() {
		return unitMapper.getAll();
	}

	/**
	 * 根据主键id查询机构信息
	 * 
	 * @param id
	 * @return
	 */
	public Unit getUnitById(Long id) {
		return unitMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存机构信息
	 * 
	 * @param unit
	 */
	public void saveUnit(Unit unit) {
		// 判断是新增还是修改
		if (unit.getId() == null) {
			unitMapper.insert(unit);
			// logger.info("新增机构：" + JsonMapper.nonEmptyMapper().toJson(unit));
		} else {
			unitMapper.updateByPrimaryKey(unit);
			// logger.info("修改机构：" + JsonMapper.nonEmptyMapper().toJson(unit));
		}
	}

	/**
	 * 通过拖拽更新机构信息
	 * 
	 * @param sourceId
	 * @param targetId
	 * @param action
	 * @param point
	 * @return
	 */
	public void updateUnit(Long sourceId, Long targetId, String action, String point) {
		Unit unit = unitMapper.selectByPrimaryKey(sourceId);
		// logger.info("修改前机构：" + JsonMapper.nonEmptyMapper().toJson(unit));
		// 把sourceId追加到targetId后
		if ("append".equals(action)) {
			unit.setParentId(targetId);
			// 重新排序
			unit.setOrderId(1);
		} else if ("top".equals(point)) {
			Unit targetUnit = unitMapper.selectByPrimaryKey(targetId);
			int orderId = targetUnit.getOrderId() == null ? 1 : targetUnit.getOrderId();
			unit.setOrderId(orderId - 1 > 0 ? orderId - 1 : 1);
			// 设置和目标同一个父类
			unit.setParentId(targetUnit.getParentId());
		} else {
			Unit targetUnit = unitMapper.selectByPrimaryKey(targetId);
			int orderId = targetUnit.getOrderId() == null ? 1 : targetUnit.getOrderId();
			unit.setOrderId(orderId + 1);
			unit.setParentId(targetUnit.getParentId());
		}
		// logger.info("修改后机构：" + JsonMapper.nonEmptyMapper().toJson(unit));
		unitMapper.updateByPrimaryKey(unit);
	}

	/**
	 * 删除机构信息
	 * 
	 * @param id
	 * @return
	 */
	public void delete(Long id) {
		// 删除组织机构
		unitMapper.deleteByPrimaryKey(id);

		// 把此组织机构的人员，移动到根组织机构下
		userMapper.updateUnit(id);
	}
}
