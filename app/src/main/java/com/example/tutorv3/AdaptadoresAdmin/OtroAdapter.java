package com.example.tutorv3.AdaptadoresAdmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorv3.ClasesAdmin.Alumnos;
import com.example.tutorv3.R;

import java.util.ArrayList;

public class OtroAdapter  extends RecyclerView.Adapter<OtroAdapter.ViewHolderDatos>{


    ArrayList<Alumnos> listaPersonaje;

    public OtroAdapter(ArrayList<Alumnos> listaPersonaje) {
        this.listaPersonaje = listaPersonaje;
    }

    @NonNull
    @Override
    public OtroAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buscador,null,false);
        //  vista.setOnClickListener(this);
        return new OtroAdapter.ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull OtroAdapter.ViewHolderDatos holder, int position) {

        if (holder instanceof OtroAdapter.ViewHolderDatos){
            //  final Dato dato=
            final OtroAdapter.ViewHolderDatos datgolder =(OtroAdapter.ViewHolderDatos)holder;
            datgolder.codigo.setText(listaPersonaje.get(position).getCodigo());
            datgolder.nombre.setText(listaPersonaje.get(position).getNombre());
            datgolder.id=(listaPersonaje.get(position).getId());



        }
    }

    @Override
    public int getItemCount() {
        return listaPersonaje.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView codigo,nombre,ape,telefono,cancursos;
        ImageView foto;
        Button btnprueba;
        String id;
        String telefonos,apellidos,codigos,correo;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            codigo=(TextView)itemView.findViewById(R.id.idcodigo11);
            nombre=(TextView)itemView.findViewById(R.id.idnombre11);
            btnprueba=(Button)itemView.findViewById(R.id.btbclick1);
        }
    }
}
