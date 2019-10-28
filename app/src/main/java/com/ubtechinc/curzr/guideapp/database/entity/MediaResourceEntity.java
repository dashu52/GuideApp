package com.ubtechinc.curzr.guideapp.database.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

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

    @Embedded
    public Emotion emotion;
    @Embedded
    public Motion motion;
    @Embedded
    public Music music;
    @Embedded
    public Video video;
    @Embedded
    public Picture picture;

}
