<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		//加载表格
		loadGrid();

		$('#resources').combotree({
			url : '${pageContext.request.contextPath}/system/resource/list',
			idField : 'id',
			textField : 'name',
			parentField : 'parentId',
			lines : true,
			multiple : true,
			onLoadSuccess : function() {
			}
		});
	});
	
	function loadGrid(){
		dataGrid = $('#role_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/system/role/list',
	        height: 338,
	        singleSelect: true,
	        fitColumns: true,
	        rownumbers: true,
	        pagination: true,
	        multiSort: true,
	        loadMsg: '数据加载中......',
	        columns: [[
                {field: 'id', title: '编号', align: 'center', width: 100, hidden: true},
                {field: 'code', title: '角色编码', align: 'center', width: 150, sortable:true},
                {field: 'name', title: '角色名称', align: 'center', width: 150, sortable:true},
                {field: 'description', title: '角色描述', width: 400},
                {field: 'resourceNames', title: '拥有资源', width: 600},
                {field: 'orderId', title: '排序', align: 'center', width: 100},
                {field: 'operator', title: '操作', align: 'center', width: 150,
                	formatter : function(value, row, index) {
    					var str = '';
   						str += $.formatString('<img onclick="add(\'{0}\');" src="{1}" title="新增"/>', row.id, '${pageContext.request.contextPath}/static/style/images/extjs_icons/pencil_add.png');
   						str += '&nbsp;';
    					str += $.formatString('<img onclick="edit(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${pageContext.request.contextPath}/static/style/images/extjs_icons/pencil.png');
    					str += '&nbsp;';
    					str += $.formatString('<img onclick="del(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/static/style/images/extjs_icons/cancel.png');
    					str += '&nbsp;';
						str += $.formatString('<img onclick="accredit(\'{0}\');" src="{1}" title="授权"/>', row.id, '${pageContext.request.contextPath}/static/style/images/extjs_icons/key.png');
    					return str;
    				}
                }
            ]],
			toolbar : '#toolbar',
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll').datagrid('uncheckAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			onLoadSuccess : function(data) {
			}
		});
		dataGrid.datagrid('doCellTip', {
	        onlyShowInterrupt:true,
	        position:'bottom',
	        tipStyler:{'backgroundColor':'#fff000', borderColor:'#ff0000', maxWidth:'250px', boxShadow:'1px 1px 3px #292929'}
	    });
	}
	function add(id){
		//目前ID没用，留存以后扩展用。 id是行的id
		$('#dialog').dialog({
			title:'新增', 
			iconCls:'icon-save', 
			closed:false, 
			onBeforeClose:function(){
				$('#form').form('disableValidation');
			}
		}, 'open');
	}

	function save(){
		var isValid = $('#form').form('validate');
		if(isValid){
	        $.jBox.tip("正在处理，请稍候。。。", 'loading');
			var data = $('#form').form('serialize');

			//处理半选中状态的资源
			var nodes = $('#resources').combotree('tree').tree('getChecked', 'indeterminate');
			var ids="";
			$.each(nodes, function() {  
				ids += ","+this.id;
			});
			if(ids != ""){
				data.resources += ids; 
			}
			
			jQuery.ajax({
                type : 'POST',
                url : '${pageContext.request.contextPath}/system/role/save',
                cache : false,
                data: data,
                success : function(data){
					$.jBox.tip("保存成功", 'success');
					closeDialog();
					dataGrid.datagrid('reload');
                },
                error : function(jqXHR, textStatus, errorThrown){
					$.jBox.tip(jqXHR.responseText, 'error');
                }
            });
		}
	}
	
	function edit(id){
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			if(rows == null || rows.length == 0){
	            $.jBox.tip('请先选择一行！', 'error');
	            return false;
			}
			id = rows[0].id;
		}
		//回显要修改的Role
		jQuery.ajax({
	        type: 'POST',
	        url: '${pageContext.request.contextPath}/system/role/edit',
	        cache: false,
	        data: {
	            id: id
	        },
	        success: function (data) {
	        	$('#form').form('setValues',data);
	        	//把资源设置上
				$('#resources').combotree('setValues', $.stringToList(data.resourceIds));
	            // 显示修改界面
	    		$('#dialog').dialog({title: '编辑', iconCls: 'icon-edit', closed:false}, 'open');
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            $.jBox.tip(jqXHR.responseText, 'error');
	        }
	    });
	}
	function del(id){
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			if(rows == null || rows.length == 0){
	            $.jBox.tip('请先选择一行！', 'error');
	            return false;
			}
			id = rows[0].id;
		}
		var deleteInfo = function(v,h,f){
			if (v == true) {
				$.jBox.tip("正在处理，请稍候。。。", 'loading');
				jQuery.ajax({
			        type: 'POST',
			        url: '${pageContext.request.contextPath}/system/role/delete',
			        cache: false,
			        data: {
			            id: id
			        },
			        success: function (data) {
						$.jBox.tip("删除成功", 'success');
						dataGrid.datagrid('reload');
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			            $.jBox.tip(jqXHR.responseText, 'error');
			        }
			    });
			}
			return true;
		};
		$.jBox.confirm("是否删除角色?", "提示", deleteInfo, { buttons: { '确定': true, '取消': false} });
	}
	function closeDialog(){
		$('#form').form('reset');
		$('#dialog').dialog('close');
	}
	function query(){
		dataGrid.datagrid('load', $('#searchForm').form('serialize'));
	}
	function reset(){
		$('#searchForm').form('reset');
	}
