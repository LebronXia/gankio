package com.xiaobozheng.gankio.mvp.presenter.impl;

import com.xiaobozheng.gankio.data.model.CategoryData;
import com.xiaobozheng.gankio.mvp.model.impl.CategoryModel;
import com.xiaobozheng.gankio.mvp.model.impl.RecycentModel;
import com.xiaobozheng.gankio.mvp.presenter.BasePresenter;
import com.xiaobozheng.gankio.mvp.view.Impl.CategoryView;
import com.xiaobozheng.gankio.mvp.view.Impl.RecentView;

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
    private void getCategory(){
        CategoryPresent.this.getMvpView().showData(mCategoryModel.getCategory());
    }

}
