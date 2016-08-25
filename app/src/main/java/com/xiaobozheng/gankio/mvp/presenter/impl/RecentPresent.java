package com.xiaobozheng.gankio.mvp.presenter.impl;

import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.data.model.GankDataBean;
import com.xiaobozheng.gankio.mvp.model.impl.RecycentModel;
import com.xiaobozheng.gankio.mvp.presenter.BasePresenter;
import com.xiaobozheng.gankio.mvp.view.Impl.RecentView;
import com.xiaobozheng.gankio.ui.fragment.NewDetailFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import rx.Subscriber;

/**
 * Created by xiaobozheng on 6/22/2016.
 */
public class RecentPresent extends BasePresenter<RecentView>{
    private static final int DAY_OF_MILLISECOND = 24*60*60*1000;
    private RecycentModel mRecycentModel;
    //private RecentView mRecentView;
    private NewDetailFragment mNewDetailFragment;

    public RecentPresent(NewDetailFragment view,
                         RecycentModel recycentModel){
        attachView(view);
        mRecycentModel = recycentModel;
    }

    /**
     * 获得最近的数据
     * @param date
     */
    public void getRecentData(Date date) {
        RecentPresent.this.getMvpView().showLoading();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        Logger.e(mDay + ":天数");
        Subscriber subscriber = new Subscriber<ArrayList<ArrayList<GankDataBean>>>() {
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
            public void onNext(ArrayList<ArrayList<GankDataBean>> mGankList) {
                // 如果有一天数据为空，则继续获取下一天的数据
               if (mGankList.isEmpty()){
                   getRecentData(new Date(date.getTime()-DAY_OF_MILLISECOND));
                   Logger.d("没数据");
               } else {
                   Logger.d("完成");
                   RecentPresent.this.getMvpView().showData(mGankList);
               }

            }
        };
        mRecycentModel.getRecentlyData(subscriber,mYear,mMonth,mDay);
        //注册
        subscribe(subscriber);
    }

}
