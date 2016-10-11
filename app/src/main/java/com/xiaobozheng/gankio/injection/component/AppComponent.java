package com.xiaobozheng.gankio.injection.component;

import android.app.Application;

import com.xiaobozheng.gankio.data.API.GankApiManagerService;
import com.xiaobozheng.gankio.injection.module.ApiServiceModule;
import com.xiaobozheng.gankio.injection.module.AppModule;
import com.xiaobozheng.gankio.injection.module.CategoryFragmentModule;
import com.xiaobozheng.gankio.injection.module.NewDetailFragmentModule;
import com.xiaobozheng.gankio.ui.fragment.CategoryFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by xiaobozheng on 8/23/2016.
 */
@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {

    NewDetailFragmentComponent plus(NewDetailFragmentModule module);

    CategoryFragmentComponent plus(CategoryFragmentModule module);

}
