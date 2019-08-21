package com.llh.searchview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

public class SearchActivity extends AppCompatActivity {
    private SearchView searchView;
    private List<BookListData> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        searchView = findViewById(R.id.search_view);
        //点击搜索按钮
        searchView.setOnClickSearch(new ICallBack() {
            @Override                   //搜索框的内容
            public void SearchAciton(String string) {
                //Log.d("llhData", "SearchAciton: "+string);
                //搜索框内容
                if(string.equals("")){
                    Toast.makeText(SearchActivity.this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    int catalog_id = Integer.parseInt(string);
                    String url = "http://apis.juhe.cn/goodbook/query?key=0095c828443aeb604ca2511b97202c57&catalog_id="+catalog_id+"&rn=10&rn=10";
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
                            //Log.d("llhData",t);
                             parsingJson(t);
                            //页面跳转
                            Intent intent = new Intent(SearchActivity.this,DisplayActivity.class);
                            intent.putExtra("listObj",(Serializable)mList);
//                            Log.d("llhData", "SearchAciton: "+mList);
//                            Log.d("llhData", "SearchAciton: "+mList.size());
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(VolleyError error) {
                            Log.d("llhData","请求失败");
                        }
                    });
                }
            }
        });
        //设置返回事件
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });
    }
    //解析json
    private void parsingJson(String t) {
        mList.clear();
        //Log.d("llhData", "t: "+t);
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonArray = jsonResult.getJSONArray("data");
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject json = (JSONObject) jsonArray.get(i);
                //获取当前数据
                String imageUrl = json.getString("img");
                //Log.d("llhData", "imageUrl: "+imageUrl);
                String title = json.getString("title");
                String catalog = json.getString("catalog");
                //创建对象
                BookListData data = new BookListData();
                data.setTitle(title);
                data.setCatalog(catalog);
                data.setImageUrl(imageUrl);
                mList.add(data);
            }
//            Log.d("llhData", "SearchAciton: "+mList);
//            Log.d("llhData", "SearchAciton: "+mList.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
