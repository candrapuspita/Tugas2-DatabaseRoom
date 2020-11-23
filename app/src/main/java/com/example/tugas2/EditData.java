package com.example.tugas2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class EditData extends AppCompatActivity {

    AppDatabase database;
    EditText enim, enama, ealamat;
    Button bsimpan;
    Mahasiswa mahasiswa;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_data);

            enim = findViewById(R.id.inputnim);
            enama = findViewById(R.id.inputnama);
            ealamat = findViewById(R.id.inputalamat);
            bsimpan = findViewById(R.id.save);

            database = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "dbMahasiswabaru").build();

            getDataMahasiswa();

            bsimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mahasiswa.setNim(enim.getText().toString());
                    mahasiswa.setNama(enama.getText().toString());
                    mahasiswa.setAlamat(ealamat.getText().toString());
                    updateData(mahasiswa);
                }
            });
        }

        private void getDataMahasiswa(){
            mahasiswa = (Mahasiswa)getIntent().getSerializableExtra("data");

            enim.setText(mahasiswa.getNim());
            enama.setText(mahasiswa.getNama());
            ealamat.setText(mahasiswa.getAlamat());
        }

        @SuppressLint("StaticFieldLeak")
        private void updateData(final Mahasiswa mahasiswa){
            new AsyncTask<Void, Void, Integer>() {
                @Override
                protected Integer doInBackground(Void... voids) {
                    return database.mahasiswaDAO().updateMahasiswa(mahasiswa);
                }

                @Override
                protected void onPostExecute(Integer status) {
                    Toast.makeText(EditData.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditData.this, ReadData.class);
                    startActivity(intent);
                }
            }.execute();
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            Intent intent = new Intent(EditData.this, ReadData.class);
            startActivity(intent);
        }
}