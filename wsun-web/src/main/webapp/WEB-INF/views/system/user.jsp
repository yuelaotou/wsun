<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
	$(function() {
		$('#contentTable').dataTable({
			"iDisplayLength" : 1,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "${ctx}/system/user/list",
			"aoColumns": [
			    { "mData": "name" },
			    { "mData": "loginName" },
			    { "mData": "email" },
			    { "mData": "statusName" },
			    { "mData": "sexName" },
			    { "mData": "mobile", "bSortable": false },
			    { "mData": "createDate" },
			    { "mData": "roleNames", "bSortable": false },
			    { "mData": "id", "bSortable": false,
			    	"mRender": function ( data, type, full ) {
						var str = '';
						str += '<a class="btn btn-success btn-small" href="${ctx}/system/user/view/'+data+'"> <i class="icon-zoom-in icon-white"></i> 查看</a>';
						str += '&nbsp;';
			    		<shiro:hasPermission name="user:button:update">
							str += '<a class="btn btn-info btn-small" href="${ctx}/system/user/update/'+data+'"> <i class="icon-edit icon-white"></i>编辑</a>';
							str += '&nbsp;';
			    		</shiro:hasPermission>
			    		<shiro:hasPermission name="user:button:delete">
							str += '<a class="btn btn-danger btn-small" onclick="return confirm();" href="${ctx}/system/user/delete/'+data+'"> <i class="icon-trash icon-white"></i> 删除</a>';
			    		</shiro:hasPermission>
			    		return str;
		      		}
			    }
			]
		});
	});
	function confirm(){
		bootbox.confirm("Are you sure?", function(result) {
		  alert("Confirm result: "+result);
		  return false;
		}); 
	}
</script>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form class="form-inline" action="${ctx}/system/user/list">
				<fieldset>
					<legend>用户查询</legend>
					<div class="row-fluid">
						<div class="span4">
							<div class="form-group">
								<label for="loginName">登录名：</label> <input type="text" class="form-control" name="loginName">
							</div>
						</div>
						<div class="span4">
							<div class="form-group">
								<label for="name">用户名：</label> <input type="text" class="form-control" name="name">
							</div>
						</div>
						<div class="span4">
							<div class="form-group">
								<label for="roleCode">角色编码：</label> <input type="text" class="form-control" name="roleCode">
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span4">
							<div class="form-group">
								<label for="status">用户状态：</label>
								<select id="type" name="status">
									<option value=""></option>
									<c:forEach items="${statusList}" var="status">
										<option value="${status.key}">${status.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="span8">
							<div class="form-group">
								<button type="submit" class="btn btn-primary">查询</button>
								<button class="btn btn-primary" data-toggle="modal" data-target="#myModal">新增</button>
							</div>
						</div>
					</div>
				</fieldset>
			</form>

			<table class="table table-striped table-bordered table-hover table-responsive" id="contentTable">
				<thead>
					<tr>
						<th>用户名</th>
						<th>登录名</th>
						<th>邮件</th>
						<th>状态</th>
						<th>性别</th>
						<th>移动电话</th>
						<th>创建日期</th>
						<th>拥有角色</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      	<form class="form-horizontal" role="form">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">新增</h4>
      </div>
      <div class="modal-body">
		  <div class="form-group">
		    <label for="loginName" class="col-sm-2 control-label">登录名：</label>
		    <div class="col-sm-4">
		      <input type="text" id="loginName" name="loginName" class="form-control" placeholder="请输入登录名">
		    </div>
		    <label for="name" class="col-sm-2 control-label">用户名：</label>
		    <div class="col-sm-4">
		      <input type="text" id="name" name="name" class="form-control" placeholder="请输入用户名">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="password" class="col-sm-2 control-label">密码：</label>
		    <div class="col-sm-4">
		      <input type="password" id="password" name="password" class="form-control" placeholder="请输入密码">
		    </div>
		    <label for="email" class="col-sm-2 control-label">邮件：</label>
		    <div class="col-sm-4">
		      <input type="email" id="email" name="email" class="form-control" placeholder="请输入邮件地址">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="telephone" class="col-sm-2 control-label">电话：</label>
		    <div class="col-sm-4">
		      <input type="text" id="telephone" name="telephone" class="form-control" placeholder="请输入联系电话">
		    </div>
		    <label for="mobile" class="col-sm-2 control-label">手机：</label>
		    <div class="col-sm-4">
		      <input type="text" id="mobile" name="mobile" class="form-control" placeholder="请输入手机号码">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="sex" class="col-sm-2 control-label">性别：</label>
		    <div class="col-sm-4">
		      <select class="form-control" id="sex" name="sex" >
				<c:forEach items="${sexList}" var="sex">
					<option value="${sex.key}">${sex.value}</option>
				</c:forEach>
		      </select>
		    </div>
		    <label for="expireDate" class="col-sm-2 control-label">过期时间：</label>
		    <div class="col-sm-4">
		      <div class="input-append date form_datetime" id="expireDate" name="expireDate" data-date="2013-02-21T15:25:00Z">
			    <input size="16" type="text" value="" readonly>
			    <span class="add-on"><i class="icon-remove"></i></span>
			    <span class="add-on"><i class="icon-calendar"></i></span>
			</div>
		    </div>
		  </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" onclick="confirm()">保存</button>
      </div>
		</form>
    </div>
  </div>
</div>
<script type="text/javascript">
    $(".form_datetime").datetimepicker({
        format: "yyyy-mm-dd hh:ii:ss",
        autoclose: true,
        todayBtn: true,
        startDate: "2013-02-14 10:00:00",
        minuteStep: 10
    });
</script>     
<!--
		<table class="table table-hover table-condensed">
			<tr>
				<th>
					<span style="color:red">*</span>性别：
				</th>
				<td>
					<select id="type" name="sex" class="easyui-combobox select18" data-options="required:true, editable: false">
						<c:forEach items="${sexList}" var="sex">
							<option value="${sex.key}">${sex.value}</option>
						</c:forEach>
					</select>
				</td>
				<th>
					<span style="color:red">*</span>过期时间：
				</th>
				<td>
					<input id="expireDate" name="expireDate" type="text" class="easyui-my97" data-options="required:true">
				</td>
			</tr>
			<tr>
				<th>
					所属机构：
				</th>
				<td>
					<select id="unit" name="unit.id" class="easyui-validatebox select18"></select>
				</td>
				<th>
					拥有角色：
				</th>
				<td>
					<select id="roles" name="roles" class="easyui-validatebox select18"></select>
				</td>
			</tr>
			<tr>
				<th>
					用户描述：
				</th>
				<td>
					<textarea name="description" placeholder="请输入用户描述" class="easyui-validatebox" data-options="validType:['length[0,200]']"></textarea>
				</td>
				<th>
					用户ID：
				</th>
				<td>
					<input name="id" type="text" class="textField18" readonly="readonly">
				</td>
			</tr>
		</table> -->