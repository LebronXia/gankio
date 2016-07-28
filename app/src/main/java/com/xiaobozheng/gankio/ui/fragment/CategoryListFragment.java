package com.xiaobozheng.gankio.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.camnter.easyrecyclerview.widget.EasyRecyclerView;
import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.ui.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class CategoryListFragment extends BaseFragment{

    @Bind(R.id.rv_category)
    EasyRecyclerView mEasyRecyclerView;

    private String mType;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_categorylist;
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
