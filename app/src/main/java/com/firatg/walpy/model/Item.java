package com.firatg.walpy.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "waal_table")
public class Item {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String url;
    private boolean like;


    public Item(int id, String url, boolean like) {
        this.id = id;
        this.url = url;
        this.like = like;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
