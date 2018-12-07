package com.kdev.simplenotes.database;

import android.content.Context;

import com.kdev.simplenotes.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {

    public List<NoteEntity> mNotes;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    private static AppRepository instance;

    public static AppRepository getInstance(Context context) {
        if(instance == null){
            instance = new AppRepository(context);
        }
        return instance;
    }

    private AppRepository(Context context) {
        mNotes = SampleData.getAllNotes();
        mDb = AppDatabase.getInstance(context);
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().insertAll(SampleData.getAllNotes());
            }
        });
    }
}
