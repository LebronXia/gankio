package com.xiaobozheng.gankio.Constant;

import com.xiaobozheng.gankio.util.AppUtils;
import com.xiaobozheng.gankio.util.FileUtils;

/**
 * Description：GankApi - http://gank.io/api
 * <p>
 * 分类数据: http://gank.avosapps.com/api/data/数据类型/请求个数/第几页
 * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
 * 请求个数： 数字，大于0
 * 第几页：数字，大于0
 * <p>
 * Created by xiaobozheng on 6/30/2016.
 */
public class Constant {

    public static final String[] GankCategory = new String[]{"all", "休息视频", "福利", "Android", "iOS", "拓展资源", "前端", "瞎推荐"};
    public static final String GANK_HOME_PAGE_NAME = "干货集中营";
    public static final String GANK_HOME_PAGE_URL = "http://gank.io/";

    public static final String BASE_URL = "http://gank.io/api/";
    public static final int DEFAULT_TIMEOUT = 5000;

    public static final String DATA_TYPE_WELFARE = "福利";
    public static final String DATA_TYPE_ANDROID = "Android";
    public static final String DATA_TYPE_IOS = "iOS";
    public static final String DATA_TYPE_REST_VIDEO = "休息视频";
    public static final String DATA_TYPE_EXTEND_RESOURCES = "拓展资源";
    public static final String DATA_TYPE_JS = "前端";
    public static final String DATA_TYPE_APP = "App";
    public static final String DATA_TYPE_RECOMMEND = "瞎推荐";
    public static final String DATA_TYPE_ALL = "all";

    public static final int daily = 22061;
    public static final int android = 22062;
    public static final int ios = 22063;
    public static final int js = 22064;
    public static final int resources = 22065;
    public static final int welfare = 22066;
    public static final int video = 22067;
    public static final int app = 22068;
    public static final int recommend = 22060;

    public static final int DEFAULT_DATA_SIZE = 10;
    public static final int DEFAULT_DAILY_SIZE = 15;

    public static String PATH_DATA = FileUtils.createRootPath(AppUtils.getAppContext()) + "/cache";
    public static String BASE_PATH = FileUtils.createRootPath(AppUtils.getAppContext()) + "/book/";
}