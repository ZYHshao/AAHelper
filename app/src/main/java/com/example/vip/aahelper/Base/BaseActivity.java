package com.example.vip.aahelper.Base;

import android.app.Activity;
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
}
