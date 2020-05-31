package com.firatg.walpy.api.data;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.firatg.walpy.model.PhotosItem;

public class CategoryDataSourceFactory extends DataSource.Factory<Long, PhotosItem> {

    public MutableLiveData<CategoryDataSource> categoryLiveDataSource =new MutableLiveData<>();
    public String search = "";

    public CategoryDataSourceFactory(String search){
        this.search = search;
    }

    @Override
    public DataSource<Long, PhotosItem> create() {
        CategoryDataSource categoryDataSource = new CategoryDataSource(search);
        categoryLiveDataSource.postValue(categoryDataSource);
        return categoryDataSource;
    }



}