package com.firatg.walpy.api.data;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import com.firatg.walpy.api.MyApi;
import com.firatg.walpy.api.MyClient;
import com.firatg.walpy.model.PhotosItem;
import com.firatg.walpy.model.PopularResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaalDataSource extends PageKeyedDataSource<Long, PhotosItem> {
    public static int PAGE_SIZE = 15;
    public static long FIRST_PAGE = 1;
    public static List<PhotosItem> responseItems;
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, PhotosItem> callback) {
        MyApi api= MyClient.getInstance().getMyApi();
        Call<PopularResponse> call=api.getWallpapers(MyClient.API_KEY, 1);
        call.enqueue(new Callback<PopularResponse>() {
            @Override
            public void onResponse(Call<PopularResponse> call, Response<PopularResponse> response) {
                Log.i("fg",response.message());
                PopularResponse waalResponse = response.body();
                if (waalResponse != null && waalResponse.getPhotos() != null) {
                   responseItems = waalResponse.getPhotos();
                    callback.onResult(responseItems, null, (long) 2);
                }
            }

            @Override
            public void onFailure(Call<PopularResponse> call, Throwable t) {
                Log.i("error",t.getMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, PhotosItem> callback) {
        MyApi api= MyClient.getInstance().getMyApi();
        Call<PopularResponse> call=api.getWallpapers(MyClient.API_KEY, Math.toIntExact(params.key));
        call.enqueue(new Callback<PopularResponse>() {
            @Override
            public void onResponse(Call<PopularResponse> call, Response<PopularResponse> response) {
                Log.i("fg",response.message());
                PopularResponse waalResponse = response.body();
                if (waalResponse != null) {
                    responseItems = waalResponse.getPhotos();
                    long key;
                    if (params.key > 1) {
                        key = params.key - 1;
                    } else {
                        key = 0;
                    }
                    callback.onResult(responseItems, key);
                }
            }

            @Override
            public void onFailure(Call<PopularResponse> call, Throwable t) {
                Log.i("error",t.getMessage());
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, PhotosItem> callback) {
        MyApi api= MyClient.getInstance().getMyApi();
        Call<PopularResponse> call=api.getWallpapers(MyClient.API_KEY, Math.toIntExact(params.key));
        call.enqueue(new Callback<PopularResponse>() {
            @Override
            public void onResponse(Call<PopularResponse> call, Response<PopularResponse> response) {
                Log.i("fg",response.message());
                PopularResponse waalResponse = response.body();
                if (waalResponse != null && waalResponse.getPhotos() != null) {
                    responseItems = waalResponse.getPhotos();
                    callback.onResult(responseItems, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<PopularResponse> call, Throwable t) {
                Log.i("error",t.getMessage());
            }
        });

    }
}
