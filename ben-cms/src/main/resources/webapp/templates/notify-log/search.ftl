
<script type="text/javascript">
    /**
     * 查询
     */
    function searchNotifyLog() {
        $('#notify_log_datagrid').datagrid('load', serializeObject($('#notify_log_searchform')));
    }

    /**
     * 清空
     */
    function clearSearchNotifyLogForm() {
        $('#notify_log_searchform').form('clear'); // 清空数据
        $('#notify_log_datagrid').datagrid('load', {}); // 重新加载
    }
</script>

<form id="notify_log_searchform" class="field-form" style="margin-top:5px;margin-bottom: 0px;">
    <table>
        <tr>
            <td>通知记录ID</td>
            <td><input name="notifyId" class="easyui-validatebox" style="width: 255px;"/></td>
            <td>通知日志ID</td>
            <td><input name="id" class="easyui-validatebox"/></td>
            <td>业务ID</td>
            <td><input name="businessId" class="easyui-validatebox"/></td>
            <td>业务名称</td>
            <td><input name="businessName" class="easyui-validatebox"/></td>
        </tr>
        <tr>
            <td>创建时间</td>
            <td colspan="3">
                <input name="createStartTime" class="easyui-datetimebox"/>
                至
                <input name="createEndTime" class="easyui-datetimebox"/>
            </td>
            <td>Http状态</td>
            <td colspan="3"><input name="httpStatus" class="easyui-validatebox"/></td>
        </tr>
        <tr>
            <td style="text-align:right" colspan="3">
                <a onclick="searchNotifyLog()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
            </td>
            <td style="text-align:left" colspan="5">
                <a onclick="clearSearchNotifyLogForm()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">重置</a>
            </td>
        </tr>
    </table>
</form>