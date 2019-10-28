package com.ubtechinc.curzr.guideapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.ubtechinc.curzr.guideapp.base.BaseViewModel;
import com.ubtechinc.curzr.guideapp.base.OnLoadDatasListener;
import com.ubtechinc.curzr.guideapp.bean.CurrencyBean;
import com.ubtechinc.curzr.guideapp.database.entity.GuideLineEntity;
import com.ubtechinc.curzr.guideapp.viewmodel.repository.SampleRepository;

import java.util.List;

/**
 * @author jianlin.duan
 * @description:
 * @date : 2019/10/25 11:27
 */
public class SampleViewModel extends BaseViewModel {

    public MutableLiveData<CurrencyBean.DataBean> mSampleData = new MutableLiveData<>();
    public MutableLiveData<String> mErrorMsg = new MutableLiveData<>();

    private SampleRepository mSampleRepository = new SampleRepository();

    public SampleViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 获取导航路线完整数据，包括位置点，媒体资源
     * @param guideLineEntity
     * @return
     */
    public GuideLineEntity getGuideLineData(GuideLineEntity guideLineEntity){
        return mSampleRepository.getGuideLineData(guideLineEntity);
    }
    public List<GuideLineEntity> getAllGuideLineData(){
        return mSampleRepository.getAllGuidleLineData();
    }
    public LiveData<List<GuideLineEntity>> getAllGLLiveData(){
        return mSampleRepository.getAllGLLiveData();
    }

    public void login(String userName, String pwd, LifecycleTransformer<Long> lifecycleTransformer){
        mSampleRepository.login(userName, pwd, lifecycleTransformer, new OnLoadDatasListener<CurrencyBean.DataBean>() {
            @Override
            public void onSuccess(CurrencyBean.DataBean dataBean) {
                mSampleData.postValue(dataBean);
            }

            @Override
            public void onFail(String error) {
                mErrorMsg.postValue(error);
            }
        });
    }

    public void test(){
        mErrorMsg.postValue("this is a test interface");
    }
}
