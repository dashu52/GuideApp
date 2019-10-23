package com.ubtechinc.curzr.guideapp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * @author jianlin.duan
 * @description: 1个MediaResourceEntity对应0-*个MotionEntity
 * @date : 2019/10/22 14:41
 */
@Entity(tableName = "motion_table" ,
        foreignKeys = @ForeignKey(entity = MediaResourceEntity.class,
                parentColumns = "id",
                childColumns = "media_resource_id"),
        indices = @Index(value = "media_resource_id")
)
public class MotionEntity {

    /**ros 可识别的id**/
    @NonNull
    @PrimaryKey
    public String motion_id;
    public String name;
    /**执行时长**/
    public long cost_time;

    @ColumnInfo(name = "media_resource_id")
    public int mediaResourceId;
}
