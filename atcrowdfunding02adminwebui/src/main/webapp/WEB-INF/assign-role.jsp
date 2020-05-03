<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="UTF-8">
<%@include file="/WEB-INF/include-head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<%@include file="/WEB-INF/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="admin/mainPage.html">首页</a></li>
                <li><a href="admin/getPageBean.html">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form" action="assign/doAssignSave.html" type="post" class="form-inline">
                        <input type="hidden" name="adminId" value="${param.adminId}">
                        <input type="hidden" name="pageNum" value="${param.pageNum}">
                        <input type="hidden" name="inputStr" value="${param.inputStr}">
                        <div class="form-group">
                            <label for="exampleInputPassword1">未分配角色列表</label><br>
                            <select class="form-control" multiple="" size="10" style="width:300px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unAssignedRoles}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                                <option value="pm">PM</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group"  style="margin-left:40px;">
                            <label for="exampleInputPassword1">已分配角色列表</label><br>
                            <select name="roleIdList" class="form-control" multiple="" size="10" style="width:300px;overflow-y:auto;">
                                <c:forEach items="${requestScope.assignedRoles}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>

                            </select>
                        </div>
                        <button id="submitBtn" style="width: 70px;margin-left:335px;margin-top: 20px" class="btn btn-sm btn-success btn-block" type="submit">保存</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $("#toRightBtn").click(function () {
            //select 是标签选择器
            // :eq(0)表示选择页面上的第一个
            // :eq(1)表示选择页面上的第二个
            // “>”表示选择子元素
            // :selected 表示选择“被选中的”option
            // appendTo()能够将 jQuery 对象追加到指定的位置
            $("select:eq(0)>option:selected").appendTo("select:eq(1)");
        });

        $("#toLeftBtn").click(function () {
            //select 是标签选择器
            // :eq(0)表示选择页面上的第一个
            // :eq(1)表示选择页面上的第二个
            // “>”表示选择子元素
            // :selected 表示选择“被选中的”option
            // appendTo()能够将 jQuery 对象追加到指定的位置
            $("select:eq(1)>option:selected").appendTo("select:eq(0)");
        });

        // 为了解决浏览器只提交当前选中的 其实是要将已分配角色列表的全部提交
        $("#submitBtn").click(function(){
            // 在提交表单前把“已分配”部分的 option 全部选中
            $("select:eq(1)>option").prop("selected","selected");
        });
    })
</script>
</body>
</html>
