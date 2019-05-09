<script type="text/javascript">
    $(function () {//初始化
        $('#notify_log_datagrid').datagrid({
            border: false,
            fit: true,
            striped: true,
            url: 'notify/log/page',
            method: 'get',
            pagination: true,//显示分页
            fitColumns: true,//自动计算列的宽度
            pageSize: 10,//每页显示几条
            pageList: [10, 20, 30, 40, 50],//页面设置几条数据
            //rownumbers:true,不要设置，否则在IE6速度放慢
            //checkOnSelect : false,
            //selectOnCheck : false,
            singleSelect: true,
            onDblClickRow: function (rowIndex, rowData) {
                showNotifyLogDetail(rowData);
            },
            frozenColumns: [[ //冻结列
                {
                    field: 'ck',
                    checkbox: true
                }
            ]],
            columns: [[
                {
                    field: 'id',
                    title: '通知日志ID',
                    width: 15
                },
                {
                    field: 'notifyId',
                    title: '通知记录ID',
                    width: 50
                },
                {
                    field: 'businessName',
                    title: '业务名称',
                    width: 20
                },
                {
                    field: 'businessId',
                    title: '业务ID',
                    width: 30
                },
                {
                    field: 'httpStatus',
                    title: 'Http状态',
                    width: 15,
                    formatter: function (value, row, index) {
                        if(row.httpStatus != null){
                            if (row.httpStatus >= 200 && row.httpStatus < 300) {
                                return "<span style='color: dodgerblue'>" + row.httpStatus + "</span>";
                            } else {
                                return "<span style='color: red'>" + row.httpStatus + "</span>";
                            }
                        }
                    }
                },
                {
                    field: 'httpResponse',
                    title: 'Http请求响应',
                    width: 50
                },
                {
                    field: 'httpException',
                    title: 'Http请求异常',
                    width: 30,
                    formatter: function (value, row, index) {
                        if(row.httpException != null){
                            return "<span style='color: red'>" + row.httpException + "</span>";
                        }
                    }
                },
                {
                    field: 'createTime',
                    title: '创建时间',
                    width: 30
                }
            ]],
            toolbar: [
                {
                    text: '<span style="color: red"><strong>查看详情（双击）</strong></span>',
                    handler: function () {
                        var checkedRows = $('#notify_log_datagrid').datagrid('getChecked');
                        if (checkedRows.length <= 0) {
                            $.messager.alert('错误提示', '请选择要查看的记录', 'error');
                        } else {
                            showNotifyLogDetail(checkedRows[0]);
                        }
                    }
                }, '-']
        });
    });


    /**
     * 详情页
     */
    function showNotifyLogDetail(rowData) {
        var dig = $('<div  />').dialog({
            href: 'page/notify-log/detail',
            width: 950,
            height: 600,
            modal: true,
            title: '详情',
            buttons: [{
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {
                    //关闭窗口
                    dig.dialog('close');
                }
            }],
            onClose: function () {
                //关闭后回收内存
                $(this).dialog('destroy');
            },
            onLoad: function () {
                //必须在窗体打开之前加载数据
                getNotifyLogDetail(rowData);
            }
        });
    }

    /**
     * 获取详情
     */
    function getNotifyLogDetail(rowData) {
        $.ajax({
            type: "GET",
            cache: false,
            dataType: "json",
            timeout: 15000,
            url: "notify/record/" + rowData.notifyId,
            success: function (retObj, textStatus, XMLHttpRequest) {
                if (0 === retObj.code) {
                    $('#notify_log_detail_form').form('load', retObj.data);
                    $('#notify_log_detail_form').form('load', rowData);
                } else {
                    $.messager.alert('错误提示', retObj.msg, 'error');
                    $('#notify_log_detail_form').form('load', rowData);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    }

</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',title:'查询条件',border:false" style="height: 130px;">
         <#include "search.ftl"/>
    </div>
    <div data-options="region:'center',border:false">
        <div id="notify_log_datagrid"></div>
    </div>
</div>