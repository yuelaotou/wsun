/*
 * File : Page.java
 * date : 2013年8月2日 下午12:43:02
 */
package com.wsun.common.dao.page;

import java.io.Serializable;

/**
* 翻页
* @author: dbyangguang
* @date: 2013年8月2日 下午12:43:02
* @version: 1.0
*/
public class Page extends P implements Serializable {

	private static final long serialVersionUID = -8726975066316870333L;

	/**
	 * 显示的起始索引
	 */
	private int iDisplayStart;

	/**
	 * 显示的行数
	 */
	private int iDisplayLength;

	/**
	 * 显示的列数
	 */
	private int iColumns;

	/**
	 * 全局搜索字段 
	 */
	private String sSearch;

	/**
	 * 全局搜索是否正则
	 */
	private boolean bRegex;

	private boolean bEscapeRegex;

	/**
	 * 排序的列数
	 */
	private int iSortingCols;

	/**
	 * DataTables 用来生成的信息
	 */
	private String sEcho;

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiColumns() {
		return iColumns;
	}

	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public boolean isbRegex() {
		return bRegex;
	}

	public void setbRegex(boolean bRegex) {
		this.bRegex = bRegex;
	}

	public boolean isbEscapeRegex() {
		return bEscapeRegex;
	}

	public void setbEscapeRegex(boolean bEscapeRegex) {
		this.bEscapeRegex = bEscapeRegex;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
