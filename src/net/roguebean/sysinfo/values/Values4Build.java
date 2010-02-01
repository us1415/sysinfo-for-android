/*
 * Values4Build.java, Aug 13, 2009
 *
 */
package net.roguebean.sysinfo.values;

import java.lang.reflect.Field;

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
        String cpu_abi;
        String manufacturer;
        
        try {
            Field field = Build.class.getDeclaredField("CPU_ABI");
            Object value = field.get(null);
            cpu_abi = String.valueOf(value);
        } catch(Exception e) {
            cpu_abi = "N/A (Available only on Android 1.6 or later)";
        }
        
        try {
            Field field = Build.class.getDeclaredField("MANUFACTURER");
            Object value = field.get(null);
            manufacturer = String.valueOf(value);
        } catch(Exception e) {
            manufacturer = "N/A (Available only on Android 1.6 or later)";
        }
        
        return new Object[] {
                Build.ID,
                Build.PRODUCT,
                Build.DEVICE,
                Build.BOARD,
                cpu_abi,
                manufacturer,
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
