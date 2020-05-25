package com.example.tutorv3.AdaptadoresAdmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorv3.ClasesAdmin.ClsAlumnos;
import com.example.tutorv3.R;

import java.util.ArrayList;

public class AdapterAlumnos2   extends RecyclerView.Adapter<AdapterAlumnos2.ViewHolderDatos>  implements View.OnClickListener {

ArrayList<ClsAlumnos>listaAlumnos;

    public AdapterAlumnos2(ArrayList<ClsAlumnos> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_alumnos    ,parent,false);
        vista.setOnClickListener(this);
        return new ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        if (holder instanceof ViewHolderDatos) {

            final ViewHolderDatos datgolder = (ViewHolderDatos) holder;
            datgolder.nombrealumno.setText(listaAlumnos.get(position).getNombrealumno());
            datgolder.apellidoalumno.setText(listaAlumnos.get(position).getApellidoalumno());
            datgolder.verificado.setText(listaAlumnos.get(position).getVerified());
            datgolder.estado.setText(listaAlumnos.get(position).getActive_now());
            datgolder.correo.setText(listaAlumnos.get(position).getCorreoapoderado1());


        }

    }

    public  void filtrar(ArrayList<ClsAlumnos> filtro){
        this.listaAlumnos=filtro;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView nombrealumno,apellidoalumno,correo,verificado,estado;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            nombrealumno=(TextView)itemView.findViewById(R.id.txtnombrealumno);
            apellidoalumno=(TextView)itemView.findViewById(R.id.txtapellidoalumno);
            correo=(TextView)itemView.findViewById(R.id.txtcorreoalumno);
            verificado=(TextView)itemView.findViewById(R.id.idverificado);
            estado=(TextView)itemView.findViewById(R.id.idactivo);
            correo=(TextView)itemView.findViewById(R.id.txtcorreoalumno);


        }
    }
}
