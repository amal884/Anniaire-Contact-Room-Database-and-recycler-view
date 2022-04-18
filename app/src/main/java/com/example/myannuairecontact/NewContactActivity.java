package com.example.myannuairecontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewContactActivity extends AppCompatActivity {

    private EditText mEditNameView;
    private EditText mEditNumberView;
    private EditText mEditEmailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        mEditNameView = findViewById(R.id.edit_name);
        mEditNumberView = findViewById(R.id.edit_number);
        mEditEmailView = findViewById(R.id.edit_email);

        final Button button = findViewById(R.id.button_save);
        ContactRoomDatabase db = Room.databaseBuilder(getApplicationContext(),ContactRoomDatabase.class,"Contact")
                .allowMainThreadQueries()
                .build();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mEditNameView.getText().toString();
                String number = mEditNumberView.getText().toString();
                String email = mEditEmailView.getText().toString();
                Contact contact = new Contact(name , number ,email);
                db.wordDao().insert(contact);
                startActivity(new Intent(NewContactActivity.this ,MainActivity.class));
            }
        });
    }
}