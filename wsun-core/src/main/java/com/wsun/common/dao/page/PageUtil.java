/*
 * File : PageUtil.java
 * date : 2013年9月2日 下午1:27:45
 */
package com.wsun.common.dao.page;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.google.common.base.CaseFormat;
import com.wsun.common.exception.BusinessException;
import com.wsun.common.utils.Reflections;

/**
 * 处理分页，排序，查询条件参数。统一绑定到Map<String, Object>中
 * 
 * @author: dbyangguang
 * @date: 2013年9月2日 下午1:27:45
 * @version: 1.0
 */
public abstract class PageUtil {

	private static String SORT_NAME = "mDataProp_";

	private static String SORT_COL = "iSortCol_";

	private static String SORT_DIR = "sSortDir_";

	public static Map<String, Object> getParameters(Page page, Object obj) {

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		// 1、把查询条件参数进行绑定拆分。
		try {
			parameterMap.putAll(DataTransferUtil.voToMap(obj));
		} catch (Exception e) {
			throw new BusinessException("查询条件参数绑定失败", e);
		}

		// 2、把分页参数进行绑定
		RowBounds rowBounds = new RowBounds((page.getiDisplayStart()) * page.getiDisplayLength(),
				page.getiDisplayLength());
		parameterMap.put("rowBounds", rowBounds);

		// 3、把排序条件进行绑定
		// page.getiSortingCols()总共需要排序的列数。
		if (page.getiSortingCols() > 0) {
			Map<String, String> sortItemMap = new LinkedHashMap<String, String>();
			for (int i = 0; i < page.getiSortingCols(); i++) {
				int col = (Integer) Reflections.getFieldValue(page, SORT_COL + i);
				String sortName = (String) Reflections.getFieldValue(page, SORT_NAME + col);
				String sName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sortName);
				String sortDir = (String) Reflections.getFieldValue(page, SORT_DIR + i);
				sortItemMap.put(sName, sortDir);
			}
			parameterMap.put("_sorts", sortItemMap);
		}

		return parameterMap;
	}

}
