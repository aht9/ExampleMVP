package com.amirht97.examplemvp.peresenter.api;

import com.amirht97.examplemvp.model.MovieResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface apiInterface {
    @GET("?s=2020&type=movie&apikey=137d5bf1&r=json")
    Observable<MovieResponse> getMovie();
}
