package com.xiaobozheng.gankio.mvp.presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.xiaobozheng.gankio.util.AppUtils;
import com.xiaobozheng.gankio.util.ToastUtils;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by xiaobozheng on 6/22/2016.
 */
public class BasePresenter<V> implements Presenter<V> {

    public V mvpView;
    public CompositeSubscription mCompositeSubscription;
    private Context context = AppUtils.getAppContext();

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

    public void showToast(String msg) {
        this.showToast(msg, Toast.LENGTH_SHORT);
    }


    public void showToast(String msg, int duration) {
        if (msg == null) return;
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            ToastUtils.show(context, msg, duration);
        } else {
            ToastUtils.show(context, msg, ToastUtils.LENGTH_SHORT);
        }
    }
}
