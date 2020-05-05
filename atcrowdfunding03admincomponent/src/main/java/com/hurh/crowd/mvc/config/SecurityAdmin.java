package com.hurh.crowd.mvc.config;

import com.hurh.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;


/**
 * User对象中只包含账号和密码，为了
 * 能获取到Admin的所有对象，用这个类来继承User，获取Admin的全部对象
 * @Param:
 * @return:
 */
public class SecurityAdmin extends User {

    private static final long serialVersionUID = 1L;


    private Admin admin;

    // authorities包含权限和角色信息
    public SecurityAdmin(Admin admin, List<GrantedAuthority> authorities) {
        // 调用父类构造器
        super(admin.getLoginAcct(), admin.getUserPswd(), authorities);
        this.admin = admin;

        // 讲Admin中的密码擦除
        this.admin.setUserPswd(null);
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
