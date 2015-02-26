package com.wsun.common.dao.interceptor;

/**
 * 此拦截器可实现数据列表的排序及物理分页
 * <br>User: yanghongfeng
 * <br>DateTime: Sep 18, 2012 9:55:09 AM
 * <br>Version 1.0
 */

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {
	private static final Logger logger = LogManager.getLogger(PaginationInterceptor.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, new DefaultObjectFactory(),
				new DefaultObjectWrapperFactory());

		// 获取参数
		DefaultParameterHandler defaultParameterHandler = (DefaultParameterHandler) metaStatementHandler
				.getValue("delegate.parameterHandler");
		if (!(defaultParameterHandler.getParameterObject() instanceof Map)) {
			return invocation.proceed();
		}

		Map parameterMap = (Map) defaultParameterHandler.getParameterObject();
		// 如果分页数据为空，则跳过拦截器
		RowBounds rowBounds = (RowBounds) parameterMap.get("rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}

		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");

		Dialect.Type databaseType = null;
		try {
			databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
		} catch (Exception e) {
			logger.error("PaginationInterceptor error", e);
		}
		if (databaseType == null) {
			throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : "
					+ configuration.getVariables().getProperty("dialect"));
		}
		Dialect dialect = null;
		switch (databaseType) {
		case ORACLE:
			dialect = new OracleDialect();
			break;
		case MYSQL:
			dialect = new MySqlDialect();
			break;

		}

		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

		if (parameterMap.get("_sorts") != null) {
			if (parameterMap.get("_sorts") instanceof LinkedHashMap) {
				LinkedHashMap<String, String> sorts = (LinkedHashMap<String, String>) parameterMap.get("_sorts");
				// 使用自定义排序
				originalSql = dialect.getOrderString(originalSql, sorts);
			} else {
				logger.debug("the sort parameter must be 'LinkedHashMap' type");
			}
		}

		// 使用自定义的物理分页方法。若不使用自定义分页（此处注释该方法），则使用mybatis的逻辑分页。物理分页效率高于逻辑分页
		originalSql = dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit());
		// 如果使用自定义的物理分页法，请打开下边的两个注释。
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);

		metaStatementHandler.setValue("delegate.boundSql.sql", originalSql);

		logger.debug("生成分页SQL : " + statementHandler.getBoundSql().getSql());
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}