package com.xiaobozheng.gankio.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by xiaobozheng on 6/29/2016.
 */
public class GankDaily extends Error{
    @SerializedName("results")
    public DailyResults results;
    @SerializedName("category")
    public ArrayList<String> category;

    public class DailyResults{
        @SerializedName("福利")
        public ArrayList<GankDataBean> welfareData;
        @SerializedName("Android")
        public ArrayList<GankDataBean> androidData;

        @SerializedName("iOS")
        public ArrayList<GankDataBean> iosData;

        @SerializedName("前端")
        public ArrayList<GankDataBean> jsData;

        @SerializedName("休息视频")
        public ArrayList<GankDataBean> videoData;

        @SerializedName("拓展资源")
        public ArrayList<GankDataBean> resourcesData;

        @SerializedName("App")
        public ArrayList<GankDataBean> appData;

        @SerializedName("瞎推荐")
        public ArrayList<GankDataBean> recommendData;
    }

}
