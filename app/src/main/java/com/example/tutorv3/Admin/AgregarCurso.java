package com.example.tutorv3.Admin;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.tutorv3.ClasesAdmin.Grupo;
import com.example.tutorv3.ClasesAdmin.Grupos;
import com.example.tutorv3.ClasesAdmin.Listas;
import com.example.tutorv3.ClasesAdmin.cursotutor;
import com.example.tutorv3.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AgregarCurso extends AppCompatActivity {

    private Spinner spinnerciclo;
    private Spinner spinnercursos;
    ArrayAdapter<String> adaperspinner;
    ArrayAdapter<String> adaperspinner2;
    TextView txtcode,txtname,txtid;
    ListView lisview;
    ImageView img;

    private List<cursotutor> liscursos;
    DatabaseReference reference;
    DatabaseReference reference2;
    DatabaseReference reference3;
    DatabaseReference reference4;
    DatabaseReference reference5;
    private FirebaseAuth firebaseAuth;
    public static ArrayList<String> Cursos;
    public  static String idgruposs;
    public  static String nombregrupo;
    public  static String cursogrupo;
    private ArrayAdapter adaperliscursos;
    private static final String[] ciclos = new String []{"1er Ciclo","2do Ciclo","3er Ciclo","4 Ciclo"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_curso);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtname=(TextView)findViewById(R.id.idnombrestutor);
        //img=(ImageView)findViewById(R.id.idfototutor);


        txtcode=(TextView)findViewById(R.id.codigotutor);
        String id=getIntent().getStringExtra("id" );
        String dato=getIntent().getStringExtra("code" );
        String dato2=getIntent().getStringExtra("name" );
  txtid=(TextView)findViewById(R.id.idntutor);
  txtid.setText(id);
        txtcode.setText(dato);
        txtname.setText(dato2);

        lisview=(ListView)findViewById(R.id.idlisviewlista1);
        liscursos= new ArrayList<>();

        reference= FirebaseDatabase.getInstance().getReference("Tutorcurso").child(id);
        reference2= FirebaseDatabase.getInstance().getReference("TutorGrupo").child(id);
        reference3= FirebaseDatabase.getInstance().getReference("Grupos");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogOpcione2();
            }
        });
    }

    public void mostrarDialogOpcione2(){

        Cursos=new ArrayList<String>();
        Cursos.add("Matematica 1");
        Cursos.add("Matematica 2");
        Cursos.add("Fisica");
        Cursos.add("Programacion 1");
        Cursos.add("Programacion 2");
        Cursos.add("Programacion 3");

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");

        adaperspinner2= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,ciclos);
        adaperspinner= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Cursos);

        LayoutInflater inflater =this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_cursos, null);

        dialogo1.setView(view)
                .setTitle("Agregar Curso")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String curso = spinnercursos.getSelectedItem().toString();
                        // Toast.makeText(AgregarCurso.this, "Hola"+curso, Toast.LENGTH_SHORT).show();
                        cursotutor o = new cursotutor();
                        o.setCodigotutor(txtcode.getText().toString());
                        o.setCurso(spinnercursos.getSelectedItem().toString());

                        //   String trackName = editTextTrackName.getText().toString().trim();
                        // int rating = seekBarRating.getProgress();
                        if (!TextUtils.isEmpty(txtcode.getText())) {


                            // TUTOR GRUPO
                            String idgrupo  = reference2.push().getKey();
                            Grupo grupo = new Grupo(idgrupo,"Grupo de " +spinnercursos.getSelectedItem().toString() , spinnercursos.getSelectedItem().toString());
                            reference2.child(idgrupo).setValue(grupo);

                            //GRUPOSS
                            reference3= FirebaseDatabase.getInstance().getReference("Grupos").child(idgrupo);
                            String idgrupos  = reference3.push().getKey();
                            Grupos grupos = new Grupos(idgrupos,txtid.getText().toString(), txtcode.getText().toString(),txtname.getText().toString(),"Tutor");
                            reference3.child(idgrupos).setValue(grupos);


                            // TUTOR CURSO
                            String id  = reference.push().getKey();
                            cursotutor track = new cursotutor(idgrupo, spinnercursos.getSelectedItem().toString());
                            reference.child(id).setValue(track);

                            // CHTA GRUPAL 2
                            reference4= FirebaseDatabase.getInstance().getReference("ChatGrupal2");
                            Map<String,Object> map = new HashMap<>();
                            map.put(idgrupo, "");
                            reference4.updateChildren(map);

                        // CREANDO GRUPO  PARA QUE LA INGENIERA PUEDA VER
                            reference5 = FirebaseDatabase.getInstance().getReference().child("Grupo").child(idgrupo);
                            reference5.child("id").setValue(idgrupo);
                            reference5.child("nombre").setValue("Grupo de " +spinnercursos.getSelectedItem().toString());
                            reference5.child("curso").setValue(spinnercursos.getSelectedItem().toString());



                            Toast.makeText(getBaseContext(), "Registrado", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getBaseContext(), "Please enter track name", Toast.LENGTH_LONG).show();
                        }

                    }
                });


        spinnerciclo=view.findViewById(R.id.idcicloo);
        spinnercursos=view.findViewById(R.id.idcursos);

        spinnerciclo.setAdapter(adaperspinner2);
        spinnercursos.setAdapter(adaperspinner);

        dialogo1.show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                liscursos.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    cursotutor track = postSnapshot.getValue(cursotutor.class);
                    liscursos.add(track);
                    Log.e("nombres "," "+track);
                }
                //   TrackList trackListAdapter = new TrackList(ArtistsActivity.this, tracks);
                // listViewTracks.setAdapter(trackListAdapter);
                //  liscursos.add(curso);

                Listas trackListAdapter = new Listas(AgregarCurso.this, liscursos);
                lisview.setAdapter(trackListAdapter);
                // adaperliscursos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
