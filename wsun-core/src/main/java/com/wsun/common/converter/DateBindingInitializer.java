/*
 * File : DateConverter.java
 * date : 2013年9月5日 下午5:43:23
 */
package com.wsun.common.converter;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
* 配置全局日期转换
* @author: dbyangguang
* @date: 2013年9月5日 下午5:43:23
* @version: 1.0
*/
public class DateBindingInitializer implements WebBindingInitializer {

	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		//		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//		datetimeFormat.setLenient(false);

		//自动转换日期类型的字段格式
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
		//		binder.registerCustomEditor(java.sql.Timestamp.class, new CustomTimestampEditor(datetimeFormat, true));

		//防止XSS攻击
		//		binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
	}
}
