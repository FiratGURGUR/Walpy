package com.firatg.walpy.api.data;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.firatg.walpy.model.PhotosItem;

public class SearchDataSourceFactory extends DataSource.Factory<Long, PhotosItem> {

    public MutableLiveData<SearchDataSource>searchLiveDataSource =new MutableLiveData<>();
    public String search = "";

      public SearchDataSourceFactory(String search){
          this.search = search;
      }

    @Override
    public DataSource<Long, PhotosItem> create() {
        SearchDataSource searchDataSource = new SearchDataSource(search);
        searchLiveDataSource.postValue(searchDataSource);
        return searchDataSource;
    }



}