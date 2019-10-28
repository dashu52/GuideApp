package com.ubtechinc.curzr.guideapp.common;

import android.os.Bundle;
import android.widget.Button;

import androidx.lifecycle.Observer;

import com.ubtechinc.curzr.guideapp.R;
import com.ubtechinc.curzr.guideapp.base.BaseActivity;
import com.ubtechinc.curzr.guideapp.viewmodel.SampleViewModel;
import com.ubtechinc.curzr.utils.CommonUtil;
import com.ubtechinc.curzr.utils.MyLogger;
import com.ubtechinc.curzr.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<SampleViewModel> {

    @BindView(R.id.btn1)
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        if (!SPUtils.get("db_is_created")) {
            CommonUtil.createWithSampleData();
            SPUtils.put("db_is_created", true);
        }
    }

    @Override
    protected Class getViewModelClass() {
        return SampleViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mViewModel.mErrorMsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String msg) {
                MyLogger.dLog().d("onChanged ：" + msg);
            }
        });
        mViewModel.getAllGLLiveData().observe(this,guideLineEntityList ->{
            MyLogger.dLog().d("guideLineEntityList="+guideLineEntityList);
        });
    }

    @OnClick(R.id.btn1)
    public void onViewClicked() {
        mViewModel.test();
    }
}
