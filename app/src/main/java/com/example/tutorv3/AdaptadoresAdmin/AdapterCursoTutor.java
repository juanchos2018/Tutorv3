package com.example.tutorv3.AdaptadoresAdmin;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorv3.ClasesAdmin.cursotutor;

import java.util.ArrayList;

public class AdapterCursoTutor  extends RecyclerView.Adapter<AdapterCursoTutor.ViewHolderDatos> {
    ArrayList<cursotutor> listacursotutor;

    public AdapterCursoTutor(ArrayList<cursotutor> listacursotutor) {
        this.listacursotutor = listacursotutor;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listacursotutor.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
        }
    }
}
