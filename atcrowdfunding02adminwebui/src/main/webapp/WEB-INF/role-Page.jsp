<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/include-head.jsp" %>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript" src="js/role.js" charset="utf-8"></script>
<body>
<%@include file="/WEB-INF/modal/addRole.jsp" %>
<%@include file="/WEB-INF/modal/editRole.jsp" %>
<%@include file="/WEB-INF/modal/deleteRole_comfirm.jsp" %>
<%@include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" method="post" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="inputStr" class="form-control has-success" name="inputStr" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" id="searchBtn" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" id="toDelete" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" id="toAddModal" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30" style="width: 50px">序号</th>
                                <th width="30"><input id="summaryBox" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="rolePageBody">

                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        //  为分页操作做准备初始化数据
        window.pageNum = 1;
        window.pageSize = 5;
        window.inputStr = "";

        // 2.调用执行分页的函数，显示分页效果
        generatePage();

        // 绑定"查询"按钮的点击事件
        $("#searchBtn").click(function () {

            // 个体window.inputStr赋值
            window.inputStr = $("#inputStr").val();

            // 调用分页函数 刷新页面
            generatePage();
        });
        
        // 点击新增 显示新增模态框
        $("#toAddModal").click(function () {
            $("#addModal_role").modal("show");
        });

        // 点击保存 保存角色
        $("#saveRole").click(function () {

            // 获取用户在文本框中输入的角色名称
            // #addModal_role表示找到整个模态框
            // 空格表示在后代元素中继续查找
            // [name=roleName]表示匹配name属性等于roleName的元素
            var roleName = $.trim($("#addModal_role [name = roleName]").val());
            $.ajax({
                "url":"role/saveRole.json",
                "type":"post",
                "data":{
                    "roleName":roleName
                },
                "dataType":"json",
                "success":function (response) {
                    var result = response.result;
                    if (result == "SUCCESS") {
                        layer.msg("保存成功!");
                        // 定位到最后一页 显示新增数据
                        window.pageNum = 99999999;

                        //重新加载分页数据
                        generatePage();
                    }
                    if (result == "FAILED") {
                        layer.msg("保存失败!"+response.message);
                    }
                },"error":function (response) {
                    error_layer(response);
                }
            });

            // 保存完后关闭模态窗
            $("#addModal_role").modal("hide");

            // 清除模态框的内容
            $("#addModal_role [name = roleName]").val("");
        });


        // 前往更新页面模态框
        // ①首先找到所有“动态生成”的元素所附着的“静态”元素
        // ②on()函数的第一个参数是事件类型
        // ③on()函数的第二个参数是找到真正要绑定事件的元素的选择器
        // ③on()函数的第三个参数是事件的响应函数
        $("#rolePageBody").on("click", ".pencilBtn", function () {
            $("#editModal_role").modal("show");
            // 获取表格中当前行的角色名称
            var roleName = $(this).parent().prev().text();

            // 为了让执行更新的按钮能够获取到roleId的值，把它放在全局变量上
            window.roleId = this.id;

            // 给修改页面赋值
            $("#editModal_role [name = roleName]").val(roleName);
        });

        // 更新角色-后台操作
        $("#editRole").click(function () {
            // 获取模态框中id的name
            var roleName =  $.trim($("#editModal_role [name = roleName]").val());

            $.ajax({
                "url":"role/editRole.json",
                "type":"post",
                "data":{
                    "id":window.roleId,
                    "name":roleName
                },
                "dataType":"json",
                "success":function (response) {
                    var result = response.result;

                    if ("SUCCESS" == result){
                        layer.msg("更新成功");

                     //   window.pageSize = ;
                        // 重新绘制页面
                        generatePage();
                    }
                    if ("FAILED" == result){
                        layer.msg("更新失败!"+ response.message);
                    }

                },"error":function (response) {
                    error_layer(response);
                }
            })
            // 更新完后关闭模态窗
            $("#editModal_role").modal("hide");
            // 清除模态框的内容
            $("#editModal_role [name = roleName]").val("");
        })

        // 绑定单条删除删除时显示的确认弹窗
        $("#rolePageBody").on("click", ".deleteBtn", function () {

            // 获取角色名
            var roleName = $(this).parent().prev().text();

            // 创建role对象存入数组
            var roleArray =[{
                "roleId":this.id,
                "name":roleName
            }];

            // 调用专门显示模态框的方法
            showConfirmModal(roleArray);
        });

        // 确认删除
        $("#deleteConfirmRole").click(function () {

            // 将数组转换为字符串
            var requestBody = JSON.stringify(arrayRole);

            $.ajax({
                "url":"role/deleteRole.json",
                "type":"post",
                "data":requestBody,
                "contentType":"application/json;charset=UTF-8",
                "dataType":"json",
                "success":function(response){

                    var result = response.result;

                    if(result == "SUCCESS") {
                        layer.msg("操作成功！");

                        // 设置全选按钮不被选中
                        $("#summaryBox").prop("checked", false);

                        // 重新加载分页数据
                        generatePage();
                    }

                    if(result == "FAILED") {
                        layer.msg("操作失败！"+response.message);
                    }

                }, "error":function(response){
                    error_layer(response);
                }
            })

            // 保存完后关闭模态窗
            $("#removeModal_role_confirm").modal("hide");

        });

        // 设置全选/全不选
        $("#summaryBox").click(function () {

            // 获取按钮当前的状态
            var currentStatus = this.checked;

            // 给其他多选框设置是否选中
            $(".itemBox").prop("checked", currentStatus);
        });

        // 设置全选按钮是否为全选
        $("#rolePageBody").on("click", ".itemBox", function () {
            // 获取当前选中的checkbox的数量
            var checkedBoxCount = $(".itemBox:checked").length;

            // 获取全部checkbox的数量
            var totalCount = $(".itemBox").length;

            $("#summaryBox").prop("checked", checkedBoxCount == totalCount);
        });

        // 给批量删除按钮绑定点击事件
        $("#toDelete").click(function () {
            var roleArray =[];
            var ckeckedCount = $(".itemBox:checked").length;
            if (ckeckedCount == 0){
                layer.msg("请选择要删除的角色!");
                return false;
            }
            // 获取选中的checkbox
            $(".itemBox:checked").each(function () {
                // 使用this引用当前遍历得到的多选框
                var roleId = this.id;

                // 通过DOM操作获取角色名称
                var roleName = $(this).parent().next().text();
                roleArray.push({
                    "roleId":roleId,
                    "name":roleName
                });

                // 调用专门显示模态框的方法
                showConfirmModal(roleArray);
            })
        })
    });

function error_layer(response) {
    layer.msg(response.status +" "+ response.statusText);
}
</script>
</body>
</html>