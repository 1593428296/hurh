// 执行分页 生成页面效果
function generatePage() {
    // 获取分页数据
    var pageInfo = getPageInfoRemote();

    // 填充表格
    fillTableBody(pageInfo);

}

// 访问服务器段程序 获取pageInfo数据
function getPageInfoRemote() {
    // 调用$.ajax()函数发送请求并接受$.ajax()的返回值
   var ajaxResult = $.ajax({
        "url":"role/getPageBean_role.json",
        "type":"post",
        "data":{
            "inputStr":window.inputStr,
            "pageNum":window.pageNum,
            "pageSize":window.pageSize
        },
        "async":false,
        "dataType":"json"
    });

    // 判断当前的请求的状态码是否为200
    var status = ajaxResult.status;

   // 如果不是200 则说明发生错误 让程序终止运行
    if (status != 200){
        layer.msg("失败！响应码为"+status + ";原因:"+ajaxResult.statusText);
    }

    var resultEntity = ajaxResult.responseJSON;
    // 判断result是否成功
    var result = ajaxResult.result;
    if (result == "FAILED"){
        layer.msg(resultEntity.message);
        result ;
    }

    // 成功则获取pageInfo的数据
    var pageInfo = resultEntity.data;
    return pageInfo;
}

// 填充表格
function fillTableBody(pageInfo) {

    // 清空tbody中的旧内容
    $("#rolePageBody").empty();

    // 清空分页条中的旧内容
    $("#Pagination").empty();

    // 判断pageInfo是否有效
    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length ==0){
        $("#rolePageBody").append("<tr><td colspan='4' align='center'>未获取到任何信息!</td></tr>");
        return;
    }

    // 如果有效 则遍历 填充表格
    for (var i =0; i<pageInfo.list.length; i++){

        var role = pageInfo.list[i];

        var roleId = role.id;
        var roleName = role.name;
        var checkBox = "<th width='30'><input type='checkbox'></th>";
        var roleNameId = "<td>"+ roleName+"</td>";
        var numberId = "<td>"+(i+1)+"</td>";
        var checkBtn = "<button type='button' class='btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></button>";
        var pencilBtn = "<button type='button' class='btn btn-primary btn-xs'><i class=' glyphicon glyphicon-pencil'></i></button>";
        var removeBtn = "<button type='button' class='btn btn-danger btn-xs'><i class=' glyphicon glyphicon-remove'></i></button>";

        var buttonTd = "<td>"+checkBtn+" "+ pencilBtn +" "+removeBtn+"</tr>";

        var tr = "<tr>"+ numberId + checkBox + roleNameId + buttonTd +"</tr>";

        $("#rolePageBody").append(tr);
    }

    // 生成分页导航栏
    generateNavigator(pageInfo);
}

// 生成分页页码
function generateNavigator(pageInfo) {
    // 获取总记录数
    var totalRecord = pageInfo.total;

    // 声明相关属性
    var properties = {
        num_edge_entries: 2,                                  // 边缘页数
        num_display_entries: 4,                               // 主体页数
        callback: paginationCallBack,                         // 指定用户点击“翻页”的按钮时跳转页面的回调函数
        items_per_page : pageInfo.pageSize,                 // 每页显示1项
        current_page : pageInfo.pageNum - 1,                // Pagination内部使用pageIndex来管理页码，pageIndex从0开始，pageNum从1开始
        prev_text : "上一页",                                 // 上一页按钮上显示的文本
        next_text : "下一页"                                  // 下一页按钮上显示的文本
    }

    // 调用pagination()函数
    $("#Pagination").pagination(totalRecord, properties);
}

// 翻页时的回调函数
function paginationCallBack(pageIndex, jQuery) {
    //修改window对象的pageNum属性
    window.pageNum = pageIndex +1;

    // 调用分页函数
    generatePage();

    // 由于每一个页码按钮都是超链接，所以在这个函数最后取消超链接的默认行为
    return false;
}