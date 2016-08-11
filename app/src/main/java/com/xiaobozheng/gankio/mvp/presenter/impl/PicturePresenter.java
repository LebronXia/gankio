package com.xiaobozheng.gankio.mvp.presenter.impl;

import com.xiaobozheng.gankio.mvp.presenter.BasePresenter;
import com.xiaobozheng.gankio.mvp.view.Impl.PictureView;

import rx.Subscriber;

/**
 * Created by xiaobozheng on 8/10/2016.
 */
public class PicturePresenter extends BasePresenter<PictureView>{

    public PicturePresenter(PictureView pictureView){
        attachView(pictureView);
    }

}
