package com.hurh.crowd.mvc.config;

import com.google.gson.Gson;
import com.hurh.crowd.constant.Constant;
import com.hurh.crowd.exception.AboutRoleExistdException;
import com.hurh.crowd.exception.LoginAcctAlreadyExistsException;
import com.hurh.crowd.exception.LoginAcctAlreadyExistsForUpdateException;
import com.hurh.crowd.exception.LoginFailedException;
import com.hurh.crowd.util.CrowdUtil;
import com.hurh.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PackAgeName:com.hurh.crowd.mvc.config
 * @ClassName:CrowdExceptionResolver
 * @Description: 基于注解的异常处理器类
 * @Author:hrh
 * @Date:2020/4/25
 */
//@ControllerAdvice: 表示当前类是一个基于注解的异常处理类
@ControllerAdvice
public class CrowdExceptionResolver {

    /*
   * @Author: hrh
   * @Description: 没有登陆就访问受保护资源
   * @Date: 2020/4/25 20:27
   * @Param: [exception, request, response]
   * @return org.springframework.web.servlet.ModelAndView
   **/
    @ExceptionHandler(value = Exception.class)
    public ModelAndView resolveException(
            Exception exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String viewName = "system-error";

        return commonResolve(viewName, exception, request, response);
    }

    /*
     * @Author: hrh
     * @Description: 检查角色是否重复
     * @Date: 2020/4/25 20:27
     * @Param: [exception, request, response]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @ExceptionHandler(value = AboutRoleExistdException.class)
    public ModelAndView resolveRoleAlreadyExistsException(
            AboutRoleExistdException exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String viewName = "role-Page";

        return commonResolve(viewName, exception, request, response);
    }


    /*
     * @Author: hrh
     * @Description: 注册时 检测账号是否存在抛出的异常
     * @Date: 2020/4/25 20:27
     * @Param: [exception, request, response]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @ExceptionHandler(value = LoginAcctAlreadyExistsException.class)
    public ModelAndView resolveLoginAcctAlreadyExistsException(
            LoginAcctAlreadyExistsException exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String viewName = "admin-add";

        return commonResolve(viewName, exception, request, response);
    }

    /*
     * @Author: hrh
     * @Description: 更新时 检测账号是否存在抛出的异常
     * @Date: 2020/4/25 20:27
     * @Param: [exception, request, response]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @ExceptionHandler(value = LoginAcctAlreadyExistsForUpdateException.class)
    public ModelAndView resolveLoginAcctAlreadyExistsForUpdateException(
            LoginAcctAlreadyExistsForUpdateException exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String viewName = "system-error";

        return commonResolve(viewName, exception, request, response);
    }

    /*
     * @Author: hrh
     * @Description: 空指针异常处理
     * @Date: 2020/4/25 20:27
     * @Param: [exception, request, response]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointerException(
            NullPointerException exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String viewName = "system-error";

        return commonResolve(viewName, exception, request, response);
    }


    /*
     * @Author: hrh
     * @Description: 登录失败的异常
     * @Date: 2020/4/25 20:27
     * @Param: [exception, request, response]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(
            LoginFailedException exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String viewName = "admin-login";

        return commonResolve(viewName, exception, request, response);
    }


    /*
     * @Author: hrh
     * @Description:@ExceptionHandler将一个具体的异常类型和一个方法关联起来
     * @Date: 2020/4/25 20:26
     * @Param: [viewName, exception, request, response]
     * @return org.springframework.web.servlet.ModelAndView
     * @throws IOException
     **/
    private ModelAndView commonResolve(

            // 异常处理完成后要返回的页面
            String viewName,

            // 实际捕获到的异常类型
            Exception exception,

            // 当前请求对象
            HttpServletRequest request,

            // 当前响应对象
            HttpServletResponse response) throws IOException {

        // 1.判断当前请求类型
        boolean judgeResult = CrowdUtil.judgeRequestType(request);

        // 2.如果是Ajax请求
        if (judgeResult) {

            // 3.创建ResultEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());

            // 4.创建Gson对象
            Gson gson = new Gson();

            // 5.将ResultEntity对象转换为JSON字符串
            String json = gson.toJson(resultEntity);

            // 6.将JSON字符串作为响应体返回给浏览器
            response.getWriter().write(json);

            // 7.由于上面已经通过原生的response对象返回了响应，所以不提供ModelAndView对象
            return null;
        }

        // 8.如果不是Ajax请求则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();

        // 9.将Exception对象存入模型
        modelAndView.addObject(Constant.ATTR_NAME_EXCEPTION, exception);

        // 10.设置对应的视图名称
        modelAndView.setViewName(viewName);

        // 11.返回modelAndView对象
        return modelAndView;
    }

}
