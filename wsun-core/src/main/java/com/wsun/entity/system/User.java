package com.wsun.entity.system;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;
import com.wsun.common.constants.Consts.Sex;
import com.wsun.common.constants.Consts.Status;
import com.wsun.common.converter.CustomDateSerializer;
import com.wsun.common.converter.CustomDateTimeSerializer;
import com.wsun.common.utils.Collections3;
import com.wsun.entity.IdEntity;

public class User extends IdEntity {

	/**
	 * 组织机构
	 */
	private Unit unit;

	/**
	 * 登陆用户名
	 */
	private String loginName;

	/**
	 * 真实用户名
	 */
	private String name;

	/**
	 * 密码
	 */
	private String plainPassword;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 盐值
	 */
	private String salt;

	/**
	 * 邮件
	 */
	private String email;

	/**
	 * 性别
	 */
	private Integer sex;

	/**
	 * 性别中文
	 */
	@SuppressWarnings("unused")
	private String sexName;

	/**
	 * 移动电话
	 */
	private String mobile;

	/**
	 * 办公电话
	 */
	private String telephone;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 状态名称
	 */
	@SuppressWarnings("unused")
	private String statusName;

	/**
	 * 创建日期
	 */
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	private Date createDate;

	/**
	 * 过期日期
	 */
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date expireDate;

	/**
	 * 描述
	 */
	private String description;

	private String roles;

	/**
	 * 角色编码，供查询时使用
	 */
	private String roleCode;

	/**
	 * 角色列表
	 */
	private List<Role> roleList = Lists.newArrayList();

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSexName() {
		return Sex.getValue(sex);
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getStatusName() {
		return Status.getValue(status);
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	/**
	 * 获取RoleIds
	 * 
	 * @return
	 */
	public String getRoleIds() {
		return Collections3.extractToString(roleList, "id", ", ");
	}

	/**
	 * 获取RoleCodes
	 * 
	 * @return
	 */
	public String getRoleCodes() {
		return Collections3.extractToString(roleList, "code", ", ");
	}

	/**
	 * 获取RoleNames
	 * 
	 * @return
	 */
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "name", ", ");
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
