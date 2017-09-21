package com.example.acer.smart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.acer.smart.R;
import com.example.acer.smart.entity.CourierData;

import java.util.List;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.adapter
*  文件名：CourierAdapter
*  创建者：朱勇杰
*  创建时间：2017/9/21
*  描述：  快递查询adapter
*/
public class CourierAdapter extends BaseAdapter{

    private Context mContext;
    private List<CourierData> mList;
    //布局加载器
    private LayoutInflater inflater;
    private CourierData data;


    public  CourierAdapter(Context mContext,List<CourierData> mList){

        this.mContext=mContext;
        this.mList=mList;
        inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewholder=null;
        if(view==null){

            viewholder=new ViewHolder();
            view = inflater.inflate(R.layout.layout_courier_item,null);
            viewholder.tv_remark=view.findViewById(R.id.tv_remark);
            viewholder.tv_zone=view.findViewById(R.id.tv_zone);
            viewholder.tv_datatime=view.findViewById(R.id.tv_datetime);
            //设置缓存
            view.setTag(viewholder);
        }
        else{

            viewholder= (ViewHolder) view.getTag();

        }
        //设置数据
        data=mList.get(i);
        viewholder.tv_datatime.setText(data.getDatatime());
        viewholder.tv_remark.setText(data.getRemark());
        viewholder.tv_zone.setText(data.getZone());
        return null;
    }
    class ViewHolder{

        private TextView tv_remark;
        private TextView tv_zone;
        private TextView tv_datatime;
    }

}
