package com.firatg.walpy.api;

import com.firatg.walpy.model.PopularResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApi {


    @GET("popular")
    Call<PopularResponse> getWallpapers(@Header("Authorization") String api_key, @Query("page") int page);

    @GET("search")
    Call<PopularResponse> searchWallpaper(@Header("Authorization") String api_key, @Query("page") int page,@Query("query") String query);


}
