/*
 * File : PageBean.java
 * date : Sep 28, 2012 8:16:16 PM
 */
package com.wsun.common.dao.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PageBean<T> implements Serializable {

	private static final long serialVersionUID = -3284694395882955584L;

	public PageBean() {
	}

	public PageBean(Page page, int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalRecords;
		this.sEcho = page.getsEcho();
		//		this.aaData = Lists.newArrayList();
		this.aaData = new ArrayList<T>(page.getiDisplayLength());
	}

	/**
	 * 实际的行数
	 */
	private int iTotalRecords;

	/**
	 * 过滤之后，实际的行数。
	 */
	private int iTotalDisplayRecords;

	/**
	 * 数组的数组，表格中的实际数据。
	 */
	private List<T> aaData;

	/**
	 * 来自客户端 sEcho 的没有变化的复制品，
	 */
	private String sEcho;

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public boolean addAll(Collection<? extends T> c) {
		return aaData.addAll(c);
	}
}
