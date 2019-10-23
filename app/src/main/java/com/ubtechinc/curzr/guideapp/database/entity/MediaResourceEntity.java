package com.ubtechinc.curzr.guideapp.database.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * @author jianlin.duan
 * @description:
 * @date : 2019/10/22 14:27
 */
@Entity(tableName = "media_resource_table",
foreignKeys = @ForeignKey(entity = LocationEntity.class,
                parentColumns = "id",
                childColumns = "location_id"),
indices = {@Index(value = {"location_id"})}
)
public class MediaResourceEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    /**外键1对1关系**/
    public int location_id;
    /**说话前过渡语**/
    public String previous_tts;
    /**说话后过渡语**/
    public String later_tts;
    /**说话内容**/
    public String introduce_tts;

    public String face_name;

    public String face_id;

    @Embedded
    public Music music;
    @Embedded
    public Video video;
    @Embedded
    public Picture picture;

    //1 MediaResourceEntity 对* MotionEntity
    // 不存数据库字段，程序运行时使用
    @Ignore
    public List<MotionEntity> mMotionList;
}
