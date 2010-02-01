/*
 * Values.java, Aug 10, 2009
 *
 */
package net.roguebean.sysinfo;

import android.app.Activity;

/**
 * The <code>Values</code> class provides values of information in specific category.
 * 
 * @author Yonghwan Cho
 * @version 0.5
 */
public abstract class Values {
    
    private static final String PACKAGE_FOR_VALUES = "net.roguebean.sysinfo.values";
    
    public abstract Object[] values(Activity activity);
    
    public static Object[] get(Activity activity, String infoName) {
        Object[] values;
        
        Values instance = getInstance(infoName);
        if(instance != null) {
            values = instance.values(activity);
        } else {
            values = null;
        }
        
        return values;
    }
    
    private static Values getInstance(String infoName) {
        Values instance;
        
        Class<?> cls = findClass(infoName);
        if(Values.class.isAssignableFrom(cls)) {
            try {
                instance = (Values) cls.newInstance();
            } catch(Exception e) {
                e.printStackTrace();
                instance = null;
            }
        } else {
            instance = null;
        }
        
        return instance;
    }
    
    private static Class<?> findClass(String infoName) {
        Class<?> cls;
        
        if(infoName != null && infoName.length() > 0) {
            String pkgName = PACKAGE_FOR_VALUES;
            String clsName = toClassName(pkgName, infoName);
            try {
                cls = Class.forName(clsName);
            } catch(ClassNotFoundException e) {
                e.printStackTrace();
                cls = null;
            }
        } else {
            cls = null;
        }
        
        return cls;
    }
    
    private static String toClassName(String pkgName, String infoName) {
        StringBuilder b = new StringBuilder(infoName.length());
        
        // append package name first
        b.append(pkgName).append(".Values4");
        
        // convert data set name to class name by convention
        String[] words = infoName.split("_");
        for(String word : words) {
            int length = word.length();
            if(length > 0) {
                b.append(Character.toUpperCase(word.charAt(0)));
                if(length > 1) {
                    b.append(word.substring(1));
                }
            }
        }
        
        return b.toString();
    }
    
}
