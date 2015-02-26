package com.wsun.entity.system;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.wsun.common.utils.Collections3;
import com.wsun.entity.IdEntity;

public class Role extends IdEntity {

	/**
	 * 角色编码
	 */
	private String code;

	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 父角色ID
	 */
	private Long parentId;

	/**
	 * 排序编号
	 */
	private Integer orderId;

	private String resources;

	/**
	 * 资源编码，供查询条件使用
	 */
	private String resourceCode;

	/**
	 * 资源列表
	 */
	private List<Resource> resourceList = Lists.newArrayList();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	/**
	 * 获取ResourceIds
	 * 
	 * @return
	 */
	public String getResourceIds() {
		return Collections3.extractToString(resourceList, "id", ", ");
	}

	/**
	 * 获取ResourceCodes
	 * 
	 * @return
	 */
	public String getResourceCodes() {
		return Collections3.extractToString(resourceList, "code", ", ");
	}

	/**
	 * 获取ResourceNames
	 * 
	 * @return
	 */
	public String getResourceNames() {
		return Collections3.extractToString(resourceList, "name", ", ");
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}