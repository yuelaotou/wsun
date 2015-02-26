<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
	$(function() {
		$('#contentTable').dataTable({
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "${ctx}/system/unit/list",
			"aoColumns": [
			    { "mData": "name" },
			    { "mData": "code" },
			    { "mData": "description" },
			    { "mData": "description" },
			    { "mData": "description" },
			    { "mData": "description" },
			    { "mData": "parentId" },
			    { "mData": "code" }
			]
		});
	});
</script>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form class="form-inline">
				<fieldset>
					<legend>机构查询</legend>
					<div class="row-fluid">
						<div class="span4">
							<div class="form-group">
								<label for="name">机构名称：</label> <input type="text" class="form-control" id="name">
							</div>
						</div>
						<div class="span4">
							<div class="form-group">
								<label for="code">机构编码：</label> <input type="text" class="form-control" id="code">
							</div>
						</div>
						<div class="span4">
							<div class="form-group">
								<label for="name">机构名称：</label> <input type="text" class="form-control" id="name">
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="offset10">
							<div class="form-group">
								<button type="submit" class="btn btn-primary">查询</button>
							</div>
						</div>
					</div>
				</fieldset>
			</form>

			<table class="table table-striped table-bordered table-hover table-responsive" id="contentTable">
				<thead>
					<tr>
						<th>机构名称</th>
						<th>机构编码</th>
						<th>机构描述</th>
						<th>机构描述</th>
						<th>机构描述</th>
						<th>机构描述</th>
						<th>父机构ID</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>
