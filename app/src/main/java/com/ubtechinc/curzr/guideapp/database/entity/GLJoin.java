package com.ubtechinc.curzr.guideapp.database.entity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

/**
 * @author jianlin.duan
 * @description: GuideLineEntity 和 LocationEntity对应关系表
 * @date : 2019/10/18 14:50
 */

@Entity(tableName = "guideline_location_join",
        primaryKeys = { "guide_line_id", "location_id" },
        foreignKeys = {
                @ForeignKey(entity = GuideLineEntity.class,
                        parentColumns = "id",
                        childColumns = "guide_line_id"),
                @ForeignKey(entity = LocationEntity.class,
                        parentColumns = "id",
                        childColumns = "location_id")
        },
        indices = {@Index(value = "location_id"),@Index(value = "guide_line_id")}
)
public class GLJoin {
    public int guide_line_id;
    public int location_id;
}
