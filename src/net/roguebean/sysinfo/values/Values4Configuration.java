/*
 * Values4Configuration.java, Mar 25, 2010
 *
 * Copyright 2010 ThinkFree. All rights reserved.
 */
package net.roguebean.sysinfo.values;

import android.app.Activity;
import android.content.res.Configuration;

import net.roguebean.sysinfo.Values;


/**
 * The <code>Values4Configuration</code> type.
 *
 * @author Yonghwan Cho
 * @version 0.7
 *
 */
public class Values4Configuration extends Values {

    @Override
    public Object[] values(Activity activity) {
        Configuration c = activity.getResources().getConfiguration();
        
        // Configuration.navigationHidden
        Object navigationHidden = getFieldValue(Configuration.class, "navigationHidden", c, 5);
        String navigationHiddenAsText;
        if(navigationHidden instanceof Integer) {
            int nh = ((Integer) navigationHidden).intValue();
            navigationHiddenAsText = nh + " (" + getNavigationHiddenAsText(nh) + ")";
        } else {
            navigationHiddenAsText = String.valueOf(navigationHidden);
        }
        
        // Configuration.uiMode
        Object uiMode = getFieldValue(Configuration.class, "uiMode", c, 8);
        String uiModeAsText;
        if(uiMode instanceof Integer) {
            int um = ((Integer) uiMode).intValue();
            uiModeAsText = um + " (" + getUiModeAsText(um) + ")";
        } else {
            uiModeAsText = String.valueOf(uiMode);
        }
        
        return new Object[] {
                c.fontScale,
                c.mcc,
                c.mnc,
                c.locale,
                c.screenLayout + " (" + getScreenLayoutAsText(c.screenLayout) + ")",
                c.touchscreen + " (" + getTouchScreenAsText(c.touchscreen) + ")",
                c.keyboard + " (" + getKeyboardAsText(c.keyboard) + ")",
                c.keyboardHidden + " (" + getKeyboardHiddenAsText(c.keyboardHidden) + ")",
                c.hardKeyboardHidden + " (" + getHardKeyboardHiddenAsText(c.hardKeyboardHidden) + ")",
                c.navigation + " (" + getNavigationAsText(c.navigation) + ")",
                navigationHiddenAsText,
                c.orientation + "(" + getOrientationAsText(c.orientation) + ")",
                uiModeAsText,
        };
    }
    
    private String getScreenLayoutAsText(int flags) {
        int lsize = flags & Configuration.SCREENLAYOUT_SIZE_MASK;
        int llong = flags & Configuration.SCREENLAYOUT_LONG_MASK;
        
        String ssize;
        switch(lsize) {
            case Configuration.SCREENLAYOUT_SIZE_UNDEFINED:
                ssize = "UNDEFINED";
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                ssize = "SMALL";
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                ssize = "NORMAL";
                break;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                ssize = "LARGE";
                break;
            default:
                ssize = "UNKNOWN";
                break;
        }
        
        String slong;
        switch(llong) {
            case Configuration.SCREENLAYOUT_LONG_UNDEFINED:
                slong = "UNDEFINED";
                break;
            case Configuration.SCREENLAYOUT_LONG_NO:
                slong = "NO";
                break;
            case Configuration.SCREENLAYOUT_LONG_YES:
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
    
    private static final int UI_MODE_TYPE_MASK = 0x0000000f;
    private static final int UI_MODE_TYPE_UNDEFINED = 0x00000000;
    private static final int UI_MODE_TYPE_NORMAL = 0x00000001;
    private static final int UI_MODE_TYPE_DESK = 0x00000002;
    private static final int UI_MODE_TYPE_CAR = 0x00000003;
    
    private static final int UI_MODE_NIGHT_MASK = 0x00000030;
    private static final int UI_MODE_NIGHT_UNDEFINED = 0x00000000;
    private static final int UI_MODE_NIGHT_NO = 0x00000010;
    private static final int UI_MODE_NIGHT_YES = 0x00000020;
    
    private String getUiModeAsText(int flags) {
        int type = flags & UI_MODE_TYPE_MASK;
        int night = flags & UI_MODE_NIGHT_MASK;
        
        String typeText;
        switch(type) {
            case UI_MODE_TYPE_UNDEFINED:
                typeText = "UNDEFINED";
                break;
            case UI_MODE_TYPE_NORMAL:
                typeText = "NORMAL";
                break;
            case UI_MODE_TYPE_DESK:
                typeText = "DESK";
                break;
            case UI_MODE_TYPE_CAR:
                typeText = "CAR";
                break;
            default:
                typeText = "UNKNOWN";
                break;
        }
        
        String nightText;
        switch(night) {
            case UI_MODE_NIGHT_UNDEFINED:
                nightText = "UNDEFINED";
                break;
            case UI_MODE_NIGHT_NO:
                nightText = "NO";
                break;
            case UI_MODE_NIGHT_YES:
                nightText = "YES";
                break;
            default:
                nightText = "UNKNOWN";
                break;
        }
        
        return typeText + "|" + nightText;
    }
    
}
