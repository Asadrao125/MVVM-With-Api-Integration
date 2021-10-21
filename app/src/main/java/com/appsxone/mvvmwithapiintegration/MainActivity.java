package com.appsxone.mvvmwithapiintegration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.appsxone.mvvmwithapiintegration.adapter.MovieListAdapter;
import com.appsxone.mvvmwithapiintegration.model.MovieModel;
import com.appsxone.mvvmwithapiintegration.view_model.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MovieListAdapter adapter;
    RecyclerView rvMoviesList;
    List<MovieModel> moviesList;
    MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMoviesList = findViewById(R.id.rvMoviesList);
        rvMoviesList.setLayoutManager(new LinearLayoutManager(this));
        rvMoviesList.setHasFixedSize(true);

        adapter = new MovieListAdapter(this, moviesList);
        rvMoviesList.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        viewModel.getMoviesListObserver().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null) {
                    moviesList = movieModels;
                    adapter.setMoviesList(moviesList);
                } else {
                    Toast.makeText(MainActivity.this, "No Result Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.makeApiCall();
    }
}