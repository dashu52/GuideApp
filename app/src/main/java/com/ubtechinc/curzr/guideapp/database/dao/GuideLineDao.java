package com.ubtechinc.curzr.guideapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ubtechinc.curzr.guideapp.database.entity.GuideLineEntity;

import java.util.List;

/**
 * @author jianlin.duan
 * @description:
 * @date : 2019/10/18 15:30
 */
@Dao
public interface GuideLineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GuideLineEntity guideLineEntity);

    @Delete()
    void delete(GuideLineEntity guideLineEntity);

    @Query("select * from guide_line_table")
    List<GuideLineEntity> getAllGuideLine();

    @Query("select * from guide_line_table")
    LiveData<List<GuideLineEntity>> getAllGuideLineLive();


    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(GuideLineEntity guideLineEntity);
}
