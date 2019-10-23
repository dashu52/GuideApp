package com.ubtechinc.curzr.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.ubtechinc.curzr.guideapp.common.APP;


/***
 * toast
 */
public class CommonUtil {
    private static Toast toast;

    public static void showToast(
            String content) {
        if (toast == null) {
            toast = Toast.makeText(APP.getContext(),
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}
