package com.ubtechinc.curzr.guideapp.database.entity;

import androidx.room.DatabaseView;

/**
 * @author jianlin.duan
 * @description:
 * @date : 2019/10/22 11:14
 */

@DatabaseView(value = "SELECT guide_line_table.id AS guild_line_id, " +
        "guide_line_table.name AS guild_line_name, " +
        "location_table.id AS location_id, " +
        "location_table.name AS location_name " +
        "FROM guide_line_table " +
        "INNER JOIN guideline_location_join ON guide_line_table.id = guideline_location_join.guide_line_id " +
        "INNER JOIN location_table ON location_table.id = guideline_location_join.location_id",
viewName = "gl_view")
public class GLView {

    public int guild_line_id;
    public int location_id;
    public String guild_line_name;
    public String location_name;

}
