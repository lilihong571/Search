package com.llh.searchview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {
    private RecyclerView rv_display;
    BookAdapter adapter;
    private List<BookListData> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        initView();
    }

    private void initView() {
        rv_display = (RecyclerView)findViewById(R.id.rv_display);
        //创建布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理
        rv_display.setLayoutManager(layoutManager);
        //初始化链表
        initList();
        //创建适配器
        adapter = new BookAdapter(this,mList);
        //设置适配器
        rv_display.setAdapter(adapter);
    }

    private void initList() {
        mList = (List<BookListData>) getIntent().getSerializableExtra("listObj");
    }
}
