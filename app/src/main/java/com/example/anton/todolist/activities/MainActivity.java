package com.example.anton.todolist.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.anton.todolist.R;
import com.example.anton.todolist.model.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_TITLE = "EXTRA_TITLE";
    public final static String EXTRA_BODY = "EXTRA_BODY";

    public static final String ACTION_MODE = "ACTION_MODE";
    private static final int REQUEST_ADD = 1001;
    public static final int REQUEST_EDIT = 1002;
    private static final int EDITE_MODE = 1;
    private static final int CREATE_MODE = 2;

    Intent intent;

    RecyclerView noteRv;
    LinearLayoutManager linearLayoutManager;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent = getIntent();

        noteRv = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        noteAdapter = new NoteAdapter(this);
        noteRv.setLayoutManager(linearLayoutManager);
        noteRv.setAdapter(noteAdapter);
        noteAdapter.setDataSet(getNotes());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changeActivity = new Intent(MainActivity.this, AddNoteActivity.class);
                changeActivity.putExtra(ACTION_MODE, CREATE_MODE);
                startActivityForResult(changeActivity, REQUEST_ADD);
            }
        });
    }

    public ArrayList<Note> getNotes(){
        ArrayList<Note> notes = new ArrayList<>();
        Note note = new Note();

        for(int i = 0; i < 5; i++){
            notes.add(note);
        }

        return notes;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_ADD && resultCode == Activity.RESULT_OK){
            Note note = new Note();
            note.setTitolo(data.getStringExtra(EXTRA_TITLE));
            note.setCorpo(data.getStringExtra(EXTRA_BODY));
            noteAdapter.addNote(note);
        }
    }

    Note editingNote;

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_delete:
                noteAdapter.removeNote(noteAdapter.getPosition());
                break;
            case R.id.action_edit:
                editingNote = noteAdapter.getNote(noteAdapter.getPosition());
                Intent i = new Intent(this,AddNoteActivity.class);
                i.putExtra(ACTION_MODE, EDITE_MODE);
                i.putExtra(EXTRA_TITLE, editingNote.getTitolo());
                i.putExtra(EXTRA_BODY, editingNote.getCorpo());
                startActivityForResult(i, REQUEST_EDIT);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
