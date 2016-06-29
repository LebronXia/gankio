package com.xiaobozheng.gankio.mvp.model.impl;

import com.xiaobozheng.gankio.data.API.ApiManager;
import com.xiaobozheng.gankio.data.model.RecentlyBean;
import com.xiaobozheng.gankio.mvp.model.IRecycentModel;

import java.util.List;
import java.util.Objects;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by xiaobozheng on 6/22/2016.
 */
public class RecycentModel implements IRecycentModel {

    private OnRecycentListener mOnRecycentListener;

    public RecycentModel(){
    }

    @Override
    public void getRecentlyData(Subscriber subscriber,int year, int month, int day) {
        ApiManager.getInstance().getRecentlyData(subscriber, year, month, day);
    }
}
