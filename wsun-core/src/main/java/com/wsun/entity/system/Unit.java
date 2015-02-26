package com.wsun.entity.system;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.wsun.entity.IdEntity;

public class Unit extends IdEntity {

	/**
	 * 组织机构编码
	 */
	private String code;

	/**
	 * 组织机构名称
	 */
	private String name;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 父菜单ID
	 */
	private Long parentId;

	/**
	 * 排序编号
	 */
	private Integer orderId;

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
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}