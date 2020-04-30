package com.hurh.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.hurh.crowd.entity.Role;
import com.hurh.crowd.service.aop.RoleService;
import com.hurh.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @PackAgeName:com.hurh.crowd.mvc.controller
 * @ClassName:RoleController
 * @Description:
 * @Author:hrh
 * @Date:2020/4/29
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/getPageBean_role.json")
    public ResultEntity<PageInfo<Role>> getPageBean_role(@RequestParam(value = "inputStr", defaultValue = "") String inputStr,
                                                         //使用@RequestParam注解的defaultValue指定默认值 在传入的参数为空时使用默认值
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {

        PageInfo<Role> roles = roleService.getRoles(inputStr, pageNum, pageSize);

        // 封装ResultEntity对象中返回 若抛出异常 则由异常处理机制进行处理
        return ResultEntity.successWithData(roles);
    }

    @ResponseBody
    @RequestMapping("/saveRole.json")
    public ResultEntity<String> saveRole(@RequestParam("roleName") String roleName) {

        roleService.saveRole(roleName);

        // 封装ResultEntity对象中返回 若抛出异常 则由异常处理机制进行处理
        return ResultEntity.successWithoutData();
    }
}
