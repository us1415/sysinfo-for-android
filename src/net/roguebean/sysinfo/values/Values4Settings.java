/*
 * Values4Settings.java, Aug 11, 2009
 *
 */
package net.roguebean.sysinfo.values;

import static android.provider.Settings.System.*;
import android.app.Activity;
import android.content.ContentResolver;
import android.os.Build;
import android.provider.Settings;

import net.roguebean.sysinfo.Values;

/**
 * The <code>Values4Settings</code> class provides values about the settings.
 * 
 * @author Yonghwan Cho
 * @version 0.7
 */
public class Values4Settings extends Values {
    
    @Override
    public Object[] values(Activity activity) {
        ContentResolver r = activity.getContentResolver();
        return new Object[] {
                Settings.System.getString(r, STAY_ON_WHILE_PLUGGED_IN),
                Settings.System.getString(r, END_BUTTON_BEHAVIOR),
                Settings.System.getString(r, AIRPLANE_MODE_ON),
                Settings.System.getString(r, AIRPLANE_MODE_RADIOS),
                Settings.System.getString(r, WIFI_USE_STATIC_IP),
                Settings.System.getString(r, WIFI_STATIC_IP),
                Settings.System.getString(r, WIFI_STATIC_GATEWAY),
                Settings.System.getString(r, WIFI_STATIC_NETMASK),
                Settings.System.getString(r, WIFI_STATIC_DNS1),
                Settings.System.getString(r, WIFI_STATIC_DNS2),
                Settings.System.getString(r, BLUETOOTH_DISCOVERABILITY),
                Settings.System.getString(r, BLUETOOTH_DISCOVERABILITY_TIMEOUT),
                Settings.System.getString(r, LOCK_PATTERN_ENABLED),
                Settings.System.getString(r, LOCK_PATTERN_VISIBLE),
                Settings.System.getString(r, NEXT_ALARM_FORMATTED),
                Settings.System.getString(r, FONT_SCALE),
                Settings.System.getString(r, DEBUG_APP),
                Settings.System.getString(r, WAIT_FOR_DEBUGGER),
                Settings.System.getString(r, DIM_SCREEN),
                Settings.System.getString(r, SCREEN_OFF_TIMEOUT),
                Settings.System.getString(r, SCREEN_BRIGHTNESS),
                getScreenBrightnessMode(r),
                Settings.System.getString(r, SHOW_PROCESSES),
                Settings.System.getString(r, ALWAYS_FINISH_ACTIVITIES),
                Settings.System.getString(r, MODE_RINGER),
                Settings.System.getString(r, MODE_RINGER_STREAMS_AFFECTED),
                Settings.System.getString(r, MUTE_STREAMS_AFFECTED),
                Settings.System.getString(r, VIBRATE_ON),
                Settings.System.getString(r, VOLUME_RING),
                Settings.System.getString(r, VOLUME_SYSTEM),
                Settings.System.getString(r, VOLUME_VOICE),
                Settings.System.getString(r, VOLUME_MUSIC),
                Settings.System.getString(r, VOLUME_ALARM),
                getVolumeBluetoothSCO(r),
                Settings.System.getString(r, APPEND_FOR_LAST_AUDIBLE),
                Settings.System.getString(r, RINGTONE),
                Settings.System.getString(r, NOTIFICATION_SOUND),
                Settings.System.getString(r, TEXT_AUTO_REPLACE),
                Settings.System.getString(r, TEXT_AUTO_CAPS),
                Settings.System.getString(r, TEXT_AUTO_PUNCTUATE),
                Settings.System.getString(r, TEXT_SHOW_PASSWORD),
                Settings.System.getString(r, SHOW_GTALK_SERVICE_STATUS),
                Settings.System.getString(r, WALLPAPER_ACTIVITY),
                Settings.System.getString(r, AUTO_TIME),
                Settings.System.getString(r, TIME_12_24),
                Settings.System.getString(r, DATE_FORMAT),
                Settings.System.getString(r, SETUP_WIZARD_HAS_RUN),
                Settings.System.getString(r, WINDOW_ANIMATION_SCALE),
                Settings.System.getString(r, TRANSITION_ANIMATION_SCALE),
                Settings.System.getString(r, DTMF_TONE_WHEN_DIALING),
                Settings.System.getString(r, SOUND_EFFECTS_ENABLED),
                Settings.System.getString(r, ADB_ENABLED),
                Settings.System.getString(r, ANDROID_ID),
                Settings.System.getString(r, BLUETOOTH_ON),
                Settings.System.getString(r, DATA_ROAMING),
                Settings.System.getString(r, DEVICE_PROVISIONED),
                Settings.System.getString(r, HTTP_PROXY),
                Settings.System.getString(r, INSTALL_NON_MARKET_APPS),
                Settings.System.getString(r, LOCATION_PROVIDERS_ALLOWED),
                Settings.System.getString(r, LOGGING_ID),
                Settings.System.getString(r, NETWORK_PREFERENCE),
                Settings.System.getString(r, PARENTAL_CONTROL_ENABLED),
                Settings.System.getString(r, PARENTAL_CONTROL_LAST_UPDATE),
                Settings.System.getString(r, PARENTAL_CONTROL_REDIRECT_URL),
                Settings.System.getString(r, SETTINGS_CLASSNAME),
                Settings.System.getString(r, USB_MASS_STORAGE_ENABLED),
                Settings.System.getString(r, USE_GOOGLE_MAIL),
                Settings.System.getString(r, WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON),
                Settings.System.getString(r, WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY),
                Settings.System.getString(r, WIFI_NUM_OPEN_NETWORKS_KEPT),
                Settings.System.getString(r, WIFI_ON),
                Settings.System.getString(r, WIFI_WATCHDOG_ACCEPTABLE_PACKET_LOSS_PERCENTAGE),
                Settings.System.getString(r, WIFI_WATCHDOG_AP_COUNT),
                Settings.System.getString(r, WIFI_WATCHDOG_BACKGROUND_CHECK_DELAY_MS),
                Settings.System.getString(r, WIFI_WATCHDOG_BACKGROUND_CHECK_ENABLED),
                Settings.System.getString(r, WIFI_WATCHDOG_BACKGROUND_CHECK_TIMEOUT_MS),
                Settings.System.getString(r, WIFI_WATCHDOG_INITIAL_IGNORED_PING_COUNT),
                Settings.System.getString(r, WIFI_WATCHDOG_MAX_AP_CHECKS),
                Settings.System.getString(r, WIFI_WATCHDOG_ON),
                Settings.System.getString(r, WIFI_WATCHDOG_PING_COUNT),
                Settings.System.getString(r, WIFI_WATCHDOG_PING_DELAY_MS),
                Settings.System.getString(r, WIFI_WATCHDOG_PING_TIMEOUT_MS),
        };
    }
    
    private static final String SCREEN_BRIGHTNESS_MODE = "screen_brightness_mode";
    
    private static String getScreenBrightnessMode(ContentResolver r) {
        String value;
        
        final int apiLevel = 8;
        if(Build.VERSION.SDK_INT >= apiLevel) {
            value = Settings.System.getString(r, SCREEN_BRIGHTNESS_MODE);
        } else {
            value = getMessageForNotAvaliable(apiLevel);
        }
        
        return value;
    }
    
    private static final String VOLUME_BLUETOOTH_SCO = "volume_bluetooth_sco";
    
    private static String getVolumeBluetoothSCO(ContentResolver r) {
        String value;
        
        final int apiLevel = 8;
        if(Build.VERSION.SDK_INT >= apiLevel) {
            value = Settings.System.getString(r, VOLUME_BLUETOOTH_SCO);
        } else {
            value = getMessageForNotAvaliable(apiLevel);
        }
        
        return value;
    }
    
}
