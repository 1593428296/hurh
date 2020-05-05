package com.hurh.crowd.service.impl;

import com.hurh.crowd.entity.Auth;
import com.hurh.crowd.mapper.AuthMapper;
import com.hurh.crowd.mapper.UtilMapper;
import com.hurh.crowd.mvc.config.CommUtils;
import com.hurh.crowd.service.aop.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * @ClassName: AuthServiceImpl
 * @Author: hurh
 * @Descriprion: TODO
 * @Date: 2020/5/3 13:28
 * @Modifier:
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private UtilMapper utilMapper;

    @Override
    public List<String> getAssignedAuthByAdminId(String adminId) {
        System.out.println(adminId);
        return authMapper.getAssignedAuthByAdminId(adminId);
    }

    @Override
    public List<Auth> getAllAuths() {
        return authMapper.getAllAuths();
    }

    @Override
    public void saveRoleAuth(Map<String, List<Integer>> map) {

        Integer roleId = map.get("roleId").get(0);

        // 页面传过来的权限
        List<Integer> authIds = map.get("authIdArray");

        // 已经存在的权限
        List<Integer> existAuthId = authMapper.getAssignedAuthIdByRoleId(roleId);

        // 将要新增的权限
        List<Integer> addAuthIDs = authIds.stream().filter(e -> !existAuthId.contains(e)).collect(toList());

        // 将要删除的权限
        List<Integer> removeAuthIDs = existAuthId.stream().filter(e -> !authIds.contains(e)).collect(toList());
        Map param = new HashMap();

        if (addAuthIDs.size()>0){
            for (Integer addAuthID : addAuthIDs) {
                param.clear();
                param.put("id", utilMapper.getSeq_inner_role_auth_id());
                param.put("role_id", roleId);
                param.put("auth_id", addAuthID);
                param.put("create_time", CommUtils.getSysTime());
                authMapper.insertInnerRoleAuth(param);
            }
        }

        if (removeAuthIDs.size()> 0){
            for (Integer removeAuthID : removeAuthIDs) {
                param.clear();
                param.put("role_id", roleId);
                param.put("auth_id", removeAuthID);
                authMapper.removeInnerRoleAuth(param);
            }
        }
    }

    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        return authMapper.getAssignedAuthIdByRoleId(roleId);
    }
}
