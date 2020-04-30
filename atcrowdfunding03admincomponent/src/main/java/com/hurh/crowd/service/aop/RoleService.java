package com.hurh.crowd.service.aop;

import com.github.pagehelper.PageInfo;
import com.hurh.crowd.entity.Role;

import java.util.List;

/**
 * @PackAgeName:com.hurh.crowd.service.aop
 * @ClassName:RoleService
 * @Description:
 * @Author:hrh
 * @Date:2020/4/29
 */
public interface RoleService {

    PageInfo<Role> getRoles(String inputStr, Integer pageNum, Integer pageSize);

    int saveRole(String roleName);
}
