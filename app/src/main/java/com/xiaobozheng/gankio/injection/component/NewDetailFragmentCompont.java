package com.xiaobozheng.gankio.injection.component;

import com.xiaobozheng.gankio.injection.module.NewDetailFragmentModule;
import com.xiaobozheng.gankio.ui.FragmentScope;
import com.xiaobozheng.gankio.ui.fragment.NewDetailFragment;

import dagger.Subcomponent;

/**
 * Created by xiaobozheng on 8/24/2016.
 */
@FragmentScope
@Subcomponent(modules = NewDetailFragmentModule.class)
public interface NewDetailFragmentCompont {

    NewDetailFragment inject(NewDetailFragment newDetailFragment);
}
