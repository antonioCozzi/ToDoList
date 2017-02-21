package com.example.anton.todolist.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anton.todolist.R;
import com.example.anton.todolist.model.Note;

import java.util.ArrayList;

/**
 * Created by anton on 20/02/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVH> {
    Context context;

    private ArrayList<Note> dataSet = new ArrayList<>();

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<Note> getDataSet() {
        return dataSet;
    }

    public void setDataSet(ArrayList<Note> dataSet) {
        this.dataSet = dataSet;
    }

    public void addNote(Note note){
        dataSet.add(0, note);
        notifyItemInserted(0);
    }

    public void removeNote(int position){
        dataSet.remove(position);
    }

    public Note getNote(int position){
        return dataSet.get(position);
    }

    public NoteAdapter(Context c){
        context = c;
    }

    @Override
    public NoteAdapter.NoteVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteVH(view);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.NoteVH holder, final int position) {
        Note note = dataSet.get(position);
        holder.noteTitle.setText(note.getTitolo());
        holder.noteBody.setText(note.getCorpo());
        holder.noteDate.setText(note.getDataCreazione());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class NoteVH extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView noteTitle, noteBody, noteDate;

        public NoteVH(View view){
            super(view);
            noteTitle = (TextView)view.findViewById(R.id.note_title);
            noteBody = (TextView)view.findViewById(R.id.note_body);
            noteDate = (TextView)view.findViewById(R.id.note_date);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = ((MainActivity)context).getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
        }
    }
}
