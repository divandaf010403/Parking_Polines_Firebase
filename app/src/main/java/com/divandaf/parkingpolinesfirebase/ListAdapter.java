package com.divandaf.parkingpolinesfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class ListAdapter extends FirebaseRecyclerAdapter<model, ListAdapter.myViewHolder> {

    public ListAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull model model) {
        holder.nama.setText(model.getNama());
        holder.nim.setText(model.getNim());
        holder.kelas.setText(model.getKelas());
        holder.noHP.setText(model.getNoHP());
        holder.email.setText(model.getEmail());
        holder.noKendaraan.setText(model.getNoKendaraan());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView nama, nim, kelas, noHP, email, noKendaraan;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = (TextView) itemView.findViewById(R.id.viewNamaCard);
            nim = (TextView) itemView.findViewById(R.id.viewNimCard);
            kelas = (TextView) itemView.findViewById(R.id.viewKelasCard);
            noHP = (TextView) itemView.findViewById(R.id.viewNoHPCard);
            email = (TextView) itemView.findViewById(R.id.viewEmailCard);
            noKendaraan = (TextView) itemView.findViewById(R.id.viewNoPlatCard);
        }
    }

}
