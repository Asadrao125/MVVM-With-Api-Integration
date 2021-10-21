package com.appsxone.mvvmwithapiintegration.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.appsxone.mvvmwithapiintegration.model.MovieModel;
import com.appsxone.mvvmwithapiintegration.network.APIService;
import com.appsxone.mvvmwithapiintegration.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends AndroidViewModel {
    private MutableLiveData<List<MovieModel>> movieList;

    public MovieListViewModel(@NonNull Application application) {
        super(application);
        movieList = new MutableLiveData<>();
    }

    public MutableLiveData<List<MovieModel>> getMoviesListObserver() {
        return movieList;
    }

    public void makeApiCall() {
        APIService apiService = RetrofitInstance.getRetrofit().create(APIService.class);
        Call<List<MovieModel>> call = apiService.getMovieList();
        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                movieList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                movieList.postValue(null);
            }
        });
    }
}
