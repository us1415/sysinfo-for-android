/*
 * PatterenedStringResolver.java, Aug 11, 2009
 *
 */
package net.roguebean.android.content.res;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.SpannableStringBuilder;

import net.roguebean.java.util.XArrays;

/**
 * The <code>PatterenedStringResolver</code> class provides methods to resolve patterned string.
 * 
 * @author Yonghwan Cho
 * @version 0.6
 */
public abstract class PatternedStringResolver implements StringResolver {
    
    private final Matcher matcher;
    
    private final int[] groups;
    
    protected PatternedStringResolver(String regex, int... groups) {
        Pattern pattern = Pattern.compile(regex);
        this.matcher = pattern.matcher("");
        this.groups = copyOf(groups);
    }
    
    public CharSequence resolve(CharSequence source) {
        CharSequence resolved;
        
        Matcher m = this.matcher;
        m.reset(source);
        boolean found = m.find();
        if(found) {
            SpannableStringBuilder result = new SpannableStringBuilder();
            int last = 0;
            do {
                // retrieve captured input subsequences
                String[] captured;
                
                int[] gs = this.groups;
                if(gs != null) {
                    int requestedCount = gs.length;
                    if(requestedCount > 0) {
                        int c = m.groupCount() + 1;
                        captured = new String[requestedCount];
                        for(int i = 0; i < requestedCount; i++) {
                            int group = gs[i];
                            if(group < c) {
                                captured[i] = m.group(gs[i]);
                            } else {
                                captured[i] = null;
                            }
                        }
                    } else {
                        captured = null;
                    }
                } else {
                    captured = null;
                }
                
                // get the string to replace
                String match = m.group();
                CharSequence replacement = resolve(match, captured);
                CharSequence literal = source.subSequence(last, m.start());
                result.append(literal).append(replacement);
                
                last = m.end();
                found = m.find();
            } while(found);
            CharSequence tail = source.subSequence(last, source.length());
            result.append(tail);
            resolved = result;
        } else {
            resolved = source;
        }
        
        return resolved;
    }
    
    protected abstract CharSequence resolve(String match, String[] captured);
    
    private static int[] copyOf(int[] original) {
        int[] copy;
        
        if(original != null) {
            copy = XArrays.copyOf(original, original.length);
        } else {
            copy = null;
        }
        
        return copy;
    }
    
}
