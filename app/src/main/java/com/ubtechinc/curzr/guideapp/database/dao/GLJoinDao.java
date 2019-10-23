package com.ubtechinc.curzr.guideapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import com.ubtechinc.curzr.guideapp.database.entity.GLJoin;
import com.ubtechinc.curzr.guideapp.database.entity.GuideLineEntity;
import com.ubtechinc.curzr.guideapp.database.entity.LocationEntity;

import java.util.List;

@Dao
public interface GLJoinDao {

    @Insert
    void insert(GLJoin glJoin);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select * from guide_line_table " +
            "inner join guideline_location_join " +
            "on guide_line_table.id=guideline_location_join.guide_line_id " +
            "where guideline_location_join.location_id=:locationId")
    List<GuideLineEntity> getGuideLineListFor(int locationId);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select * from location_table " +
            "inner join guideline_location_join " +
            "on location_table.id=guideline_location_join.location_id " +
            "where guideline_location_join.guide_line_id=:guideLineId")
    List<LocationEntity> getLocationListFor(int guideLineId);


}
