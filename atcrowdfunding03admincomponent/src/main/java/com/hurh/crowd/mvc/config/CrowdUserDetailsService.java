package com.hurh.crowd.mvc.config;


import com.hurh.crowd.entity.Admin;
import com.hurh.crowd.entity.Role;
import com.hurh.crowd.mapper.AdminMapper;
import com.hurh.crowd.service.aop.AdminService;
import com.hurh.crowd.service.aop.AuthService;
import com.hurh.crowd.service.aop.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CrowdUserDetailsService  implements UserDetailsService{

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleService roleService;

    @Autowired

    private AuthService authService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //1.根据账号名称查询 Admin 对象
        Admin admin = adminMapper.selectByLoginAcct(s).get(0);

        //2.获取 adminId
        String adminId = admin.getId();
        //3.根据 adminId 查询角色信息
        Map param = new HashMap();
        param.put("id", Integer.valueOf(adminId));
        param.put("isAssign", "yes");
        List<Role> assignedRoles = roleService.getAssignedRoles(param);

        //4.根据 adminId 查询权限信息
        List<String> assignedAuthByAdminId = authService.getAssignedAuthByAdminId(adminId);

        //5.创建集合对象用来存储 GrantedAuthority
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        //6.遍历 assignedRoleList 存入角色信息
        for (Role role : assignedRoles) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_"+role.getName());
            grantedAuthorities.add(simpleGrantedAuthority);
        }
        //7.遍历 authNameList 存入权限信息
        for (String s1 : assignedAuthByAdminId) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(s1);
            grantedAuthorities.add(simpleGrantedAuthority);
        }

        //8.封装 SecurityAdmin 对象
        SecurityAdmin securityAdmin = new SecurityAdmin(admin, grantedAuthorities);
        return securityAdmin;
    }
}
