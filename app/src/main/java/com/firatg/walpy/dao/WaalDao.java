package com.firatg.walpy.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.firatg.walpy.model.Item;
import com.firatg.walpy.model.PhotosItem;

import java.util.List;
@Dao
public interface WaalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Item photosItem);


    @Update
    void update(Item photosItem);

    @Delete
    void delete(Item photosItem);

    @Query("DELETE FROM waal_table")
    void deleteAllNotes();

    @Query("SELECT * FROM waal_table WHERE `like`= 1 ORDER BY id DESC")
    LiveData<List<Item>> getAllNotes();

    @Query("SELECT * FROM waal_table WHERE id = :idd limit 1")
    LiveData<Item>  getSingleRecord(int idd);



}


