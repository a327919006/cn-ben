
<script type="text/javascript">
    /**
     * 查询
     */
    function searchNotifyRecord() {
        $('#notify_record_datagrid').datagrid('load', serializeObject($('#notify_record_searchform')));
    }

    /**
     * 清空
     */
    function clearSearchNotifyRecordForm() {
        $('#notify_record_searchform').form('clear'); // 清空数据
        $('#notify_record_datagrid').datagrid('load', {}); // 重新加载
    }
</script>

<form id="notify_record_searchform" class="field-form" style="margin-top:5px;margin-bottom: 0px;">
    <table>
        <tr>
            <td>业务ID</td>
            <td><input name="businessId" class="easyui-validatebox"/></td>
            <td>业务名称</td>
            <td><input name="consumerQueue" class="easyui-validatebox"/></td>
            <td>消息状态</td>
            <td>
                <select name="notifyStatus" class="easyui-combobox" style="width: 165px;"
                        data-options="panelHeight:'auto', editable:false" >
                    <option value="" selected="selected">全部</option>
                    <option value="0">待通知</option>
                    <option value="1">通知成功</option>
                    <option value="2">业务方处理失败</option>
                    <option value="3">Http请求异常</option>
                    <option value="4">通知失败</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>创建时间</td>
            <td colspan="3">
                <input name="createStartTime" class="easyui-datetimebox"/>
                至
                <input name="createEndTime" class="easyui-datetimebox"/>
            </td>
        </tr>
        <tr>
            <td style="text-align:right" colspan="3">
                <a onclick="searchNotifyRecord()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
            </td>
            <td style="text-align:left" colspan="3">
                <a onclick="clearSearchNotifyRecordForm()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">重置</a>
            </td>
        </tr>
    </table>
</form>