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


public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity {

    protected VM mViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContainer.getInstance().add(this);
        setContentView(getLayoutId());

        mViewModel = (VM)ViewModelProviders.of(this).get(getViewModelClass());
        getLifecycle().addObserver(mViewModel);
        initView();
        initData();
    }

    /**
     * 获取泛型T的class类型
     * @return
     */
    protected abstract Class getViewModelClass();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(mViewModel);
        ActivityContainer.getInstance().remove(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

}
