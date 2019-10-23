package com.ubtechinc.curzr.guideapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ubtechinc.curzr.guideapp.database.entity.GuideLineEntity;
import com.ubtechinc.curzr.guideapp.database.entity.LocationEntity;

import java.util.List;

/**
 * @author jianlin.duan
 * @description:
 * @date : 2019/10/18 15:30
 */
@Dao
public interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LocationEntity locationEntity);

    @Query("select * from location_table")
    List<LocationEntity> getAllLocation();

}
