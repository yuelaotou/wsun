<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
	.row-top td{border-top:1px solid red; background:#fff;}
	.row-bottom td{border-bottom:1px solid red; background:#fff;}
	.row-append td{border:0; background:#FBEC88;}
</style>
<script type="text/javascript">
	var treeGrid;

	$(function() {

		//加载表格
		loadGrid();
		//菜单图标下拉框设置
		$('#icon').combobox({
			data : $.iconData,
			editable: false,
			formatter : function(v) {
				return $.formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
			}
		});
		//上级菜单下拉框设置
		$('#parentId').combotree({
			url : '${pageContext.request.contextPath}/system/resource/list',
			idField : 'id',
			textField : 'name',
			parentField : 'parentId',
			lines : true,
			onLoadSuccess : function() {
			}
		});
	});
	
	function loadGrid(){
		treeGrid = $('#resource_treegrid').treegrid({
			url : '${pageContext.request.contextPath}/system/resource/list',
			idField : 'id',
			treeField : 'name',
			parentField : 'parentId',
			fitColumns : true,
	        rownumbers: true,
			animate : true,
			columns : [ [ 
				{field : 'id', title : '编号', width : 100, hidden : true}, 
				{field : 'name', title : '资源名称', width : 300}, 
				{field : 'code', title : '资源编码', width : 300}, 
				{field : 'type', title : '资源类型', width : 100, hidden : true}, 
				{field : 'typeName', title : '资源类型', width : 100,
					styler: function (value, row, index) {
						if(row.type == 'menu'){
					    	return 'font-weight:bold; color:red;';
						}
					}
				},
				{field : 'url', title : '资源路径', width : 300},
				{field : 'orderId', title : '排序', width : 100}, 
				{field : 'parentId', title : '父资源ID', width : 100, hidden : true},
				{field : 'operator', title : '操作', width : 150,
					formatter : function(value, row, index) {
						var str = '';
					
						if(row.type == 'menu'){
							str += $.formatString('<img onclick="add(\'{0}\');" src="{1}" title="新增"/>', row.id, '${pageContext.request.contextPath}/static/style/images/extjs_icons/pencil_add.png');
							str += '&nbsp;';
						}
						str += $.formatString('<img onclick="edit(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${pageContext.request.contextPath}/static/style/images/extjs_icons/pencil.png');
						str += '&nbsp;';
						str += $.formatString('<img onclick="del(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/static/style/images/extjs_icons/cancel.png');
						return str;
					}
				}
	        ] ],
			toolbar : '#toolbar',
			onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.id);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			//行的样式。作为菜单的行，给用户明确的提示，效果随时调整。
// 			rowStyler: function(row, index){
// 				if (row.type == 'menu'){
// 					return 'background-color:#6293BB;color:#fff;';
// 				}
// 			},
			onLoadSuccess : function(data) {
				enableDnd($(this));
			}
		});
	}

	function add(id){
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			$('#parentId').combotree('setValue', node.id);
		}
		$('#dialog').dialog({title:'新增', iconCls:'icon-save', closed:false}, 'open');
	}
	
	function edit(id){
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			//回显要修改的Object
			jQuery.ajax({
		        type: 'POST',
		        url: '${pageContext.request.contextPath}/system/resource/edit',
		        cache: false,
		        data: {
		            id: node.id
		        },
		        success: function (data) {
		        	$('#form').form('setValues',data);
		            // 显示修改界面
		    		$('#dialog').dialog({title: '编辑', iconCls: 'icon-edit', closed:false}, 'open');
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            $.jBox.tip(jqXHR.responseText, 'error');
		        }
		    });
		}
	}
	
	function save(){
		var isValid = $('#form').form('validate');
		if(isValid){
	        $.jBox.tip("正在处理，请稍候。。。", 'loading');
			var data = $('#form').form('serialize');
			jQuery.ajax({
                type : 'POST',
                url : '${pageContext.request.contextPath}/system/resource/save',
                cache : false,
                data: data,
                success : function(data){
					$.jBox.tip("保存成功", 'success');
					//关闭Dialog弹出框
					closeDialog();
					$('#parentId').combotree('reload');
					treeGrid.treegrid('reload');
                },
                error : function(jqXHR, textStatus, errorThrown){
					$.jBox.tip(jqXHR.responseText, 'error');
                }
            });
		}
	}
	
	function del(id){
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {

			//只能删除子节点
			var children = treeGrid.treegrid('getChildren',node.id);
			if(children.length!=0){
				$.jBox.tip("["+node.name+"]" + '仍有下属资源，请先删除下属资源', 'error');
				return false;
			}
			
			var deleteInfo = function(v,h,f){
				if (v == true) {
					$.jBox.tip("正在处理，请稍候。。。", 'loading');
					jQuery.ajax({
				        type: 'POST',
				        url: '${pageContext.request.contextPath}/system/resource/delete',
				        cache: false,
				        data: {
				            id: node.id
				        },
				        success: function (data) {
							$.jBox.tip("删除成功", 'success');
							$('#parentId').combotree('reload');
							treeGrid.treegrid('reload');
				        },
				        error: function (jqXHR, textStatus, errorThrown) {
				            $.jBox.tip(jqXHR.responseText, 'error');
				        }
				    });
				}
				return true;
			};
			$.jBox.confirm("是否删除 ["+node.name+"] 资源?", "提示", deleteInfo, { buttons: { '确定': true, '取消': false} });
		}
	}
	
	function redo() {
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			treeGrid.treegrid('expandAll', node.id);
		} else {
			treeGrid.treegrid('expandAll');
		}
	}

	function undo() {
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			treeGrid.treegrid('collapseAll', node.id);
		} else {
			treeGrid.treegrid('collapseAll');
		}
	}
	function closeDialog(){
		$('#form').form('reset');
		$('#dialog').dialog('close');
	}
	
	function enableDnd(t) {
	    var nodes = t.treegrid('getPanel').find('tr[node-id]');
	    nodes.find('span.tree-hit').bind('mousedown.treegrid', function() {
	        return false;
	    });
	    nodes.draggable({
	        disabled: false,
	        revert: true,
	        cursor: 'pointer',
	        proxy: function(source) {
	            var p = $('<div class="tree-node-proxy tree-dnd-no"></div>').appendTo('body');
	            p.html($(source).find('.tree-title').html());
	            p.hide();
	            return p;
	        },
	        deltaX: 15,
	        deltaY: 15,
	        onBeforeDrag: function() {
	            $(this).next('tr.treegrid-tr-tree').find('tr[node-id]').droppable({ accept: 'no-accept' });
	        },
	        onStartDrag: function() {
	            $(this).draggable('proxy').css({
	                left: -10000,
	                top: -10000
	            });
	        },
	        onDrag: function(e) {
	        	if(this.pageY && this.pageY != e.pageY){
	            	$(this).draggable('proxy').show();
	        	}
	            this.pageY = e.pageY;
	        },
	        onStopDrag: function() {
	            $(this).next('tr.treegrid-tr-tree').find('tr[node-id]').droppable({ accept: 'tr[node-id]' });
	        }
	    }).droppable({
	        accept: 'tr[node-id]',
	        onDragOver: function(e, source) {
	            var pageY = source.pageY;
	            var top = $(this).offset().top;
	            var bottom = top + $(this).outerHeight();
	            $(source).draggable('proxy').removeClass('tree-dnd-no').addClass('tree-dnd-yes');
	            $(this).removeClass('row-append row-top row-bottom');
	            if (pageY > top + (bottom - top) / 2) {
	                if (bottom - pageY < 5) {
	                    $(this).addClass('row-bottom');
	                } else {
	                    $(this).addClass('row-append');
	                }
	            } else {
	                if (pageY - top < 5) {
	                    $(this).addClass('row-top');
	                } else {
	                    $(this).addClass('row-append');
	                }
	            }
	        },
	        onDragLeave: function(e, source) {
	            $(source).draggable('proxy').removeClass('tree-dnd-yes').addClass('tree-dnd-no');
	            $(this).removeClass('row-append row-top row-bottom');
	        },
	        onDrop: function(e, source, point) {
	            var action, point;
	            if ($(this).hasClass('row-append')) {
	                action = 'append';
	                point = '';
	            } else {
	                action = 'insert';
	                point = $(this).hasClass('row-top') ? 'top' : 'bottom';
	            }
	            $(this).removeClass('row-append row-top row-bottom');

	            var targetId = $(e.target).attr('node-id');
	            var sourceId = $(source).attr('node-id');
	            //console.info(targetId + ", " +sourceId + ", " +action + ", " +point);
	           
	            jQuery.ajax({
	                type : 'POST',
	                url : '${pageContext.request.contextPath}/system/resource/update',
	                cache : false,
	                data: {targetId:targetId, sourceId:sourceId, action:action, point:point},
	                success : function(data){
						treeGrid.treegrid('reload');
	                },
	                error : function(jqXHR, textStatus, errorThrown){
						$.jBox.tip(jqXHR.responseText, 'error');
	                }
	            });
	            treeGrid.treegrid('reload');
	        }
	    });
	}
	
