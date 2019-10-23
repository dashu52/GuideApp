package com.ubtechinc.curzr.guideapp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<M> extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutId(),container,false);
        initView(view);
        initData();
        return view;
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void initData();
}
