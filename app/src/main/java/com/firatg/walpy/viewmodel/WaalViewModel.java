package com.firatg.walpy.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.firatg.walpy.api.data.WaalDataSource;
import com.firatg.walpy.api.data.WaalDataSourceFactory;
import com.firatg.walpy.model.PhotosItem;

public class WaalViewModel extends ViewModel {

    public LiveData<PagedList<PhotosItem>> userPagedList;
    public LiveData<WaalDataSource> liveDataSource;

    public WaalViewModel() {
        init();
    }
    private void init() {
        WaalDataSourceFactory itemDataSourceFactory = new WaalDataSourceFactory();



        liveDataSource = itemDataSourceFactory.waalLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(WaalDataSource.PAGE_SIZE)
                .build();
        userPagedList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();




    }




}