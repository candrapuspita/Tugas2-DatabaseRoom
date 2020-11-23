package com.example.tugas2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;

public class RecyclerMahasiswaAdapter extends RecyclerView.Adapter<RecyclerMahasiswaAdapter.ViewHolder> {

    ArrayList<Mahasiswa> daftarMahasiswa;
    AppDatabase appDatabase;
    Context context;

    public RecyclerMahasiswaAdapter(ArrayList<Mahasiswa> daftarMahasiswa, Context context) {
        this.daftarMahasiswa = daftarMahasiswa;
        this.context = context;
        appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "dbMahasiswabaru").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nim1, nama2, alamat3;
        CardView item;

        ViewHolder(View itemView) {
            super(itemView);
            nim1 = itemView.findViewById(R.id.nims);
            nama2 = itemView.findViewById(R.id.namas);
            alamat3 = itemView.findViewById(R.id.alamats);
            item = itemView.findViewById(R.id.cvMain);
        }
    }

    @NonNull
    @Override
    public RecyclerMahasiswaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hasil, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMahasiswaAdapter.ViewHolder holder, final int position) {
        String getNim = daftarMahasiswa.get(position).getNim();
        String getNama = daftarMahasiswa.get(position).getNama();
        String getAlamat = daftarMahasiswa.get(position).getAlamat();


        holder.nim1.setText(getNim);
        holder.nama2.setText(getNama);
        holder.alamat3.setText(getAlamat);

        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence[] menuPilihan = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext())
                        .setTitle("Pilih Aksi")
                        .setItems(menuPilihan, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        onEditData(position, context);
                                        break;

                                    case 1:
                                        onDeleteData(position);
                                        break;
                                }
                            }
                        });
                dialog.create();
                dialog.show();
                return true;
            }
        });
    }

    private void onEditData(int position, Context context) {
        context.startActivity(new Intent(context, EditData.class).putExtra("data", daftarMahasiswa.get(position)));
        ((Activity) context).finish();
    }

    private void onDeleteData(int position){
        appDatabase.mahasiswaDAO().deleteMahasiswa(daftarMahasiswa.get(position));
        daftarMahasiswa.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, daftarMahasiswa.size());
        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return daftarMahasiswa.size();
    }
}

