package com.xiaobozheng.gankio.ui.activity;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.ui.base.BaseActivity;
import com.xiaobozheng.gankio.ui.fragment.DailyDetailFragment;
import com.xiaobozheng.gankio.ui.fragment.NewDetailFragment;
import com.xiaobozheng.gankio.ui.fragment.SortFragment;

import butterknife.Bind;


public class MainActivity extends BaseActivity {
    @Bind(R.id.root_view)
    public DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private Fragment currentFragment, lastFragment;
    private int lastMenuItemId;
    private NewDetailFragment mNewDetailFragment;
    private DailyDetailFragment mDailyDetailFragment;
    private SortFragment mSortFragment;

    @Override
    protected void initView() {
        mToolbar.setTitle("Toolbar");
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.app_name,R.string.app_name);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        currentFragment = new NewDetailFragment();
                        break;
                    case R.id.navigation_daily:
                        currentFragment = new DailyDetailFragment();
                        break;
                    case R.id.navigation_sort:
                        currentFragment = new SortFragment();
                        break;
                }
                if (mDrawerLayout != null) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });
        switchFragment();
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    private void switchFragment(){
        if (currentFragment instanceof NewDetailFragment){
            switchContent(currentFragment,getString(R.string.common_home));
        } else if(currentFragment instanceof DailyDetailFragment){
            switchContent(currentFragment,getString(R.string.common_daily));
        } else if (currentFragment instanceof SortFragment){
            switchContent(currentFragment,getString(R.string.common_sort));
        }
    }
    //切换Fragment,不会重新加载
    public void switchContent(Fragment fragment, String tilte){
        if (currentFragment != fragment){
            currentFragment = fragment;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!fragment.isAdded()){
                transaction.hide(lastFragment).add(R.id.framelayout, currentFragment);
            } else {
                transaction.hide(lastFragment).show(currentFragment).commit();
            }
            lastFragment = currentFragment;
        }
    }

}
