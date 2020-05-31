package com.firatg.walpy.dao;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.firatg.walpy.model.Item;

import java.util.List;

public class WallFavRepository {
    private WaalDao noteDao;
    private LiveData<List<Item>> allNotes;
    private LiveData<Item> tek;



    public WallFavRepository(Application application,int id){
        WaalDatabase database = WaalDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
        tek = noteDao.getSingleRecord(id);
    }

    public WallFavRepository(Application application) {
        WaalDatabase database = WaalDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }


    public void insert(Item note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Item note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Item note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    public  LiveData<List<Item>> getAllNotes(){
        return allNotes;
    }

    public LiveData<Item> getTek(){
        return tek;
    }


    private static class InsertNoteAsyncTask extends AsyncTask<Item,Void,Void> {
        private WaalDao noteDao;

        private InsertNoteAsyncTask(WaalDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Item... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Item,Void,Void>{
        private WaalDao noteDao;

        private UpdateNoteAsyncTask(WaalDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Item... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Item,Void,Void>{
        private WaalDao noteDao;

        private DeleteNoteAsyncTask(WaalDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Item... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void,Void,Void>{
        private WaalDao noteDao;

        private DeleteAllNotesAsyncTask(WaalDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
