package com.hurh.crowd.util;

import com.hurh.crowd.constant.Constant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @PackAgeName:com.hurh.crowd.util
 * @ClassName:CrowdUtil
 * @Description: 通用工具类
 * @Author:hrh
 * @Date:2020/4/25
 */
public class CrowdUtil {
    /*
     * @Author: hrh
     * @Description: 判断当前请求是否为ajax请求
     * @Date: 2020/4/25 19:30
     * @Param: [request]
     * @return java.lang.Boolean {ture:当前是ajax请求;   false:当前不是ajax请求}
     **/
    public static Boolean judgeRequestType(HttpServletRequest request) {
        //1.获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");

        return  (
                (acceptHeader != null && acceptHeader.contains("application/json"))
                ||
                (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"))
        );
    }

    /*
     * @Author: hrh
     * @Description: 对明文字符串进行MD5加密
     * @Date: 2020/4/25 22:03
     * @Param: [source] 传入的字符串
     * @return java.lang.String
     **/
    public static String md5(String source){
        // 1.判断参数是否有效
        if (source == null || source.length() == 0){
            //2.若果为空 则抛出异常
            throw new RuntimeException(Constant.MESSAGE_LOGIN_FAILED_INVALIDATE_PASSWORD);
        }
        try {
            // 3.获取MessageDigest对象
            String algorithm = "md5";

            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            // 4.获取明文字符对象的字节数组
            byte[] input = source.getBytes();

            // 5.执行加密
            byte[] output = messageDigest.digest(input);

            // 6.创建BigInteger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            // 7.按照16进制将BigInteger的值转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
