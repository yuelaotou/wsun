package com.wsun.entity.system;

import java.util.Date;

import com.wsun.entity.IdEntity;

public class UserRole extends IdEntity {

	private Long userId;

	private Long roleId;

	private boolean isCopy;

	private Long copyUserId;

	private Date beginDate;

	private Date endDate;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public boolean isCopy() {
		return isCopy;
	}

	public void setCopy(boolean isCopy) {
		this.isCopy = isCopy;
	}

	public Long getCopyUserId() {
		return copyUserId;
	}

	public void setCopyUserId(Long copyUserId) {
		this.copyUserId = copyUserId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}