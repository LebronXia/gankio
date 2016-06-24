package com.xiaobozheng.gankio.mvp.presenter.impl;

import com.xiaobozheng.gankio.data.model.RecentlyBean;
import com.xiaobozheng.gankio.mvp.model.impl.RecycentModel;
import com.xiaobozheng.gankio.mvp.presenter.BasePresenter;
import com.xiaobozheng.gankio.mvp.view.RecentView;

import rx.Subscriber;

/**
 * Created by xiaobozheng on 6/22/2016.
 */
public class RecentPresent extends BasePresenter<RecentView>{
    private RecycentModel mRecycentModel;
    private RecentView mRecentView;

    public RecentPresent(RecentView view){
        attachView(view);
    }

    public void getRecentData(int year, int month, int day) {
        mRecentView.showLoading();
        Subscriber subscriber = new Subscriber<RecentlyBean>() {
            @Override
            public void onCompleted() {
                mRecentView.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                mRecentView.showError();
            }

            @Override
            public void onNext(RecentlyBean mRecentBean) {
                mRecentView.showData(mRecentBean);
            }
        };
        mRecycentModel.getRecentlyData(subscriber,year,month,day);
        //注册
        subscribe(subscriber);
    }

}
