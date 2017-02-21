package com.example.anton.todolist.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.anton.todolist.R;

/**
 * Created by anton on 20/02/2017.
 */

public class AddNoteActivity extends AppCompatActivity{
    EditText titleEditText, bodyEditText;

    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titleEditText = (EditText)findViewById(R.id.title_note_et);
        bodyEditText = (EditText)findViewById(R.id.body_note_et);

        intent = getIntent();

        if(intent != null){
            if(intent.getIntExtra(MainActivity.ACTION_MODE, 0) == MainActivity.REQUEST_EDIT) {
                titleEditText.setText(intent.getStringExtra(MainActivity.EXTRA_TITLE));
                bodyEditText.setText(intent.getStringExtra(MainActivity.EXTRA_BODY));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_confirm:
                confirmNote();
                return true;

            case android.R.id.home:
                setResult(Activity.RESULT_CANCELED);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void confirmNote(){
        Intent i = new Intent();
        i.putExtra(MainActivity.EXTRA_TITLE, titleEditText.getText().toString());
        i.putExtra(MainActivity.EXTRA_BODY, bodyEditText.getText().toString());
        setResult(Activity.RESULT_OK, i);
        finish();
    }
}
