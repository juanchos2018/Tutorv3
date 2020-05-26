package com.example.tutorv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tutorv3.ClasesAdmin.ClsArchivos;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class ListaArchivos extends AppCompatActivity {

    String idprofe;
    String tiposuario;

    private DatabaseReference referencemisclases2;

    RecyclerView recycler;

    android.app.AlertDialog.Builder builder1;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_archivos);
        recycler=findViewById(R.id.idrecylermisclases2);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        referencemisclases2= FirebaseDatabase.getInstance().getReference("Archivos");

    }

    private void descargaar(final String ruta,String nombre){
        builder1 = new AlertDialog.Builder(ListaArchivos.this);
        builder1.setTitle("Descargar Tarea");
        Button btcerrrar;

        TextView tvestado;
        View v = LayoutInflater.from(ListaArchivos.this).inflate(R.layout.dialogo_descarga, null);

        builder1.setView(v);
        btcerrrar=(Button)v.findViewById(R.id.idbtncerrardialogo);
        tvestado=(TextView)v.findViewById(R.id.nombrear);
        tvestado.setText(nombre);

        btcerrrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(ruta);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        alert  = builder1.create();


        alert.show();
    }
    private void dowiload(){

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<ClsArchivos> recyclerOptions = new FirebaseRecyclerOptions.Builder<ClsArchivos>()
                .setQuery(referencemisclases2, ClsArchivos.class).build();
        FirebaseRecyclerAdapter<ClsArchivos,Items> adapter =new FirebaseRecyclerAdapter<ClsArchivos, Items>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull final Items items, final int i, @NonNull ClsArchivos claseesprofe) {
                final String key = getRef(i).getKey();
                referencemisclases2.child(key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            final String nombreclase=dataSnapshot.child("nombreclase").getValue().toString();
                            final String ruta=dataSnapshot.child("ruta").getValue().toString();
                            final String nombrear=dataSnapshot.child("nombrearchivo").getValue().toString();

                            items.tvnombreclase.setText(nombreclase);
                            items.tvfechaclase.setText(nombrear);
                         items.itemView.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View v) {
                                 descargaar(ruta,nombrear);
                             }
                         });


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public Items onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_archivos, parent, false);
                return new Items(vista);

            }
        };
        recycler.setAdapter(adapter);
        adapter.startListening();
    }
    public class Items extends RecyclerView.ViewHolder {
        TextView tvnombreclase, tvfechaclase;
        String ruta;


        public Items(final View itemView) {
            super(itemView);
            tvnombreclase=(TextView)itemView.findViewById(R.id.nombrearchivo);
            tvfechaclase=(TextView)itemView.findViewById(R.id.nombrear);


        }
    }
}
