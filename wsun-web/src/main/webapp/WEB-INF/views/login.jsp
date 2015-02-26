<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.LockedAccountException "%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="bootstrap" value="bootstrap-3.3.2" />
<html>
<head>
<title>登录页</title>
<link rel="stylesheet" href="${ctx}/static/${bootstrap}/css/bootstrap.min.css" />
<script src="${ctx}/static/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/${bootstrap}/js/bootstrap.min.js"></script>
</head>

<body>
	<h1>登录页</h1>
	<form id="loginForm" action="${ctx}/login" method="post">
		<%
			String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
			if (error != null) {
		%>
		<div class="alert alert-error controls input-large">
			<button class="close" data-dismiss="alert">×</button>
			<%
				if (error.contains("DisabledAccountException")) {
						out.print("用户已被屏蔽,请登录其他用户.");
					} else {
						out.println("登录失败，请重试.");
						out.print(error);
					}
			%>
		</div>
		<%
			}
		%>
		<div class="control-group">
			<label for="username" class="control-label">名称:</label>
			<div class="controls">
				<input type="text" id="username" name="username" value="${username}" class="input-medium required" />
			</div>
		</div>
		<div class="control-group">
			<label for="password" class="control-label">密码:</label>
			<div class="controls">
				<input type="password" id="password" name="password" class="input-medium required" />
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<label class="checkbox inline" for="rememberMe"> <input type="checkbox" id="rememberMe" name="rememberMe" /> 记住我
				</label> <input id="submit_btn" class="btn" type="submit" value="登录" />
				<p class="help-block">
					(管理员：<b>admin/admin</b>, 普通用户：<b>user/user</b>)
				</p>
			</div>
		</div>
	</form>
</body>
</html>
