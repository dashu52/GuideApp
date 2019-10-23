package com.ubtechinc.curzr.guideapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ubtechinc.curzr.guideapp.database.entity.MediaResourceEntity;
import com.ubtechinc.curzr.guideapp.database.entity.MotionEntity;

import java.util.List;

/**
 * @author jianlin.duan
 * @description:
 * @date : 2019/10/22 15:29
 */
@Dao
public interface MotionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MotionEntity motionEntity);

    @Delete()
    void delete(MotionEntity motionEntity);

    @Query("select * from motion_table")
    List<MotionEntity> getAllMotion();

    @Query("select * from motion_table " +
            "where media_resource_id = :mediaResourceId")
    List<MotionEntity> getMotionListFor(int mediaResourceId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(MotionEntity motionEntity);
}
