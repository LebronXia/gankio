package com.xiaobozheng.gankio.mvp.presenter;

/**
 * Created by xiaobozheng on 6/22/2016.
 */
public interface IRecentPresent extends BasePresenter{

    /**
     * 获取最近的信息
     * @param year
     * @param month
     * @param day
     */
    void getRecentData(int year, int month, int day);
}
