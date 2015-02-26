package com.wsun.common.utils;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * Title: Configuration.java
 * </p>
 * <p>
 * Description: 取配置信息公共类
 * </p>
 * 
 * 创建时间: 2012-5-22 上午11:54:23 CopyRight(C), 2012, qianyongming
 */
public class Configuration {

	/**
	 * 属性值
	 */
	private static Properties prop;

	public static Properties getProp() {
		return prop;
	}

	public static void setProp(Properties prop) {
		Configuration.prop = prop;
	}

	public static String getProproperty(String key) {
		return prop.getProperty(key);
	}

	public static void setValue(String key, String value) {
		if (prop.containsKey(key) && StringUtils.isNotBlank(value)) {
			prop.setProperty(key, value);
		}
	}

	public static Properties getProps() {
		return prop;
	}
}
