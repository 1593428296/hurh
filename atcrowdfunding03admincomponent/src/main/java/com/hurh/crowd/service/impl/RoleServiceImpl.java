package com.hurh.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hurh.crowd.constant.Constant;
import com.hurh.crowd.entity.Role;
import com.hurh.crowd.exception.AboutRoleExistdException;
import com.hurh.crowd.exception.LoginAcctAlreadyExistsException;
import com.hurh.crowd.exception.LoginFailedException;
import com.hurh.crowd.mapper.RoleMapper;
import com.hurh.crowd.service.aop.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public int saveRole(String roleName) {

        Role role = roleMapper.getSeq_Role_id();
        role.setName(roleName);
        int i = 0;
        try {
           i = roleMapper.insert(role);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException){
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
