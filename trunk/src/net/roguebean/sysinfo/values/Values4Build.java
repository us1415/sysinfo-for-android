/*
 * Values4Build.java, Aug 13, 2009
 *
 */
package net.roguebean.sysinfo.values;

import android.app.Activity;
import android.os.Build;

import net.roguebean.sysinfo.Values;

/**
 * The <code>Values4Build</code> class provides values about the build.
 * 
 * @author Yonghwan Cho
 * @version 0.5
 */
public class Values4Build extends Values {
    
    @Override
    public Object[] values(Activity activity) {
        return new Object[] {
                Build.ID,
                Build.PRODUCT,
                Build.DEVICE,
                Build.BOARD,
                Build.BRAND,
                Build.MODEL,
                Build.TYPE,
                Build.TAGS,
                Build.FINGERPRINT,
                Build.TIME,
                Build.USER,
                Build.HOST,
                Build.VERSION.INCREMENTAL,
                Build.VERSION.RELEASE,
                Build.VERSION.SDK
        };
    }
    
}
