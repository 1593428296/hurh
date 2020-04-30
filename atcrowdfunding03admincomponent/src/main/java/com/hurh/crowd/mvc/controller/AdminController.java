package com.hurh.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.hurh.crowd.constant.Constant;
import com.hurh.crowd.entity.Admin;
import com.hurh.crowd.service.aop.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @PackAgeName:com.hurh.crowd.mvc.controller
 * @ClassName:AdminController
 * @Description:
 * @Author:hrh
 * @Date:2020/4/25
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /*
     * @Author: hrh
     * @Description: 登录校验
     * @Date: 2020/4/25 23:28
     * @Param: [loginAcct, passWord, session]
     * @return java.lang.String
     **/
    @RequestMapping("/loginCheck.html")
    public String loginCheck(@RequestParam("loginAcct") String loginAcct,
                             @RequestParam("userPswd") String passWord,
                             HttpSession session) {

        Admin admin = adminService.getAdminByLoginAcct(loginAcct, passWord);
        session.setAttribute(Constant.ATTR_NAME_LOGIN_ADMIN, admin);

        /*
         * 为避免跳转到后台主页面再刷新浏览器导致重复提交登录表单，所以采用重定向到目标页面
         * redirect后面的地址在spring-mvc中配置mvc:view-controller
         */
        return "redirect:/admin/mainPage.html";
    }

    /*
     * @Author: hrh
     * @Description: 退出登录
     * @Date: 2020/4/26 22:01
     * @Param: [session]
     * @return java.lang.String
     **/
    @RequestMapping("/logOut.html")
    public String logOut(HttpSession session) {

        //强制session失效
        session.invalidate();
        /*
         * 为避免跳转到后台主页面再刷新浏览器导致重复提交登录表单，所以采用重定向到目标页面
         * redirect后面的地址在spring-mvc中配置mvc:view-controller
         */
        return "redirect:/admin/login.html";
    }

    @RequestMapping("/getPageBean.html")
    public String getPageBean(@RequestParam(value = "inputStr", defaultValue = "") String inputStr,

                              //使用@RequestParam注解的defaultValue指定默认值 在传入的参数为空时使用默认值
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                              ModelMap modelMap
    ) {

        PageInfo<Admin> pageInfo = adminService.getPageInfo(inputStr, pageNum, pageSize);

        modelMap.addAttribute(Constant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin-page";
    }

    @RequestMapping("/remove/{adminId}/{pageNum}/{inputStr}.html")
    public String removeAdmin(@PathVariable("adminId") String adminId,
                              @PathVariable("pageNum") String pageNum,
                              @PathVariable("inputStr") String inputStr) {
        adminService.DeleteAdmin(adminId);
        // 页面跳转：回到分页页面

        // 尝试方案1：直接转发到admin-page.jsp会无法显示分页数据
        // return "admin-page";

        // 尝试方案2：转发到/admin/get/page.html地址，一旦刷新页面会重复执行删除浪费性能
        //    return "forward:/admin/getPageBean.html";

        // 尝试方案3：重定向到/admin/get/page.html地址
        // 同时为了保持原本所在的页面和查询关键词再附加pageNum和keyword两个请求参数
        return "redirect:/admin/getPageBean.html?pageNum=" + pageNum + "&keyword=" + inputStr;

    }

    @RequestMapping("/addAdmin.html")
    public String addAdmin(Admin admin) {

        adminService.saveAdmin(admin);

        return "redirect:/admin/getPageBean.html?pageNum=" + Integer.MAX_VALUE;
    }

    @RequestMapping("/toEditPage.html")
    public String toEditPage(@RequestParam("adminId") String adminId,
                             @RequestParam("pageNum") Integer pageNum,
                             @RequestParam("inputStr") String inputStr,
                             ModelMap modelMap) {

        Admin admin = adminService.getAdminById(adminId);

        modelMap.addAttribute("admin", admin);
        return "admin-edit";
    }

    @RequestMapping("/updateAdmin.html")
    public String editAdmin(Admin admin,
                            @RequestParam("pageNum") Integer pageNum,
                            @RequestParam("inputStr") String inputStr){

        adminService.updateAdmin(admin);

        return "redirect:/admin/getPageBean.html?pageNum=" + pageNum + "&inputStr=" + inputStr;
    }

}
