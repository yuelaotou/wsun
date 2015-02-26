package com.wsun.entity.system;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.wsun.common.constants.Consts.ResourceType;
import com.wsun.entity.IdEntity;

public class Resource extends IdEntity {

	/**
	 * 资源码
	 */
	private String code;

	/**
	 * 资源名称
	 */
	private String name;

	/**
	 * 资源类型
	 */
	private String type;

	/**
	 * 资源类型名称
	 */
	@SuppressWarnings("unused")
	private String typeName;

	/**
	 * 资源路径
	 */
	private String url;

	/**
	 * 图片
	 */
	private String icon;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 父资源ID
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return ResourceType.getValue(type);
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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