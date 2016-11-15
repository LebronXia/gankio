package com.xiaobozheng.gankio.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.ui.fragment.CategoryListFragment;
import com.xiaobozheng.gankio.util.ViewUtils;

import java.util.List;

/**
 * Created by xiaobozheng on 7/25/2016.
 */
public class CategoryPageAdapter extends FragmentPagerAdapter{

    public static final String BUNDLE_TYPE = "bundle_type";
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
        Fragment fragment;
        if (!"福利".equals(titles.get(position))){
            fragment = ViewUtils.createFragment(CategoryListFragment.class, false);
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_TYPE, titles.get(position));
            fragment.setArguments(bundle);
        } else {
            fragment = ViewUtils.createFragment(CategoryListFragment.class, false);
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_TYPE, titles.get(position));
           // Logger.d("position" + position);
           // Logger.d(titles.get(position) + "在CategoryPageAdapter里的mType");
            fragment.setArguments(bundle);
        }

        return fragment;
    }
}
