package com.xiaobozheng.gankio.mvp.presenter.impl;

import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.util.FixedPreloadSizeProvider;
import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.Constant.Constant;
import com.xiaobozheng.gankio.data.model.CategoryData;
import com.xiaobozheng.gankio.data.model.GankDataBean;
import com.xiaobozheng.gankio.mvp.model.impl.CategoryModel;
import com.xiaobozheng.gankio.mvp.model.impl.RecycentModel;
import com.xiaobozheng.gankio.mvp.presenter.BasePresenter;
import com.xiaobozheng.gankio.mvp.view.Impl.CategoryView;
import com.xiaobozheng.gankio.mvp.view.Impl.RecentView;
import com.xiaobozheng.gankio.util.Stringutils;
import com.xiaobozheng.gankio.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class CategoryPresent extends BasePresenter<CategoryView>{

    private CategoryModel mCategoryModel;
    private Realm realm;

    public CategoryPresent(CategoryView view, CategoryModel categoryModel){
        attachView(view);
        mCategoryModel = categoryModel;
    }

    //获取类型数据
    public void getCategory(){
        CategoryPresent.this.getMvpView().showData(mCategoryModel.getCategory());
    }

    //获取分类的各个数据
    public void getCategoryData(String mType, int page, boolean refresh){
        CategoryPresent.this.getMvpView().showLoading();

        //Observable<List<GankDataBean>> fromNetWork =

        Subscriber<CategoryData> subscriber = new Subscriber<CategoryData>() {
            @Override
            public void onCompleted() {
                CategoryPresent.this.getMvpView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                CategoryPresent.this.getMvpView().hideLoading();
                CategoryPresent.this.getMvpView().showError();
                if(refresh){
                    //查询全部数据
                    // List<GankDataBean> gankDataBeanList = realm.where(GankDataBean.class).findAll();
                    RealmResults<GankDataBean> realmResults = null;
                    if (mType.equals("ALL")){
                        realmResults = realm.where(GankDataBean.class)
                                .equalTo("isAll", true).findAll();
                    } else {

                        realmResults = realm.where(GankDataBean.class)
                                .equalTo("type", mType)
                                .equalTo("isAll", false).findAll();
                    }

                    if (realmResults != null && realmResults.size() > 0){
                        CategoryPresent.this.getMvpView().showCategoyData(realmResults);
                    }
                } else {
                    CategoryPresent.this.getMvpView().showError();
                }
            }

            @Override
            public void onNext(CategoryData mCategoryData) {
                CategoryPresent.this.getMvpView().hideLoading();
                if (mCategoryData.results != null && mCategoryData.results.size() > 0){
                    CategoryPresent.this.getMvpView().showCategoyData(mCategoryData.results);
                   // Logger.d(mCategoryData.results.get(0).getDesc());
                } else {
                    showToast("没有干货了");
                }
            }
        };

        mCategoryModel.getCategotyData(subscriber, mType, Constant.DEFAULT_DATA_SIZE, page);
        subscribe(subscriber);
    }

}
