package com.example.tutorv3.AdaptadoresAdmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorv3.Admin.AgregarAlumo;
import com.example.tutorv3.Admin.AgregarCurso;
import com.example.tutorv3.ClasesAdmin.Usuarios;
import com.example.tutorv3.R;

import java.util.ArrayList;

public class AdapterTutores extends RecyclerView.Adapter<AdapterTutores.ViewHolderDatos>  implements View.OnClickListener{

        ArrayList<Usuarios> listaPersonaje;

    private Spinner spinnerciclo;
    private Spinner spinnercursos;
    ArrayAdapter<String> adaperspinner;
    ArrayAdapter<String> adaperspinner2;
    //private ExampleDialogListener listener;

    ArrayList<String> Cursos;
    ArrayAdapter<String> adapter;

    private View.OnClickListener listener;

    public  void setOnClickListener(View.OnClickListener listener)
    {
        this.listener=listener;
    }
    public AdapterTutores(ArrayList<Usuarios> listaPersonaje) {
        this.listaPersonaje = listaPersonaje;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tutores,null,false);
        vista.setOnClickListener(this);
        return new ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        if (holder instanceof ViewHolderDatos){
            //  final Dato dato=
            final ViewHolderDatos datgolder =(ViewHolderDatos)holder;
            datgolder.codigo.setText(listaPersonaje.get(position).getCodigo());
            datgolder.nombre.setText(listaPersonaje.get(position).getNombre());
            datgolder.id=(listaPersonaje.get(position).getId());
            datgolder.telefono=listaPersonaje.get(position).getTelefono();
            datgolder.correo=listaPersonaje.get(position).getCorreo();
            //  datgolder.apellidos=listaPersonaje.get(position).getApellido();
            //datgolder.telefonos=listaPersonaje.get(position).getTelefono();

            // datgolder.foto.setImageResource(listaPersonaje.get(position).getFoto());

            datgolder.btnprueba.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //   Toast.makeText(null, "hola", Toast.LENGTH_SHORT).show();
                    //   Intent intent= new Intent(datgolder.btnprueba.getContext(), Login.class);
                    // datgolder.btnprueba.getContext().startActivity(intent);


                    final  Button b1 = new Button(datgolder.btnprueba.getContext());
                    b1.setText("boton1");
                    final  Button b2 = new Button(datgolder.btnprueba.getContext());
                    b2.setText("boton 2");

                    final CharSequence[] items = new CharSequence[3];
                    AlertDialog.Builder dialog= new AlertDialog.Builder(datgolder.btnprueba.getContext());
                    dialog.setCancelable(false);
                    items[0] = "Asignar Curso";
                    items[1] = "Asignar Alumnos";
                    items[2] = "Ver Cursos";
                    //dialog.setItems(items);
                    dialog.setTitle("Acciones").setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //   Toast.makeText( datgolder.btnprueba.getContext(),"Seleccionaste:" + items[which],Toast.LENGTH_SHORT)
                            //         .show();
                            if (which==0){
                                Intent intent= new Intent(datgolder.btnprueba.getContext(), AgregarCurso.class);
                                //  intent.putExtra("codigo",datgolder.codigo.getText());
                                //intent.putExtra("name",datgolder.nombre.getText());
                                //intent.putExtra("foto",datgolder.fo);
                                int img;
                                ImageView g;


                                Bundle bundle = new Bundle();
                                //img=listaPersonaje.get(position).getFoto();
                                bundle.putString("id",datgolder.id);
                                bundle.putString("code",datgolder.codigo.getText().toString());
                                bundle.putString("name",datgolder.nombre.getText().toString());
                                //  bundle.putInt("foto",img);
                                intent.putExtras(bundle);
                                datgolder.btnprueba.getContext().startActivity(intent);



                                //  datgolder.btnprueba.getContext().startActivity(intent);
                            }
                            if (which==1){
                                Intent intent= new Intent(datgolder.btnprueba.getContext(), AgregarAlumo.class);

                                Bundle bundle = new Bundle();
                                //img=listaPersonaje.get(position).getFoto();
                                bundle.putString("id1",datgolder.id);
                                bundle.putString("code1",datgolder.codigo.getText().toString());
                                bundle.putString("name1",datgolder.nombre.getText().toString());
                                bundle.putString("ce1",datgolder.telefono);
                                bundle.putString("co1",datgolder.correo);
                                //  bundle.putInt("foto",img);
                                intent.putExtras(bundle);
                                datgolder.btnprueba.getContext().startActivity(intent);
                            }
                            if (which==2){
                               // Intent intent= new Intent(datgolder.btnprueba.getContext(), VerCursos.class);
                                Bundle bundle=  new Bundle();
                                bundle.putString("name2",datgolder.nombre.getText().toString());
                                bundle.putString("ape2",datgolder.apellidos);
                                //intent.putExtras(bundle);
                                //datgolder.btnprueba.getContext().startActivity(intent);
                            }

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

            datgolder.botoncelular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+datgolder.telefono));
                    datgolder.botoncelular.getContext().startActivity(intent);


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
        TextView codigo,nombre,ape,cancursos;
        ImageView foto;
        Button btnprueba;
        String id;

        String telefono;


        String telefonos,apellidos,codigos,correo;
        ImageView botoncelular;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            codigo=(TextView)itemView.findViewById(R.id.idcodigo);
            nombre=(TextView)itemView.findViewById(R.id.idnombre);
            btnprueba=(Button)itemView.findViewById(R.id.btbclick);
            botoncelular=(ImageView)itemView.findViewById(R.id.idcelular);

        }
    }
}
