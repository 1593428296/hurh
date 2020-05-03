package com.hurh.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hurh.crowd.constant.Constant;
import com.hurh.crowd.entity.Role;
import com.hurh.crowd.exception.AboutRoleExistdException;
import com.hurh.crowd.mapper.RoleMapper;
import com.hurh.crowd.mapper.UtilMapper;
import com.hurh.crowd.mvc.config.CommUtils;
import com.hurh.crowd.service.aop.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @PackAgeName:com.hurh.crowd.service.impl
 * @ClassName:RoleService
 * @Description:
 * @Author:hrh
 * @Date:2020/4/29
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UtilMapper utilMapper;


    @Override
    public int deleteRolesByID(List<Integer> integerList) {

        if (integerList.size() ==0){
            throw new AboutRoleExistdException(Constant.MESSAGE_ROLE_FAILED_NO_ID);
        }
        int i = 0;
        for (Integer integer : integerList) {
            roleMapper.deleteByPrimaryKey(integer);
            i++;
        }

        return i;
    }

    @Override
    public List<Role> getAssignedRoles(Map map) {
        List<Role> unAssignRoles = roleMapper.getAssignRoles(map);
        return unAssignRoles;
    }


    @Override
    public void saveRelationship(Integer adminId, List<Integer> integerList) {
        // 获取当前时间
        String createTime = CommUtils.getSysTime();

        Integer aaa = utilMapper.getSeq_inner_admin_role_id();

        Map map = new HashMap();
        map.put("id", adminId);
        map.put("isAssign", "yes");
        // 查询已分配的角色
        List<Role> assignedRoles = roleMapper.getAssignRoles(map);
        List<Integer> integerList1 = new ArrayList<>();
        for (Role assignedRole : assignedRoles) {
            Integer id = assignedRole.getId();
            integerList1.add(id);
        }

        // 将要新增的角色的id
        List<Integer> ids = integerList.stream()
                .filter(item -> !integerList1.stream()
                        .map(e -> e.longValue())
                        .collect(Collectors.toList())
                        .contains(item.longValue()))
                .collect(Collectors.toList());

        for (Integer id : ids) {
            map.clear();
            map.put("id", utilMapper.getSeq_inner_admin_role_id());
            map.put("admin_id", adminId);
            map.put("role_id", id);
            map.put("createTime", createTime);
            roleMapper.saveNewrelationship(map);
        }


        // 将要删除的角色id
        List<Integer> removeAssignRoles = integerList1.stream()
                .filter(item -> !integerList.stream()
                        .map(e -> e.longValue())
                        .collect(Collectors.toList())
                        .contains(item.longValue()))
                .collect(Collectors.toList());
        for (Integer removeAssignRole : removeAssignRoles) {
            map.clear();
            map.put("admin_id", adminId);
            map.put("role_id", removeAssignRole);
            roleMapper.deleteRelatuionShip(map);
        }

    }

    @Override
    public int editRoleById(Role role) {
        Role role_id = roleMapper.selectByPrimaryKey(role.getId());
        if (Objects.equals(role.getName(), role_id.getName())){
            throw new AboutRoleExistdException(Constant.MESSAGE_ROLE_FAILED_NO_CHANGE);
        }
        int i = 0;
        try {
            i = roleMapper.updateByPrimaryKey(role);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new AboutRoleExistdException(Constant.MESSAGE_ROLE_FAILED_ALREADY_IN_USE);
            }
        }
        return i;
    }

    @Override
    public int saveRole(String roleName) {
        Role role = new Role();
        role.setId(utilMapper.getSeq_Role_id());
        role.setName(roleName);
        int i = 0;
        try {
            i = roleMapper.insert(role);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new AboutRoleExistdException(Constant.MESSAGE_ROLE_FAILED_ALREADY_IN_USE);
                //  throw new AboutRoleExistdException("123123123");
            }
        }
        return i;
    }

    @Override
    public PageInfo<Role> getRoles(String inputStr, Integer pageNum, Integer pageSize) {
        // 调用PageHelper的静态方法开启分页功能
        PageHelper.startPage(pageNum, pageSize);

        // 执行sql
        List<Role> list = roleMapper.getRolesByInputStr(inputStr);
        // 将查询结果封装到PageInfo中
        return new PageInfo<>(list);
    }
}
