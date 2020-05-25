package com.example.tutorv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tutorv3.AdaptadoresAdmin.AdapterAlumnos2;
import com.example.tutorv3.ClasesAdmin.ClsAlumnos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaAlumnos extends AppCompatActivity {



    private FirebaseAuth mAuth;
    public FirebaseUser currentUser;
    private DatabaseReference reference;
    RecyclerView recycler;
    private EditText etbuscarnombre;
    Button btnmenaje;
    String idprofesor;
    ArrayList<ClsAlumnos> listaAlumnos;
    AdapterAlumnos2 adapter;
    TextView txtcantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alumnos);
        listaAlumnos=new ArrayList<>();
        recycler=findViewById(R.id.recylcermisalumnos);
        txtcantidad=(TextView)findViewById(R.id.cantidad);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    int contador=0;
    @Override
    protected void onStart() {
        super.onStart();
        reference = FirebaseDatabase.getInstance().getReference().child("Alumnos2");
        Query q=reference;
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaAlumnos.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ClsAlumnos artist = postSnapshot.getValue(ClsAlumnos.class);
                    listaAlumnos.add(artist);
                    contador++;
                }
                adapter = new AdapterAlumnos2(listaAlumnos);
                recycler.setAdapter(adapter);
                txtcantidad.setText(""+contador +" alumnos");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        contador=0;
    }
}
