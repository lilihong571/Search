package com.llh.searchview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 项目名:    SearchView
 * 包名:      com.llh.searchview
 * 文件名:    BookAdapter
 * 创建者:    LLH
 * 创建时间:  2019/8/20 16:02
 * 描述:      TODO
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<BookListData> mList;
    private Context mContent;
    public BookAdapter(Context mContent,List<BookListData> mList){
        this.mContent = mContent;
        this.mList = mList;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_catalog;
        ImageView imageView;
        //构造函数
        public ViewHolder(View view){
            super(view);
            //绑定控件
            tv_title = view.findViewById(R.id.tv_book_title);
            tv_catalog = view.findViewById(R.id.tv_book_catalog);
            imageView = view.findViewById(R.id.iv_book);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //填充视图
        View view = LayoutInflater.from(mContent).inflate(R.layout.item_book,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //设置数据
        BookListData data = mList.get(i);
        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_catalog.setText(data.getCatalog());
        //设置图片
        Picasso.get().load(data.getImageUrl()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
