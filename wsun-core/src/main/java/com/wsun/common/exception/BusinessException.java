package com.wsun.common.exception;

/**
 * @author : yanghongfeng
 * @version : 1.0
 * @date : 13-2-1 下午1:49
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -8984848558761330684L;

	private Object[] parameters = null;

	public Object[] getParameters() {
		return parameters;
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Object... params) {
		super(message);
		this.parameters = params;
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message, Throwable cause, Object... params) {
		super(message, cause);
		this.parameters = params;
	}

	@Override
	public String getLocalizedMessage() {

		return super.getLocalizedMessage();

		// TODO:此处对本地化消息处理进行注释，若使用需要正确加载本地化处理对象
		//		String message = getMessage();
		//		if (message == null)
		//			return null;
		//		String localizedMessage = null;
		//		MessageResource mr = new NestedMultiResourceBundle();
		//		if (parameters == null || parameters.length == 0) {
		//			localizedMessage = mr.getMessage(message, Locale.getDefault());
		//
		//		} else {
		//			localizedMessage = mr.getMessage(message, parameters, Locale.getDefault());
		//		}
		//		return localizedMessage;
	}

}
