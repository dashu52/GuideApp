package com.ubtechinc.curzr.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.ubtechinc.curzr.guideapp.common.APP;
import com.ubtechinc.curzr.guideapp.database.AppDataBase;
import com.ubtechinc.curzr.guideapp.database.entity.Emotion;
import com.ubtechinc.curzr.guideapp.database.entity.GLJoin;
import com.ubtechinc.curzr.guideapp.database.entity.GLView;
import com.ubtechinc.curzr.guideapp.database.entity.GuideLineEntity;
import com.ubtechinc.curzr.guideapp.database.entity.LocationEntity;
import com.ubtechinc.curzr.guideapp.database.entity.MediaResourceEntity;
import com.ubtechinc.curzr.guideapp.database.entity.Motion;
import com.ubtechinc.curzr.guideapp.viewmodel.repository.SampleRepository;

import java.util.List;


/***
 * toast
 */
public class CommonUtil {
    private static Toast toast;

    public static void showToast(
            String content) {
        if (toast == null) {
            toast = Toast.makeText(APP.getContext(),
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /****
     * 使用测试数据创建数据库
     */
    public static void createWithSampleData(){
        GuideLineEntity guideLineEntity = new GuideLineEntity();
        guideLineEntity.name = "经典路线1";
        LocationEntity locationEntity1 = new LocationEntity();
        locationEntity1.name = "开始点";
        LocationEntity locationEntity2 = new LocationEntity();
        locationEntity2.name = "结束点";
        AppDataBase.get().getGuideLineDao().insert(guideLineEntity);
        AppDataBase.get().getLocationDao().insert(locationEntity1);
        AppDataBase.get().getLocationDao().insert(locationEntity2);
        guideLineEntity = AppDataBase.get().getGuideLineDao().getAllGuideLine().get(0);
        locationEntity1 = AppDataBase.get().getLocationDao().getAllLocation().get(0);
        locationEntity2 = AppDataBase.get().getLocationDao().getAllLocation().get(1);
        GLJoin glJoin = new GLJoin();
        glJoin.guide_line_id = guideLineEntity.id;
        glJoin.location_id = locationEntity1.id;
        AppDataBase.get().getGLJoinDao().insert(glJoin);

        GLJoin glJoin2 = new GLJoin();
        glJoin2.guide_line_id = guideLineEntity.id;
        glJoin2.location_id = locationEntity2.id;
        AppDataBase.get().getGLJoinDao().insert(glJoin2);

        List<LocationEntity> locationEntityList = AppDataBase.get().getGLJoinDao().getLocationListFor(guideLineEntity.id);

        List<GLView> glViewList = AppDataBase.get().getGLViewDao().getAllGLView();

        MediaResourceEntity mediaResourceEntity = new MediaResourceEntity();
        mediaResourceEntity.emotion = new Emotion();
        mediaResourceEntity.emotion.emotionId = "face_happy";
        mediaResourceEntity.emotion.emotionName = "高兴";
        mediaResourceEntity.location_id = glViewList.get(0).location_id;
        AppDataBase.get().getMediaResourceDao().insert(mediaResourceEntity);
        mediaResourceEntity = AppDataBase.get().getMediaResourceDao().getAllMediaResource().get(0);
        Motion hug = new Motion();
        hug.motion_id = "hug";
        hug.name = "拥抱";

        GuideLineEntity guideLineEntityFull = new SampleRepository().getGuideLineData(guideLineEntity);

        MyLogger.dLog().d("ip===="+ DebugDB.getAddressLog());//adb forward tcp:8080 tcp:8080
    }
}
