/*
 * Sysinfo.java, Aug 11, 2009
 *
 */
package net.roguebean.sysinfo;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import net.roguebean.android.content.res.XResources;
import net.roguebean.android.widget.TitleSummaryArrayAdapter;
import net.roguebean.android.widget.TitleSummaryItem;

/**
 * The <code>Sysinfo</code> class is the Sysinfo launcher activity.
 * 
 * @author Yonghwan Cho
 * @version 0.5
 */
public class Sysinfo extends ListActivity {
    
    static final String ID_ALL = "all";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }
    
    private void initialize() {
        // loads data
        List<String> data = load();
        
        // sets list adapter
        ListAdapter adapter;
        if(data != null && !data.isEmpty()) {
            Context context = this;
            XResources resources = new XResources(getResources(), getPackageName());
            int count = data.size();
            List<TitleSummaryItem> items = new ArrayList<TitleSummaryItem>(count);
            for(String id : data) {
                CharSequence name; // required
                CharSequence summary; // optional
                
                // loads name
                try {
                    name = resources.getText(id + "_name");
                } catch(NotFoundException e) {
                    name = null;
                }
                
                // creates and adds an item only if the name, which is required field, is valid.
                if(name != null) {
                    // loads summary
                    try {
                        summary = resources.getText(id + "_summary");
                    } catch(NotFoundException e) {
                        summary = null;
                    }
                    items.add(new TitleSummaryItem(name, summary, id));
                }
            }
            adapter = new TitleSummaryArrayAdapter<TitleSummaryItem>(context, items);
        } else {
            adapter = null;
        }
        setListAdapter(adapter);
    }
    
    private List<String> load() {
        List<String> list;
        
        XResources resources = new XResources(getResources(), getPackageName());
        String[] ids = resources.getStringArray(R.array.sysinfo_ids);
        int count = ids != null ? ids.length : 0;
        if(count > 0) {
            list = new ArrayList<String>(count + 1);
            if(count > 0) {
                list.add(ID_ALL);
                for(String id : ids) {
                    list.add(id);
                }
            }
        } else {
            list = null;
        }
        
        return list;
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        TitleSummaryItem item = (TitleSummaryItem) getListAdapter().getItem(position);
        startSysinfoListActivity((String) item.data);
    }
    
    private void startSysinfoListActivity(String id) {
        String scheme = "sysinfo";
        String ssp = id;
        Uri uri = Uri.fromParts(scheme, ssp, null);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_sysinfo, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_about:
                SysinfoUtils.showAbout(this);
                break;
            default:
                break;
        }
        return true;
    }
    
}