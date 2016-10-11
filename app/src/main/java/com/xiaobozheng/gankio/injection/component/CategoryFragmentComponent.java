package com.xiaobozheng.gankio.injection.component;

import com.xiaobozheng.gankio.injection.module.CategoryFragmentModule;
import com.xiaobozheng.gankio.injection.module.NewDetailFragmentModule;
import com.xiaobozheng.gankio.ui.FragmentScope;
import com.xiaobozheng.gankio.ui.fragment.CategoryFragment;
import com.xiaobozheng.gankio.ui.fragment.CategoryListFragment;

import dagger.Subcomponent;

/**
 * Created by xiaobozheng on 10/11/2016.
 */
@FragmentScope
@Subcomponent(modules = CategoryFragmentModule.class)
public interface CategoryFragmentComponent {

    CategoryListFragment inject(CategoryListFragment categoryListFragment);
}
