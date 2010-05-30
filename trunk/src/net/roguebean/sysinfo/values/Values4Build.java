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
 * @version 0.7
 */
public class Values4Build extends Values {
    
    @Override
    public Object[] values(Activity activity) {
        // prepare values which are introduced since API level 5.
        String cpu_abi2 = (String) getFieldValue(Build.class, "CPU_ABI2", null, 8);
        String hardware = (String) getFieldValue(Build.class, "HARDWARE", null, 8);
        String radio = (String) getFieldValue(Build.class, "RADIO", null, 8);
        String bootloader = (String) getFieldValue(Build.class, "BOOTLOADER", null, 8);
        
        return new Object[] {
                Build.ID,
                Build.PRODUCT,
                Build.DEVICE,
                Build.BOARD,
                Build.CPU_ABI,
                cpu_abi2,
                Build.MANUFACTURER,
                Build.BRAND,
                Build.MODEL,
                Build.TYPE,
                Build.TAGS,
                Build.FINGERPRINT,
                Build.TIME,
                Build.USER,
                Build.HOST,
                hardware,
                radio,
                bootloader,
                Build.VERSION.INCREMENTAL,
                Build.VERSION.RELEASE,
                Build.VERSION.SDK
        };
    }
    
}
