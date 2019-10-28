package com.ubtechinc.curzr.guideapp.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.amitshekhar.DebugDB;
import com.ubtechinc.curzr.guideapp.common.APP;
import com.ubtechinc.curzr.guideapp.database.dao.GLJoinDao;
import com.ubtechinc.curzr.guideapp.database.dao.GLViewDao;
import com.ubtechinc.curzr.guideapp.database.dao.GuideLineDao;
import com.ubtechinc.curzr.guideapp.database.dao.LocationDao;
import com.ubtechinc.curzr.guideapp.database.dao.MediaResourceDao;
import com.ubtechinc.curzr.guideapp.database.entity.Emotion;
import com.ubtechinc.curzr.guideapp.database.entity.GLJoin;
import com.ubtechinc.curzr.guideapp.database.entity.GLView;
import com.ubtechinc.curzr.guideapp.database.entity.GuideLineEntity;
import com.ubtechinc.curzr.guideapp.database.entity.LocationEntity;
import com.ubtechinc.curzr.guideapp.database.entity.MediaResourceEntity;
import com.ubtechinc.curzr.guideapp.database.entity.Motion;
import com.ubtechinc.curzr.guideapp.viewmodel.repository.SampleRepository;
import com.ubtechinc.curzr.utils.MyLogger;

import java.util.List;

@Database(
        entities = {
            GuideLineEntity.class,
            LocationEntity.class,
            GLJoin.class,
            MediaResourceEntity.class,
        },
        views = {
            GLView.class
        },
        version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static final String DB_NAME = "gruide_app_db";

    public static AppDataBase get(){
        return HOLDER.INSTANCE;
    }
    private static class HOLDER{
        static AppDataBase INSTANCE = Room.databaseBuilder(APP.getContext(),AppDataBase.class,DB_NAME)
                .allowMainThreadQueries()
//                .addMigrations(migration_3_4)
                .build();
    }

    private static Migration migration_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
    public abstract GLJoinDao getGLJoinDao();
    public abstract GuideLineDao getGuideLineDao();
    public abstract LocationDao getLocationDao();
    public abstract GLViewDao getGLViewDao();
    public abstract MediaResourceDao getMediaResourceDao();


}
