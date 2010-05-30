/*
 * XArrays.java, Aug 11, 2009
 *
 */
package net.roguebean.java.util;

import java.lang.reflect.Array;

/**
 * The <code>XArrays</code> class provides utility methods which deal with arrays. Methods defined in
 * <code>XArrays</code> are generally based on the methods defined in <code>Arrays</code> class of Java 6.
 * 
 * @author Yonghwan Cho
 * @version 0.7
 */
public final class XArrays {
    
    private XArrays() {
    }
    
    public static int[] copyOf(int[] original, int newLength) {
        int[] copy = new int[newLength];
        int length = Math.min(original.length, newLength);
        System.arraycopy(original, 0, copy, 0, length);
        return copy;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T[] copyOf(T[] original, int newLength) {
        return (T[]) copyOf(original, newLength, original.getClass());
    }
    
    @SuppressWarnings("unchecked")
    public static <T, U> T[] copyOf(U[] original, int newLength, Class<? extends T> newType) {
        T[] copy = ((Object) newType == (Object) Object[].class) ? (T[]) new Object[newLength] : (T[]) Array
                .newInstance(newType.getComponentType(), newLength);
        int length = Math.min(original.length, newLength);
        System.arraycopy(original, 0, copy, 0, length);
        return copy;
    }
    
}
