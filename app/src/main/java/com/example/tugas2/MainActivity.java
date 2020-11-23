package com.example.tugas2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AppDatabase database;
    EditText enim, enama, ealamat;
    Button bsimpan, blihatdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enim = findViewById(R.id.inputnim);
        enama = findViewById(R.id.inputnama);
        ealamat = findViewById(R.id.inputalamat);

        bsimpan = findViewById(R.id.save);
        bsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.save:

                        //Mengecek Data NIM dan Nama
                        if(enim.getText().toString().isEmpty() || enama.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this, "NIM dan Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                        }else {
                            //Membuat Instance/Objek Dari Class Entity Mahasisiwaa
                            Mahasiswa data = new Mahasiswa();

                            //Memasukan data yang diinputkan user pada database
                            data.setNim(enim.getText().toString());
                            data.setNama(enama.getText().toString());
                            data.setAlamat(ealamat.getText().toString());
                            insertData(data);

                            //Mengembalikan EditText menjadi seperti semula
                            enim.setText("");
                            enama.setText("");
                            ealamat.setText("");
                        }
                        break;

                    case R.id.show:
                        startActivity(new Intent(MainActivity.this, ReadData.class));
                        break;
                }
            }
        });

        blihatdata = findViewById(R.id.show);
        blihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReadData.class);
                startActivity(intent);
            }
        });

        database = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "dbMahasiswabaru").build();
    }

    @SuppressLint("StaticFieldLeak")
    private void insertData(final Mahasiswa mahasiswa){
        new AsyncTask<Void, Void, Long>(){
        @Override
        protected Long doInBackground(Void... voids) {
            return database.mahasiswaDAO().insertMahasiswa(mahasiswa);
        }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(MainActivity.this, "Status Row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}