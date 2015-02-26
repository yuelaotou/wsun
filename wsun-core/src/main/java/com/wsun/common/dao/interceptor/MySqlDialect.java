/*
 * File : OracleDialect.java
 * date : Sep 18, 2012 9:54:04 AM
 */
package com.wsun.common.dao.interceptor;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

/**
 * [添加说明] <br>
 * User: yanghongfeng <br>
 * DateTime: Sep 18, 2012 9:54:04 AM <br>
 * Version 1.0
 */
public class MySqlDialect extends Dialect {

	protected static final String SQL_END_DELIMITER = ";";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mybatis.extend.interceptor.IDialect#getLimitString(java.lang.String, int, int)
	 */
	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = trim(sql);
		StringBuffer sb = new StringBuffer(sql.length() + 20);
		sb.append(sql);
		if (offset > 0) {
			sb.append(" limit ").append(offset).append(',').append(limit).append(SQL_END_DELIMITER);
		} else {
			sb.append(" limit ").append(limit).append(SQL_END_DELIMITER);
		}
		return sb.toString();
	}

	@Override
	public String getOrderString(String originalSql, LinkedHashMap<String, String> orderItems) {
		String ex = "";
		if (orderItems != null && orderItems.size() > 0) {
			Iterator<Entry<String, String>> it = orderItems.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				ex = ex + "," + entry.getKey() + " " + entry.getValue();
			}
		}

		if (ex.trim().length() > 0) {
			if (ex.startsWith(",")) {
				ex = ex.substring(1);
			}
			if (StringUtils.containsIgnoreCase(originalSql, "order by")) {
				return originalSql + ", " + ex;
			} else {
				return originalSql + " order by " + ex;
			}
		}

		return originalSql;
	}

	private String trim(String sql) {
		sql = sql.trim();
		if (sql.endsWith(SQL_END_DELIMITER)) {
			sql = sql.substring(0, sql.length() - 1 - SQL_END_DELIMITER.length());
		}
		return sql;
	}

}
