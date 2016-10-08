package com.xiaobozheng.gankio.injection.component;

import com.xiaobozheng.gankio.injection.module.NewDetailFragmentModule;
import com.xiaobozheng.gankio.ui.FragmentScope;
import com.xiaobozheng.gankio.ui.fragment.NewDetailFragment;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by xiaobozheng on 8/24/2016.
 */
@FragmentScope
@Component(modules = NewDetailFragmentModule.class, dependencies = AppComponent.class)
public interface NewDetailFragmentComponent {

    NewDetailFragment inject(NewDetailFragment newDetailFragment);
}
