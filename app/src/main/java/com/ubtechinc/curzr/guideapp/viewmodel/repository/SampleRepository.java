package com.ubtechinc.curzr.guideapp.viewmodel.repository;

import androidx.lifecycle.LiveData;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.ubtechinc.curzr.guideapp.base.OnLoadDatasListener;
import com.ubtechinc.curzr.guideapp.bean.CurrencyBean;
import com.ubtechinc.curzr.guideapp.database.AppDataBase;
import com.ubtechinc.curzr.guideapp.database.entity.GuideLineEntity;
import com.ubtechinc.curzr.guideapp.database.entity.LocationEntity;
import com.ubtechinc.curzr.guideapp.database.entity.MediaResourceEntity;
import com.ubtechinc.curzr.guideapp.database.entity.Motion;
import com.ubtechinc.curzr.guideapp.http.BaseObserver;
import com.ubtechinc.curzr.guideapp.http.RetrofitFactory;
import com.ubtechinc.curzr.utils.MyLogger;

import java.util.List;

/**
 * @author jianlin.duan
 * @description:
 * @date : 2019/10/23 10:52
 */
public class SampleRepository {

    /**
     * 获取导览路线上的点和媒体资源
     * @param guideLineEntity
     */
    public GuideLineEntity getGuideLineData(GuideLineEntity guideLineEntity){
        List<LocationEntity> locationEntityList = AppDataBase.get().getGLJoinDao().getLocationListFor(guideLineEntity.id);
        if(locationEntityList==null){
            MyLogger.dLog().e("guideLine:"+guideLineEntity.name+" id="+guideLineEntity.id+" do not have any locations.");
            return guideLineEntity;
        }
        guideLineEntity.mLocationList = locationEntityList;
        for (LocationEntity entity:locationEntityList) {
            MediaResourceEntity mediaResourceEntity = AppDataBase.get().getMediaResourceDao().getMediaResourceListFor(entity.id);
            entity.mMediaResourceEntity = mediaResourceEntity;
        }
        return guideLineEntity;
    }

    public List<GuideLineEntity> getAllGuidleLineData(){
        List<GuideLineEntity> all = AppDataBase.get().getGuideLineDao().getAllGuideLine();
        if(all!=null){
            for (GuideLineEntity item:all) {
                item = getGuideLineData(item);
            }
        }
        return all;
    }
    public LiveData<List<GuideLineEntity>> getAllGLLiveData(){
        return AppDataBase.get().getGuideLineDao().getAllGuideLineLive();
    }


    public void login(String username, String password, LifecycleTransformer<Long> lifecycleTransformer, final OnLoadDatasListener<CurrencyBean.DataBean> onLoadDatasListener){
        RetrofitFactory.getInstence().login(username, password, lifecycleTransformer, new BaseObserver<CurrencyBean.DataBean>() {
            @Override
            protected void onFailure(String error, boolean isNetWorkError) throws Exception {
                onLoadDatasListener.onFail(error);
            }

            @Override
            protected void onSuccees(CurrencyBean.DataBean dataBean) throws Exception {
                onLoadDatasListener.onSuccess(dataBean);
            }
        });
    }
}
