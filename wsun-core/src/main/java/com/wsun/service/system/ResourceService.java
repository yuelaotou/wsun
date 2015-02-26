package com.wsun.service.system;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.wsun.entity.system.Resource;
import com.wsun.repository.mapper.system.ResourceMapper;
import com.wsun.repository.mapper.system.RoleResourceMapper;

/**
 * 菜单管理业务类.
 * 
 */
@Service
public class ResourceService {

	private static final Logger logger = LogManager.getLogger(ResourceService.class);
	@Autowired
	private ResourceMapper resourceMapper;

	@Autowired
	private RoleResourceMapper roleResourceMapper;

	/**
	 * 获取父资源列表
	 * 
	 * @return
	 */
	public List<Resource> getParents(String loginName) {
		logger.info(String.format("查询{0}根结点的菜单", loginName));
		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("loginName", loginName);
		return resourceMapper.getParents(parameters);
	}

	/**
	 * 获取子资源列表
	 * 
	 * @return
	 */
	public List<Resource> getChildrenById(String loginName, Long id) {
		logger.info(String.format("查询{0}子节点的菜单，父节点ID：{1}", loginName, id));
		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("loginName", loginName);
		parameters.put("id", id);
		return resourceMapper.getChildrenById(parameters);
	}

	/**
	 * 查询所有资源列表
	 * 
	 * @return
	 */
	public List<Resource> getAll() {
		return resourceMapper.getAll();
	}

	/**
	 * 根据主键id查询资源信息
	 * 
	 * @param id
	 * @return
	 */
	public Resource getResourceById(Long id) {
		return resourceMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存资源信息
	 * 
	 * @param resource
	 */
	public void saveResource(Resource resource) {
		// 判断是新增还是修改
		if (resource.getId() == null) {
			resourceMapper.insert(resource);
			// logger.info("新增资源：" + JsonMapper.nonEmptyMapper().toJson(resource));
		} else {
			resourceMapper.updateByPrimaryKey(resource);
			// logger.info("修改资源：" + JsonMapper.nonEmptyMapper().toJson(resource));
		}
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
	public void updateResource(Long sourceId, Long targetId, String action, String point) {
		Resource resource = resourceMapper.selectByPrimaryKey(sourceId);
		// logger.info("修改前资源：" + JsonMapper.nonEmptyMapper().toJson(resource));
		// 把sourceId追加到targetId后
		if ("append".equals(action)) {
			resource.setParentId(targetId);
			// 重新排序
			resource.setOrderId(1);
		} else if ("top".equals(point)) {
			Resource targetResource = resourceMapper.selectByPrimaryKey(targetId);
			int orderId = targetResource.getOrderId() == null ? 1 : targetResource.getOrderId();
			resource.setOrderId(orderId - 1 > 0 ? orderId - 1 : 1);
			// 设置和目标同一个父类
			resource.setParentId(targetResource.getParentId());
		} else {
			Resource targetResource = resourceMapper.selectByPrimaryKey(targetId);
			int orderId = targetResource.getOrderId() == null ? 1 : targetResource.getOrderId();
			resource.setOrderId(orderId + 1);
			resource.setParentId(targetResource.getParentId());
		}
		// logger.info("修改后资源：" + JsonMapper.nonEmptyMapper().toJson(resource));
		resourceMapper.updateByPrimaryKey(resource);
	}

	/**
	 * 删除资源信息
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		// 删除资源信息
		resourceMapper.deleteByPrimaryKey(id);

		// 同时删掉角色和资源对应的联系
		roleResourceMapper.deleteByResourceId(id);
	}
}
