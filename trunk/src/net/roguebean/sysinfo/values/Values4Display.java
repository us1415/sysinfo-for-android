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
 * @version 0.7
 */
public class Values4Display extends Values {
    
    @Override
    public Object[] values(Activity activity) {
        Display d = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics m = new DisplayMetrics();
        d.getMetrics(m);
        
        // Display.getRotation()
        Object rotation = getMethodValue(Display.class, "getRotation", d, 8);
        String rotationAsText;
        if(rotation instanceof Integer) {
            int r = ((Integer) rotation).intValue();
            rotationAsText = r + " (" + getRotationAsText(r) + ")";
        } else {
            rotationAsText = String.valueOf(rotation);
        }
        
        return new Object[] {
                d.getDisplayId(),
                d.getWidth(),
                d.getHeight(),
                d.getOrientation(),
                rotationAsText,
                d.getPixelFormat(),
                d.getRefreshRate(),
                m.density,
                m.scaledDensity,
                m.xdpi,
                m.ydpi,
        };
    }
    
    private static String getRotationAsText(int rotation) {
        String text;
        
        switch(rotation) {
            case FakeSurface.ROTATION_0:
                text = "0 degrees";
                break;
            case FakeSurface.ROTATION_90:
                text = "90 degrees";
                break;
            case FakeSurface.ROTATION_180:
                text = "180 degrees";
                break;
            case FakeSurface.ROTATION_270:
                text = "270 degrees";
                break;
            default:
                text = "UNKNOWN";
                break;
        }
        
        return text;
    }
    
    private static class FakeSurface {
        
        static final int ROTATION_0 = 0;
        static final int ROTATION_90 = 1;
        static final int ROTATION_180 = 2;
        static final int ROTATION_270 = 3;
        
    }
    
}
