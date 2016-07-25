package com.xiaobozheng.gankio.mvp.model.impl;

import com.xiaobozheng.gankio.Constant.Constant;
import com.xiaobozheng.gankio.mvp.model.ICategoryModel;
import com.xiaobozheng.gankio.util.GankTypeDict;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class CategoryModel implements ICategoryModel{
    //获取类型
    @Override
    public List<String> getCategory() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < Constant.GankCategory.length; i++){
            stringList.add(Constant.GankCategory[i]);
        }
        return stringList;
    }
}
