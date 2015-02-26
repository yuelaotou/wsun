/*
 * File : GlobalController.java
 * date : 2013年9月6日 下午2:01:28
 */
package com.wsun.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

import com.wsun.common.converter.CustomTimestampEditor;
import com.wsun.common.converter.StringEscapeEditor;
import com.wsun.common.exception.BusinessException;

/**
* 全局Controller，配置日期绑定等
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

		//自动转换日期类型的字段格式
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(java.sql.Timestamp.class, new CustomTimestampEditor(datetimeFormat, true));

		//防止XSS攻击
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
	}

	//	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	//	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request, PrintWriter pw) {
	//		String bodyOfResponse = "This should be application specific";
	//		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	//	}

	@ExceptionHandler(value = { BusinessException.class })
	protected void handleBusinessException(BusinessException ex, WebRequest request, HttpServletResponse response)
			throws Exception {

		String errorMsg = ex.getLocalizedMessage();
		response.setContentType("text/html; charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		PrintWriter pw = response.getWriter();
		pw.println(errorMsg);
		pw.flush();
		pw.close();
		LoggerFactory.getLogger("business").error(errorMsg, ex);
		//		return new ResponseEntity<Object>("Hello World", responseHeaders, HttpStatus.CREATED);

		//		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus., request);
	}

	@ExceptionHandler(value = { AuthorizationException.class })
	protected void handleAuthorizationException(AuthorizationException ex, WebRequest request,
			HttpServletResponse response) throws Exception {
		String errorMsg = ex.getLocalizedMessage();
		response.setContentType("text/html; charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		PrintWriter pw = response.getWriter();
		pw.println(errorMsg);
		pw.flush();
		pw.close();
		LoggerFactory.getLogger("exception").error(errorMsg, ex);
	}

	@ExceptionHandler(value = { Exception.class })
	protected void handleException(Exception ex, WebRequest request, HttpServletResponse response) throws Exception {
		String errorMsg = "系统异常，请联系管理员！";
		response.setContentType("text/html; charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		PrintWriter pw = response.getWriter();
		pw.println(errorMsg);
		pw.flush();
		pw.close();
		LoggerFactory.getLogger("exception").error(errorMsg, ex);
		//		return new ResponseEntity<Object>("Hello World", responseHeaders, HttpStatus.CREATED);

		//		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus., request);
	}

	/**
	* <p>
	* 记录日志
	* </p>
	*/
	//	private void doLog(Exception ex) {

	// log4j日志记录操作
	//		StringWriter sw = new StringWriter();
	//		PrintWriter pw = new PrintWriter(sw);
	//		ex.printStackTrace(pw);
	//Logger.getLogger("exception").error(sw.getBuffer().toString());
	//	}
}
