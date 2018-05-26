package com.example.linfengwang.tasksreminder.TaskUtils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public final class DpUtil {

    private DpUtil() {
    }

    public static int dpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) px;
    }
}
