package com.xiaobozheng.gankio.util;

/**
 * Created by xiaobozheng on 11/15/2016.
 */
public class Stringutils {

    //创建缓存的键
    public static String creatAcacheKey(Object ... param){
        String key = "";
        for (Object o : param){
            key += "-" + o;
        }
        return key.replaceFirst("-", "");
    }
}
