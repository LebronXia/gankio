package com.xiaobozheng.gankio.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.ui.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class CategoryFragment extends BaseFragment{

    @Bind(R.id.cate_tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.cate_viewpager)
    ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initViews(View self, Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }
}
