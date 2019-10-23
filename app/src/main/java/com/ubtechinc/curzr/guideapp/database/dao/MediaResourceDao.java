package com.ubtechinc.curzr.guideapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ubtechinc.curzr.guideapp.database.entity.GLView;
import com.ubtechinc.curzr.guideapp.database.entity.MediaResourceEntity;

import java.util.List;

/**
 * @author jianlin.duan
 * @description:
 * @date : 2019/10/22 15:29
 */
@Dao
public interface MediaResourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MediaResourceEntity mediaResourceEntity);

    @Delete()
    void delete(MediaResourceEntity mediaResourceEntity);

    @Query("select * from media_resource_table")
    List<MediaResourceEntity> getAllMediaResource();

    @Query("select * from media_resource_table " +
            "where location_id=:locationId limit 1")
    MediaResourceEntity getMediaResourceListFor(int locationId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(MediaResourceEntity mediaResourceEntity);
}
