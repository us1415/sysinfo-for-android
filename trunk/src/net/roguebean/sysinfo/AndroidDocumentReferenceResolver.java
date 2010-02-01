/*
 * AndroidDocumentReferenceResolver.java, Aug 11, 2009
 *
 */
package net.roguebean.sysinfo;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;

import net.roguebean.android.content.res.PatternedStringResolver;

/**
 * The <code>AndroidDocumentReferenceResolver</code> class resolves a string which has the form of fully qualified name
 * of a java element into a string indicating Android API reference URL.
 * 
 * @author Yonghwan Cho
 * @version 0.5
 */
class AndroidDocumentReferenceResolver extends PatternedStringResolver {
    
    private static final AndroidDocumentReferenceResolver SHARED = new AndroidDocumentReferenceResolver();
    
    private static final String BLANK = "";
    
    private static final String ANDROID_SOURCE_REGEX = "\\{([\\w\\.\\(\\)]+)\\}";
    
    private static final String SOURCE_SEPARATOR_REGEX = "\\.";
    
    private static final char SOURCE_SEPARATOR = '.';
    
    private static final String PATH_SEPARATOR = "/";
    
    private static final char FRAGMENT_SEPARATOR = '#';
    
    private static final String DOCUMENT_URL = "http://developer.android.com/reference/%s.html%s";
    
    private static final String PACKAGE_PAGE = "package-summary";
    
    private static final String PACKAGES_PAGE = "packages";
    
    private AndroidDocumentReferenceResolver() {
        super(ANDROID_SOURCE_REGEX, 1);
    }
    
    @Override
    protected CharSequence resolve(String match, String[] captured) {
        String source = captured[0];
        String url = getDocumentURL(source);
        URLSpan span = new URLSpan(url);
        
        SpannableString resolved = new SpannableString(source);
        resolved.setSpan(span, 0, source.length(), Spanned.SPAN_POINT_MARK);
        
        return resolved;
    }
    
    private static String getDocumentURL(String source) {
        int start = 0;
        int found = -1;
        
        int length = source.length();
        while(true) {
            int index = source.indexOf(SOURCE_SEPARATOR, start);
            if(index >= 0) {
                String temp = source.substring(0, index);
                if(isClassName(temp)) {
                    found = index;
                    break;
                } else if(isPackageName(temp)) {
                    found = 0;
                    start = index + 1;
                } else {
                    found = -1;
                    break;
                }
            } else {
                if(isClassName(source)) {
                    found = length;
                }
                break;
            }
        }
        
        String page;
        String frag;
        
        if(found > 0) {
            String clsName = source.substring(0, found);
            page = clsName.replaceAll(SOURCE_SEPARATOR_REGEX, PATH_SEPARATOR);
            
            if(found < length) {
                // source is a member name not a class name.
                int last = source.lastIndexOf(SOURCE_SEPARATOR);
                if(last > found) {
                    // source has an inner class name.
                    String inClsName = source.substring(found + 1, last);
                    page += SOURCE_SEPARATOR + inClsName;
                }
                // source is a member name. member can be a field or a method.
                frag = FRAGMENT_SEPARATOR + source.substring(last + 1);
            } else {
                // source is a class name.
                frag = BLANK;
            }
        } else if(found == 0) {
            // source is a package name
            page = source.replaceAll(SOURCE_SEPARATOR_REGEX, PATH_SEPARATOR);
            page += PATH_SEPARATOR + PACKAGE_PAGE;
            frag = BLANK;
        } else {
            // source is not a valid name
            page = PACKAGES_PAGE;
            frag = BLANK;
        }
        
        return String.format(DOCUMENT_URL, page, frag);
    }
    
    private static boolean isPackageName(String s) {
        Package pkg = Package.getPackage(s);
        return pkg != null;
    }
    
    private static boolean isClassName(String s) {
        try {
            Class.forName(s);
            return true;
        } catch(ClassNotFoundException e) {
            return false;
        }
    }
    
    static AndroidDocumentReferenceResolver getSharedInstance() {
        return SHARED;
    }
    
}
