package com.example.vip.aahelper.Base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;

/**
 * Created by vip on 16/7/21.
 */
public class BaseActivity extends Activity {
    protected void OpenActivity(Class<?> cls){
        Intent intent = new Intent();
        intent.setClass(this,cls);
        startActivity(intent);
    }
    protected void showSimAlertDialog(String title, AlertDialog.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setNegativeButton("是",listener)
                .setNeutralButton("否",listener)
                .show();
    }
}
