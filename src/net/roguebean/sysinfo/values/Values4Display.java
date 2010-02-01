/*
 * Values4Display.java, Aug 17, 2009
 *
 */
package net.roguebean.sysinfo.values;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

import net.roguebean.sysinfo.Values;

/**
 * The <code>Values4Display</code> class provides values about the display.
 * 
 * @author Yonghwan Cho
 * @version 0.5
 */
public class Values4Display extends Values {
    
    @Override
    public Object[] values(Activity activity) {
        Display d = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics m = new DisplayMetrics();
        d.getMetrics(m);
        
        return new Object[] {
                d.getDisplayId(),
                d.getWidth(),
                d.getHeight(),
                d.getOrientation(),
                d.getPixelFormat(),
                d.getRefreshRate(),
                m.density,
                m.scaledDensity,
                m.xdpi,
                m.ydpi,
        };
    }
    
}
