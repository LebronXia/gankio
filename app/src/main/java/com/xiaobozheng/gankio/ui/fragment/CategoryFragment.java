package com.xiaobozheng.gankio.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.mvp.presenter.impl.CategoryPresent;
import com.xiaobozheng.gankio.mvp.view.Impl.CategoryView;
import com.xiaobozheng.gankio.ui.adapter.CategoryPageAdapter;
import com.xiaobozheng.gankio.ui.base.BaseFragment;

import java.util.List;

import butterknife.Bind;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class CategoryFragment extends BaseFragment implements CategoryView{

    @Bind(R.id.cate_tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.cate_viewpager)
    ViewPager mViewPager;
    private CategoryPresent mCategoryPresent;
    private CategoryPageAdapter mCategoryPageAdapter;
    private Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initViews(View self, Bundle savedInstanceState) {
        this.mCategoryPresent = new CategoryPresent(this);
        this.context = getActivity();
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        mCategoryPresent.getCategory();
    }

    @Override
    public void showData(List<String> list) {
        if(list != null){
            Logger.d(list.get(0)+ "标签列表的值");
            mCategoryPageAdapter = new CategoryPageAdapter(getChildFragmentManager(), list);
            mViewPager.setAdapter(mCategoryPageAdapter);
            //将TabLayout和VIewPager关联起来
            mTabLayout.setupWithViewPager(mViewPager);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }
}
