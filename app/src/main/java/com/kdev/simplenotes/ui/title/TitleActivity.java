package com.kdev.simplenotes.ui.title;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
        initRecView();
        initViewModel();

    }

    private void initViewModel() {
        final Observer<List<NoteEntity>> listObserver =
                new Observer<List<NoteEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<NoteEntity> noteEntities) {
                        noteData.clear();
                        noteData.addAll(noteEntities);

                        if(adapter == null){
                            adapter = new TitleAdapter(noteData);
                            mRecView.setAdapter(adapter);
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    }
                };

        mViewModel = ViewModelProviders.of(this)
                .get(TitleViewModel.class);
        mViewModel.mNotes.observe(this, listObserver);
    }

    private void initRecView(){
        mRecView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecView.setLayoutManager(linearLayoutManager);

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
        } else if (id == R.id.actionDeleteSampleData) {
            deleteSampleData();
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteSampleData() {
        mViewModel.deleteSampleData();
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }
}

