package com.hurh.crowd.mvc.controller;

import com.hurh.crowd.entity.Auth;
import com.hurh.crowd.entity.Role;
import com.hurh.crowd.service.aop.AdminService;
import com.hurh.crowd.service.aop.AuthService;
import com.hurh.crowd.service.aop.RoleService;
import com.hurh.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/assign")
public class AssignController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthService authService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/toAssignPage.html")
    public String toAssignRolePage(@RequestParam("adminId") Integer adminId,
                                   ModelMap modelMap) {

        Map map = new HashMap();
        map.put("id", adminId);
        map.put("isAssign", "yes");
        // 查询已分配的角色
        List<Role> assignedRoles = roleService.getAssignedRoles(map);

        // 查询未分配的角色
        map.put("isAssign", "no");
        List<Role> unAssignedRoles = roleService.getAssignedRoles(map);

        //存入模型
        modelMap.addAttribute("assignedRoles", assignedRoles);
        modelMap.addAttribute("unAssignedRoles", unAssignedRoles);

        return "assign-role";
    }

    @RequestMapping("/doAssignSave.html")
    public String doAssignSave(@RequestParam("adminId") Integer adminId,
                               @RequestParam("pageNum") Integer pageNum,
                               @RequestParam("inputStr") String inputStr,
                               // 用户在页面上取消所有已分配角色再提交表单，所以可以不提供 roleIdList 请求参数
                               // 设置 required=false 表示这个请求参数不是必须的
                               @RequestParam(value = "roleIdList", required = false) List<Integer> integerList) {


        roleService.saveRelationship(adminId, integerList);

        return "redirect:/admin/getPageBean.html?pageNum=" + pageNum + "&keyword=" + inputStr;
    }

    @ResponseBody
    @RequestMapping("/getAllAuth.json")
    public ResultEntity<List<Auth>> getAllAuth(){

        List<Auth> authList = authService.getAllAuths();

        return ResultEntity.successWithData(authList);
    }

    @ResponseBody
    @RequestMapping("/getAssignedAuthIdByRoleId.json")
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(@RequestParam("roleId") Integer roleId) {

        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);

        return ResultEntity.successWithData(authIdList);
    }

    @ResponseBody
    @RequestMapping("/saveRolesAuth.json")
    public ResultEntity saveRolesAuth(@RequestBody Map<String,List<Integer>> map) {

        authService.saveRoleAuth(map);

        return ResultEntity.successWithoutData();
    }
}
