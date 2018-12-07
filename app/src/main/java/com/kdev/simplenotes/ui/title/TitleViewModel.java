package com.kdev.simplenotes.ui.title;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.kdev.simplenotes.database.AppRepository;
import com.kdev.simplenotes.database.NoteEntity;
import com.kdev.simplenotes.utilities.SampleData;

import java.util.List;

public class TitleViewModel extends AndroidViewModel {

    public LiveData<List<NoteEntity>> mNotes;
    private AppRepository mRepository;

    public TitleViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mNotes = mRepository.mNotes;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public void deleteSampleData() {
        mRepository.deleteSampleData();
    }
}
