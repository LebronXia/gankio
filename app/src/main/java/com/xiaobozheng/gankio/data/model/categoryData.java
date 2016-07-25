package com.xiaobozheng.gankio.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class CategoryData extends Error {

    @SerializedName("results")
    private List<GankDataBean> results;
}
