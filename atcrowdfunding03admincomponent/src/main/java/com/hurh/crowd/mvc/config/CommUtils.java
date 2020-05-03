package com.hurh.crowd.mvc.config;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: CommUtils
 * @Author: hurh
 * @Descriprion: 工具类
 * @Date: 2020/5/2 20:58
 * @Modifier:
 **/
public class CommUtils {

    /**
     * 获取当前时间
     * @Param: []
     * @return: java.lang.String
     */
    public static String getSysTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(date);
        return createTime;
    }

}
