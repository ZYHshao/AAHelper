package com.example.vip.aahelper.Adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vip.aahelper.Model.SliderListItemModel;
import com.example.vip.aahelper.R;

import java.util.ArrayList;




/**
 * Created by vip on 16/7/23.
 */
public class SliderListAdpter extends BaseAdapter {

    private class HolderSlider{
        TextView textView;
    }

    Context mContext;
    ArrayList<SliderListItemModel> dataArray;
    public SliderListAdpter(ArrayList<SliderListItemModel> array,Context context){
        mContext = context;
        dataArray = array;
    }


    @Override
    public int getCount() {
        return dataArray.size();
    }

    @Override
    public Object getItem(int i) {
        return dataArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HolderSlider holder;
        if (view==null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.layout_slider_list_item,null);
            TextView tv = (TextView) view.findViewById(R.id.layoutSliderListItemTextView);
            holder = new HolderSlider();
            holder.textView = tv;
            view.setTag(holder);
        }else {
            holder = (HolderSlider) view.getTag();
        }
        holder.textView.setText(dataArray.get(i).title);
        return view;
    }
}
