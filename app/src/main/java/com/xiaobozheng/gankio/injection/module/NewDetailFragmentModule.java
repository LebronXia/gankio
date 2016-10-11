package com.xiaobozheng.gankio.injection.module;

import com.xiaobozheng.gankio.mvp.model.impl.RecycentModel;
import com.xiaobozheng.gankio.mvp.presenter.impl.RecentPresent;
import com.xiaobozheng.gankio.ui.FragmentScope;
import com.xiaobozheng.gankio.ui.fragment.NewDetailFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xiaobozheng on 8/24/2016.
 */
@Module
public class NewDetailFragmentModule {
    private NewDetailFragment mNewDetailFragment;

    public NewDetailFragmentModule(NewDetailFragment newDetailFragment){
        this.mNewDetailFragment = newDetailFragment;
    }

    @Provides
    @FragmentScope
    NewDetailFragment provideNewDetailFragment(){
        return  mNewDetailFragment;
    }

    @Provides
    @FragmentScope
    RecentPresent provideRecentPresent(NewDetailFragment mNewDetailFragment, RecycentModel recycentModel){
        return new RecentPresent(mNewDetailFragment, recycentModel);
    }

}
