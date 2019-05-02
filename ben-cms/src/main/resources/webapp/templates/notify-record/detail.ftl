
<script type="text/javascript">
    $(function () {
        $("#notify_record_detail_form input").attr("readonly", "readonly");
        $("#notify_record_detail_form select").attr("readonly", "readonly");
        $("#notify_record_detail_form textarea").attr("readonly", "readonly");
    });

</script>

<form id="notify_record_detail_form" method="post" class="field-form">
    <table>
        <tr>
            <td>通知记录ID</td>
            <td><input name="id" class="easyui-validatebox" style="width: 250px;"/></td>
            <td>请求地址</td>
            <td><input name="notifyUrl" class="easyui-validatebox" style="width: 250px;"/></td>
        </tr>
        <tr>
            <td>请求方式</td>
            <td><input name="notifyMethodName" class="easyui-validatebox" style="width: 250px;"/></td>
            <td>请求头</td>
            <td><input name="notifyHeader" class="easyui-validatebox" style="width: 250px;"/></td>
        </tr>
        <tr>
            <td>参数类型</td>
            <td><input name="notifyParamTypeName" class="easyui-validatebox" style="width: 250px;"/></td>
            <td>超时时长</td>
            <td><input name="notifyTimeout" class="easyui-validatebox" style="width: 250px;"/></td>
        </tr>
        <tr>
            <td>成功标识</td>
            <td><input name="successFlag" class="easyui-validatebox" style="width: 250px;"/></td>
            <td>已通知次数</td>
            <td><input name="notifyTimes" class="easyui-validatebox" style="width: 250px;"/></td>
        </tr>
        <tr>
            <td>通知状态</td>
            <td><input name="notifyStatusName" class="easyui-validatebox" style="width: 250px;"/></td>
            <td>业务名称</td>
            <td><input name="businessName" class="easyui-validatebox" style="width: 250px;"/></td>
        </tr>
        <tr>
            <td>业务ID</td>
            <td><input name="businessId" class="easyui-validatebox" style="width: 250px;"/></td>
            <td>创建时间</td>
            <td><input name="createTime" class="easyui-validatebox" style="width: 250px;"/></td>
        </tr>
        <tr>
            <td>更新时间</td>
            <td><input name="updateTime" class="easyui-validatebox" style="width: 250px;"/></td>
        </tr>
        <tr>
            <td>请求参数</td>
            <td colspan="3">
                <textarea name="notifyParam" class="easyui-textbox" data-options="multiline:true"
                          style="width: 700px; height:250px; resize:none"></textarea>
            </td>
        </tr>
    </table>
</form>
