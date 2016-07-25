package com.xiaobozheng.gankio.util;

import com.xiaobozheng.gankio.ui.base.BaseFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Riane on 2016/7/26.
 */
public class ViewUtils {

    private static Map<String, BaseFragment> fragmentList = new HashMap<>();
    /**
            * 根据Class创建Fragment
    *
            * @param clazz the Fragment of create
    * @return
            */
    public static BaseFragment createFragment(Class<?> clazz, boolean isObtain) {
        BaseFragment resultFragment = null;
        String className = clazz.getName();
        if (fragmentList.containsKey(className)) {
            resultFragment = fragmentList.get(className);
        } else {
            try {
                try {
                    resultFragment = (BaseFragment) Class.forName(className).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isObtain)
                fragmentList.put(className, resultFragment);
        }

        return resultFragment;
    }

    public static BaseFragment createFragment(Class<?> clazz) {
        return createFragment(clazz, true);
    }
}
