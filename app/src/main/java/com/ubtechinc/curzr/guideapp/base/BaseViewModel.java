package com.ubtechinc.curzr.guideapp.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;

/**
 * @author jianlin.duan
 * @description:
 * @date : 2019/10/25 17:06
 */
public class BaseViewModel extends AndroidViewModel implements LifecycleObserver {
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
