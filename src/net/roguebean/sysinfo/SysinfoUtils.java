/*
 * SysinfoUtils.java, Aug 19, 2009
 *
 */
package net.roguebean.sysinfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

import net.roguebean.android.app.AboutActivity;

/**
 * The <code>SysinfoUtils</code> class provides utility methods for sysinfo activities.
 * 
 * @author Yonghwan Cho
 * @version 0.5
 */
final class SysinfoUtils {
    
    static void showAbout(Context c) {
        Intent intent = new Intent("net.roguebean.android.intent.action.VIEW_ABOUT");
        
        int descriptionRes;
        String version;
        
        PackageInfo info;
        try {
            info = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
        } catch(NameNotFoundException e) {
            info = null;
        }
        
        if(info != null) {
            descriptionRes = info.applicationInfo.descriptionRes;
            version = info.versionName;
        } else {
            descriptionRes = 0;
            version = null;
        }
        
        Bundle b = new Bundle(8);
        b.putInt(AboutActivity.EXTRA_ICON, R.drawable.sysinfo_icon);
        b.putInt(AboutActivity.EXTRA_NAME, R.string.sysinfo_name);
        b.putString(AboutActivity.EXTRA_VERSION, version);
        b.putInt(AboutActivity.EXTRA_DESCRIPTION, descriptionRes);
        b.putInt(AboutActivity.EXTRA_LINK, R.string.sysinfo_link);
        b.putInt(AboutActivity.EXTRA_COPYRIGHT, R.string.sysinfo_copyright);
        b.putInt(AboutActivity.EXTRA_CONTACT, R.string.sysinfo_contact);
        intent.putExtras(b);
        
        c.startActivity(intent);
    }
    
    static void save(final SysinfoListActivity info) throws Exception {
        List<SysinfoList> data = info.getData();
        if(data == null || data.isEmpty()) {
            String msg = info.getString(R.string.msg_no_information);
            throw new Exception(msg);
        }
        
        int[] selectionIndex = new int[2];
        final File f = getFileToSave(selectionIndex); // suggested file
        final String path = f.getCanonicalPath();
        final EditText et = new EditText(info);
        et.setText(path);
        et.setSingleLine();
        et.setSelection(selectionIndex[0], selectionIndex[1]);
        
        AlertDialog.Builder b = new AlertDialog.Builder(info);
        b.setTitle(R.string.label_save_location);
        b.setView(et);
        b.setPositiveButton(android.R.string.ok, new OnClickListener() {
            
            public void onClick(DialogInterface dialog, int which) {
                String fileName = et.getText().toString();
                save(info, fileName);
            }
        });
        b.setNegativeButton(android.R.string.cancel, new OnClickListener() {
            
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        
        final AlertDialog dialog = b.show();
        et.setOnKeyListener(new OnKeyListener() {
            
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                        if(event.getAction() == KeyEvent.ACTION_DOWN) {
                            String fileName = et.getText().toString();
                            save(info, fileName);
                            dialog.dismiss();
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    
    private static void save(SysinfoListActivity info, String fileName) {
        FileWriter w = null;
        try {
            File dst = new File(fileName);
            if(dst.exists()) {
                showMessage(info, info.getString(R.string.msg_file_exists));
                save(info);
            } else {
                w = new FileWriter(dst);
                writeInfoAsPlainText(w, info.getData());
                w.flush();
                
                onSaved(info, dst);
            }
        } catch(Exception e) {
            if(w != null) {
                try {
                    w.close();
                } catch(IOException e1) {
                    e1.printStackTrace();
                }
            }
            showMessage(info, e);
        }
    }
    
    private static void onSaved(final SysinfoListActivity sysinfo, final File f) throws IOException {
        String path = f.getCanonicalPath();
        String msg = sysinfo.getString(R.string.msg_save, path);
        
        AlertDialog.Builder b = new AlertDialog.Builder(sysinfo);
        b.setTitle(R.string.label_save);
        b.setMessage(msg);
        b.setPositiveButton(android.R.string.ok, new OnClickListener() {
            
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        b.setNegativeButton(R.string.label_view, new OnClickListener() {
            
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.fromFile(f);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                SysinfoUtils.startActivity(sysinfo, intent);
            }
        });
        
        b.show();
    }
    
    static void send(SysinfoListActivity info) throws Exception {
        List<SysinfoList> data = info.getData();
        if(data == null || data.isEmpty()) {
            String msg = info.getString(R.string.msg_no_information);
            throw new Exception(msg);
        }
        
        StringWriter w = new StringWriter(1024);
        writeInfoAsPlainText(w, info.getData());
        String msg = w.toString();
        CharSequence subject = info.getTitle();
        
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        
        CharSequence chooserTitle = info.getText(R.string.label_send);
        Intent chooser = Intent.createChooser(intent, chooserTitle);
        
        SysinfoUtils.startActivity(info, chooser);
    }
    
    static void writeInfoAsPlainText(Writer w, List<SysinfoList> data) throws IOException {
        for(SysinfoList list : data) {
            w.append("# ").append(list.name).append(" #");
            List<SysinfoListItem> items = list.items;
            for(SysinfoListItem item : items) {
                w.append('\n').append(item.name).append("=").append(item.value);
            }
            w.append("\n\n");
        }
    }
    
    static void showMessage(Activity activity, Throwable t) {
        String msg = t.toString();
        showMessage(activity, msg, Toast.LENGTH_SHORT);
    }
    
    static void showMessage(Activity activity, CharSequence msg) {
        showMessage(activity, msg, Toast.LENGTH_SHORT);
    }
    
    static void showMessage(Activity activity, CharSequence msg, int length) {
        Toast.makeText(activity, msg, length).show();
    }
    
    static void startActivity(Activity activity, Intent intent) {
        try {
            activity.startActivity(intent);
        } catch(ActivityNotFoundException e) {
            showMessage(activity, e);
        }
    }
    
    private static File getFileToSave(int[] timeIndex) {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            File esd = Environment.getExternalStorageDirectory();
            if(esd.isDirectory()) {
                long time = System.currentTimeMillis();
                String timeStr = String.valueOf(time);
                String fileName = "sysinfo-" + timeStr + ".txt";
                File file = new File(esd, fileName);
                
                int timeStartIndex = esd.getPath().length() + 9;
                timeIndex[0] = timeStartIndex;
                timeIndex[1] = timeStartIndex + timeStr.length();
                return file;
            } else {
                // should not reach here
                throw new IllegalStateException("External storage directory is not valid. Directory path: "
                        + esd.getPath());
            }
        } else {
            throw new IllegalStateException("External storage is not accessible. Storage state: " + state);
        }
    }
    
}
