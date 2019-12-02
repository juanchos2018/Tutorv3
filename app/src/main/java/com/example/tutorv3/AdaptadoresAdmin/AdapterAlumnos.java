package com.example.tutorv3.AdaptadoresAdmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorv3.ClasesAdmin.Alumnos;
import com.example.tutorv3.ClasesAdmin.Usuarios;
import com.example.tutorv3.R;

import java.util.ArrayList;

public class AdapterAlumnos   extends RecyclerView.Adapter<AdapterAlumnos.ViewHolderDatos>  implements View.OnClickListener{

    ArrayList<Alumnos> listaPersonaje;
    private View.OnClickListener listener;
    public AdapterAlumnos(ArrayList<Alumnos> listaPersonaje) {
        this.listaPersonaje = listaPersonaje;
    }


    public  void setOnClickListener(View.OnClickListener listener)
    {
        this.listener=listener;
    }


    @NonNull
    @Override
    public AdapterAlumnos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno,null,false);
       vista.setOnClickListener(this);
        return new ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAlumnos.ViewHolderDatos holder, int position) {

        if (holder instanceof ViewHolderDatos){
            //  final Dato dato=
            final ViewHolderDatos datgolder =(ViewHolderDatos)holder;
            datgolder.codigo.setText(listaPersonaje.get(position).getCodigo());
            datgolder.nombre.setText(listaPersonaje.get(position).getNombre());
            datgolder.id=(listaPersonaje.get(position).getId());
            datgolder.telefonos=listaPersonaje.get(position).getTelefono();

            datgolder.btnprueba.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final  Button b1 = new Button(datgolder.btnprueba.getContext());
                    b1.setText("boton1");
                    final  Button b2 = new Button(datgolder.btnprueba.getContext());
                    b2.setText("boton 2");

                    final CharSequence[] items = new CharSequence[3];
                    AlertDialog.Builder dialog= new AlertDialog.Builder(datgolder.btnprueba.getContext());
                    dialog.setCancelable(false);
                    items[0] = "Ver Tutor";
                    items[1] = "Otro";

                    //dialog.setItems(items);
                    dialog.setTitle("Acciones").setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                   /* dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {

                        }
                    });*/
                    dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {

                        }
                    });
                    dialog.show();
                }
            });

            holder.imgtelefono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+datgolder.telefonos));
                    datgolder.imgtelefono.getContext().startActivity(intent);

                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return listaPersonaje.size();
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView codigo,nombre,ape,telefono,cancursos;
        ImageView foto;
        Button btnprueba;
        String id;

        ImageView imgtelefono;
        String telefonos,apellidos,codigos,correo;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            codigo=(TextView)itemView.findViewById(R.id.idcodigo1);
            nombre=(TextView)itemView.findViewById(R.id.idnombre1);
            btnprueba=(Button)itemView.findViewById(R.id.btbclick1);
            imgtelefono=(ImageView)itemView.findViewById(R.id.idcelular2);
        }
    }
}
