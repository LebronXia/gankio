package com.xiaobozheng.gankio.mvp.model;

import com.xiaobozheng.gankio.data.model.GankDataBean;

import java.util.List;

import rx.Subscriber;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public interface ICategoryModel {
    List<String> getCategory();
    void getCategotyData(Subscriber subscriber, String type, int size, int page);
}
