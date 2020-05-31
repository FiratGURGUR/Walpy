package com.firatg.walpy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.firatg.walpy.api.data.SearchDataSource;
import com.firatg.walpy.api.data.SearchDataSourceFactory;
import com.firatg.walpy.api.data.WaalDataSource;
import com.firatg.walpy.api.data.WaalDataSourceFactory;
import com.firatg.walpy.model.PhotosItem;

public class SearchViewModel extends ViewModel {

    public LiveData<PagedList<PhotosItem>> userPagedList;
    public LiveData<SearchDataSource> liveDataSource;


    public void init(String search) {
        SearchDataSourceFactory itemDataSourceFactory = new SearchDataSourceFactory(search);

        liveDataSource = itemDataSourceFactory.searchLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(SearchDataSource.PAGE_SIZE)
                .build();
        userPagedList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();

    }
}