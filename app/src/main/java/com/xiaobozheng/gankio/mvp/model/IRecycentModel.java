package com.xiaobozheng.gankio.mvp.model;

import com.xiaobozheng.gankio.data.model.GankDataBean;
import com.xiaobozheng.gankio.data.model.RecentlyBean;
import com.xiaobozheng.gankio.mvp.model.impl.RecycentModel;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by xiaobozheng on 6/22/2016.
 */
public interface IRecycentModel {

    /**
     * 获取最近一天的数据
     * @param year
     * @param month
     * @param day
     */
    void getRecentlyData(Subscriber subscriber, int year, int month, int day);

    /**
     * 回调接口
     */
    interface OnRecycentListener{
        void onSuccess(RecentlyBean mRecentlyBean);
        void onFailure(Throwable e);
    }
}
