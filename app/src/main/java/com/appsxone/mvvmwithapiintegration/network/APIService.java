package com.appsxone.mvvmwithapiintegration.network;

import com.appsxone.mvvmwithapiintegration.model.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("photos")
    Call<List<MovieModel>> getMovieList();

}
