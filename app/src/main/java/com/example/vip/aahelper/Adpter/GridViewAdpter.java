package com.example.vip.aahelper.Adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vip.aahelper.Model.GridViewAdpterModel;
import com.example.vip.aahelper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vip on 16/7/21.
 */
public class GridViewAdpter extends BaseAdapter {

    Context context;
    ArrayList<GridViewAdpterModel> dataList = new ArrayList<GridViewAdpterModel>();
    public GridViewAdpter(Context context){
        this.context = context;
        creatDatalist();
    }

    private void creatDatalist() {
        GridViewAdpterModel model = new GridViewAdpterModel();
        model.text = "记录消费";
        model.imageID = R.drawable.jizhang;

        GridViewAdpterModel model2 = new GridViewAdpterModel();
        model2.text = "查询消费";
        model2.imageID = R.drawable.shengyijingying;

        GridViewAdpterModel model3 = new GridViewAdpterModel();
        model3.text = "统计管理";
        model3.imageID = R.drawable.tongji;

        GridViewAdpterModel model4 = new GridViewAdpterModel();
        model4.text = "账本管理";
        model4.imageID = R.drawable.zhangben;

        GridViewAdpterModel model5 = new GridViewAdpterModel();
        model5.text = "类别管理";
        model5.imageID = R.drawable.gongzixinchou;

        GridViewAdpterModel model6 = new GridViewAdpterModel();
        model6.text = "人员管理";
        model6.imageID = R.drawable.cishanjuanzeng;

        dataList.add(model);
        dataList.add(model2);
        dataList.add(model3);
        dataList.add(model4);
        dataList.add(model5);
        dataList.add(model6);



    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view==null){
            LayoutInflater layout = LayoutInflater.from(context);
            view = layout.inflate(R.layout.layout_gridview_item,null);
            holder = new Holder();
            holder.ivImage = (ImageView) view.findViewById(R.id.gridViewItemImageView);
            holder.tvText = (TextView) view.findViewById(R.id.gridViewItemTextView);
            view.setTag(holder);

        }else {
            holder = (Holder) view.getTag();
        }
        holder.ivImage.setBackgroundResource(dataList.get(i).imageID);
        holder.tvText.setText(dataList.get(i).text);

        return view;
    }


    private class Holder{
        ImageView ivImage;
        TextView tvText;
    }
}
