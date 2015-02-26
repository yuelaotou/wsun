package com.wsun.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * JSON的工具类
 * <p/>
 */
@SuppressWarnings("unchecked")
public final class JsonUtil {

	private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	private JsonUtil() {
	}

	/**
	 * 将对象转换成json
	 */
	public static <T> String toJson(T src) {
		return GSON.toJson(src);
	}

	/**
	 * 将json通过类型转换成对象
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		return GSON.fromJson(json, clazz);
	}

	/**
	 * 将json通过类型转换成对象，可以用List
	 */
	public static <T> T fromJson(String json, TypeToken<?> type) {
		return (T) GSON.fromJson(json, type.getType());
	}
}
