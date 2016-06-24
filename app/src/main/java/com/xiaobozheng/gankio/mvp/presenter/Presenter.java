package com.xiaobozheng.gankio.mvp.presenter;

/**
 * Created by xiaobozheng on 6/23/2016.
 */
public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
