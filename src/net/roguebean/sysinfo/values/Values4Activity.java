/*
 * Values4Activity.java, Oct 28, 2009
 *
 */
package net.roguebean.sysinfo.values;

import java.lang.reflect.Method;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;

import net.roguebean.sysinfo.Values;

/**
 * The <code>Values4Activity</code> class provides values about the activity.
 * 
 * @author Yonghwan Cho
 * @version 0.5
 */
public class Values4Activity extends Values {
    
    @Override
    public Object[] values(Activity activity) {
        ActivityManager m = (ActivityManager) activity.getSystemService(Activity.ACTIVITY_SERVICE);
        MemoryInfo info = new MemoryInfo();
        m.getMemoryInfo(info);
        
        String memoryClass;
        try {
            Method method = ActivityManager.class.getMethod("getMemoryClass");
            Object result = method.invoke(m);
            memoryClass = String.valueOf(result);
        } catch(Exception e) {
            memoryClass = "N/A (Available only on Android 2.0 or later)";
        }
        
        return new Object[] { memoryClass, info.availMem, info.lowMemory, info.threshold };
    }
    
}
