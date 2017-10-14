package com.example.acer.smart.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.acer.smart.R;
import com.example.acer.smart.adapter.CourierAdapter;
import com.example.acer.smart.entity.CourierData;
import com.example.acer.smart.utils.L;
import com.example.acer.smart.utils.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
*  项目名：Smart
*  包名： com.example.acer.smart.ui
*  文件名：CourierActivity
*  创建者：朱勇杰
*  创建时间：2017/9/21
*  描述：  
*/
public class CourierActivity extends BaseActivity implements View.OnClickListener {


    private EditText et_name;
    private EditText et_number;
    private Button  btn_get_courier;
    private ListView mListView;


    private List<CourierData>  mList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);
        //初始化界面
        initView();
    }

    private void initView() {

        et_name= (EditText) findViewById(R.id.et_name);
        et_number= (EditText) findViewById(R.id.et_number);
        btn_get_courier= (Button) findViewById(R.id.btn_get_courier);
        btn_get_courier.setOnClickListener(this);
        mListView= (ListView) findViewById(R.id.mListView);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get_courier:
                /**
                 * 1.获取输入框中的内容
                 * 2.判断是否为空
                 * 3.拿到数据去请求json
                 * 4.解析json数据包
                 * 5.listview适配器
                 * 6.实体类
                 * 7.设置数据/显示效果
                 */
                String name=et_name.getText().toString().trim();
                String number=et_number.getText().toString().trim();
                String url="http://jisukdcx.market.alicloudapi.com"+ StaticClass.COURIER_KEY+"&com="+name+"&no="+number;
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(number))
                {
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
                            Toast.makeText(CourierActivity.this, t, Toast.LENGTH_SHORT).show();
                            L.d("json"+t);
                            parsingJson(t);
                        }
                    });
                }
                else {
                    Toast.makeText(this, "输入框不能为空！", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }
     //解析数据
    private void parsingJson(String t) {

        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonArray = jsonResult.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);

                CourierData data = new CourierData();
                data.setRemark(json.getString("remark"));
                data.setZone(json.getString("zone"));
                data.setDatatime(json.getString("datetime"));
                mList.add(data);
            }
            //倒序
            Collections.reverse(mList);
            CourierAdapter adapter = new CourierAdapter(this,mList);
            mListView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
