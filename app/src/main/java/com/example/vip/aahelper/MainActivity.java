package com.example.vip.aahelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.vip.aahelper.Adpter.GridViewAdpter;
import com.example.vip.aahelper.Base.FrameActivity;
import com.example.vip.aahelper.Model.SliderListItemModel;
import com.example.vip.aahelper.View.SliderView;

public class MainActivity extends FrameActivity implements SliderView.SliderViewItemClickListener{

    GridView gridView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppendMainBody(R.layout.layout_main_gradview);
        gridView = (GridView) findViewById(R.id.mainGridView);
        gridView.setAdapter(new GridViewAdpter(this));
        creatSlider();
        //进行slider数据的绑定
        String[] stringArray = getResources().getStringArray(R.array.SliderMainActivity);
        for (int i=0; i<stringArray.length;i++){
            SliderListItemModel model = new SliderListItemModel();
            model.title = stringArray[i];
            sliderView.addItem(model);
        }
        sliderView.bindAdpter();
    }

    @Override
    public void sliderViewItemClick(View listItem, SliderListItemModel model, int position) {
        Toast.makeText(this,model.title,1).show();
    }
}
