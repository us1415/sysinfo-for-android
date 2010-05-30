/*
 * Values4Activity.java, Oct 28, 2009
 *
 */
package net.roguebean.sysinfo.values;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;

import net.roguebean.sysinfo.Values;

/**
 * The <code>Values4Activity</code> class provides values about the activity.
 * 
 * @author Yonghwan Cho
 * @version 0.7
 */
public class Values4Activity extends Values {
    
    @Override
    public Object[] values(Activity activity) {
        ActivityManager m = (ActivityManager) activity.getSystemService(Activity.ACTIVITY_SERVICE);
        MemoryInfo info = new MemoryInfo();
        m.getMemoryInfo(info);
        
        // ActivityManager.getMemoryClass()
        String memoryClass = String.valueOf(getMethodValue(ActivityManager.class, "getMemoryClass", m, 5));
        
        return new Object[] { memoryClass, info.availMem, info.lowMemory, info.threshold };
    }
    
}
