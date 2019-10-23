package com.ubtechinc.curzr.guideapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "guide_line_table")
public class GuideLineEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    /**一条导航路线包含多个地点**/
    @Ignore
    public List<LocationEntity> mLocationList;
}
