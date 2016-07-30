package com.example.vip.aahelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.vip.aahelper.Activity.UserManagerActivity;
import com.example.vip.aahelper.Adpter.GridViewAdpter;
import com.example.vip.aahelper.Base.FrameActivity;
import com.example.vip.aahelper.Model.PeopleModel;
import com.example.vip.aahelper.Model.SliderListItemModel;
import com.example.vip.aahelper.Util.DBDeal;
import com.example.vip.aahelper.View.SliderView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends FrameActivity implements SliderView.SliderViewItemClickListener{

    GridView gridView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppendMainBody(R.layout.layout_main_gradview);
        gridView = (GridView) findViewById(R.id.mainGridView);
        gridView.setAdapter(new GridViewAdpter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    //人员管理模块
                    case 5:
                        OpenActivity(UserManagerActivity.class);
                        break;
                }
            }
        });
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
//        //测试数据库
//        if (position==0){
//            DBDeal deal = new DBDeal(this);
//            PeopleModel people = new PeopleModel();
//            people.isAble=1;
//            people.time = new Date().getTime();
//            people.name = "HS";
//            deal.addPeople(people);
//        }else {
//            DBDeal deal = new DBDeal(this);
//            ArrayList<PeopleModel> array = deal.getAllAbledPeople();
//            Log.e("#####", "sliderViewItemClick: "+array.get(0).name);
//        }
    }
}
