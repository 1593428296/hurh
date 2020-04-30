<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>主页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
</head>
<body>
<a href="test/ssm.html">测试SSM整合环境</a>
<br>
<br>
<button id="btn1">发送one[1,2,3,4]</button>
<br>
<br>
<button id="btn2">发送two[1,2,3,4]</button>
<br>
<br>
<button id="btn3">发送three[1,2,3,4]</button>
<br>
<br>
<button id="btn4">Send Compose Object</button>

<br>
<br>
<button id="btn5">弹出框</button>
</body>
<script type="text/javascript">
    $(function () {
        $("#btn1").click(function () {
            $.ajax({
                "url":"test/sendArray1.html",                          //请求目标资源的地址
                "type":"post",                                    //请求的方式
                "data":{                                          //要发送的请求参数
                    "array":[1,2,3,4]
                },
                "dataType":"text",                                //服务器端返回的数据类型
                "success":function (response) {                   //服务器处理成功之后的回调函数
                    alert(response);
                },
                "error":function (response) {                             //服务器处理失败之后的回调函数
                    alert(response);
                }
            })
        });


        $("#btn2").click(function () {
            $.ajax({
                "url":"test/sendArray2.html",                          //请求目标资源的地址
                "type":"post",                                    //请求的方式
                "data":{                                          //要发生的请求参数
                    "araay[0]":1,
                    "araay[1]":2,
                    "araay[2]":3
                },
                "dataType":"text",                                //服务器端返回的数据类型
                "success":function (response) {                   //服务器处理成功之后的回调函数
                    alert(response);
                },
                "error":function (response) {                             //服务器处理失败之后的回调函数
                    alert(response);
                }
            })
        });

        //定义数组
        var araay =[1,2,3,4];
        //将json数组装换为json字符穿串
        var requestBody = JSON.stringify(araay);
        $("#btn3").click(function () {
            $.ajax({
                "url":"test/sendArray3.html",                          //请求目标资源的地址
                "type":"post",                                    //请求的方式
                "data":requestBody,                               //要发送的请求参数
                "contentType":"application/json;charset=UTF-8",   //设置请求体的内容类型
                "dataType":"text",                                //服务器端返回的数据类型
                "success":function (response) {                   //服务器处理成功之后的回调函数
                    alert(response);
                },
                "error":function (response) {                             //服务器处理失败之后的回调函数
                    alert(response);
                }
            })
        });

        $("#btn4").click(function () {
            //定义数组
            var student={
                "stuid": 11,
                "name" : "张三",
                "addresses" :{
                    "province":"四川省",
                    "city":"成都市",
                    "street":"武侯区"
                },
                "subjectList":[
                    {
                        "subName":"语文",
                        "score":"98"
                    },{
                        "subName":"数学",
                        "score":"97"
                    }
                ],
                "map":{
                    "key1":"value1",
                    "key2":"value2"
                }
            };

            //将json对象装换为json字符串
            var requestBody = JSON.stringify(student);
            // 发送Ajax请求
            $.ajax({
                "url":"test/sendArray4.json",
                "type":"post",
                "data":requestBody,
                "contentType":"application/json;charset=UTF-8",
           //     "dataType":"json",
                "success":function(response){
                    console.log(response);
                    console.log("成功了");
                },
                "error":function(response){
                    console.log(response);
                    console.log("失败了");
                }
            });
        });

        $("#btn5").click(function () {
            layer.msg("layer弹出框!");
        });

    })
</script>
</html>