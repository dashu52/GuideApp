package com.ubtechinc.curzr.guideapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ubtechinc.curzr.guideapp.database.entity.GLView;
import com.ubtechinc.curzr.guideapp.database.entity.GuideLineEntity;

import java.util.List;

/**
 * @author jianlin.duan
 * @description:
 * @date : 2019/10/22 15:29
 */
@Dao
public interface GLViewDao {


    @Query("select * from gl_view")
    List<GLView> getAllGLView();
}
