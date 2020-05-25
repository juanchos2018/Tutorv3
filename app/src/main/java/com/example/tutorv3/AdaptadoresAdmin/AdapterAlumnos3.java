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
import java.util.Collections;
import java.util.List;

public class AdapterAlumnos3 extends RecyclerView.Adapter<AdapterAlumnos3.ViewHolderDatos>  implements View.OnClickListener  {

    ArrayList<ClsAlumnos>listaAlumnos1;
   // ArrayList<ClsAlumnos> listaAlumnos2;
   //public static ArrayList<ClsAlumnos> listaAlumnos3;

    public AdapterAlumnos3(ArrayList<ClsAlumnos> listaAlumnos1) {
        this.listaAlumnos1 = listaAlumnos1;

    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_alumnos2    ,parent,false);
        vista.setOnClickListener(this);
        return new ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        if (holder instanceof ViewHolderDatos) {

            final ViewHolderDatos datgolder = (ViewHolderDatos) holder;
            datgolder.nombrealumno.setText(listaAlumnos1.get(position).getNombrealumno());
            datgolder.apellidoalumno.setText(listaAlumnos1.get(position).getApellidoalumno());
            datgolder.correo.setText(listaAlumnos1.get(position).getCorreoapoderado1());

        }
    }

    @Override
    public int getItemCount() {
        return listaAlumnos1.size();
    }

    @Override
    public void onClick(View v) {

    }




    public  boolean equalLists(List<ClsAlumnos> one, List<ClsAlumnos> two){
        if (one == null && two == null){
            return true;
        }

        if((one == null && two != null)
                || one != null && two == null
                || one.size() != two.size()){
            return false;
        }


        one = new ArrayList<>(one);
        two = new ArrayList<>(two);

      //  Collections.sort(one);
        //Collections.sort(two);

        return one.equals(two);
    }
    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView nombrealumno,apellidoalumno,correo,verificado,estado;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombrealumno=(TextView)itemView.findViewById(R.id.txtnombrealumno1);
            apellidoalumno=(TextView)itemView.findViewById(R.id.txtapellidoalumno1);
            correo=(TextView)itemView.findViewById(R.id.txtcorreoalumno1);
        }
    }
}
