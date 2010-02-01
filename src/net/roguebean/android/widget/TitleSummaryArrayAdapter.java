/*
 * TitleSummaryArrayAdapter.java, Aug 10, 2009
 *
 */
package net.roguebean.android.widget;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.roguebean.sysinfo.R;

/**
 * The <code>TitleSummaryArrayAdapter</code> class provides list items from <code>TitleSummaryItem<code> instances.
 * 
 * @author Yonghwan Cho
 * @version 0.5
 */
public class TitleSummaryArrayAdapter<T extends TitleSummaryItem> extends ArrayAdapter<T> {
    
    public TitleSummaryArrayAdapter(Context context) {
        super(context, R.layout.title_summary_list_item, R.id.title);
    }
    
    public TitleSummaryArrayAdapter(Context context, T[] objects) {
        super(context, R.layout.title_summary_list_item, R.id.title, objects);
    }
    
    public TitleSummaryArrayAdapter(Context context, List<T> objects) {
        super(context, R.layout.title_summary_list_item, R.id.title, objects);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if(view != null) {
            TextView summaryView = (TextView) view.findViewById(R.id.summary);
            summaryView.setText(getItem(position).summary);
        }
        return view;
    }
    
}
