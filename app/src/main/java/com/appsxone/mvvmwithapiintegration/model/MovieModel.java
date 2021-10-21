package com.appsxone.mvvmwithapiintegration.model;

public class MovieModel {
    public String title;
    public String thumbnailUrl;

    public MovieModel(String title, String body) {
        this.title = title;
        this.thumbnailUrl = body;
    }
}
