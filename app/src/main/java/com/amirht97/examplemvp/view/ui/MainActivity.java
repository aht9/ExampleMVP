package com.amirht97.examplemvp.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.amirht97.examplemvp.R;
import com.amirht97.examplemvp.model.MovieResponse;
import com.amirht97.examplemvp.peresenter.MainPresenter;
import com.amirht97.examplemvp.peresenter.adapter.MovieAdapter;
import com.amirht97.examplemvp.view.interfaces.MainViewInterface;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    private static final String TAG = "mainActivity";
    RecyclerView recyclerView;
    MainPresenter mainPresenter;

    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mainPresenter = new MainPresenter(this);
        mainPresenter.getMovie();
    }

    @Override
    public void displayMovies(MovieResponse movieResponse) {
        Log.d(TAG,movieResponse.getSearch().get(1).getTitle());
        adapter = new MovieAdapter(MainActivity.this, movieResponse.getSearch());
        recyclerView.setAdapter(adapter);
    }
}