</script>

<div>
	<form id="searchForm">
		<fieldset>
		<legend>查询</legend>
			<table style="width:100%">
				<tbody>
					<tr>
						<td class="textR">角色编码：</td>
						<td class="textL">
							<input type="text" name="code" class="easyui-validatebox textField18" data-options="validType:'chinese'">
						</td>
						<td class="textR">角色名称：</td>
						<td class="textL">
							<input type="text" name="name" class="easyui-validatebox textField18" data-options="validType:'chinese'">
						</td>
						<td class="textR">资源编码：</td>
						<td class="textL">
							<input type="text" name="resourceCode" class="textField18">
						</td>
					</tr>
					<tr>
						<td class="textR" colspan="6">
	    					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="reset();">重置</a>
	    					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="query();">查询</a>
						</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</form>
</div>

<!-- DataGrid begin -->
<table id="role_datagrid"></table>
<div id="toolbar" style="display: none;">
	<a onclick="add();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a>
	<a onclick="dataGrid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
</div>
<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
	<div onclick="add();" data-options="iconCls:'pencil_add'">新增</div>
	<div onclick="edit();" data-options="iconCls:'pencil'">编辑</div>
	<div onclick="del();" data-options="iconCls:'pencil_delete'">删除</div>
</div>
<!-- DataGrid end -->

<!-- Dialog begin -->
<div id="dialog" class="easyui-dialog" title="新增" style="width:500px;height:200px;padding:10px;" 
            data-options="iconCls:'icon-save', closed:true, modal:true, buttons:'#dialog-buttons'">
	<form id="form" method="post">
		<table class="table table-hover table-condensed">
			<tr>
				<th>
					<span style="color:red">*</span>角色编码：
				</th>
				<td>
					<input name="code" type="text" placeholder="请输入角色编码" class="easyui-validatebox textField18" data-options="required:true, validType:'letter'">
				</td>
				<th>
					<span style="color:red">*</span>角色名称：
				</th>
				<td>
					<input name="name" type="text" placeholder="请输入角色名称" class="easyui-validatebox textField18" data-options="required:true, validType:'chinese'">
				</td>
			</tr>
			<tr>
				<th>
					拥有资源：
				</th>
				<td>
					<select id="resources" name="resources" class="easyui-validatebox select18"></select>
				</td>
				<th>
					<span style="color:red">*</span>排序：
				</th>
				<td>
					<input name="orderId" value="1" class="easyui-numberspinner textField18" required="required" data-options="editable:false,min:1">
				</td>
			</tr>
			<tr>
				<th>
					角色描述：
				</th>
				<td>
					<textarea name="description" placeholder="请输入角色描述" class="easyui-validatebox" data-options="validType:['length[0,200]']"></textarea>
				</td>
				<th>
					角色ID：
				</th>
				<td>
					<input name="id" type="text" class="textField18" readonly="readonly">
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="dialog-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="javascript:save();">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:closeDialog();">关闭</a>
</div>
<!-- Dialog end -->
