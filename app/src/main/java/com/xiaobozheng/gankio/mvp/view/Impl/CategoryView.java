package com.xiaobozheng.gankio.mvp.view.Impl;

import com.xiaobozheng.gankio.mvp.view.IBaseView;

import java.util.List;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public interface CategoryView extends IBaseView{

    void showData(List<String> list);
}
