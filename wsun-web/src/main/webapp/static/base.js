/**
 * 刷新表格
 * @param tableId
 * @param url
 */
function refreshTable(tableId) {
    var table = $("#" + tableId).dataTable();
    start = table.fnSettings()._iDisplayStart;
    total = table.fnSettings().fnRecordsDisplay();
    table.fnDraw();
}
/**
 * 提示框，在1秒钟后消失
 * @param message
 */
function tip(message){
    var box = bootbox.alert(message);
    setTimeout(function() {
        // be careful not to call box.hide() here, which will invoke jQuery's hide method
        box.modal('hide');
    }, 1000);
}
