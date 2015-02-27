<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="bootstrap" value="bootstrap-3.3.2" />
<c:set var="datatables" value="datatables-1.10.2" />
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<title>wsun platform</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="shortcut icon" href="http://www.jd.com/favicon.ico">
<link rel="stylesheet" href="${ctx}/static/${bootstrap}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/static/Flat-UI-master/css/flat-ui.css" />
<link rel="stylesheet" href="${ctx}/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="${ctx}/static/${datatables}/css/jquery.dataTables.min.css" />
<link rel="stylesheet" href="${ctx}/static/${datatables}/css/dataTables.bootstrap.css" />

<link rel="stylesheet" href="${ctx}/static/wsun/css/wsun-style.css" />
<link rel="stylesheet" href="${ctx}/static/wsun/css/wsun-media.css" />
<link rel="stylesheet" href="${ctx}/static/wsun/font-awesome/css/font-awesome.css" />

<script src="${ctx}/static/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/${bootstrap}/js/bootstrap.min.js"></script>
<script src="${ctx}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/static/${datatables}/js/jquery.dataTables.min.js"></script>
<script src="${ctx}/static/${datatables}/js/dataTables.bootstrap.js"></script>
<script src="${ctx}/static/base.js"></script>
<script src="${ctx}/static/js/bootbox.min.js"></script>

