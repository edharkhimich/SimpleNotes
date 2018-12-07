package com.kdev.simplenotes.ui.title;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kdev.simplenotes.R;
import com.kdev.simplenotes.database.NoteEntity;
import com.kdev.simplenotes.ui.editor.EditorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kdev.simplenotes.utilities.SampleData.getAllNotes;

public class TitleActivity extends AppCompatActivity {

    private List<NoteEntity> noteData = new ArrayList<>();
    private TitleAdapter adapter;
    private TitleViewModel mViewModel;

    @BindView(R.id.recView)
    RecyclerView mRecView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @OnClick(R.id.fab)
    public void onClick(){
        Intent intent = new Intent(this, EditorActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initViewModel();
        initRecView();

        noteData.addAll(mViewModel.mNotes);


    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(TitleViewModel.class);

    }

    private void initRecView(){
        mRecView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecView.setLayoutManager(linearLayoutManager);

        adapter = new TitleAdapter(noteData);
        mRecView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.actionAddSampleData) {
            addSampleData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }
}

