package com.amirht97.examplemvp.peresenter;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.amirht97.examplemvp.model.MovieResponse;
import com.amirht97.examplemvp.peresenter.adapter.MovieAdapter;
import com.amirht97.examplemvp.peresenter.api.apiClient;
import com.amirht97.examplemvp.peresenter.api.apiInterface;
import com.amirht97.examplemvp.view.interfaces.MainInterface;
import com.amirht97.examplemvp.view.interfaces.MainViewInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresenter implements MainInterface {

//    apiInterface request;

    MainViewInterface mvi ;

//    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static final String TAG = "MainPresenter";

    public MainPresenter(MainViewInterface mvi) {
        this.mvi = mvi;
    }

    public void getObserverS() {
        getObservable().subscribeWith(getObserver());
    }

    public Observable<MovieResponse> getObservable(){
        return apiClient.getRetrofit().create(apiInterface.class)
                .getMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<MovieResponse> getObserver(){
        return new DisposableObserver<MovieResponse>() {

            @Override
            public void onNext(@NonNull MovieResponse movieResponse) {
                Log.d(TAG,"OnNext : "+movieResponse.getTotalResults());
                mvi.displayMovies(movieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }

    @Override
    public void getMovie() {
        getObserverS();
        /*
        request = apiClient.getRetrofit().create(apiInterface.class);
        compositeDisposable.add(request.getMovie()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse posts) throws Throwable {
                        adapter = new MovieAdapter(context,posts.getSearch());
                        recyclerView.setAdapter(adapter);
                    }
                }));
         */

    }
}
