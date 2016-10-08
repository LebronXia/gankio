package com.xiaobozheng.gankio.mvp.presenter;

import android.view.View;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by xiaobozheng on 6/22/2016.
 */
public class BasePresenter<V> implements Presenter<V> {

    public V mvpView;
    public CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(V view) {
        this.mvpView = view;
        //负责管理CompositeSubscription
        this.mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }

    public V getMvpView(){
        return mvpView;
    }

    /**
     * RxJava解除注册
     */
    public void onUnsubscribe(){
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    public void subscribe(Subscriber subscriber){
        mCompositeSubscription.add(subscriber);
    }
}
