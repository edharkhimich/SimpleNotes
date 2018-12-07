package com.kdev.simplenotes;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.kdev.simplenotes.database.AppDatabase;
import com.kdev.simplenotes.database.NoteDao;
import com.kdev.simplenotes.database.NoteEntity;
import com.kdev.simplenotes.utilities.SampleData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    public static final String TAG = "junit";
    private AppDatabase mDb;
    private NoteDao mDao;

    @Before
    public void createDb(){
        Context context = InstrumentationRegistry.getContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mDao = mDb.noteDao();
        Log.d(TAG, "createDb: ");
    }

    @Test
    public void createAndRetrieveNotes(){
        mDao.insertAll(SampleData.getAllNotes());
        int count = mDao.getCount();
        Log.d(TAG, "createAndRetrieveNotes: " + count);
        assertEquals(SampleData.getAllNotes().size(), count);
    }

    @Test
    public void compareNoteEntotoesStrings(){
        mDao.insertAll(SampleData.getAllNotes());
        NoteEntity original = SampleData.getAllNotes().get(0);
        NoteEntity fromDb = mDao.getNoteById(1);
        assertEquals(original.getText(), fromDb.getText());
        assertEquals(1, fromDb.getId());
    }


    @After
    public void closeDb(){
        mDb.close();
    }


}
