package com.example.vip.aahelper.Base;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.vip.aahelper.R;
import com.example.vip.aahelper.View.SliderView;

/**
 * Created by vip on 16/7/21.
 */
public class FrameActivity extends BaseActivity {
    protected SliderView sliderView;

    protected void AppendMainBody(int resId){
        LinearLayout mainbody = (LinearLayout) findViewById(R.id.contentLayout);
        View view = LayoutInflater.from(this).inflate(resId,null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        mainbody.addView(view,params);
    }
    protected void creatSlider(){
       sliderView = new SliderView(this);
    }
}
