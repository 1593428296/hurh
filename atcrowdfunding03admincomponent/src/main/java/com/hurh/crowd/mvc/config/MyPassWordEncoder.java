package com.hurh.crowd.mvc.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * 密码加密的类
 * @Param:
 * @return:
 */
@Component
public class MyPassWordEncoder implements PasswordEncoder{


    /**
     * 加密方法
     * @Param: [charSequence]
     * @return: java.lang.String
     */
    @Override
    public String encode(CharSequence charSequence) {

        return doEncode(charSequence);

    }

    /**
     * 比较密码 前值为页面加密之后的值 后值为数据库中加密的密码值
     * @Param: [charSequence, s]
     * @return: boolean
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {

        // 对明文密码进行加密-表单密码
        String formPass = doEncode(charSequence);

        System.out.println("s:"+s);
        System.out.println("formPass:"+formPass);

        return Objects.equals(formPass, s);
    }

    private String doEncode(CharSequence charSequence){
        try {
            // 创建MessageDigest对象
            String algorithm = "MD5";
            MessageDigest instance = MessageDigest.getInstance(algorithm);

            // 获取charSequence的字节数组
            byte[] bytes = ((String) charSequence).getBytes();

            // 加密
            byte[] output = instance.digest(bytes);

            // 转换为16进制对应的字符
            String encoding = new BigInteger(1, output).toString(16).toUpperCase();

            return encoding;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
