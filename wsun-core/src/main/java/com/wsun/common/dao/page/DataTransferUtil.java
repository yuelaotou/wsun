/**
 * 
 */
package com.wsun.common.dao.page;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 工具类
 * 
 * @author yixu
 * 
 */
public class DataTransferUtil {

	private static final Logger logger = LogManager.getLogger(DataTransferUtil.class);

	/**
	 * @throws java.lang.reflect.InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * 
	 */
	public static <T, K, V> List<T> listToVO(List<HashMap<K, V>> sourceList, Class<T> clsVO)
			throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		List<T> list = null;
		if (clsVO == null || sourceList == null) {
			return null;
		}
		list = new ArrayList<T>();
		Iterator<HashMap<K, V>> iter = sourceList.iterator();
		while (iter.hasNext()) {
			Map<K, V> m = iter.next();
			T o = DataTransferUtil.mapToVO(m, clsVO);
			list.add(o);
		}
		return list;
	}

	/**
	 * mybatisMap转换Bean
	 * 
	 * @param sourceMap
	 *            Map
	 * @param clsVO
	 *            Bean.class
	 * @return VO
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 * @throws java.lang.reflect.InvocationTargetException
	 */
	public static <T, K, V> T mapToVO(Map<K, V> sourceMap, Class<T> clsVO) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		if (clsVO == null || sourceMap == null) {
			return null;
		}
		T obj = clsVO.newInstance();
		Iterator<K> keys = sourceMap.keySet().iterator();
		while (keys.hasNext()) {
			Object key = keys.next();
			Object value = sourceMap.get(key);
			if (!(key instanceof String)) {
				continue;
			}
			String propertie = DataTransferUtil.getCamelCaseString((String) key, false);
			if (logger.isDebugEnabled()) {
				logger.debug("propertie=" + propertie);
			}
			Class<?> cls = PropertyUtils.getPropertyDescriptor(obj, propertie).getPropertyType();
			if (logger.isDebugEnabled()) {
				logger.debug("HashMap Data Type=" + value.getClass().getName());
				logger.debug("Bean Data Type=" + cls.getName());
			}
			// yn字段Integer向Boolean转换
			if (Boolean.class.getName().equals(cls.getName())
					&& Integer.class.getName().equals(value.getClass().getName())) {
				value = ((Integer) value).intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
			}
			// Timestamp字段向Date转换
			else if (Date.class.getName().equals(cls.getName())
					&& Timestamp.class.getName().equals(value.getClass().getName())) {

				value = new Date(((Timestamp) value).getTime());
			}
			BeanUtils.setProperty(obj, propertie, value);
		}
		return obj;
	}

	/**
	 * mybatisMap转换Bean<BR>
	 * Map中的Key没有下划线<BR>
	 * Key值与VO属性值相同
	 * 
	 * @param sourceMap
	 *            Map
	 * @param clsVO
	 *            Bean.class
	 * @return VO
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 * @throws java.lang.reflect.InvocationTargetException
	 */
	public static <T, K, V> T mapToVOWithoutUnderline(Map<K, V> sourceMap, Class<T> clsVO)
			throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		if (clsVO == null || sourceMap == null) {
			return null;
		}
		T obj = clsVO.newInstance();
		Iterator<K> keys = sourceMap.keySet().iterator();
		while (keys.hasNext()) {
			Object key = keys.next();
			Object value = sourceMap.get(key);
			if (!(key instanceof String)) {
				continue;
			}
			if (logger.isDebugEnabled()) {
				logger.debug("propertie=" + key);
			}
			Class<?> cls = PropertyUtils.getPropertyDescriptor(obj, (String) key).getPropertyType();
			if (logger.isDebugEnabled()) {
				logger.debug("HashMap Data Type=" + value.getClass().getName());
				logger.debug("Bean Data Type=" + cls.getName());
			}
			// yn字段Integer向Boolean转换
			if (Boolean.class.getName().equals(cls.getName())
					&& Integer.class.getName().equals(value.getClass().getName())) {
				value = ((Integer) value).intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
			}
			// Timestamp字段向Date转换
			else if (Date.class.getName().equals(cls.getName())
					&& Timestamp.class.getName().equals(value.getClass().getName())) {

				value = new Date(((Timestamp) value).getTime());
			}
			BeanUtils.setProperty(obj, (String) key, value);
		}
		return obj;
	}

	/**
	 * Bean转换Map
	 * 
	 * @param sourceMap
	 *            Map
	 * @param clsVO
	 *            Bean.class
	 * @return VO
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 * @throws java.lang.reflect.InvocationTargetException
	 */
	public static Map<String, Object> voToMap(Object obj) throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		BeanUtilsBean.setInstance(new BeanUtilsBean2());
		ArrayConverter converter = new ArrayConverter(String[].class, new StringConverter(), 0);
		converter.setOnlyFirstToString(false);
		ConvertUtils.register(converter, String[].class);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = BeanUtils.describe(obj);
		map.remove("class");
		Field[] fields = ReflectionUtil.getDeclareFieldList(obj.getClass());
		for (Field field : fields) {
			if (field.getType().equals(List.class)) {
				if (map.containsKey(field.getName()) && map.get(field.getName()) != null) {
					String value = String.valueOf(map.get(field.getName()));
					String[] _value = value.split(",");
					map.put(field.getName(), _value);
				}
			}
			// 分页转换DATE类型
			if (field.getType().equals(Date.class)) {
				if (map.containsKey(field.getName()) && map.get(field.getName()) != null) {
					map.put(field.getName(), ReflectionUtil.getFieldValue(obj, obj.getClass(), field.getName()));
				}
			}
			// 分页转换boolean类型
			if (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class)) {
				if (map.containsKey(field.getName()) && map.get(field.getName()) != null) {
					map.put(field.getName(), ReflectionUtil.getFieldValue(obj, obj.getClass(), field.getName()));
				}
			}
		}
		return map;
	}

	/**
	 * CamelCaseString
	 * 
	 * @param inputString
	 *            输入字符串
	 * @param firstCharacterUppercase
	 *            首字母是否大写
	 * @return CamelCaseString
	 */
	public static String getCamelCaseString(String inputString, boolean firstCharacterUppercase) {
		StringBuilder sb = new StringBuilder();

		boolean nextUpperCase = false;
		for (int i = 0; i < inputString.length(); i++) {
			char c = inputString.charAt(i);

			switch (c) {
			case '_':
			case '-':
			case '@':
			case '$':
			case '#':
			case ' ':
			case '/':
			case '&':
				if (sb.length() > 0) {
					nextUpperCase = true;
				}
				break;

			default:
				if (nextUpperCase) {
					sb.append(Character.toUpperCase(c));
					nextUpperCase = false;
				} else {
					sb.append(Character.toLowerCase(c));
				}
				break;
			}
		}

		if (firstCharacterUppercase) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}

		return sb.toString();
	}

	/**
	 * 判断对象内给定的属性值是否为空
	 * 
	 * @param obj
	 *            对象
	 * @param properties
	 *            属列数组
	 * @return true:属性中没有为空 false:属性中有为空<br>
	 *         Create : 2012-11-22 上午10:22:43 <br>
	 */
	public static boolean checkPropertiesNull(Object obj, String... properties) {
		if (obj == null || properties == null) {
			return true;
		}
		for (int i = 0; i < properties.length; i++) {
			String property = properties[i];
			if (StringUtils.isEmpty(property)) {
				continue;
			}
			Object ret = ReflectionUtil.getFieldValue(obj, property);
			if (ret instanceof String) {
				if (StringUtils.isEmpty((String) ret)) {
					return false;
				}
			} else {
				if (ret == null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 判断Map内给定的Key的值是否为空
	 * 
	 * @param obj
	 *            对象
	 * @param properties
	 *            属列数组
	 * @return true:属性中没有为空 false:属性中有为空<br>
	 *         Create : 2012-11-22 上午10:22:43 <br>
	 */
	public static boolean checkPropertiesNull(@SuppressWarnings("rawtypes") Map map, String... properties) {
		if (map == null || properties == null) {
			return true;
		}
		for (int i = 0; i < properties.length; i++) {
			String property = properties[i];
			if (StringUtils.isEmpty(property)) {
				continue;
			}
			Object ret = map.get(property);
			if (ret instanceof String) {
				if (StringUtils.isEmpty((String) ret)) {
					return false;
				}
			} else {
				if (ret == null) {
					return false;
				}
			}
		}
		return true;
	}
}
