package com.firatg.walpy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.firatg.walpy.api.data.CategoryDataSource;
import com.firatg.walpy.api.data.CategoryDataSourceFactory;
import com.firatg.walpy.api.data.SearchDataSource;
import com.firatg.walpy.api.data.SearchDataSourceFactory;
import com.firatg.walpy.model.PhotosItem;

public class CategoryViewModel extends ViewModel {

    public LiveData<PagedList<PhotosItem>> userPagedList;
    public LiveData<CategoryDataSource> liveDataSource;


    public void init(String search) {
        CategoryDataSourceFactory itemDataSourceFactory = new CategoryDataSourceFactory(search);

        liveDataSource = itemDataSourceFactory.categoryLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(CategoryDataSource.PAGE_SIZE)
                .build();
        userPagedList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();

    }
}