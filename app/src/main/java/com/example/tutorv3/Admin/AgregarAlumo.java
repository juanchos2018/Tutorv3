package com.example.tutorv3.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorv3.AdaptadoresAdmin.AdapterAlumnos;
import com.example.tutorv3.AdaptadoresAdmin.OtroAdapter;
import com.example.tutorv3.ClasesAdmin.Alumnos;
import com.example.tutorv3.ClasesAdmin.Lista2;
import com.example.tutorv3.ClasesAdmin.Listas;
import com.example.tutorv3.ClasesAdmin.cursotutor;
import com.example.tutorv3.FragmentoAdmin.AlumnosFragment;
import com.example.tutorv3.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AgregarAlumo extends AppCompatActivity {


    private String mParam1;
    private String mParam2;
    ArrayList<Alumnos> listaPersonaje;
    OtroAdapter adapter;
    RecyclerView recycler;
    private List<Alumnos> lisalumnos;
    TextView txt1,txt2;
    ListView lisview;

    private DatabaseReference reference;
    private AlumnosFragment.OnFragmentInteractionListener mListener;
    String id;
    String dato2,dato3,dato4,dato5;
    ImageView imgbuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_alumo);

        reference = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        listaPersonaje = new ArrayList<>();

        txt1=(TextView)findViewById(R.id.idnombretutor2);
        txt2=(TextView)findViewById(R.id.codigotutor2);


         id=getIntent().getStringExtra("id1" );
        String dato=getIntent().getStringExtra("code1" );
         dato2=getIntent().getStringExtra("name1" );
         dato4=getIntent().getStringExtra("ce1" );
        dato5=getIntent().getStringExtra("co1" );
         // bundle.putString("ce1",datgolder.telefono);
        //  bundle.putString("co1",datgolder.correo);

        txt1.setText(dato2);
        txt2.setText(dato);

        lisview=(ListView)findViewById(R.id.idlisviewlista2);
        lisalumnos= new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference("TutorAlumno").child(id);

        recycler = (RecyclerView)findViewById(R.id.SearchList);
        imgbuscar=(ImageView)findViewById(R.id.imbuscar);
        imgbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AgregarAlumo.this,Buscar.class);
               Bundle bundle1= new Bundle();
               bundle1.putString("id3",id);
                bundle1.putString("nom",dato2);
                bundle1.putString("ce2",dato4);
                bundle1.putString("co2",dato5);
                //bundle1.putString("cel",);


               intent.putExtras(bundle1);
              //  Toast.makeText(AgregarAlumo.this, "n" +dato2, Toast.LENGTH_SHORT).show();
                startActivity(intent); // abreotro actividdad para buscar

               // mostrarDialogOpcione2();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lisalumnos.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Alumnos track = postSnapshot.getValue(Alumnos.class);
                  lisalumnos.add(track);
                    Log.e("nombres "," "+track);
                }
                //   TrackList trackListAdapter = new TrackList(ArtistsActivity.this, tracks);
                // listViewTracks.setAdapter(trackListAdapter);
                //  liscursos.add(curso);

               Lista2 trackListAdapter = new Lista2(AgregarAlumo.this, lisalumnos);
                lisview.setAdapter(trackListAdapter);
                // adaperliscursos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    EditText searchInput;

    public void mostrarDialogOpcione2(){

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Alumns");

        LayoutInflater inflater =this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_buscar, null);

        searchInput = view.findViewById(R.id.serachInput1);

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

        recycler = (RecyclerView)view.findViewById(R.id.SearchList);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        dialogo1.setView(view)
                .setTitle("Agregar Alunmo")


                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


       /* reference = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        Query q=reference.orderByChild("tipo").equalTo("Alumno");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listaPersonaje.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Alumnos artist = postSnapshot.getValue(Alumnos.class);
                    listaPersonaje.add(artist);
                }

                adapter = new OtroAdapter(listaPersonaje);
                recycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
        dialogo1.show();
    }

    String re ="(RecyclerView)view.findViewById(R.id.SearchList)";

    private void searchPeopleProfile(final String searchString) {
        final Query searchQuery = reference.orderByChild("nombre")
                .startAt(searchString).endAt(searchString + "\uf8ff");
        //final Query searchQuery = peoplesDatabaseReference.orderByChild("search_name").equalTo(searchString);


        FirebaseRecyclerOptions<Alumnos> recyclerOptions = new FirebaseRecyclerOptions.Builder<Alumnos>()
                .setQuery(searchQuery, Alumnos.class)
                .build();

        FirebaseRecyclerAdapter<Alumnos, SearchPeopleVH> adapter = new FirebaseRecyclerAdapter<Alumnos, SearchPeopleVH>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull SearchPeopleVH holder, final int position, @NonNull Alumnos model) {
                holder.name.setText(model.getNombre());
                holder.status.setText(model.getApellido());


            }

            @NonNull
            @Override
            public SearchPeopleVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                Toast.makeText(AgregarAlumo.this, "es nulo esto", Toast.LENGTH_SHORT).show();
               // View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_single_profile_display, viewGroup, false);
                return null;
            }
        };
        recycler.setAdapter(adapter);
        adapter.startListening();
    }
    public static class SearchPeopleVH extends RecyclerView.ViewHolder{
        TextView name, status;
        CircleImageView profile_pic;
        ImageView verified_icon;
        public SearchPeopleVH(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.idnombre11);

        }
    }
}
