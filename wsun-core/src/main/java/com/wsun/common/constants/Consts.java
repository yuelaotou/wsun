/*
 * File : Const.java
 * date : 2013年8月16日 下午3:06:51
 */
package com.wsun.common.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 添加说明
 * 
 * @author: dbyangguang
 * @date: 2013年8月16日 下午3:06:51
 * @version: 1.0
 */
public class Consts {

	/**
	 * [是和否]
	 * 
	 * @author: dbyangguang
	 * @date: 2013-5-30 下午9:33:55
	 * @version: 1.0
	 */
	public enum YesOrNo {
		YES(1, "是"), NO(0, "否");

		private Integer key;

		private String value;

		private YesOrNo(Integer key, String value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		/**
		 * [通过key查找value值]
		 * 
		 * @param key
		 * @return
		 */
		public static String getValue(Integer key) {
			for (YesOrNo element : list()) {
				if (element.key.equals(key)) {
					return element.value;
				}
			}
			return null;
		}

		/**
		 * [返回数据列表]
		 * 
		 * @return
		 */
		public static List<YesOrNo> list() {
			return Arrays.asList(YesOrNo.values());
		}

		public Integer getKey() {
			return key;
		}

		public void setKey(Integer key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * [男和女]
	 * 
	 * @author: dbyangguang
	 * @date: 2013-5-30 下午9:33:55
	 * @version: 1.0
	 */
	public enum Sex {
		MALE(1, "男"), FEMALE(0, "女");

		private Integer key;

		private String value;

		private Sex(Integer key, String value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		/**
		 * [通过key查找value值]
		 * 
		 * @param key
		 * @return
		 */
		public static String getValue(Integer key) {
			for (Sex element : list()) {
				if (element.key.equals(key)) {
					return element.value;
				}
			}
			return null;
		}

		/**
		 * [返回数据列表]
		 * 
		 * @return
		 */
		public static List<Sex> list() {
			return Arrays.asList(Sex.values());
		}

		public Integer getKey() {
			return key;
		}

		public void setKey(Integer key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * [资源类型]
	 * 
	 * @author: dbyangguang
	 * @date: 2013-5-30 下午9:33:55
	 * @version: 1.0
	 */
	public enum ResourceType {
		MENU("menu", "菜单"), BUTTON("button", "按钮"), COLUMN("column", "列"), OTHER("other", "其他");

		private String key;

		private String value;

		private ResourceType(String key, String value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		/**
		 * [通过key查找value值]
		 * 
		 * @param key
		 * @return
		 */
		public static String getValue(String key) {
			for (ResourceType element : list()) {
				if (element.key.equals(key)) {
					return element.value;
				}
			}
			return null;
		}

		/**
		 * [返回数据列表]
		 * 
		 * @return
		 */
		public static List<ResourceType> list() {
			return Arrays.asList(ResourceType.values());
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * [用户状态]
	 * 
	 * @author: dbyangguang
	 * @date: 2013-5-30 下午9:33:55
	 * @version: 1.0
	 */
	public enum Status {
		NORMAL(1, "正常"), DISCARD(0, "锁定");

		private Integer key;

		private String value;

		private Status(Integer key, String value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		/**
		 * [通过key查找value值]
		 * 
		 * @param key
		 * @return
		 */
		public static String getValue(Integer key) {
			for (Status element : list()) {
				if (element.key.equals(key)) {
					return element.value;
				}
			}
			return null;
		}

		/**
		 * [返回数据列表]
		 * 
		 * @return
		 */
		public static List<Status> list() {
			return Arrays.asList(Status.values());
		}

		public Integer getKey() {
			return key;
		}

		public void setKey(Integer key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
