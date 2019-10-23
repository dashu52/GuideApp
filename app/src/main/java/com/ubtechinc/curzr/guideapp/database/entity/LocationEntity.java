package com.ubtechinc.curzr.guideapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * @author jianlin.duan
 * @description: 地图上的介绍点和该点要展示的媒体资源
 * @date : 2019/10/18 14:28
 */
@Entity(tableName = "location_table")
public class LocationEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    /**屏幕上绘制的像素值,单位像素**/
    public int screen_x;
    public int screen_y;

    /**单位米**/
    public float real_x;
    public float real_y;

    //一个位置点对应一个媒体资源
    @Ignore
    public MediaResourceEntity mMediaResourceEntity;
}
