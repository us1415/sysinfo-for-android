/*
 * SysinfoListAdapter.java, Aug 11, 2009
 *
 */
package net.roguebean.sysinfo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * The <code>SysinfoListAdapter</code> class provides list items from the list of sysinfo list to the expandable list
 * view.
 * 
 * @author Yonghwan Cho
 * @version 0.5
 */
class SysinfoListAdapter extends BaseExpandableListAdapter {
    
    private final LayoutInflater inflater;
    
    private final List<SysinfoList> list;
    
    SysinfoListAdapter(Context context, List<SysinfoList> list) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }
    
    public int getGroupCount() {
        return list.size();
    }
    
    public int getChildrenCount(int groupPosition) {
        return ((SysinfoList) getGroup(groupPosition)).items.size();
    }
    
    public Object getGroup(int groupPosition) {
        return getSysinfoList(groupPosition);
    }
    
    public Object getChild(int groupPosition, int childPosition) {
        return getSysinfoListItem(groupPosition, childPosition);
    }
    
    public long getGroupId(int groupPosition) {
        return getSysinfoList(groupPosition).name.hashCode();
    }
    
    public long getChildId(int groupPosition, int childPosition) {
        return getSysinfoListItem(groupPosition, childPosition).name.hashCode();
    }
    
    public boolean hasStableIds() {
        return true;
    }
    
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        
        if(convertView == null) {
            view = inflater.inflate(R.layout.expandable_list_item_group, parent, false);
        } else {
            view = convertView;
        }
        
        SysinfoList group = getSysinfoList(groupPosition);
        TextView tv = (TextView) view.findViewById(R.id.label);
        tv.setText(group.name);
        
        return view;
    }
    
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
            ViewGroup parent) {
        View view;
        
        if(convertView == null) {
            view = inflater.inflate(R.layout.title_summary_list_item, parent, false);
        } else {
            view = convertView;
        }
        
        SysinfoListItem child = getSysinfoListItem(groupPosition, childPosition);
        TextView tv = (TextView) view.findViewById(R.id.title);
        tv.setText(child.name);
        tv = (TextView) view.findViewById(R.id.summary);
        tv.setText(child.value);
        
        return view;
    }
    
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    
    private SysinfoList getSysinfoList(int groupPosition) {
        return list.get(groupPosition);
    }
    
    private SysinfoListItem getSysinfoListItem(int groupPosition, int childPosition) {
        return list.get(groupPosition).items.get(childPosition);
    }
    
}
