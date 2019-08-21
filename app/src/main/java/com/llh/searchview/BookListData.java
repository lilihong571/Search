package com.llh.searchview;

import java.io.Serializable;

/**
 * 项目名:    SearchView
 * 包名:      com.llh.searchview
 * 文件名:    BookListData
 * 创建者:    LLH
 * 创建时间:  2019/8/20 16:01
 * 描述:      TODO
 */
public class BookListData implements Serializable {
    private String imageUrl;
    private String title;
    private String catalog;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
