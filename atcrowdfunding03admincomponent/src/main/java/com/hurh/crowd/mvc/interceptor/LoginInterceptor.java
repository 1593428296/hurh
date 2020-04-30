package com.hurh.crowd.mvc.interceptor;

import com.hurh.crowd.constant.Constant;
import com.hurh.crowd.entity.Admin;
import com.hurh.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @PackAgeName:com.hurh.crowd.mvc.interceptor
 * @ClassName:LoginInterceptor
 * @Description: 拦截器
 * @Author:hrh
 * @Date:2020/4/26
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //通关request对象或许session对象
        HttpSession session = request.getSession();

        //从session中获取admin对象
        Admin admin = (Admin) session.getAttribute(Constant.ATTR_NAME_LOGIN_ADMIN);

        if (admin == null){
            //抛异常
            throw new AccessForbiddenException(Constant.MESSAGE_ACCESS_FORBIDEN);
        }

        //不为空则放行
        return true;
    }
}
