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
    var result = resultEntity.result;
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
        var checkBox = "<th width='30'><input  id='"+roleId+"' class='itemBox' type='checkbox'></th>";
        var roleNameId = "<td>"+ roleName+"</td>";
        var numberId = "<td>"+(i+1)+"</td>";
        var checkBtn = "<button id='"+ roleId +"' type='button' class='btn btn-success btn-xs checkBtn'><i class=' glyphicon glyphicon-check'></i></button>";
        var pencilBtn = "<button id='"+ roleId +"'  type='button' id='"+roleId+"' class='btn btn-primary btn-xs pencilBtn'><i class=' glyphicon glyphicon-pencil'></i></button>";
        var removeBtn = "<button id='"+ roleId +"' type='button' id='"+roleId+"' class='btn btn-danger btn-xs deleteBtn'><i class=' glyphicon glyphicon-remove'></i></button>";

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
    };

    // 调用pagination()函数
    $("#Pagination").pagination(totalRecord, properties);
}

// 翻页时的回调函数
function paginationCallBack(pageIndex, jQuery) {
    //修改window对象的pageNum属性
    window.pageNum = pageIndex +1;

    // 设置全选按钮不被选中
    $("#summaryBox").prop("checked", false);
    // 调用分页函数
    generatePage();

    // 由于每一个页码按钮都是超链接，所以在这个函数最后取消超链接的默认行为
    return false;
}

// 确实删除的模态框显示函数及操作
function showConfirmModal(roleArray) {
    // 显示确认删除模态框
    $("#removeModal_role_confirm").modal("show");
    // 声明全局变量存储需要删除的role 的id
    window.arrayRole = [];

    // 清除旧数据
    $("#deleteRoleDiv").empty();

    // 遍历数组 显示要被删除的角色
    for(var i = 0; i<roleArray.length; i++){
        var role = roleArray[i];
        var roleName = role.name;
        $("#deleteRoleDiv").append(roleName+"<br/>")

        // 将要被删除的角色存入全局变量
        window.arrayRole.push(role.roleId);
    }
}

// 声明专门的函数用来在分配 Auth 的模态框中显示 Auth 的树形结构数据
function fillAuthTree(){
    // 获取auth数据
    var ajaxReturn = $.ajax({
        "url":"assign/getAllAuth.json",
        "type":"post",
        "dataType":"json",
        "async":false
    });
    if (ajaxReturn.status != 200){
        layer.msg("请求处理出错！响应状态码是:"+ajaxReturn.status+"说明:"+ajaxReturn.statusText);
        return;
    }

    //  从响应结果中获取 Auth 的 JSON 数据
    // 从服务器端查询到的 list 不需要组装成树形结构，用zTree 去组装
    var authList = ajaxReturn.responseJSON.data;
    // 准备对 zTree 进行设置的 JSON 对象
    var setting={
        "data":{
            "simpleData":{
                // 开启简单 JSON 功能
                "enable":true,
                // 使用 categoryId 属性关联父节点，不用默认的 pId 了
                "pIdKey":"category_id"
             },
            "key":{
                // 使用 title 属性显示节点名称，不用默认的 name 作为属性名了
                "name":"title"
            }
        },
        "check":{
            "enable":true
        }
    };
    // 生成树形结构 <ul id="authTreeDemo" class="ztree"></ul>
    $.fn.zTree.init($("#authTreeDemo"),setting,authList);

    // 获取 zTreeObj 对象
    var zTreeObj=$.fn.zTree.getZTreeObj("authTreeDemo");

    // 调用 zTreeObj 对象的方法，把节点展开
    zTreeObj.expandAll(true);

    // 查询已分配的 Auth 的 id 组成的数组
    ajaxReturn = $.ajax({
        "url":"assign/getAssignedAuthIdByRoleId.json",
        "type":"post",
        "data":{
            "roleId":window.roleId
        },
        "dataType":"json",
        "async":false
    });
    if (ajaxReturn.status != 200){
        layer.msg("请求处理出错12！响应状态码是:"+ajaxReturn.status+"说明:"+ajaxReturn.statusText);
        return;
    }
    // 从响应结果中获取 authIdArray
    var authIdArray=ajaxReturn.responseJSON.data;
    // 根据 authIdArray 把树形结构中对应的节点勾选上
    // 遍历 authIdArray
    for(var i=0; i < authIdArray.length; i++){
        var authId=authIdArray[i];
        // 根据 id 查询树形结构中对应的节点
        var treeNode=zTreeObj.getNodeByParam("id",authId);

        // 将 treeNode 设置为被勾选
        //checked 设置为 true 表示节点勾选
        var checked=true;
        //checkTypeFlag 设置为 false，表示不“联动”，不联动是为了避免把不该勾选的勾选上
        var checkTypeFlag=false;
        // 执行
        zTreeObj.checkNode(treeNode,checked,checkTypeFlag);
    }
}