</script>

<!-- DataGrid begin -->
<table id="resource_treegrid"></table>
<div id="toolbar" style="display: none;">
	<a onclick="add();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a>
	<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a>
	<a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a>
	<a onclick="treeGrid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
</div>
<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
	<div onclick="add();" data-options="iconCls:'pencil_add'">新增</div>
	<div onclick="edit();" data-options="iconCls:'pencil'">编辑</div>
	<div onclick="del();" data-options="iconCls:'pencil_delete'">删除</div>
</div>
<!-- DataGrid end -->

<!-- Dialog begin -->
<div id="dialog" class="easyui-dialog" title="新增" style="width:500px;height:220px;padding:10px;" 
            data-options="iconCls:'icon-save', closed:true, modal:true, buttons:'#dialog-buttons'">
	<form id="form" method="post">
		<table class="table table-hover table-condensed">
			<tr>
				<th>
					<span style="color:red">*</span>资源编码：
				</th>
				<td>
					<input name="code" type="text" placeholder="请输入资源编码" class="easyui-validatebox textField18" data-options="required:true, validType:'resourceType[\'type\']'">
				</td>
				<th>
					<span style="color:red">*</span>资源名称：
				</th>
				<td>
					<input name="name" type="text" placeholder="请输入资源名称" class="easyui-validatebox textField18" data-options="required:true">
				</td>
			</tr>
			<tr>
				<th>
					<span style="color:red">*</span>资源类型：
				</th>
				<td>
					<select id="type" name="type" class="easyui-validatebox easyui-combobox select18" data-options="required:true, editable: false">
						<c:forEach items="${resourceTypeList}" var="resourceType">
							<option value="${resourceType.key}">${resourceType.value}</option>
						</c:forEach>
					</select>
				</td>
				<th>
					资源路径：
				</th>
				<td>
					<input name="url" type="text" placeholder="请输入资源路径" class="easyui-validatebox textField18">
				</td>
			</tr>
			<tr>
				<th>
					<span style="color:red">*</span>排序：
				</th>
				<td>
					<input name="orderId" value="1" class="easyui-numberspinner textField18" required="required" data-options="editable:false,min:1">
				</td>
				<th>
					上级资源：
				</th>
				<td>
					<select id="parentId" name="parentId" class="easyui-validatebox select18"></select>
					<img src="${pageContext.request.contextPath}/static/style/images/extjs_icons/cut_red.png" onclick="$('#parentId').combotree('clear');" />
				</td>
			</tr>
			<tr>
				<th>
					<span style="color:red">*</span>菜单图标：
				</th>
				<td>
					<input id="icon" name="icon" class="select18"/>
				</td>
				<th>
					资源ID：
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
