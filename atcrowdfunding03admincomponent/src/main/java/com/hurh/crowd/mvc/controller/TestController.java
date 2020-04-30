package com.hurh.crowd.mvc.controller;

import com.hurh.crowd.entity.Admin;
import com.hurh.crowd.entity.ParamData;
import com.hurh.crowd.entity.Student;
import com.hurh.crowd.service.aop.AdminService;
import com.hurh.crowd.util.CrowdUtil;
import com.hurh.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @PackAgeName:com.hurh.crowd.mvc.config
 * @ClassName:TestController
 * @Description:
 * @Author:hrh
 * @Date:2020/4/23
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/ssm.html")
    public String testSsm(ModelMap modelMap, HttpServletRequest request) {
        boolean judgeRequestType = CrowdUtil.judgeRequestType(request);
        logger.info("judgeRequestType："+judgeRequestType);
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList", adminList);
        System.out.println(10/0);
        return "target";
    }

    /*
     * @Author: hrh
     * @Description: 当页面传递的"data":{
                                     "array":[1,2,3,4]
                                },为这种类型时，需要在接收的参数名后面几个'[]',入array[]
     * @Date: 2020/4/24 0:04
     * @Param: [array]
     * @return java.lang.String
     **/
    @ResponseBody
    @RequestMapping("/sendArray1.html")
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array) {
        for (Integer integer : array) {
            System.out.println("number:" + integer);
        }
        return "target";
    }

    /*
        * @Author: hrh
        * @Description: 当页面传递的"data":{
                                         "araay[0]":1,
                                         "araay[1]":2,
                                         "araay[2]":3
                                   },为这种类型时，需要用一个实体类来接收，这和实体类的变量名要和页面传递的变量名相同
        * @Date: 2020/4/24 0:04
        * @Param: [array]
        * @return java.lang.String
        **/
    @ResponseBody
    @RequestMapping("/sendArray2.html")
    public String testReceiveArrayTwo(ParamData paramData) {
        List<Integer> param = paramData.getAraay();
        for (Integer integer : param) {
            System.out.println("INERT:" + integer);
        }
        return "SUCCESS";
    }

    @ResponseBody
    @RequestMapping("/sendArray3.html")
    public String testReceiveArrayThree(@RequestBody List<Integer> array) {

        for (Integer integer : array) {
            logger.debug("number:" + integer);
        }
        return "SUCCESS";
    }

    @ResponseBody
    @RequestMapping("/sendArray4.json")
    public ResultEntity<Student> testReceiveArrayCom(@RequestBody Student student, HttpServletRequest request) {
        boolean judgeRequestType = CrowdUtil.judgeRequestType(request);
        logger.info("judgeRequestType："+judgeRequestType);
        logger.debug(student.toString());
        //将获取到student对象封装到ResultEntity中返回
        ResultEntity<Student> resultEntity = ResultEntity.successWithData(student);
        return resultEntity;
    }

    /*
     * @Author: hrh
     * @Description: 测试ajax异步工作原理
     * @Date: 2020/4/28 22:35
     * @Param: []
     * @return java.lang.String
     **/
    @ResponseBody
    @RequestMapping("/testAjax.html")
    public String testAjaxAsync() throws InterruptedException {
        Thread.sleep(2000);
        return "success";
    }


}
