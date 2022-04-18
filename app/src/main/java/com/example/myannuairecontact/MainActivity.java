package com.example.myannuairecontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ItemClicked{

    RecyclerView recyclerView;
    RecyclerView.Adapter myAadapter;
    RecyclerView.LayoutManager layoutManager ;



    FloatingActionButton fab ;
    List<Contact> contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        ContactRoomDatabase db = Room.databaseBuilder(getApplicationContext(),ContactRoomDatabase.class,"Contact")
                .allowMainThreadQueries()
                .build();
        contacts = db.wordDao().getAlphabetizezContact();
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , NewContactActivity.class));
            }
        });
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAadapter = new ContactAdapter(this, contacts);
        recyclerView.setAdapter(myAadapter);

    }

    @Override
    public void onItemClicked(int index) {
        Toast.makeText(getApplicationContext(),
                "This a toast message"+contacts.get(index).getName(),
                Toast.LENGTH_LONG).show();
        Intent intent =new Intent(MainActivity.this,EditorActivity.class) ;
        intent.putExtra("name" ,contacts.get(index).getName());
        intent.putExtra("number",contacts.get(index).getNumber());
        intent.putExtra("email",contacts.get(index).getEmail());
        startActivity(intent);

    }
}