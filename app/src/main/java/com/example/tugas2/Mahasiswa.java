package com.example.tugas2;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tMahasiswa") //Membuat tabel baru dengan nama "mahasiswa"
public class Mahasiswa implements Serializable {
    //Membuat kolom NIM sebagai Primary Key
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "nim_mahasiswa")
    private
    String nim;

    //Membuat kolom Nama
    @ColumnInfo(name = "nama_mahasiswa")
    private
    String nama;

    //Membuat kolom Alamat
    @ColumnInfo(name = "alamat")
    private
    String alamat;

    //Method untuk mengambil data NIM
    @NonNull
    public String getNim() {
        return nim;
    }

    //Method untuk memasukan data NIM
    public void setNim(@NonNull String nim) {
        this.nim = nim;
    }

    //Method untuk mengambil data Nama
    public String getNama() {
        return nama;
    }

    //Method untuk memasukan data Nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    //Method untuk mengambil data Alamat
    public String getAlamat() { return alamat; }

    //Method untuk memasukan data Nama
    public void setAlamat(String alamat) { this.alamat = alamat;}


}
