package com.kdev.simplenotes.ui.title;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kdev.simplenotes.R;
import com.kdev.simplenotes.database.NoteEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.TitleViewHolder> {

    private List<NoteEntity> list;

    public TitleAdapter(List<NoteEntity> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TitleAdapter.TitleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_node_list, viewGroup, false);
        return new TitleAdapter.TitleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TitleViewHolder titleViewHolder, int i) {
        final NoteEntity note = list.get(i);
        titleViewHolder.itemTv.setText(note.getText());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemTv)
        TextView itemTv;

        TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
