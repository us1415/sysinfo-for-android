/*
 * AboutActivity.java, Aug 19, 2009
 *
 */
package net.roguebean.android.app;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

import net.roguebean.sysinfo.R;

/**
 * The <code>AboutActivity</code> class is an activity class to show 'about' screen.
 * 
 * @author Yonghwan Cho
 * @version 0.7
 */
public class AboutActivity extends Activity {
    
    public static final String EXTRA_ICON = "icon";
    
    public static final String EXTRA_NAME = "name";
    
    public static final String EXTRA_VERSION = "version";
    
    public static final String EXTRA_DESCRIPTION = "description";
    
    public static final String EXTRA_LINK = "link";
    
    public static final String EXTRA_COPYRIGHT = "copyright";
    
    public static final String EXTRA_CONTACT = "contact";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        // get views
        ImageView vIcon = (ImageView) findViewById(R.id.about_icon);
        TextView vName = (TextView) findViewById(R.id.about_name);
        TextView vVersion = (TextView) findViewById(R.id.about_version);
        TextView vDescription = (TextView) findViewById(R.id.about_description);
        TextView vLink = (TextView) findViewById(R.id.about_link);
        TextView vCopyright = (TextView) findViewById(R.id.about_copyright);
        TextView vContact = (TextView) findViewById(R.id.about_contact);
        
        // set values
        Bundle b = getIntent().getExtras();
        if(b != null) {
            Object icon = b.get(EXTRA_ICON);
            if(icon instanceof Drawable) {
                vIcon.setImageDrawable((Drawable) icon);
            } else if(icon instanceof Integer) {
                vIcon.setImageResource((Integer) icon);
            } else if(icon instanceof Uri) {
                vIcon.setImageURI((Uri) icon);
            }
        }
        CharSequence name = getCharSequenceExtra(b, EXTRA_NAME);
        vName.setText(name);
        vVersion.setText(getCharSequenceExtra(b, EXTRA_VERSION));
        vDescription.setText(getCharSequenceExtra(b, EXTRA_DESCRIPTION));
        vLink.setText(getCharSequenceExtra(b, EXTRA_LINK));
        vCopyright.setText(getCharSequenceExtra(b, EXTRA_COPYRIGHT));
        vContact.setText(getCharSequenceExtra(b, EXTRA_CONTACT));
        
        // linkify
        Linkify.addLinks(vLink, Linkify.ALL);
        Linkify.addLinks(vCopyright, Linkify.ALL);
        Linkify.addLinks(vContact, Linkify.ALL);
        
        // set title
        setTitle(getString(R.string.about_title, name));
    }
    
    private CharSequence getCharSequenceExtra(Bundle b, String key) {
        CharSequence result;
        
        if(b != null) {
            Object value = b.get(key);
            if(value instanceof CharSequence) {
                result = (CharSequence) value;
            } else if(value instanceof Number) {
                try {
                    result = getText(((Number) value).intValue());
                } catch(NotFoundException e) {
                    result = "";
                }
            } else {
                result = "";
            }
        } else {
            result = "";
        }
        
        return result;
    }
    
}
