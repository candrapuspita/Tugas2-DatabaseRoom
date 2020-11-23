package com.example.tugas2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadData extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    AppDatabase database;
    ArrayList<Mahasiswa> daftarMahasiswa;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readdata);
        getSupportActionBar().setTitle("DAFTAR MAHASISWA");

        recyclerView = (RecyclerView) findViewById(R.id.dataitem);

        button = (Button) findViewById(R.id.adddata);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadData.this, MainActivity.class);
                startActivity(intent);
            }
        });

        daftarMahasiswa = new ArrayList<>();

        database = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "dbMahasiswabaru").allowMainThreadQueries().build();

        daftarMahasiswa.addAll(Arrays.asList(database.mahasiswaDAO().readDataMahasiswa()));

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerMahasiswaAdapter(daftarMahasiswa, ReadData.this);
        recyclerView.setAdapter(adapter);
    }
}
