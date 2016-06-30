package com.xiaobozheng.gankio.util;

import com.xiaobozheng.gankio.Constant.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 这里可以用枚举类的
 * Created by xiaobozheng on 6/30/2016.
 */
public class GankTypeDict {
    public static final HashMap<String, Integer> urlType2ColorDict = new HashMap<>();
    public static final HashMap<Integer, String> type2UrlTypeDict = new HashMap<>();
    public static final HashMap<String, Integer> urlType2TypeDict = new HashMap<>();

    static {

        type2UrlTypeDict.put(Constant.welfare, Constant.DATA_TYPE_WELFARE);
        type2UrlTypeDict.put(Constant.android, Constant.DATA_TYPE_ANDROID);
        type2UrlTypeDict.put(Constant.ios, Constant.DATA_TYPE_IOS);
        type2UrlTypeDict.put(Constant.video, Constant.DATA_TYPE_REST_VIDEO);
        type2UrlTypeDict.put(Constant.resources, Constant.DATA_TYPE_EXTEND_RESOURCES);
        type2UrlTypeDict.put(Constant.js, Constant.DATA_TYPE_JS);
        type2UrlTypeDict.put(Constant.app, Constant.DATA_TYPE_APP);
        type2UrlTypeDict.put(Constant.recommend, Constant.DATA_TYPE_RECOMMEND);
        for (Map.Entry<Integer, String> entry : type2UrlTypeDict.entrySet()) {
            urlType2TypeDict.put(entry.getValue(), entry.getKey());
        }

        urlType2ColorDict.put(Constant.DATA_TYPE_WELFARE, 0xffFFAEC9);
        urlType2ColorDict.put(Constant.DATA_TYPE_ANDROID, 0xff388E3C);
        urlType2ColorDict.put(Constant.DATA_TYPE_IOS, 0xff0377BC);
        urlType2ColorDict.put(Constant.DATA_TYPE_REST_VIDEO, 0xff039588);
        urlType2ColorDict.put(Constant.DATA_TYPE_EXTEND_RESOURCES, 0xff546E7A);
        urlType2ColorDict.put(Constant.DATA_TYPE_JS, 0xffEF6C02);
        urlType2ColorDict.put(Constant.DATA_TYPE_APP, 0xffC02E34);
        urlType2ColorDict.put(Constant.DATA_TYPE_RECOMMEND, 0xff4527A0);
    }
}
