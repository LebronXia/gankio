package com.xiaobozheng.gankio.mvp.presenter.impl;

import com.camnter.easyrecyclerview.adapter.EasyRecyclerViewAdapter;
import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.data.model.GankDaily;
import com.xiaobozheng.gankio.data.model.RecentlyBean;
import com.xiaobozheng.gankio.mvp.model.impl.RecycentModel;
import com.xiaobozheng.gankio.mvp.presenter.BasePresenter;
import com.xiaobozheng.gankio.mvp.view.RecentView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by xiaobozheng on 6/22/2016.
 */
public class RecentPresent extends BasePresenter<RecentView>{
    private RecycentModel mRecycentModel;
    private RecentView mRecentView;

    public RecentPresent(RecentView view){
        attachView(view);
        mRecycentModel = new RecycentModel();

    }

    public void getRecentData(int year, int month, int day) {
        RecentPresent.this.getMvpView().showLoading();
        Subscriber subscriber = new Subscriber<GankDaily>() {
            @Override
            public void onCompleted() {
                RecentPresent.this.getMvpView().hideLoading();
                Logger.d("达到");
            }

            @Override
            public void onError(Throwable e) {
                RecentPresent.this.getMvpView().showError();
                Logger.d("错误");
            }

            @Override
            public void onNext(GankDaily gankDailiy) {
                Logger.d("完成");
                RecentPresent.this.getMvpView().showData(gankDailiy);
            }
        };
        mRecycentModel.getRecentlyData(subscriber,year,month,day);
        //注册
        subscribe(subscriber);
    }

}
