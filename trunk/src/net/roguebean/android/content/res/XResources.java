/*
 * XResources.java, Aug 10, 2009
 *
 */
package net.roguebean.android.content.res;

import android.content.res.Resources;

import net.roguebean.java.util.XArrays;

/**
 * The <code>XResources</code> class extends <code>Resources</code> by providing methods to retrieve resources by
 * name(not by id) and in as resolved forms.
 * 
 * @author Yonghwan Cho
 * @version 0.7
 */
public class XResources extends Resources {
    
    private final String pkgName;
    
    public XResources(Resources r, String pkgName) {
        super(r.getAssets(), r.getDisplayMetrics(), r.getConfiguration());
        this.pkgName = pkgName;
    }
    
    public CharSequence getText(String name) {
        int id = getIdentifier(name, "string", pkgName);
        if(id != 0) {
            CharSequence text = getText(id);
            return text;
        }
        throw new NotFoundException("Text resource name: " + name);
    }
    
    public CharSequence getText(String name, StringResolver resolver) {
        CharSequence text = getText(name);
        return resolve(resolver, text);
    }
    
    public String getString(String name) {
        int id = getIdentifier(name, "string", pkgName);
        if(id != 0) {
            String string = getString(id);
            return string;
        }
        throw new NotFoundException("String resource name: " + name);
    }
    
    public CharSequence getString(String name, StringResolver resolver) {
        String string = getString(name);
        return resolve(resolver, string);
    }
    
    public CharSequence[] getTextArray(String name) {
        int id = getIdentifier(name, "array", pkgName);
        if(id != 0) {
            CharSequence[] array = getTextArray(id);
            return array;
        }
        throw new NotFoundException("Text array resource name: " + name);
    }
    
    public CharSequence[] getTextArray(String name, StringResolver resolver) {
        CharSequence[] array = getTextArray(name);
        return resolve(resolver, array);
    }
    
    public String[] getStringArray(String name) {
        int id = getIdentifier(name, "array", pkgName);
        if(id != 0) {
            String[] array = getStringArray(id);
            return array;
        }
        throw new NotFoundException("String array resource name: " + name);
    }
    
    public CharSequence[] getStringArray(String name, StringResolver resolver) {
        String[] array = getStringArray(name);
        return resolve(resolver, array);
    }
    
    private CharSequence resolve(StringResolver resolver, CharSequence string) {
        CharSequence resolved;
        if(string != null) {
            resolved = resolver != null ? resolver.resolve(string) : string;
        } else {
            resolved = string;
        }
        return resolved;
    }
    
    private CharSequence[] resolve(StringResolver resolver, CharSequence[] array) {
        CharSequence[] resolved;
        if(array != null) {
            if(resolver != null) {
                int count = array.length;
                resolved = XArrays.copyOf(array, count);
                for(int i = 0; i < count; i++) {
                    resolved[i] = resolver.resolve(array[i]);
                }
            } else {
                resolved = array;
            }
        } else {
            resolved = array;
        }
        return resolved;
    }
    
}
