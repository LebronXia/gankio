package com.xiaobozheng.gankio.mvp.presenter.impl;

import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.Constant.Constant;
import com.xiaobozheng.gankio.data.model.CategoryData;
import com.xiaobozheng.gankio.data.model.GankDataBean;
import com.xiaobozheng.gankio.mvp.model.impl.CategoryModel;
import com.xiaobozheng.gankio.mvp.model.impl.RecycentModel;
import com.xiaobozheng.gankio.mvp.presenter.BasePresenter;
import com.xiaobozheng.gankio.mvp.view.Impl.CategoryView;
import com.xiaobozheng.gankio.mvp.view.Impl.RecentView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class CategoryPresent extends BasePresenter<CategoryView>{

    private CategoryModel mCategoryModel;

    public CategoryPresent(CategoryView view){
        attachView(view);
        mCategoryModel = new CategoryModel();
    }

    //获取类型数据
    public void getCategory(){
        CategoryPresent.this.getMvpView().showData(mCategoryModel.getCategory());
    }

    //获取分类的各个数据
    public void getCategoryData(String mType, int page, boolean refresh){
        CategoryPresent.this.getMvpView().showLoading();
        Subscriber<List<GankDataBean>> subscriber = new Subscriber<List<GankDataBean>>() {
            @Override
            public void onCompleted() {
                CategoryPresent.this.getMvpView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                CategoryPresent.this.getMvpView().hideLoading();
            }

            @Override
            public void onNext(List<GankDataBean> gankDataBeanList) {
                CategoryPresent.this.getMvpView().hideLoading();
                if (gankDataBeanList != null){
                    Logger.d(gankDataBeanList.get(0).getDesc());
                    CategoryPresent.this.getMvpView().showCategoyData(gankDataBeanList);
                }
            }
        };

        mCategoryModel.getCategotyData(subscriber, mType, Constant.DEFAULT_DATA_SIZE, page);
        subscribe(subscriber);
    }

}
