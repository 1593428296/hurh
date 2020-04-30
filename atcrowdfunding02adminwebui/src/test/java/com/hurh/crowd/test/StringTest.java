package com.hurh.crowd.test;

import com.hurh.crowd.util.CrowdUtil;
import org.junit.Test;

/**
 * @PackAgeName:com.hurh.crowd.test
 * @ClassName:StringTest
 * @Description:
 * @Author:hrh
 * @Date:2020/4/25
 */
public class StringTest {
    @Test
    public void test(){
        String a = "111111";
        String encode = CrowdUtil.md5(a);
        System.out.println(encode);
    }
}
