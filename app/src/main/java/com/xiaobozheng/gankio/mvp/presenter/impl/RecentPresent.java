package com.xiaobozheng.gankio.mvp.presenter.impl;

import android.support.v7.widget.RecyclerView;

import com.xiaobozheng.gankio.data.model.RecentlyBean;
import com.xiaobozheng.gankio.mvp.model.IRecycentModel;
import com.xiaobozheng.gankio.mvp.model.impl.RecycentModel;
import com.xiaobozheng.gankio.mvp.presenter.IRecentPresent;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by xiaobozheng on 6/22/2016.
 */
public class RecentPresent implements IRecentPresent,IRecycentModel.OnRecycentListener{
    private RecycentModel mRecycentModel;

    private RecyclerView mRecyclerView;
    private CompositeSubscription mSubscription;
    @Override
    public void subscribe() {
        Subscription subscription = mRecycentModel
                .getRecentlyData();
    }

    @Override
    public void unsubscribe() {
        //接触绑定
        mSubscription.clear();
    }

    @Override
    public void getRecentData(int year, int month, int day) {

    }

    @Override
    public void onSuccess(RecentlyBean mRecentlyBean) {

    }

    @Override
    public void onFailure(Throwable e) {

    }
}
