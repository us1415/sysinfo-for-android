/*
 * Values4Configuration.java, Mar 25, 2010
 *
 * Copyright 2010 ThinkFree. All rights reserved.
 */
package net.roguebean.sysinfo.values;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.res.Configuration;

import net.roguebean.sysinfo.Values;


/**
 * The <code>Values4Configuration</code> type.
 *
 * @author Yonghwan Cho
 * @version 0.6
 *
 */
public class Values4Configuration extends Values {

    @Override
    public Object[] values(Activity activity) {
        Configuration c = activity.getResources().getConfiguration();
        
        // added since Android API level 4.
        String screenLayout;
        int sl = getScreenLayoutByReflection(c);
        if(sl == -1) {
            screenLayout = "N/A (Available only on Android 1.6 or later)";
        } else {
            screenLayout = sl + " (" + getScreenLayoutAsText(sl) + ")";
        }
        
        // added since Android API level 5.
        String navigationHidden; 
        int nh = getNavigationHiddenByReflection(c);
        if(nh == -1) {
            navigationHidden = "N/A (Available only on Android 2.0 or later)";
        } else {
            navigationHidden = nh + " (" + getNavigationHiddenAsText(nh) + ")";
        }
        
        return new Object[] {
                c.fontScale,
                c.mcc,
                c.mnc,
                c.locale,
                screenLayout,
                c.touchscreen + " (" + getTouchScreenAsText(c.touchscreen) + ")",
                c.keyboard + " (" + getKeyboardAsText(c.keyboard) + ")",
                c.keyboardHidden + " (" + getKeyboardHiddenAsText(c.keyboardHidden) + ")",
                c.hardKeyboardHidden + " (" + getHardKeyboardHiddenAsText(c.hardKeyboardHidden) + ")",
                c.navigation + " (" + getNavigationAsText(c.navigation) + ")",
                navigationHidden,
                c.orientation + "(" + getOrientationAsText(c.orientation) + ")"
        };
    }
    
    private int getScreenLayoutByReflection(Configuration c) {
        int value;
        try {
            Field f = Configuration.class.getField("screenLayout");
            value = ((Integer) f.get(c)).intValue();
        } catch(Exception e) {
            e.printStackTrace();
            value = -1;
        }
        return value;
    }
    
    private int getNavigationHiddenByReflection(Configuration c) {
        int value;
        try {
            Field f = Configuration.class.getField("navigationHidden");
            value = ((Integer) f.get(c)).intValue();
        } catch(Exception e) {
            e.printStackTrace();
            value = -1;
        }
        return value;
    }
    
    /* copied from Configuration class in Android 2.0 for convenience */
    public static final int SCREENLAYOUT_SIZE_MASK = 0x0f;
    public static final int SCREENLAYOUT_SIZE_UNDEFINED = 0x00;
    public static final int SCREENLAYOUT_SIZE_SMALL = 0x01;
    public static final int SCREENLAYOUT_SIZE_NORMAL = 0x02;
    public static final int SCREENLAYOUT_SIZE_LARGE = 0x03;
    
    public static final int SCREENLAYOUT_LONG_MASK = 0x30;
    public static final int SCREENLAYOUT_LONG_UNDEFINED = 0x00;
    public static final int SCREENLAYOUT_LONG_NO = 0x10;
    public static final int SCREENLAYOUT_LONG_YES = 0x20;
    
    private String getScreenLayoutAsText(int flags) {
        int lsize = flags & SCREENLAYOUT_SIZE_MASK;
        int llong = flags & SCREENLAYOUT_LONG_MASK;
        
        String ssize;
        switch(lsize) {
            case SCREENLAYOUT_SIZE_UNDEFINED:
                ssize = "UNDEFINED";
                break;
            case SCREENLAYOUT_SIZE_SMALL:
                ssize = "SMALL";
                break;
            case SCREENLAYOUT_SIZE_NORMAL:
                ssize = "NORMAL";
                break;
            case SCREENLAYOUT_SIZE_LARGE:
                ssize = "LARGE";
                break;
            default:
                ssize = "UNKNOWN";
                break;
        }
        
        String slong;
        switch(llong) {
            case SCREENLAYOUT_LONG_UNDEFINED:
                slong = "UNDEFINED";
            case SCREENLAYOUT_LONG_NO:
                slong = "NO";
                break;
            case SCREENLAYOUT_LONG_YES:
                slong = "YES";
                break;
            default:
                slong = "UNKNOWN";
                break;
        }
        
        return ssize + "|" + slong;
    }
    
