<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/include-head.jsp" %>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<body>
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
                    <button id="btnAsync">发送ajax请求</button>
                    <form class="form-inline" action="admin/getPageBean.html" method="post" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="inputStr" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='admin/toAddAdmin.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="50" align="center">序号</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th  align="center">邮箱地址</th>
                                <th>创建时间</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty requestScope.pageInfo.list}">
                                    <tr>
                                        <td colspan="7" align="center">未获取到任何数据!</td>
                                    </tr>
                                </c:if>
                                <c:if test="${!empty requestScope.pageInfo.list}">
                                   <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="myStatus">
                                       <tr>
                                           <td>${myStatus.count}</td>
                                           <td><input type="checkbox"></td>
                                           <td>${admin.loginAcct}</td>
                                           <td align="center">${admin.userName}</td>
                                           <td>${admin.email}</td>
                                           <td>${admin.createTime}</td>
                                           <td>
                                               <button type="button" class="btn btn-success btn-xs"><i class="glyphicon glyphicon-check"></i></button>
                                               <a href="admin/toEditPage.html?adminId=${admin.id}&pageNum=${requestScope.pageInfo.pageNum}&inputStr=${param.inputStr}" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-pencil"></i></a>
                                               <a href="admin/remove/${admin.id}/${requestScope.pageInfo.pageNum}/${param.inputStr}.html" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-remove"></i></a>
                                           </td>
                                       </tr>
                                   </c:forEach>
                                </c:if>
                            </tbody>
                            <tfoot>
                              <tr>
                                 <td colspan="7" align="center">
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
        //调用分页导航条初始化操作
        initPagination()
    });

    function initPagination() {
        //获取总记录数
        var totalRecord = ${requestScope.pageInfo.total};

        //声明JSON对象存储pagination要设置的参数
        var properties = {
            num_edge_entries: 2,                                  // 边缘页数
            num_display_entries: 4,                               // 主体页数
            callback: pageSelectCallback,                         // 指定用户点击“翻页”的按钮时跳转页面的回调函数
            items_per_page : ${requestScope.pageInfo.pageSize},                                   // 每页显示1项
            current_page : ${requestScope.pageInfo.pageNum - 1},  // Pagination内部使用pageIndex来管理页码，pageIndex从0开始，pageNum从1开始
            prev_text : "上一页",                                 // 上一页按钮上显示的文本
            next_text : "下一页"                                  // 下一页按钮上显示的文本
        };
        // 生成页面导航条
        $("#Pagination").pagination(totalRecord, properties);

    }

    // 点击上一页、下一页、1、2、3、 页码时调用这个函数实现页面跳转
    // pageIndex是根据Pagenation传的从0开始的页码
    function pageSelectCallback(pageIndex, jQuery) {
        // 根据pageIndex得到pageNum
        var pageNum = pageIndex + 1;

        // 跳转页面
        window.location.href = "admin/getPageBean.html?pageNum=" + pageNum+"&inputStr=${param.inputStr}";

        // 由于每一个页码按钮都是超链接，所以在这个函数最后取消超链接的默认行为
        return false;
    }

    $(function () {
        $("#btnAsync").click(function () {
            console.log("ajax方法之前");
            $.ajax({
                "url":"test/testAjax.html",
                "type":"post",
                "dataType":"text",
                "async":false,
                "success":function (response) {
                    //等待服务器响应之后执行
                    console.log("ajax方法内部:"+response);
                }
            });
            console.log("ajax方法之后");
          /*  setTimeout(function () {
                //等待$.ajax()执行完成后执行，不会等待success的函数
                console.log("ajax方法之后");
            },5000);*/
        })
    })
</script>
</body>
</html>