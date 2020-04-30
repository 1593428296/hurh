<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script type="text/javascript"  src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript"  src="bootstrap/js/bootstrap.min.js"></script>
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

        <h2 class="form-signin-heading"  style="text-align: center">
            <i class="glyphicon glyphicon-log-in"></i> 尚筹网系统消息
        </h2>
        <!--
            requestScope对应的是存放request域数据的map
            requestScope.exception对应的是request.getAttribute("exception")
            requestScope.exception.message对应的是exception.getMessage
        -->
        <h3  style="text-align: center">${requestScope.exception.message}</h3>
        <button id="btn1" style="width: 150px;margin: 50px auto 0px auto" class="btn btn-lg btn-success btn-block">返回上一步</button>
</div>
</body>
<script type="text/javascript" >
    $(function () {
        $("#btn1").click(function () {
            //相当于浏览器的后退按钮
            window.history.back()
        });
    });
</script>

</html>