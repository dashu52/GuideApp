package com.ubtechinc.curzr.guideapp.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.ubtechinc.curzr.utils.ActivityContainer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class BaseActivity<T extends AndroidViewModel> extends AppCompatActivity {

    protected T mViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContainer.getInstance().add(this);
        setContentView(getLayoutId());

        mViewModel = (T)ViewModelProviders.of(this).get(getViewModelClass());
        initView();
        initData();
    }

    /**
     * 获取泛型T的class类型
     * @return
     */
    private Class getViewModelClass(){
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class tClass = (Class) actualTypeArguments[0];
        return tClass;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityContainer.getInstance().remove(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

}
