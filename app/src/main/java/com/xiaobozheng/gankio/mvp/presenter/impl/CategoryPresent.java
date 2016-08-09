package com.xiaobozheng.gankio.mvp.presenter.impl;

import android.util.Log;

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

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Subscriber;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class CategoryPresent extends BasePresenter<CategoryView>{

    private CategoryModel mCategoryModel;
    private Realm realm;

    public CategoryPresent(CategoryView view){
        attachView(view);
        mCategoryModel = new CategoryModel();
        realm = Realm.getDefaultInstance();

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
                CategoryPresent.this.getMvpView().showError();
                if(refresh){
                    //查询全部数据
                    // List<GankDataBean> gankDataBeanList = realm.where(GankDataBean.class).findAll();
                    RealmResults<GankDataBean> realmResults = null;
                    if (mType.equals("ALL")){
                        Logger.d(mType + "~~");
                        realmResults = realm.where(GankDataBean.class)
                                .equalTo("isAll", true).findAll();
                    } else {
                        Logger.d(mType + "~~");
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
            public void onNext(List<GankDataBean> gankDataBeanList) {
                CategoryPresent.this.getMvpView().hideLoading();
                if (gankDataBeanList != null){
                    CategoryPresent.this.getMvpView().showCategoyData(gankDataBeanList);
                    Logger.d(gankDataBeanList.get(0).getDesc());

                    for (GankDataBean gankDataBean : gankDataBeanList){
                       // GankDataBean gankData = realm.createObject(GankDataBean.class);

                        if (mType.equals("ALL")){
                            gankDataBean.setAll(true);
                        }
                        if (realm.isInTransaction()){
                            realm.commitTransaction();
                        }
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(gankDataBean);
                        realm.commitTransaction();
                    }
                }
            }
        };

        mCategoryModel.getCategotyData(subscriber, mType, Constant.DEFAULT_DATA_SIZE, page);
        subscribe(subscriber);
    }

}
