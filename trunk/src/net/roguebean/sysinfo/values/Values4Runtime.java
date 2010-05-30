/*
 * Values4Runtime.java, Oct 28, 2009
 *
 */
package net.roguebean.sysinfo.values;

import android.app.Activity;

import net.roguebean.sysinfo.Values;

/**
 * The <code>Values4Runtime</code> class provides values about the runtime.
 * 
 * @author Yonghwan Cho
 * @version 0.7
 */
public class Values4Runtime extends Values {
    
    @Override
    public Object[] values(Activity activity) {
        Runtime r = Runtime.getRuntime();
        return new Object[] {
                r.availableProcessors(),
                r.freeMemory(),
                r.totalMemory(),
                r.maxMemory(),
        };
    }
    
}
