package com.example.vip.aahelper.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vip.aahelper.Base.FrameActivity;
import com.example.vip.aahelper.Model.PeopleModel;
import com.example.vip.aahelper.Model.SliderListItemModel;
import com.example.vip.aahelper.R;
import com.example.vip.aahelper.Util.DBDeal;
import com.example.vip.aahelper.View.SliderView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by vip on 16/7/25.
 */
public class UserManagerActivity extends FrameActivity implements SliderView.SliderViewItemClickListener {
    ListView listView;
    ArrayList<PeopleModel> dataArray;
    //选择的用户模型
    private PeopleModel selectPeopleModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermanager);
        AppendMainBody(R.layout.layout_list_view);
        listView = (ListView) findViewById(R.id.layoutListView);
        registerForContextMenu(listView);
        initData();
        //设置适配器
        listView.setAdapter(new UserListAdpter(this));
        creatSlider();
        SliderListItemModel model = new SliderListItemModel();
        model.title = "新建用户";
        sliderView.addItem(model);

        sliderView.bindAdpter();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo menuInfo1 = (AdapterView.AdapterContextMenuInfo) menuInfo;
        UserListAdpter  adpter = (UserListAdpter) listView.getAdapter();
        selectPeopleModel = (PeopleModel) adpter.getItem(menuInfo1.position);
        menu.setHeaderIcon(R.drawable.shezhi);
        menu.setHeaderTitle(selectPeopleModel.name);
        menu.add("修改");
        menu.add("删除");
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (item.getTitle().toString()=="修改"){
            resetOrAddUser(selectPeopleModel);
        }else {
            //弹出提示框是否删除
            showSimAlertDialog("是否确认删除" + selectPeopleModel.name, new SimpleDialogListener(this));
        }
        return true;
    }
    private class MHandler extends android.os.Handler{
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 101:
                    dataArray.remove(selectPeopleModel);
                    ((UserListAdpter)(listView.getAdapter())).notifyDataSetChanged();
                    break;
            }
        }

    }
    private class SimpleDialogListener implements AlertDialog.OnClickListener{

        Context content;
        public SimpleDialogListener(Context context){
            this.content = context;
        }
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Log.e("~~~~~~~~~~~", "onClick: remove"+i);
            if (i==-2){
                //删除
                Log.e("~~~~~~~~~~~", "onClick: remove");
                DBDeal deal = new DBDeal(content);
                deal.removePeople(selectPeopleModel);
                MHandler hander = new MHandler();
                hander.sendEmptyMessage(101);


            }
        }
    }
    //进行数据初始化
    private void initData() {
        //从数据库中取数据
        DBDeal deal = new DBDeal(this);
        dataArray = deal.getAllAbledPeople();
    }

    @Override
    public void sliderViewItemClick(View listItem, SliderListItemModel model, int position) {
        resetOrAddUser(null);
    }

    private void resetOrAddUser(PeopleModel model){
        //如果model参数为null 则为新建用户
        if (model == null){
            showUserDialog(null);
        }else {
            showUserDialog(model);
        }

    }

    private void showUserDialog(PeopleModel peopleModel) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.layout_dialog_user,null);
        EditText editText = (EditText) view.findViewById(R.id.layoutDialogUserTextEdit);
        String edString = peopleModel==null?"":peopleModel.name;
        editText.setText(edString);
        boolean isAdd = peopleModel==null?true:false;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String title = peopleModel==null?"新建用户":"修改用户";
        builder.setTitle(title)
                .setView(view)
                .setIcon(R.drawable.shezhi)
                .setNeutralButton("保存",new UserDialogOnClickListener(this,editText,peopleModel,true,isAdd))
                .setNegativeButton("取消",new UserDialogOnClickListener(this,editText,peopleModel,false,isAdd))
                .show();

    }

    class UserDialogOnClickListener implements DialogInterface.OnClickListener {

        //用户对象
        private PeopleModel people;
        //是否是保存操作
        private boolean isSave;
        //是否是添加 或者 是更新
        private boolean isAdd;
        private EditText editText;
        private Context context;
        public UserDialogOnClickListener(Context context,EditText editText,PeopleModel peopleModel,boolean isSave,boolean isAdd){
            this.people = peopleModel;
            this.isSave=isSave;
            this.isAdd=isAdd;
            this.editText = editText;
            this.context = context;
        }
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if (!isSave){
                //什么都不做
            }else {
                if (!isAdd){
                    //更新用户信息
                    people.name = editText.getText().toString();
                    DBDeal deal = new DBDeal(context);
                    deal.updatePeopel(people);
                    ((UserListAdpter)(listView.getAdapter())).notifyDataSetChanged();
                }else {
                    //添加新的用户
                    PeopleModel model = new PeopleModel();
                    model.name = editText.getText().toString();
                    model.isAble = 1;
                    model.time = new Date().getTime();
                    DBDeal deal = new DBDeal(context);
                    deal.addPeople(model);
                    dataArray.add(model);
                    ((UserListAdpter)(listView.getAdapter())).notifyDataSetChanged();
                    ((UserManagerActivity)context).sliderView.close();
                }
            }
        }
    }

    class UserListAdpter extends BaseAdapter{
        Context context;
        public UserListAdpter(Context context){
            this.context = context;
        }
        class Handle{
            TextView textView;
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
            Handle handle;
            if (view==null){
                LayoutInflater inflater = LayoutInflater.from(context);
                view = inflater.inflate(R.layout.layout_slider_list_item,null);
                handle = new Handle();
                handle.textView = (TextView) view.findViewById(R.id.layoutSliderListItemTextView);
                handle.textView.setTextColor(Color.WHITE);
                view.setTag(handle);
            }else {
                handle = (Handle) view.getTag();
            }
            handle.textView.setText(dataArray.get(i).name);
            return view;
        }
    }
}
