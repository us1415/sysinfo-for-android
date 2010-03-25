/*
 * TitleSummaryItem.java, Aug 10, 2009
 *
 */
package net.roguebean.android.widget;

/**
 * The <code>TitleSummaryItem</code> class represents an item which has title, summary and arbitrary data.
 * 
 * @author Yonghwan Cho
 * @version 0.6
 */
public class TitleSummaryItem {
    
    public final CharSequence title;
    
    public final CharSequence summary;
    
    public final Object data;
    
    public TitleSummaryItem(CharSequence title, CharSequence summary) {
        this(title, summary, null);
    }
    
    public TitleSummaryItem(CharSequence title, CharSequence summary, Object data) {
        this.title = title;
        this.summary = summary;
        this.data = data;
    }
    
    @Override
    public String toString() {
        return String.valueOf(title);
    }
    
}
