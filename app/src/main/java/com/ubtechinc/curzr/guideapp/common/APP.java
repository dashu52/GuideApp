package com.ubtechinc.curzr.guideapp.common;

import android.app.Application;
import android.content.Context;

public class APP extends Application {

    private static Context sContext;

    public static Context getContext(){
        return sContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
