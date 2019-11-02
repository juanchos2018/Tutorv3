package com.example.tutorv3.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutorv3.ClasesAdmin.Admin;
import com.example.tutorv3.R;
import com.google.android.gms.common.internal.Objects;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {


    DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    EditText txtusu,txtclave;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog =new ProgressDialog(this);

        txtusu=(EditText)findViewById(R.id.idcodigo);
        txtclave=(EditText)findViewById(R.id.idclave);
        btn=(Button)findViewById(R.id.loginId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ingresar();
            }
        });
    }

    private void  ingresar(){
        progressDialog.show();
        reference= FirebaseDatabase.getInstance().getReference("Admin");
        Query q=reference.orderByChild("codigo").equalTo(txtusu.getText().toString());
        progressDialog.setMessage("Cargando");
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot movieSnapshot : dataSnapshot.getChildren()) {
                    Admin movie = movieSnapshot.getValue(Admin.class);
                    Toast.makeText(Login.this, "Hola 2", Toast.LENGTH_SHORT).show();

                    progressDialog.setMessage("Cargando");
                    if (movie.getClave().equals(txtclave.getText().toString())) {

                        String nombre =movie.getNombre();
                        String codigo=movie.getCodigo();


                        Intent intent= new Intent(Login.this, Gestion.class);
                        Bundle bundle= new Bundle();

                        bundle.putString("codigo",codigo);
                        bundle.putString("nombre",nombre);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        startActivity(intent);
                        progressDialog.dismiss();

                    }
                    else{
                        Toast.makeText(Login.this,"no exstr wey ", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
