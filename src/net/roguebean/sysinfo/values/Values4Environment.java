/*
 * Values4Environment.java, Aug 14, 2009
 *
 */
package net.roguebean.sysinfo.values;

import android.app.Activity;
import android.os.Environment;

import net.roguebean.sysinfo.Values;

/**
 * The <code>Values4Environment</code> class provides values about the environment.
 * 
 * @author Yonghwan Cho
 * @version 0.5
 */
public class Values4Environment extends Values {

    @Override
    public Object[] values(Activity activity) {
        return new Object[] {
                Environment.getRootDirectory(),
                Environment.getDataDirectory(),
                Environment.getExternalStorageDirectory(),
                Environment.getDownloadCacheDirectory(),
                Environment.getExternalStorageState(),
        };
    }
    
}
