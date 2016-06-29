package com.xiaobozheng.gankio.mvp.view;

import com.xiaobozheng.gankio.data.model.GankDaily;
import com.xiaobozheng.gankio.data.model.RecentlyBean;

import java.util.List;

/**
 * Created by xiaobozheng on 6/23/2016.
 */
public interface RecentView extends IBaseView{
    void showData(GankDaily gankDaily);
}
