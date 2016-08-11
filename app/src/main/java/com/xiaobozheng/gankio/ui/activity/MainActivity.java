package com.xiaobozheng.gankio.ui.activity;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.orhanobut.logger.Logger;
import com.umeng.analytics.MobclickAgent;
import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.ui.base.BaseActivity;
import com.xiaobozheng.gankio.ui.base.BaseFragment;
import com.xiaobozheng.gankio.ui.fragment.CategoryFragment;
import com.xiaobozheng.gankio.ui.fragment.DailyDetailFragment;
import com.xiaobozheng.gankio.ui.fragment.NewDetailFragment;
import com.xiaobozheng.gankio.util.ViewUtils;


import butterknife.Bind;


public class MainActivity extends BaseActivity {
    @Bind(R.id.root_view)
    public DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;

    private FragmentManager mFragmentManager;
    private BaseFragment mCurrentFragment;
    private NewDetailFragment mNewDetailFragment;
    private DailyDetailFragment mDailyDetailFragment;
    private CategoryFragment mCategoryFragment;

    private Class<?> clazz = null;

    @Override
    protected void initView() {
        mToolbar.setTitle("Toolbar");
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.app_name,R.string.app_name);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        //mLastFragment = new NewDetailFragment();
                        clazz = NewDetailFragment.class;
                        mToolbar.setTitle("最新");
                        break;
                    case R.id.navigation_daily:
                        showToast("功能开发中");

                        //currentFragment = new DailyDetailFragment();
                        break;
                    case R.id.navigation_sort:
                        showToast("功能开发中");
                        clazz = CategoryFragment.class;
                        mToolbar.setTitle("分类浏览");
                        // currentFragment = new SortFragment();
                        break;
                }
                switchFragment(clazz);
                if (mDrawerLayout != null) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });

        mFragmentManager = getSupportFragmentManager();
        mCurrentFragment =  (BaseFragment)mFragmentManager.findFragmentById(R.id.framelayout);
        if (mCurrentFragment == null){
            //创建单例，保证mCurrentFragment始终是同一个，踩不会导致点击一样的还切换
            mCurrentFragment = ViewUtils.createFragment(NewDetailFragment.class);
            mFragmentManager.beginTransaction().add(R.id.framelayout, mCurrentFragment).commit();
        }
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }


    private void switchFragment(Class<?> clazz){
        if (clazz == null) return;
        BaseFragment to = ViewUtils.createFragment(clazz);
            if (!to.isAdded()) { // 先判断是否被add过
                mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.framelayout, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                Logger.d("switchFragment");
                mFragmentManager.beginTransaction().hide(mCurrentFragment).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            mCurrentFragment = to;

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


}
