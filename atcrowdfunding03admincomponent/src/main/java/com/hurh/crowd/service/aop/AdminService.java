package com.hurh.crowd.service.aop;

import com.github.pagehelper.PageInfo;
import com.hurh.crowd.entity.Admin;

import java.util.List;

/**
 * @PackAgeName:com.hurh.crowd.service.aop
 * @ClassName:AdminService
 * @Description:
 * @Author:hrh
 * @Date:2020/4/23
 */
public interface AdminService {
    void saveAdmin(Admin admin);

    /*
     * @Author: hrh
     * @Description: 查询全部
     * @Date: 2020/4/23 22:04
     * @Param: []
     * @return java.util.List<com.hurh.crowd.entity.Admin>
     **/
    List<Admin> getAll();

    /*
     * @Author: hrh
     * @Description: 通过账号获取密码
     * @Date: 2020/4/25 22:39
     * @Param: [loginAcct, passWord]
     * @return com.hurh.crowd.entity.Admin
     **/
    Admin getAdminByLoginAcct(String loginAcct, String passWord);

    /*
     * @Author: hrh
     * @Description: 分页查询
     * @Date: 2020/4/26 21:54
     * @Param: [inputStr, pageNum, pageSize]
     * @return com.github.pagehelper.PageInfo<com.hurh.crowd.entity.Admin>
     **/
    PageInfo<Admin> getPageInfo(String inputStr, Integer pageNum, Integer pageSize);

    void DeleteAdmin(String adminId);

    Admin getAdminById(String adminId);

    void updateAdmin(Admin admin);
}
