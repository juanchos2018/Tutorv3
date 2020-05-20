package com.example.tutorv3.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorv3.ClasesAdmin.Alu;
import com.example.tutorv3.ClasesAdmin.Alumnos;
import com.example.tutorv3.ClasesAdmin.Grupo;
import com.example.tutorv3.ClasesAdmin.Grupos;
import com.example.tutorv3.ClasesAdmin.Tutores;
import com.example.tutorv3.ClasesAdmin.Usuarios;
import com.example.tutorv3.ClasesAdmin.curso;
import com.example.tutorv3.ClasesAdmin.cursotutor;
import com.example.tutorv3.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Buscar extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText searchInput;
    private ImageView backButton;
    private TextView notFoundTV;

    DatabaseReference reference;
    DatabaseReference reference2;
    DatabaseReference reference3;
    DatabaseReference reference4;
    DatabaseReference reference5;
    private RecyclerView peoples_list;
    private DatabaseReference peoplesDatabaseReference;
    String dato,nombre,celular,correo;

    String idgrupo,nombregrrupo,cursgrupo;
    ArrayList<String> listaCursos2;
    ArrayList<cursotutor> listaCursos3;
    ArrayAdapter<String> adaptercursos2;
    Spinner spinercurso;
    int contador=0;
    String array1[]=new String[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        String idtutor;
        listaCursos2=new ArrayList<String>();
        listaCursos3=new ArrayList<>();
        toolbar = findViewById(R.id.search_appbar);
        setSupportActionBar(toolbar);
       final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater layoutInflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.appbar_search, null);
        actionBar.setCustomView(view);
        searchInput = findViewById(R.id.buscardor2);

        backButton = findViewById(R.id.backButton);

        dato=getIntent().getStringExtra("id3" );
        nombre=getIntent().getStringExtra("nom" );
        celular=getIntent().getStringExtra("ce2" );
        correo=getIntent().getStringExtra("co2" );


        idgrupo=AgregarCurso.idgruposs;
        nombregrrupo=AgregarCurso.nombregrupo;
        cursgrupo=AgregarCurso.cursogrupo;
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchPeopleProfile(searchInput.getText().toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        peoples_list = findViewById(R.id.SearchList1);
        peoples_list.setHasFixedSize(true);
        peoples_list.setLayoutManager(new LinearLayoutManager(this));
        String fff="dfds";

        peoplesDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Usuarios");

        peoplesDatabaseReference.keepSynced(true);
        //Toast.makeText(this, "no "+ nombre, Toast.LENGTH_SHORT).show();

    }

    private void searchPeopleProfile(final String searchString) {
        peoplesDatabaseReference.orderByChild("tipo").equalTo("Alumno");

         Query searchQuery = peoplesDatabaseReference.orderByChild("nombre")
                .startAt(searchString).endAt(searchString + "\uf8ff");
        //final Query searchQuery = peoplesDatabaseReference.orderByChild("search_name").equalTo(searchString);

        FirebaseRecyclerOptions<Usuarios> recyclerOptions = new FirebaseRecyclerOptions.Builder<Usuarios>()
                .setQuery(searchQuery, Usuarios.class)
                .build();

        FirebaseRecyclerAdapter<Usuarios, SearchPeopleVH> adapter = new FirebaseRecyclerAdapter<Usuarios, SearchPeopleVH>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull SearchPeopleVH holder, final int position, @NonNull final Usuarios model) {
                holder.name.setText(model.getNombre());
                holder.apellido=model.getApellido();
                holder.codigo.setText(model.getCodigo());
                holder.id=model.getId();
                holder.codigoalumno=model.getCodigo();
                holder.telefono=model.getTelefono();
                final String  codde =model.getCodigo();
            //    holder.id=model.getId();
             //   holder.status.setText(model.getApellido());

                /**on list >> clicking item, then, go to single user profile*/
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        listaCursos2.clear();
                     //   Toast.makeText(Buscar.this, model.getId().toString(), Toast.LENGTH_SHORT).show();

                        AlertDialog.Builder builder = new AlertDialog.Builder(Buscar.this);
                        View v1 = LayoutInflater.from(Buscar.this).inflate(R.layout.dialogo_curso2, null);


                        builder.setCancelable(true);
                        builder.setTitle("Asignar curso aalumno");
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                reference= FirebaseDatabase.getInstance().getReference("TutorAlumno").child(dato);;
                                String id  = reference.push().getKey();
                                Alu track = new Alu(id, model.getNombre(),model.getApellido(),model.getTelefono());
                                reference.child(id).setValue(track);

                                reference2=FirebaseDatabase.getInstance().getReference("AlumnoTutor").child(model.getId());
                                String id2=reference2.push().getKey();
                                Tutores trck2= new Tutores(dato,nombre,celular,correo);
                                reference2.child(id2).setValue(trck2);



                                reference4= FirebaseDatabase.getInstance().getReference("AlumnoGrupo").child(model.getId());

                                // ALUMNO GRUOPO
                                int  pos=spinercurso.getSelectedItemPosition();
                                String ides =array1[pos];
                                String namegrups=spinercurso.getSelectedItem().toString();
                                String idgru  = reference4.push().getKey();
                                Grupo grupo = new Grupo(ides,"Grupo de " +namegrups , namegrups);
                                reference4.child(idgru).setValue(grupo);

                                reference5= FirebaseDatabase.getInstance().getReference("Grupos").child(ides);
                                String idgrupos  = reference5.push().getKey();
                                Grupos grupos = new Grupos(idgrupos,model.getId(),codde,model.getNombre(),"Alumno");
                                reference5.child(idgrupos).setValue(grupos);
                               finish();

                            }
                        });
                        builder.setView(v1);
                        builder.show();
                        spinercurso=(Spinner)v1.findViewById(R.id.idsspinercursostutor);
                        reference3=FirebaseDatabase.getInstance().getReference("Tutorcurso").child(dato);
                        reference3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                                    String curo=postSnapshot.child("curso").getValue().toString();
                                   // listaCursos2.add(curo);

                                    cursotutor post = postSnapshot.getValue(cursotutor.class);
                                    cursotutor btIsdDetails = new cursotutor(post.getId(), post.getCurso());
                                    listaCursos3.add(btIsdDetails);
                                    array1[contador] = post.getId();
                                    contador++;
                                    listaCursos2.add(post.getCurso());

                                    Log.e("mensje",post.getId() + post.getCurso());


                                }
                                adaptercursos2= new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_spinner_item,listaCursos2);

                                spinercurso.setAdapter(adaptercursos2);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });




                     /*  String visit_user_id = getRef(position).getKey();
                        Intent intent = new Intent(Buscar.this, Perfil.class);
                        intent.putExtra("visitUserId", visit_user_id);
                        startActivity(intent);

                      */


                    }
                });
            }

            @NonNull
            @Override
            public SearchPeopleVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_buscador, viewGroup, false);
                return new SearchPeopleVH(view);
            }
        };
        peoples_list.setAdapter(adapter);
        adapter.startListening();
    }


    public static class SearchPeopleVH extends RecyclerView.ViewHolder{
        TextView name, codigo;
        CircleImageView profile_pic;
        ImageView verified_icon;
        String id,apellido,codigoalumno,telefono;
        public SearchPeopleVH(View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.idcodigo11);
            name = itemView.findViewById(R.id.idnombre11);

        }
    }
}
