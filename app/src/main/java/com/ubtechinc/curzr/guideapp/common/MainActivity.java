package com.ubtechinc.curzr.guideapp.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.amitshekhar.DebugDB;
import com.ubtechinc.curzr.guideapp.R;
import com.ubtechinc.curzr.guideapp.database.AppDataBase;
import com.ubtechinc.curzr.guideapp.database.entity.GLJoin;
import com.ubtechinc.curzr.guideapp.database.entity.GLView;
import com.ubtechinc.curzr.guideapp.database.entity.GuideLineEntity;
import com.ubtechinc.curzr.guideapp.database.entity.LocationEntity;
import com.ubtechinc.curzr.guideapp.database.entity.MediaResourceEntity;
import com.ubtechinc.curzr.guideapp.database.entity.MotionEntity;
import com.ubtechinc.curzr.guideapp.viewmodel.repository.GLRepository;
import com.ubtechinc.curzr.utils.MyLogger;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        mediaResourceEntity.face_id = "face_happy";
        mediaResourceEntity.face_name = "高兴";
        mediaResourceEntity.location_id = glViewList.get(0).location_id;
        AppDataBase.get().getMediaResourceDao().insert(mediaResourceEntity);
        mediaResourceEntity = AppDataBase.get().getMediaResourceDao().getAllMediaResource().get(0);
        MotionEntity hug = new MotionEntity();
        hug.motion_id = "hug";
        hug.name = "拥抱";
        hug.mediaResourceId = mediaResourceEntity.id;
        AppDataBase.get().getMotionDao().insert(hug);
        List<MotionEntity> motionEntityList = AppDataBase.get().getMotionDao().getAllMotion();

        GuideLineEntity guideLineEntityFull = new GLRepository().getGuideLineData(guideLineEntity);

        MyLogger.dLog().d("ip===="+ DebugDB.getAddressLog());
    }
}
