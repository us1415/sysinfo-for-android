/*
 * SysinfoListActivity.java, Aug 11, 2009
 *
 */
package net.roguebean.sysinfo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import net.roguebean.android.content.res.StringResolver;
import net.roguebean.android.content.res.XResources;

/**
 * The <code>SysinfoListActivity</code> class shows information items.
 * 
 * <p>Accepts an URI which has following form:
 * <br/>
 * <pre>
 *  sysinfo:id[?query]
 * </pre>
 * <br/>
 * where id can be one of the followings:
 * <br/>
 * <ul>
 * <li>all</li>
 * <li>build</li>
 * <li>environment</li>
 * <li>settings</li>
 * <li>display</li>
 * <li>activity</li>
 * <li>telephony</li>
 * <li>runtime</li>
 * </ul>
 * <br/>
 * where query can be: TBD
 * <br/>
 * 
 * @author Yonghwan Cho
 * @version 0.5
 */
public class SysinfoListActivity extends ExpandableListActivity {
    
    private AlertDialog dialog;
    
    private List<SysinfoList> data;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refresh();
    }
    
    private void refresh() {
        // get ID
        String id;
        Intent intent = getIntent();
        Uri intentData = intent.getData();
        if(intentData != null) {
            id = intentData.getSchemeSpecificPart();
        } else {
            id = Sysinfo.ID_ALL;
        }
        
        // load data
        List<SysinfoList> data = load(id);
        setData(data);
    }
    
    private List<SysinfoList> load(String id) {
        List<SysinfoList> list;
        
        String[] targetIds;
        XResources resources = new XResources(getResources(), getPackageName());
        if(Sysinfo.ID_ALL.equals(id)) {
            targetIds = resources.getStringArray(R.array.sysinfo_ids);
        } else {
            targetIds = new String[] { id };
        }
        
        if(targetIds != null) {
            int count = targetIds.length;
            list = new ArrayList<SysinfoList>(count);
            for(String targetId : targetIds) {
                SysinfoList sysinfoList = getSysinfoList(targetId, resources);
                list.add(sysinfoList);
            }
        } else {
            list = null;
        }
        
        return list;
    }
    
    private SysinfoList getSysinfoList(String id, XResources resources) {
        SysinfoList list;
        
        List<SysinfoListItem> items = getSysinfoListItems(id, resources);
        if(items != null) {
            CharSequence name = resources.getText(id + "_name");
            list = new SysinfoList(name, items);
        } else {
            list = null;
        }
        
        return list;
    }
    
    private List<SysinfoListItem> getSysinfoListItems(String id, XResources resources) {
        List<SysinfoListItem> items;
        
        Activity activity = this;
        Object[] values = Values.get(activity, id);
        if(values != null) {
            CharSequence[] names = getTextArray(resources, id + "_item_names", null);
            
            int count = names != null ? names.length : 0;
            if(count > 0) {
                items = new ArrayList<SysinfoListItem>(count);
                
                // resolve Android API document reference.
                AndroidDocumentReferenceResolver resolver = AndroidDocumentReferenceResolver.getSharedInstance();
                CharSequence[] descriptions = getTextArray(resources, id + "_item_descriptions", resolver);
                CharSequence[] references = getTextArray(resources, id + "_item_references", resolver);
                
                int valueCount = values.length;
                int descriptionCount = descriptions != null ? descriptions.length : 0;
                int referenceCount = descriptions != null ? references.length : 0;
                for(int i = 0; i < count; i++) {
                    CharSequence name = names[i];
                    CharSequence value = String.valueOf(i < valueCount ? values[i] : null);
                    CharSequence description = i < descriptionCount ? descriptions[i] : null;
                    CharSequence reference = i < referenceCount ? references[i] : null;
                    
                    items.add(new SysinfoListItem(name, value, description, reference));
                }
            } else {
                items = null;
            }
        } else {
            items = null;
        }
        
        return items;
    }
    
    private static CharSequence[] getTextArray(XResources xr, String name, StringResolver resolver) {
        CharSequence[] array;
        try {
            array = xr.getTextArray(name, resolver);
        } catch(NotFoundException e) {
            array = null;
        }
        return array;
    }
    
    private void setData(List<SysinfoList> data) {
        this.data = data;
        
        // set list adapter
        ExpandableListAdapter adapter;
        if(data != null && !data.isEmpty()) {
            Context context = this;
            adapter = new SysinfoListAdapter(context, data);
        } else {
            adapter = null;
        }
        setListAdapter(adapter);
        
        // expand all
        if(adapter != null) {
            ExpandableListView view = getExpandableListView();
            int count = adapter.getGroupCount();
            for(int i = 0; i < count; i++) {
                view.expandGroup(i);
            }
        }
    }
    
    List<SysinfoList> getData() {
        return data;
    }
    
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        ExpandableListAdapter adapter = getExpandableListAdapter();
        if(adapter != null) {
            SysinfoListItem item = (SysinfoListItem) adapter.getChild(groupPosition, childPosition);
            showDescriptionDialog(item);
            return true;
        } else {
            return false;
        }
    }
    
    private void showDescriptionDialog(SysinfoListItem item) {
        AlertDialog dialog = this.dialog;
        TextView desView;
        TextView refView;
        if(dialog == null) {
            Context context = this;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.sysinfo_list_item_description, null);
            Builder b = new Builder(context);
            b.setView(view);
            dialog = b.create();
            this.dialog = dialog;
            
            desView = (TextView) view.findViewById(R.id.description);
            refView = (TextView) view.findViewById(R.id.reference);
            
            MovementMethod movement = LinkMovementMethod.getInstance();
            desView.setMovementMethod(movement);
            refView.setMovementMethod(movement);
        } else {
            desView = (TextView) dialog.findViewById(R.id.description);
            refView = (TextView) dialog.findViewById(R.id.reference);
        }
        
        dialog.setTitle(item.name);
        desView.setText(item.description);
        refView.setText(item.reference);
        
        dialog.show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_sysinfo_list_activity, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        try {
            switch (id) {
                case R.id.menu_save:
                    SysinfoUtils.save(this);
                    break;
                case R.id.menu_send:
                    SysinfoUtils.send(this);
                    break;
                case R.id.menu_refresh:
                    refresh();
                    break;
                case R.id.menu_about:
                    SysinfoUtils.showAbout(this);
                    break;
                default:
                    break;
            }
        } catch(Exception e) {
            SysinfoUtils.showMessage(this, e);
        }
        return true;
    }
    
}
