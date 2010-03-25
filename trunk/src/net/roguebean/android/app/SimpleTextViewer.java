/*
 * SimpleTextViewer.java, Sep 28, 2009
 *
 */
package net.roguebean.android.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The <code>SimpleTextViewer</code> class is an activity class to show plain text contents.
 * 
 * @author Yonghwan Cho
 * @version 0.6
 */
public class SimpleTextViewer extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String title;
        String errMsg;
        Intent intent = getIntent();
        Uri uri = intent.getData();
        if(uri != null) {
            String scheme = uri.getScheme();
            if("file".equals(scheme)) {
                File file = new File(uri.getPath());
                if(file.canRead()) {
                    FileReader r;
                    try {
                        r = new FileReader(file);
                        updateContent(r);
                        title = getFileName(file);
                        errMsg = null;
                    } catch(FileNotFoundException e) {
                        title = null;
                        errMsg = e.getMessage();
                    }
                } else {
                    title = null;
                    errMsg = "Cannot read: " + uri;
                }
            } else {
                title = null;
                errMsg = "'file' scheme is supported only: " + uri;
            }
        } else {
            title = null;
            errMsg = "No uri specified.";
        }
        
        if(errMsg != null) {
            Context context = getApplicationContext();
            Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show();
        }
        
        if(title != null) {
            setTitle(title);
        }
    }
    
    private static String getFileName(File file) {
        String path = file.getPath();
        String parent = file.getParent();
        if(parent != null) {
            int offset = parent.length() + 1;
            return path.substring(offset);
        } else {
            return path;
        }
    }
    
    private void updateContent(Reader r) {
        char[] buf = new char[1024];
        
        StringBuilder b = new StringBuilder(1024);
        try {
            while(true) {
                int read = r.read(buf);
                if(read >= 0) {
                    b.append(buf, 0, read);
                } else {
                    break;
                }
            }
        } catch(IOException e) {
            b.append("\n### an error occured during reading text.");
            b.append("\n### ").append(e.toString());
        }
        
        Context context = getApplicationContext();
        
        TextView tv = new TextView(context);
        tv.setText(b.toString());
        ScrollView sv = new ScrollView(context);
        sv.addView(tv);
        
        setContentView(sv);
    }
    
}
