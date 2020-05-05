package com.hurh.crowd.mapper;

import com.hurh.crowd.entity.Auth;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AuthMapper {

    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    List<Auth> getAllAuths();

    void insertInnerRoleAuth(Map map);

    void removeInnerRoleAuth(Map map);

    /**
     * 根据用户名获取权限
     * @Param: [adminId]
     * @return: java.util.List<java.lang.String>
     */
    List<String> getAssignedAuthByAdminId(String adminId);
}
