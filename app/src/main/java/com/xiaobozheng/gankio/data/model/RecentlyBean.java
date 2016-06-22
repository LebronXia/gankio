package com.xiaobozheng.gankio.data.model;

import java.util.List;

/**
 * Created by xiaobozheng on 6/22/2016.
 */
public class RecentlyBean {
    private List<GankDataBean> Android;
    private List<GankDataBean> iOS;
    private List<GankDataBean> 休息视频;
    private List<GankDataBean> 拓展视频;
    private List<GankDataBean> 瞎推荐;
    private List<GankDataBean> 福利;

    public List<GankDataBean> getAndroid() {
        return Android;
    }

    public void setAndroid(List<GankDataBean> android) {
        Android = android;
    }

    public List<GankDataBean> getiOS() {
        return iOS;
    }

    public void setiOS(List<GankDataBean> iOS) {
        this.iOS = iOS;
    }

    public List<GankDataBean> get休息视频() {
        return 休息视频;
    }

    public void set休息视频(List<GankDataBean> 休息视频) {
        this.休息视频 = 休息视频;
    }

    public List<GankDataBean> get拓展视频() {
        return 拓展视频;
    }

    public void set拓展视频(List<GankDataBean> 拓展视频) {
        this.拓展视频 = 拓展视频;
    }

    public List<GankDataBean> get瞎推荐() {
        return 瞎推荐;
    }

    public void set瞎推荐(List<GankDataBean> 瞎推荐) {
        this.瞎推荐 = 瞎推荐;
    }

    public List<GankDataBean> get福利() {
        return 福利;
    }

    public void set福利(List<GankDataBean> 福利) {
        this.福利 = 福利;
    }
}
