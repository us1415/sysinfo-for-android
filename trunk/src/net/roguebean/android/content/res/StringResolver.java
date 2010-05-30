/*
 * StringResolver.java, Aug 11, 2009
 *
 */
package net.roguebean.android.content.res;

/**
 * The <code>StringResolver</code> interface defines a method to convert strings to resolved forms. Implementing classes
 * are responsible for providing the rule of resolving strings.
 * 
 * @author Yonghwan Cho
 * @version 0.7
 */
public interface StringResolver {
    
    public CharSequence resolve(CharSequence source);
    
}