    private String getTouchScreenAsText(int touchscreen) {
        String text;
        
        switch(touchscreen) {
            case Configuration.TOUCHSCREEN_UNDEFINED:
                text = "UNDEFINED";
                break;
            case Configuration.TOUCHSCREEN_NOTOUCH:
                text = "NOTOUCH";
                break;
            case Configuration.TOUCHSCREEN_STYLUS:
                text = "STYLUS";
                break;
            case Configuration.TOUCHSCREEN_FINGER:
                text = "FINGER";
                break;
            default:
                text = "UNKNOWN";
                break;
        }
        
        return text;
    }
    
    private String getKeyboardAsText(int keyboard) {
        String text;
        
        switch(keyboard) {
            case Configuration.KEYBOARD_UNDEFINED:
                text = "UNDEFINED";
                break;
            case Configuration.KEYBOARD_NOKEYS:
                text = "NOKEYS";
                break;
            case Configuration.KEYBOARD_QWERTY:
                text = "QWERTY";
                break;
            case Configuration.KEYBOARD_12KEY:
                text = "12KEY";
                break;
            default:
                text = "UNKNOWN";
                break;
        }
        
        return text;
    }
    
    private String getKeyboardHiddenAsText(int keyboardHidden) {
        String text;
        
        switch(keyboardHidden) {
            case Configuration.KEYBOARDHIDDEN_UNDEFINED:
                text = "UNDEFINED";
                break;
            case Configuration.KEYBOARDHIDDEN_NO:
                text = "NO";
                break;
            case Configuration.KEYBOARDHIDDEN_YES:
                text = "YES";
                break;
            default:
                text = "UNKNOWN";
                break;
        }
        
        return text;
    }
    
    private String getHardKeyboardHiddenAsText(int hardKeyboardHidden) {
        String text;
        
        switch(hardKeyboardHidden) {
            case Configuration.HARDKEYBOARDHIDDEN_UNDEFINED:
                text = "UNDEFINED";
                break;
            case Configuration.HARDKEYBOARDHIDDEN_NO:
                text = "NO";
                break;
            case Configuration.HARDKEYBOARDHIDDEN_YES:
                text = "YES";
                break;
            default:
                text = "UNKNOWN";
                break;
        }
        
        return text;
    }
    
    private String getNavigationAsText(int navigation) {
        String text;
        
        switch(navigation) {
            case Configuration.NAVIGATION_UNDEFINED:
                text = "UNDEFINED";
                break;
            case Configuration.NAVIGATION_NONAV:
                text = "NONAV";
                break;
            case Configuration.NAVIGATION_DPAD:
                text = "DPAD";
                break;
            case Configuration.NAVIGATION_TRACKBALL:
                text = "TRACKBALL";
                break;
            case Configuration.NAVIGATION_WHEEL:
                text = "WHEEL";
                break;
            default:
                text = "UNKNOWN";
                break;
        }
        
        return text;
    }
    
    /* copied from Configuration class in Android 2.0 for convenience */
    public static final int NAVIGATIONHIDDEN_UNDEFINED = 0;
    public static final int NAVIGATIONHIDDEN_NO = 1;
    public static final int NAVIGATIONHIDDEN_YES = 2;
    
    private String getNavigationHiddenAsText(int navigationHidden) {
        String text;
        
        switch(navigationHidden) {
            case NAVIGATIONHIDDEN_UNDEFINED:
                text = "UNDEFINED";
                break;
            case NAVIGATIONHIDDEN_NO:
                text = "NO";
                break;
            case NAVIGATIONHIDDEN_YES:
                text = "YES";
                break;
            default:
                text = "UNKNOWN";
                break;
        }
        
        return text;
    }
    
    private String getOrientationAsText(int orientation) {
        String text;
        
        switch(orientation) {
            case Configuration.ORIENTATION_UNDEFINED:
                text = "UNDEFINED";
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                text = "PORTRAIT";
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                text = "LANDSCAPE";
                break;
            case Configuration.ORIENTATION_SQUARE:
                text = "SQUARE";
                break;
            default:
                text = "UNKNOWN";
                break;
        }
        
        return text;
    }
    
}
