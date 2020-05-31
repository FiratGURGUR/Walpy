package com.firatg.walpy.api.data;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.firatg.walpy.model.PhotosItem;

public class WaalDataSourceFactory extends DataSource.Factory<Long, PhotosItem> {

    public MutableLiveData<WaalDataSource> waalLiveDataSource =new MutableLiveData<>();

    @Override
    public DataSource<Long, PhotosItem> create() {
        WaalDataSource waalDataSource = new WaalDataSource();
        waalLiveDataSource.postValue(waalDataSource);
        return waalDataSource;
    }



}
