/*
 * Values4Telephony.java, Aug 19, 2009
 *
 */
package net.roguebean.sysinfo.values;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

import net.roguebean.sysinfo.Values;

/**
 * The <code>Values4Telephony</code> class provides values about the telephony.
 * 
 * @author Yonghwan Cho
 * @version 0.5
 */
public class Values4Telephony extends Values {
    
    @Override
    public Object[] values(Activity activity) {
        TelephonyManager tm = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        return new Object[] {
                tm.getDeviceSoftwareVersion(),
                tm.getDeviceId(),
                tm.getPhoneType(),
                tm.getNetworkOperatorName(),
                tm.getNetworkOperator(),
                tm.isNetworkRoaming(),
                tm.getNetworkCountryIso(),
                tm.getNetworkType(),
                tm.getSimState(),
                tm.getSimOperator(),
                tm.getSimOperatorName(),
                tm.getSimCountryIso(),
                tm.getSimSerialNumber(),
                tm.getSubscriberId(),
                tm.getLine1Number(),
                tm.getVoiceMailNumber(),
                tm.getVoiceMailAlphaTag(),
                tm.getCallState(),
                tm.getDataActivity(),
                tm.getDataState(),
        };
    }
    
}
