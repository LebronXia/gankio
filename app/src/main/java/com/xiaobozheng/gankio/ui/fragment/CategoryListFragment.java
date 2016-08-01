package com.xiaobozheng.gankio.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.camnter.easyrecyclerview.widget.EasyRecyclerView;
import com.camnter.easyrecyclerview.widget.decorator.EasyBorderDividerItemDecoration;
import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.Constant.Constant;
import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.data.model.GankDataBean;
import com.xiaobozheng.gankio.mvp.presenter.impl.CategoryPresent;
import com.xiaobozheng.gankio.mvp.view.Impl.CategoryView;
import com.xiaobozheng.gankio.ui.adapter.CategoryListAdapter;
import com.xiaobozheng.gankio.ui.adapter.CategoryPageAdapter;
import com.xiaobozheng.gankio.ui.base.BaseFragment;
import com.xiaobozheng.gankio.util.GankTypeDict;
import com.xiaobozheng.gankio.widget.MultiSwipeRefreshLayout;

import java.util.List;

import butterknife.Bind;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class CategoryListFragment extends BaseFragment implements CategoryView {

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
    /** * 是否能添加更多 */
    private boolean canLoadingMore = false;
    List<GankDataBean> mGankDataBeanList;
    private String mType;
    private CategoryPresent mCategoryPresent;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_categorylist;
    }

    @Override
    protected void initViews(View self, Bundle savedInstanceState) {
        mCategoryPresent = new CategoryPresent(this);

        mCategoryListAdapter = new CategoryListAdapter(getActivity());
        mEasyRecyclerView.setAdapter(mCategoryListAdapter);
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

        if (mSwipeRefreshLayout != null){
            //设置下拉刷新的颜色
            mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_3,
                    R.color.refresh_progress_2, R.color.refresh_progress_1);
            //设置顶部下拉刷新
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mCurrentPage = 1;
                    mCategoryPresent.getCategoryData(mType, mCurrentPage, true);
                }
            });
        }

        mEasyRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            //判断是否下拉到底部
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mCategoryListAdapter == null || mCategoryListAdapter.getItemCount() == 0) {
                    return;
                }

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    Logger.d("滚动中.....");

                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        //加载更多功能的代码
                        Logger.d("到底部了.....");
                        mCurrentPage++;
                        mSwipeRefreshLayout.setRefreshing(true);
                        mCategoryPresent.getCategoryData(mType, mCurrentPage, true);
                        canLoadingMore = false;

                    }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dy > 0) {
                    //大于0表示正在向右滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0表示停止或向左滚动
                    isSlidingToLast = false;
                }

            }
        });
    }

    @Override
    protected void initData() {
        canLoadingMore = true;
        mType = getArguments().getString(CategoryPageAdapter.BUNDLE_TYPE);
        //根据mType的类型改变布局的下划线
        switch (mType){
            case Constant.DATA_TYPE_WELFARE:
                this.clearDecoration();
                this.mEasyRecyclerView.setLayoutManager(this.mStaggeredGridLayoutManager);
                this.mEasyRecyclerView.addItemDecoration(this.welfareDecoration);
                break;
            default:
                this.clearDecoration();
                this.mEasyRecyclerView.setLayoutManager(this.mLinearLayoutManager);
                this.mEasyRecyclerView.addItemDecoration(this.dataDecoration);
                break;
        }
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
    if (gankDataBeanList != null && gankDataBeanList.size() > 0){
        if (mCurrentPage == 1){
            mCategoryListAdapter.setType(mType);
            mCategoryListAdapter.setList(gankDataBeanList);
        } else {
            mCategoryListAdapter.setType(mType);
            mCategoryListAdapter.addAll(gankDataBeanList);
        }
        canLoadingMore = true;
        mCategoryListAdapter.notifyDataSetChanged();
    } else {
        canLoadingMore = false;
    }

    }

    @Override
    public void showData(List<String> list) {

    }
}
