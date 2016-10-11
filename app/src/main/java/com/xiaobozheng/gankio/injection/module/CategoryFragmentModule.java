package com.xiaobozheng.gankio.injection.module;

import com.xiaobozheng.gankio.mvp.model.impl.CategoryModel;
import com.xiaobozheng.gankio.mvp.presenter.impl.CategoryPresent;
import com.xiaobozheng.gankio.ui.FragmentScope;
import com.xiaobozheng.gankio.ui.fragment.CategoryFragment;
import com.xiaobozheng.gankio.ui.fragment.CategoryListFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xiaobozheng on 10/11/2016.
 */
@Module
public class CategoryFragmentModule {

    private CategoryListFragment mCategoryListFragment;

    public CategoryFragmentModule(CategoryListFragment categoryListFragment){
        mCategoryListFragment = categoryListFragment;
    }

    @Provides
    @FragmentScope
    CategoryListFragment providerCategoryListFragment(){
        return mCategoryListFragment;
    }


    @Provides
    @FragmentScope
    CategoryPresent providerCategoryPresent(CategoryListFragment categoryListFragment, CategoryModel categoryModel){
        return new CategoryPresent(categoryListFragment, categoryModel);
    }

}
