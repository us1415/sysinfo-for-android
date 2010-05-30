/*
 * Values4Environment.java, Aug 14, 2009
 *
 */
package net.roguebean.sysinfo.values;

import java.io.File;
import java.util.Arrays;

import android.app.Activity;
import android.os.Build;
import android.os.Environment;

import net.roguebean.sysinfo.Values;

/**
 * The <code>Values4Environment</code> class provides values about the environment.
 * 
 * @author Yonghwan Cho
 * @version 0.7
 */
public class Values4Environment extends Values {

    @Override
    public Object[] values(Activity activity) {
        // default values
        Object[] values = new Object[] {
                Environment.getRootDirectory(),
                Environment.getDataDirectory(),
                Environment.getExternalStorageDirectory(),
                Environment.getDownloadCacheDirectory(),
                Environment.getExternalStorageState(),
        };
        
        // directory paths
        String[] dirs = getExternalStoragePublicDirectories();
        
        // merge
        Object[] merged = new Object[values.length + dirs.length];
        System.arraycopy(values, 0, merged, 0, values.length);
        System.arraycopy(dirs, 0, merged, values.length, dirs.length);
        
        return merged;
    }
    
    private static String[] getExternalStoragePublicDirectories() {
        String[] dirs;
        
        final String[] fieldNames = {
                "DIRECTORY_ALARMS",
                "DIRECTORY_DCIM",
                "DIRECTORY_DOWNLOADS",
                "DIRECTORY_MOVIES",
                "DIRECTORY_MUSIC",
                "DIRECTORY_NOTIFICATIONS",
                "DIRECTORY_PICTURES",
                "DIRECTORY_PODCASTS",
                "DIRECTORY_RINGTONES",
        };
        int count = fieldNames.length;
        dirs = new String[count];
        
        int apiLevel = 8;
        if(Build.VERSION.SDK_INT >= apiLevel) {
            int i = 0;
            try {
                for(; i < count; i++) {
                    String fieldName = fieldNames[i];
                    String fieldValue = (String) Environment.class.getField(fieldName).get(null);
                    File dir = (File) Environment.class.getMethod("getExternalStoragePublicDirectory", String.class).invoke(null, fieldValue);
                    dirs[i] = dir.getCanonicalPath();
                }
            } catch(Exception e) {
                e.printStackTrace();
                for(; i < count; i++) {
                    dirs[i] = "N/A (Failed to load)";
                }
            }
        } else {
            Arrays.fill(dirs, Values.getMessageForNotAvaliable(apiLevel));
        }
        
        return dirs;
    }
    
}
