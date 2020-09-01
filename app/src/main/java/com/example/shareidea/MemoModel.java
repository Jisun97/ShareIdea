package com.example.shareidea;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;

public class MemoModel implements Serializable {
    private String title, sub, text, user, time;
    private Boolean favorite;

    public MemoModel(){}

    public MemoModel(String title, String sub, String text, String user, String time, Boolean favorite) {
        this.title = title;
        this.sub = sub;
        this.text = text;
        this.user = user;
        this.time = time;
        this.favorite = favorite;
    }

    public static HashMap<String, String> toMap(MemoModel model){
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(model), HashMap.class);
    }

    public static MemoModel fromMap(HashMap<String, String> map){
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(map), MemoModel.class);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}
