package com.firatg.walpy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.firatg.walpy.dao.WallFavRepository;
import com.firatg.walpy.model.Item;
import com.firatg.walpy.model.PhotosItem;

import java.util.List;

public class FavoritiesViewModel extends AndroidViewModel {
    private WallFavRepository repository;
    private LiveData<List<Item>> allNotes;
    private LiveData<Item> teks;


    public FavoritiesViewModel(@NonNull Application application,int id) {
        super(application);
        repository =new WallFavRepository(application,id);
        teks = repository.getTek();
    }
    public FavoritiesViewModel(@NonNull Application application) {
        super(application);
        repository =new WallFavRepository(application);
        allNotes = repository.getAllNotes();
    }
    public void insert(Item note){
        repository.insert(note);
    }
    public void update(Item note){
        repository.update(note);
    }
    public void delete(Item note){
        repository.delete(note);
    }
    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    public LiveData<List<Item>> getAllNotes(){
        return allNotes;
    }

    public LiveData<Item> kolp(){
        return teks;
    }

}
