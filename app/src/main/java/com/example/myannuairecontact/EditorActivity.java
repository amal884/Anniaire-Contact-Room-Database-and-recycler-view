package com.example.myannuairecontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditorActivity extends AppCompatActivity {
    EditText mEditNameView;
    EditText mEditNumberView;
    EditText mEditEmailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        getIncomingIntent();
        final Button button = findViewById(R.id.button_update);
        ContactRoomDatabase db = Room.databaseBuilder(getApplicationContext(),ContactRoomDatabase.class,"Contact")
                .allowMainThreadQueries()
                .build();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mEditNameView= findViewById(R.id.medit_name);
                 mEditNumberView=findViewById(R.id.medit_number);
                 mEditEmailView=findViewById(R.id.medit_email);
                String uName =mEditNameView.getText().toString();
                String uNumber =mEditNumberView.getText().toString();
                String uEmail =mEditEmailView.getText().toString();

               Contact contact = new Contact(uName,uNumber,uEmail);
                db.wordDao().updateContact(contact);
                startActivity(new Intent(EditorActivity.this ,MainActivity.class));
            }
        });
    }
    private void getIncomingIntent(){
        String mName = getIntent().getStringExtra("name");
        String mNumber = getIntent().getStringExtra("number");
        String mEmail = getIntent().getStringExtra("email");
        setText(mName,mNumber,mEmail);
    }
    private void setText(String mName , String mNumber , String mEmail){
        mEditNameView= findViewById(R.id.medit_name);
        mEditNumberView=findViewById(R.id.medit_number);
        mEditEmailView=findViewById(R.id.medit_email);
        mEditNameView.setText(mName);
        mEditNumberView.setText(mNumber);
        mEditEmailView.setText(mEmail);
    }
}