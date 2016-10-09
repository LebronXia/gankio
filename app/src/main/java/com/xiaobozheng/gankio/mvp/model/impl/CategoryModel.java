package com.xiaobozheng.gankio.mvp.model.impl;

import com.xiaobozheng.gankio.Constant.Constant;
import com.xiaobozheng.gankio.data.API.ApiManager;
import com.xiaobozheng.gankio.data.model.GankDataBean;
import com.xiaobozheng.gankio.mvp.model.ICategoryModel;
import com.xiaobozheng.gankio.util.GankTypeDict;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class CategoryModel implements ICategoryModel{

    private ApiManager mApiManager;

    //获取类型
    @Override
    public List<String> getCategory() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < Constant.GankCategory.length; i++){
            stringList.add(Constant.GankCategory[i]);
        }
        return stringList;
    }

    @Override
    public void getCategotyData(Subscriber subscriber, String type, int size, int page) {
        mApiManager.getCategoryData(subscriber, type, size, page);
    }
}
