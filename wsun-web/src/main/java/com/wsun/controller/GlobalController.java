/*
 * File : GlobalController.java
 * date : 2013年9月6日 下午2:01:28
 */
package com.wsun.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wsun.common.converter.CustomTimestampEditor;
import com.wsun.common.converter.StringEscapeEditor;
import com.wsun.common.exception.BusinessException;
import com.wsun.common.exception.ExceptionJSONInfo;

/**
 * 全局Controller，配置日期绑定等
 * 
 * @author: dbyangguang
 * @date: 2013年9月6日 下午2:01:28
 * @version: 1.0
 */
@ControllerAdvice
public class GlobalController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		datetimeFormat.setLenient(false);

		// 自动转换日期类型的字段格式
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(java.sql.Timestamp.class, new CustomTimestampEditor(datetimeFormat, true));

		// 防止XSS攻击
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
	}

	@ExceptionHandler
	@ResponseBody
	public ExceptionJSONInfo handleException(HttpServletRequest request, Exception ex) {

		if (ex instanceof BusinessException) {
			LogManager.getLogger("businessLogger").error(ex.getMessage(), ex);
			return new ExceptionJSONInfo(request.getRequestURL().toString(), ex.getMessage());
		} else {
			LogManager.getLogger("errorLogger").error(ex.getMessage(), ex);
			return new ExceptionJSONInfo(request.getRequestURL().toString(), "系统异常，请联系管理员！");
		}
	}
}
