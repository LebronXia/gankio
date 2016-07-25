package com.xiaobozheng.gankio.mvp.view.Impl;

import com.xiaobozheng.gankio.data.model.GankDaily;
import com.xiaobozheng.gankio.data.model.GankDataBean;
import com.xiaobozheng.gankio.data.model.RecentlyBean;
import com.xiaobozheng.gankio.mvp.view.IBaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobozheng on 6/23/2016.
 */
public interface RecentView extends IBaseView {
    void showData(ArrayList<ArrayList<GankDataBean>> mGankList);
}
