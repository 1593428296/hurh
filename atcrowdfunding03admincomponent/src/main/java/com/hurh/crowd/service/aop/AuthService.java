package com.hurh.crowd.service.aop;

import com.hurh.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: AuthService
 * @Author: hurh
 * @Date: 2020/5/3 13:28 
 **/
public interface AuthService {
    List<Auth> getAllAuths();

    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    void saveRoleAuth(Map<String, List<Integer>> map);

    List<String> getAssignedAuthByAdminId(String adminId);
}
