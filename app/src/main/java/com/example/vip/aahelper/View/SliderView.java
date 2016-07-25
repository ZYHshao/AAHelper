package com.example.vip.aahelper.View;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vip.aahelper.Adpter.SliderListAdpter;
import com.example.vip.aahelper.Model.SliderListItemModel;
import com.example.vip.aahelper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vip on 16/7/23.
 */
public class SliderView{
    Activity mActivity;
    ArrayList<SliderListItemModel> mList;
    boolean isClose;
    RelativeLayout bottomLyout;
    ListView mListView;
    SliderViewItemClickListener itemClickListener;
    public interface SliderViewItemClickListener{
        public void sliderViewItemClick(View listItem,SliderListItemModel model,int position);
    }

    public SliderView(Activity pActivity){
        mActivity = pActivity;
        itemClickListener = (SliderViewItemClickListener) pActivity;
        mList = new ArrayList<SliderListItemModel>();
        isClose = true;
        bottomLyout = (RelativeLayout) pActivity.findViewById(R.id.layoutBottomBar);
        mListView = (ListView) bottomLyout.findViewById(R.id.layoutBottomListView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemClickListener.sliderViewItemClick(view,mList.get(i),i);
            }
        });
        //设置监听事件
        bottomLyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClose){
                    open();
                    isClose=false;
                }else {
                    close();
                    isClose=true;
                }
            }
        });
    }
    private void open(){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.BELOW,R.id.layoutTitleBar);
        bottomLyout.setLayoutParams(params);
    }

    private void close(){
        final float scale = mActivity.getResources().getDisplayMetrics().density;
        int height = (int) (68 * scale + 0.5f);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,height);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bottomLyout.setLayoutParams(params);
    }

    public void bindAdpter(){
        mListView.setAdapter(new SliderListAdpter(mList,mActivity));
    }

    public void addItem(SliderListItemModel model){
        mList.add(model);
    }


}