<script src="${ctx}/static/wsun/js/wsun.js"></script>
<script src="${ctx}/static/Flat-UI-master/js/custom_checkbox_and_radio.js"></script>
</head>
<body>

	<!--Header-part-->
	<div id="header">
		<h1>
			<a href="dashboard.html">Wsun Platform</a>
		</h1>
	</div>
	<!--close-Header-part-->

	<!--top-Header-menu-->
	<div id="user-nav" class="navbar">
		<ul class="nav">
			<li><a href="#"> <i class="icon icon-user"></i> <span class="text">Welcome $!userName</span><b class="caret"></b>
			</a>
				<ul>
					<li><a href="javascript:void(0)" onclick="upContent('content', '/home', this);"> <i class="icon-user"></i>
							我的主页
					</a></li>
					<li><a href="javascript:void(0)" onclick="upContent('content', '/task', this);"> <i class="icon-check"></i>
							我的任务
					</a></li>
					<li><a href="javascript:void(0)" onclick="upContent('content', '/logout', this);"> <i class="icon-key"></i>
							退出
					</a></li>
				</ul></li>
			<li><a href="#"> <i class="icon icon-envelope"></i> <span class="text">消息</span> <span
					class="label label-important">5</span> <b class="caret"></b>
			</a>
				<ul>
					<li><a href="javascript:void(0)" onclick="upContent('content', '/message/news', this);"> <i
							class="icon-plus"></i> 新消息
					</a></li>
					<li><a href="javascript:void(0)" onclick="upContent('content', '/message/inbox', this);"> <i
							class="icon-envelope"></i> 收件箱
					</a></li>
					<li><a href="javascript:void(0)" onclick="upContent('content', '/message/outbox', this);"> <i
							class="icon-arrow-up"></i> 发件箱
					</a></li>
					<li><a href="javascript:void(0)" onclick="upContent('content', '/message/recycle', this);"> <i
							class="icon-trash"></i> 回收站
					</a></li>
				</ul></li>
			<li class=""><a href="javascript:void(0)" onclick="upContent('content', '/setting', this);"> <i
					class="icon icon-cog"></i> <span class="text">设置</span>
			</a></li>
			<li class=""><a href="javascript:void(0)" onclick="upContent('content', '/lougou', this);"> <i
					class="icon icon-share-alt"></i> <span class="text">退出</span>
			</a></li>
		</ul>
	</div>
	<!--close-top-Header-menu-->
	<!--start-top-serch-->
	<div id="search">
		<input type="text" placeholder="Search here..." />
		<button type="submit" class="tip-bottom" title="搜索">
			<i class="icon-search icon-white"></i>
		</button>
	</div>
	<!--close-top-serch-->
	<!--sidebar-menu-->
	<div id="sidebar">
		<a href="#" class="visible-xs"> <i class="icon icon-home"></i> 配送员主页
		</a>
		<ul>
			<li class="active"><a href="/"> <i class="icon icon-home"></i> <span>代办事项</span>
			</a></li>
			<li><a href="javascript:void(0)" onclick="upContent('content', '/system/resource', this);"> <i
					class="icon icon-signal"></i> <span>资源管理</span>
			</a></li>
			<li><a href="javascript:void(0)" onclick="upContent('content', '/label/list', this);"> <i
					class="icon icon-inbox"></i> <span>标签管理</span>
			</a></li>
			<li class="submenu"><a href="#"> <i class="icon icon-th-list"></i> <span>后台管理</span> <span
					class="label label-important">3</span>
			</a>
				<ul>
					<li><a href="javascript:void(0)" onclick="upContent('content', '/system/resource', this);"> <i
							class="icon icon-tint"></i> <span>资源管理</span>
					</a></li>
					<li><a href="javascript:void(0)" onclick="upContent('content', '/system/user', this);"> <i
							class="icon icon-tint"></i> <span>用户管理</span>
					</a></li>
					<li><a href="javascript:void(0)" onclick="upContent('content', '/system/role');"> <i
							class="icon icon-pencil"></i> <span>角色管理</span>
					</a></li>
					<li><a href="javascript:void(0)" onclick="upContent('content', '/system/unit');"> <i
							class="icon icon-file"></i> <span>组织机构管理</span>
					</a></li>
				</ul></li>

			<li class="content"><span>Monthly Bandwidth Transfer</span>
				<div class="progress progress-mini progress-danger active progress-striped">
					<div style="width: 77%;" class="bar"></div>
				</div> <span class="percent">77%</span>
				<div class="stat">21419.94 / 14000 MB</div></li>
			<li class="content"><span>Disk Space Usage</span>
				<div class="progress progress-mini active progress-striped">
					<div style="width: 87%;" class="bar"></div>
				</div> <span class="percent">87%</span>
				<div class="stat">604.44 / 4000 MB</div></li>
		</ul>
	</div>
	<!--sidebar-menu-->

	<!--main-container-part-->
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
			<div id="breadcrumb">
				<a href="index.html" title="Go Home" class="tip-bottom"> <i class="icon-home"></i> Home
				</a> <a href="#">Form elements</a> <a href="#" class="current">Validation</a>
			</div>
		</div>
		<!--End-breadcrumbs-->

		<!--Action boxes-->
		<div class="container-fluid">
			<div class="quick-actions_homepage">
				<ul class="quick-actions">
					<li class="bg_lb"><a href="index.html"> <i class="icon-dashboard"></i> <span class="label label-important">20</span>
							代办事项
					</a></li>
					<li class="bg_lg span3"><a href="charts.html"> <i class="icon-signal"></i> 礼物管理
					</a></li>
					<li class="bg_ly"><a href="widgets.html"> <i class="icon-inbox"></i><span class="label label-success">101</span>
							标签管理
					</a></li>
					<li class="bg_lo"><a href="tables.html"> <i class="icon-th"></i> 缓存管理
					</a></li>
					<li class="bg_ls"><a href="grid.html"> <i class="icon-fullscreen"></i> 图片计算尺寸
					</a></li>
					<li class="bg_lo span3"><a href="form-common.html"> <i class="icon-th-list"></i> 数据处理
					</a></li>
					<li class="bg_ls"><a href="buttons.html"> <i class="icon-tint"></i> 备用管理
					</a></li>
					<li class="bg_lb"><a href="interface.html"> <i class="icon-pencil"></i>备用管理
					</a></li>
					<li class="bg_lg"><a href="calendar.html"> <i class="icon-calendar"></i> 备用管理
					</a></li>
					<li class="bg_lr"><a href="error404.html"> <i class="icon-info-sign"></i> 备用管理
					</a></li>

				</ul>
			</div>
			<!--End-Action boxes-->

			<!-- Latest Posts -->
			<div class="row-fluid">
				<div class="span6">
					<div class="widget-box">
						<div class="widget-title bg_ly" data-toggle="collapse" href="#collapseG2">
							<span class="icon"><i class="icon-chevron-down"></i></span>
							<h5>Latest Posts</h5>
						</div>
						<div class="widget-content nopadding collapse in" id="collapseG2">
							<ul class="recent-posts">
								<li>
									<div class="user-thumb">
										<img width="40" height="40" alt="User" src="${ctx}/static/wsun/img/demo/av1.jpg">
									</div>
									<div class="article-post">
										<span class="user-info"> By: john Deo / Date: 2 Aug 2012 / Time:09:27 AM </span>
										<p>
											<a href="#">This is a much longer one that will go on for a few lines.It has multiple paragraphs and is
												full of waffle to pad out the comment.</a>
										</p>
									</div>
								</li>
								<li>
									<div class="user-thumb">
										<img width="40" height="40" alt="User" src="${ctx}/static/wsun/img/demo/av2.jpg">
									</div>
									<div class="article-post">
										<span class="user-info"> By: john Deo / Date: 2 Aug 2012 / Time:09:27 AM </span>
										<p>
											<a href="#">This is a much longer one that will go on for a few lines.It has multiple paragraphs and is
												full of waffle to pad out the comment.</a>
										</p>
									</div>
								</li>
								<li>
									<div class="user-thumb">
										<img width="40" height="40" alt="User" src="${ctx}/static/wsun/img/demo/av4.jpg">
									</div>
									<div class="article-post">
										<span class="user-info"> By: john Deo / Date: 2 Aug 2012 / Time:09:27 AM </span>
										<p>
											<a href="#">This is a much longer one that will go on for a few lines.Itaffle to pad out the comment.</a>
										</p>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--end-main-container-part-->

	<!--Footer-part-->

	<div class="row-fluid">
		<div id="footer" class="span12">
			2014 &copy; Delivery Man. Brought to you by <a href="http://www.jd.com">JD.COM</a>
		</div>
	</div>

	<!--end-Footer-part-->

	<!--
	<script src="${ctx}/static/matrix/js/excanvas.min.js"></script> 
    <script src="${ctx}/static/matrix/js/jquery.flot.min.js"></script> 
    <script src="${ctx}/static/matrix/js/jquery.flot.resize.min.js"></script> 
    <script src="${ctx}/static/matrix/js/jquery.peity.min.js"></script> 
    <script src="${ctx}/static/matrix/js/fullcalendar.min.js"></script> 
    <script src="${ctx}/static/matrix/js/matrix.dashboard.js"></script> 
    <script src="${ctx}/static/matrix/js/jquery.gritter.min.js"></script> 
    <script src="${ctx}/static/matrix/js/matrix.interface.js"></script> 
    <script src="${ctx}/static/matrix/js/matrix.chat.js"></script> 
    <script src="${ctx}/static/matrix/js/jquery.validate.js"></script> 
    <script src="${ctx}/static/matrix/js/matrix.form_validation.js"></script> 
    <script src="${ctx}/static/matrix/js/jquery.wizard.js"></script> 
    <script src="${ctx}/static/matrix/js/jquery.uniform.js"></script> 
    <script src="${ctx}/static/matrix/js/select2.min.js"></script> 
    <script src="${ctx}/static/matrix/js/matrix.popover.js"></script> 
    <script src="${ctx}/static/matrix/js/matrix.tables.js"></script> 
	-->

	<script>
		function upContent(contentId, url, target, _paramData, _callbackFun) {
			if (!contentId) {
				alert("请输入容器ID！");
				return false;
			}
			if (!document.getElementById(contentId)) {
				alert("传递的容器ID在页面中无法找到！");
				return false;
			}
			if (!url) {

				alert("请传递需要加载的URL！");
				return false;
			}
			if (url.indexOf("?") != -1) {
				url += "&v=" + new Date().getTime();
			} else {
				url += "?v=" + new Date().getTime();
			}
			$("#" + contentId).load(url, _paramData || null, function(data) {
				$('#sidebar li.active').removeClass('active');
				$(target).parent().addClass('active');
				try {
					if (_callbackFun && typeof (_callbackFun) == "function") {
						_callbackFun(data);
					}
				} catch (e) {
					alert("调用回调方法异常！");
				}
			});
		}
	</script>
</body>
</html>
