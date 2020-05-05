package com.hurh.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hurh.crowd.constant.Constant;
import com.hurh.crowd.entity.Admin;
import com.hurh.crowd.entity.AdminExample;
import com.hurh.crowd.entity.Role;
import com.hurh.crowd.exception.LoginAcctAlreadyExistsException;
import com.hurh.crowd.exception.LoginAcctAlreadyExistsForUpdateException;
import com.hurh.crowd.exception.LoginFailedException;
import com.hurh.crowd.mapper.AdminMapper;
import com.hurh.crowd.mapper.RoleMapper;
import com.hurh.crowd.mapper.UtilMapper;
import com.hurh.crowd.mvc.config.CommUtils;
import com.hurh.crowd.service.aop.AdminService;
import com.hurh.crowd.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @PackAgeName:com.hurh.crowd.service.impl
 * @ClassName:AdminServiceImpl
 * @Description:
 * @Author:hrh
 * @Date:2020/4/23
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UtilMapper utilMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void updateAdmin(Admin admin) {
        // "Selective" 表示有选择的更新 对于null值不更新
        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyExistsForUpdateException(Constant.MESSAGE_LOGIN_FAILED_ALREADY_IN_USE);
            }
        }
    }

    @Override
    public Admin getAdminById(String adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public void DeleteAdmin(String adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public PageInfo<Admin> getPageInfo(String inputStr, Integer pageNum, Integer pageSize) {

        // 调用PageHelper的静态方法开启分页功能
        PageHelper.startPage(pageNum, pageSize);

        // 执行sql
        List<Admin> list = adminMapper.getAdminByKeyWord(inputStr);

        // 将查询结果封装到PageInfo中
        return new PageInfo<>(list);
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String passWord) {
        // 校验账号密码是否为空
        if (loginAcct == null || loginAcct.length() == 0) {
            throw new LoginFailedException(Constant.MESSAGE_LOGIN_FAILED_INVALIDATE_ACCOUNT);
        }
        if (passWord == null || passWord.length() == 0) {
            throw new LoginFailedException(Constant.MESSAGE_LOGIN_FAILED_INVALIDATE_PASSWORD);
        }
/*
        // 根据账号查询Admin信息
        // 创建AdminExample对象
        AdminExample adminExample = new AdminExample();
        // 创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();
        // 在Criteria中封装参数
        criteria.andLoginAcctEqualTo(loginAcct);
        //调用adminMapper方法查询*/
        List<Admin> adminList = adminMapper.selectByLoginAcct(loginAcct);

        if (adminList == null || adminList.size() == 0) {
            throw new LoginFailedException(Constant.MESSAGE_LOGIN_FAILED_ACCOUNT_NOT_EXIST);
        }
        if (adminList.size() > 1) {
            throw new RuntimeException(Constant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        Admin admin = adminList.get(0);
        String passWordDB = admin.getUserPswd();

        String passWordFrom = CrowdUtil.md5(passWord);

        if (!Objects.equals(passWordFrom, passWordDB)) {
            throw new LoginFailedException(Constant.MESSAGE_LOGIN_FAILED_PASSWORD_WRONG);
        }

        return admin;
    }

    public void saveAdmin(Admin admin) {

        // 秘密加密
        String passWord = admin.getUserPswd();
        passWord = passwordEncoder.encode(passWord);
        admin.setUserPswd(passWord);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(date);

        admin.setCreateTime(createTime);

        admin.setId(utilMapper.getSeq_T_admin_id());


        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyExistsException(Constant.MESSAGE_LOGIN_FAILED_ALREADY_IN_USE);
            }
        }
    }

    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }
}
