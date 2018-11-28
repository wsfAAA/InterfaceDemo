package test.cmcc.com.networkdemo.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public final class DimenUtil {

    public static int getScreenWidth(Context context) {
        final Resources resources =context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
