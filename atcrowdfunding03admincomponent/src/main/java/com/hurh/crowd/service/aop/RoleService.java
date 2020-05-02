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

    /**
     * 分页查询
     * @Param: [inputStr, pageNum, pageSize]
     * @return: com.github.pagehelper.PageInfo<com.hurh.crowd.entity.Role>
     */
    PageInfo<Role> getRoles(String inputStr, Integer pageNum, Integer pageSize);

    /**
     * 新增
     * @Param: [roleName]
     * @return: int
     */
    int saveRole(String roleName);

    /**
     * 更改
     * @Param: [role]
     * @return: int
     */
    int editRoleById(Role role);

    /**
     * 删除
     * @Param: [integerList]
     * @return: void
     */
    int deleteRolesByID(List<Integer> integerList);
}
