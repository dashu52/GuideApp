package com.ubtechinc.curzr.utils;

import android.content.SharedPreferences;

import com.ubtechinc.curzr.guideapp.common.APP;

/**
 * @author jianlin.duan
 * @description:
 * @date : 2019/10/28 15:25
 */
public class SPUtils {

    private static final String SP_DB = "SP_DB";

    public static void put(String key, boolean value){
        getSP().edit().putBoolean(key,value).commit();
    }

    public static boolean get(String key){
        return getSP().getBoolean(key,false);
    }

    private static SharedPreferences getSP(){
        return APP.getContext().getSharedPreferences(SP_DB,0);
    }
}
