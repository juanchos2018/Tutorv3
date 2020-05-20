package com.example.tutorv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorv3.AdaptadoresAdmin.AdapterSalones2;
import com.example.tutorv3.ClasesAdmin.ClsProfeAlumno;
import com.example.tutorv3.ClasesAdmin.ClsProfesores;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Alumnos extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public FirebaseUser currentUser;
    private DatabaseReference reference,reference2;
    private RecyclerView recycler;
    String idprofe,nombresalon,sede,correo;


    android.app.AlertDialog.Builder builder1;
    AlertDialog alert;
    ArrayList<ClsProfesores> listaSalones;
    AdapterSalones2 adapteraa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        idprofe=getIntent().getStringExtra("p1");
        nombresalon=getIntent().getStringExtra("sa");
        sede=getIntent().getStringExtra("se");
        correo=getIntent().getStringExtra("co");


        recycler=findViewById(R.id.recylcermisalumnos3);
        recycler.setLayoutManager(new LinearLayoutManager(Alumnos.this));
        final String  idprofesor= mAuth.getCurrentUser().getUid();
        reference= FirebaseDatabase.getInstance().getReference("ProfesorAlumno").child(idprofe);
    }

    private void mensaje(){
        builder1 = new AlertDialog.Builder(Alumnos.this);
        final Button btcerrrar,btnsi;
         final RecyclerView recycler2;
        listaSalones = new ArrayList<>();
        final TextView tvslon,tvviene;
        final View v = LayoutInflater.from(Alumnos.this).inflate(R.layout.dialogo_check2, null);
        builder1.setTitle("Cambiar de Salon");
        builder1.setView(v);
        btcerrrar=(Button)v.findViewById(R.id.btnno);
        tvslon=(TextView)v.findViewById(R.id.txtsalon);
        tvviene=(TextView)v.findViewById(R.id.idde);
        tvviene.setText(nombresalon);

        recycler2=v.findViewById(R.id.reccylersalones);
        recycler2.setLayoutManager(new LinearLayoutManager(Alumnos.this));

        reference2 = FirebaseDatabase.getInstance().getReference().child("Profesores");
        Query q=reference2.orderByChild("tipo").equalTo("profesor");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaSalones.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ClsProfesores artist = postSnapshot.getValue(ClsProfesores.class);
                    listaSalones.add(artist);
                }

                adapteraa = new AdapterSalones2(listaSalones);
                recycler2.setAdapter(adapteraa);
                adapteraa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvslon.setText(listaSalones.get(recycler.getChildAdapterPosition(v)).getNombresalon());

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


                btnsi=(Button)v.findViewById(R.id.btnsi);
        btcerrrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        btnsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Alumnos.this, "Modificado", Toast.LENGTH_SHORT).show();
            }
        });

        alert  = builder1.create();
        alert.show();
    }
    private void mensaje2(String nombres, String apellidos, String edad, String tokentareas, String correo1, String correo2, String telefono, String nombreapoderado1, String nombreapoderado2, String rutafoto, String keyalu, String tipo) {

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ClsProfeAlumno> recyclerOptions = new FirebaseRecyclerOptions.Builder<ClsProfeAlumno>()
                .setQuery(reference, ClsProfeAlumno.class)
                .build();
        FirebaseRecyclerAdapter<ClsProfeAlumno, ChatsVH> adapter2 = new FirebaseRecyclerAdapter<ClsProfeAlumno, ChatsVH>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull final ChatsVH items, int position, @NonNull ClsProfeAlumno model) {

                final String userID = getRef(position).getKey();

                reference.child(userID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            final String nombres=dataSnapshot.child("nombrealumno").getValue().toString();
                            final String apellidos=dataSnapshot.child("apellidoalumno").getValue().toString();
                            final String edad=dataSnapshot.child("edad").getValue().toString();
                            final String tokentareas=dataSnapshot.child("tokentareas").getValue().toString();
                            final String correo1=dataSnapshot.child("correoapoderado1").getValue().toString();
                            final String correo2=dataSnapshot.child("correoapoderado2").getValue().toString();
                            final String telefono=dataSnapshot.child("telefono").getValue().toString();
                            final String nombreapoderado1=dataSnapshot.child("nombreapoderado1").getValue().toString();
                            final String nombreapoderado2=dataSnapshot.child("nombreapoderado2").getValue().toString();
                            final String rutafoto=dataSnapshot.child("rutafoto").getValue().toString();
                            final String keyalu=dataSnapshot.child("id").getValue().toString();
                            final String tipo=dataSnapshot.child("tipo").getValue().toString();
                            //tipo
                            items.txtnombres.setText(nombres);
                            items.txtapellidos.setText(apellidos);
                            items.txtedad.setText("Edad :"+edad);
                            items.keylaumno=keyalu;
                            items.ap1=nombreapoderado1;
                            items.ap2=nombreapoderado2;
                            items.telefono=telefono;
                            items.rutafoto=rutafoto;
                            items.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                      mensaje();
                                      mensaje2(nombres,apellidos,edad,tokentareas,correo1,correo2,telefono,nombreapoderado1,nombreapoderado2,rutafoto,keyalu,tipo);
                                }
                            });




                        }
                        else{
                            Toast.makeText(Alumnos.this, "no hay datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public ChatsVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_alumnos, viewGroup, false);
                return new ChatsVH(view);
            }
        };

        recycler.setAdapter(adapter2);
        adapter2.startListening();
    }



    public   class ChatsVH extends RecyclerView.ViewHolder{
        TextView txtnombres,txtcorreo,txtapellidos,txtedad;
        ImageView imgfoto,imgceulalar;
        String keylaumno,ap1,ap2,telefono,rutafoto;

        public ChatsVH(final View itemView) {
            super(itemView);
            txtnombres=(TextView)itemView.findViewById(R.id.idnmbresitems);
            txtapellidos=(TextView)itemView.findViewById(R.id.idapellidositems);
            txtedad=(TextView)itemView.findViewById(R.id.idedaditems);
            imgfoto=(ImageView)itemView.findViewById(R.id.imageView);


        }
    }

}

