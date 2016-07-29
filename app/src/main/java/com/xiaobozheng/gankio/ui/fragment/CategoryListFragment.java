package com.xiaobozheng.gankio.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.camnter.easyrecyclerview.widget.EasyRecyclerView;
import com.camnter.easyrecyclerview.widget.decorator.EasyBorderDividerItemDecoration;
import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.data.model.GankDataBean;
import com.xiaobozheng.gankio.mvp.presenter.impl.CategoryPresent;
import com.xiaobozheng.gankio.mvp.view.Impl.CategoryView;
import com.xiaobozheng.gankio.ui.adapter.CategoryListAdapter;
import com.xiaobozheng.gankio.ui.adapter.CategoryPageAdapter;
import com.xiaobozheng.gankio.ui.base.BaseFragment;
import com.xiaobozheng.gankio.widget.MultiSwipeRefreshLayout;

import java.util.List;

import butterknife.Bind;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class CategoryListFragment extends BaseFragment implements CategoryView{

    @Bind(R.id.rv_category)
    EasyRecyclerView mEasyRecyclerView;
    @Bind(R.id.multi_swipe_refresh)
    MultiSwipeRefreshLayout mSwipeRefreshLayout;

    private EasyBorderDividerItemDecoration dataDecoration;
    private EasyBorderDividerItemDecoration welfareDecoration;
    private LinearLayoutManager mLinearLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private CategoryListAdapter mCategoryListAdapter;
    public int mCurrentPage = 1;
    private String mType;
    private CategoryPresent mCategoryPresent;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_categorylist;
    }

    @Override
    protected void initViews(View self, Bundle savedInstanceState) {
        mCategoryPresent = new CategoryPresent(this);
        if (mSwipeRefreshLayout != null){
            mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_3,
                    R.color.refresh_progress_2, R.color.refresh_progress_1);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mCategoryPresent.getCategoryData(mType, mCurrentPage, true);
                }
            });
        }
        //设置Data的上下间隔为8dp
        this.dataDecoration = new EasyBorderDividerItemDecoration(this.getResources().getDimensionPixelOffset(R.dimen.data_border_divider_height),
                this.getResources()
                        .getDimensionPixelOffset(R.dimen.data_border_padding_infra_spans));
        //设置welfare上下间隔
        this.welfareDecoration = new EasyBorderDividerItemDecoration(this.getResources().getDimensionPixelOffset(R.dimen.welfare_border_divider_height),
                this.getResources()
                        .getDimensionPixelOffset(R.dimen.welfare_border_padding_infra_spans));

        this.mEasyRecyclerView.addItemDecoration(this.dataDecoration);
        //线性布局的管理器
        this.mLinearLayoutManager = (LinearLayoutManager) this.mEasyRecyclerView.getLayoutManager();
        //瀑布流的管理器
        this.mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        mType = getArguments().getString(CategoryPageAdapter.BUNDLE_TYPE);
        mCategoryPresent.getCategoryData(mType, mCurrentPage, true);

    }

    private void clearDecoration(){
        this.mEasyRecyclerView.removeItemDecoration(this.dataDecoration);
        this.mEasyRecyclerView.removeItemDecoration(this.welfareDecoration);
    }
    @Override
    public void showError() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showCategoyData(List<GankDataBean> gankDataBeanList) {
        if(gankDataBeanList != null){
            if (!mType.equals("福利")){
                this.clearDecoration();
                this.mEasyRecyclerView.setLayoutManager(this.mLinearLayoutManager);
                this.mEasyRecyclerView.addItemDecoration(this.dataDecoration);
                mCategoryListAdapter = new CategoryListAdapter(getActivity(), gankDataBeanList);
            } else {
                this.clearDecoration();
                this.mEasyRecyclerView.setLayoutManager(this.mStaggeredGridLayoutManager);
                this.mEasyRecyclerView.addItemDecoration(this.welfareDecoration);
            }
        }
    }

    @Override
    public void showData(List<String> list) {

    }
}
