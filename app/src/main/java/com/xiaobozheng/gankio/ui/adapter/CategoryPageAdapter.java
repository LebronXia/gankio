package com.xiaobozheng.gankio.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiaobozheng.gankio.ui.fragment.CategoryListFragment;

import java.util.List;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class CategoryPageAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragment;
    private List<String> titles;

    public CategoryPageAdapter(FragmentManager fm , List<String> list) {
        super(fm);
        this.titles = list;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    //显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position % titles.size());
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new  CategoryListFragment();

        return fragment;
    }
}
