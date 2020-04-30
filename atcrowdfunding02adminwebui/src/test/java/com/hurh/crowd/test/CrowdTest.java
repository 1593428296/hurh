package com.hurh.crowd.test;

import com.hurh.crowd.entity.Admin;
import com.hurh.crowd.mapper.AdminMapper;
import com.hurh.crowd.service.aop.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @PackAgeName:com.hurh.crowd.test
 * @ClassName:CrowdTest
 * @Description:
 * @Author:hrh
 * @Date:2020/4/22
 */

// 在类上上标记必要的注解， spring整合junit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void TestTx(){
        Admin admin = new Admin("23","hurh","123123","胡荣红","123@qq.com",null);
        adminMapper.insert(admin);
    }

    @Test
    public void testLog(){

        //获取logger对象，class对象就是当前打印日志的类
        Logger log =LoggerFactory.getLogger(CrowdTest.class);
        log.debug("awawa aw aw a wdaawd");
        log.debug("awawa aw aw a wdaawd");
        log.debug("awawa aw aw a wdaawd");
        log.debug("awawa aw aw a wdaawd");
        log.debug("awawa aw aw a wdaawd");
        log.debug("awawa aw aw a wdaawd");

        log.info("ads,mkkiii");
        log.info("ads,mkkiii");
        log.info("ads,mkkiii");

        log.warn("aasaa!!!!!");
        log.warn("aasaa!!!!!");
        log.warn("aasaa!!!!!");
        log.warn("aasaa!!!!!");

        log.error("!!!!!!!!1");
        log.error("!!!!!!!!1");
        log.error("!!!!!!!!1");

    }

    @Test
    public void testInsertAdmin(){
        Admin admin = new Admin("SEQ_admin_id.netxVal","hurh","123123","胡荣红","123@qq.com",null);
        int i = adminMapper.insert(admin);
        System.out.println("行数:"+i);
    }

    /*
     * @Author: hrh
     * @Description: 配置配置数据源之后测试能否连接数据库
     * @Date: 2020/4/22 21:57
     * @Param: []
     * @return void
     **/
    @Test
    public void testConnection() throws Exception{
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
