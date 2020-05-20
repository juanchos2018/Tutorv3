package com.example.tutorv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import xyz.hasnat.sweettoast.SweetToast;

public class RegistroProfesores extends AppCompatActivity {


    EditText etnombre,etapellido,etnumero,etcorreo,ettelefono,etnombresalon,etedadsalon;
    Button btnregistrrar;


    private ProgressDialog progressDialog;
    private DatabaseReference reference;
    private DatabaseReference reference2;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private Spinner spinnercursos,spineersede;


    ArrayAdapter<String> adaperspinner;
    public static ArrayList<String> tipoprofe;

    ArrayAdapter<String> adaperspinner2;
    public static ArrayList<String> tiposede;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_profesores);


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        spinnercursos=findViewById(R.id.idtipoprofsor);
        spineersede=findViewById(R.id.idtisede);

        etcorreo=(EditText)findViewById(R.id.txtcorreoprofesor);
        etnombre=(EditText)findViewById(R.id.txtnombreprofesor);
        etapellido=(EditText)findViewById(R.id.txtapellidoprofesor);
        etnumero=(EditText)findViewById(R.id.txtnumeroporfesor);
        etnombresalon=(EditText)findViewById(R.id.txtnombresalon);
        etedadsalon=(EditText)findViewById(R.id.txtedadsalon);
        tipoprofe=new ArrayList<String>();
        tipoprofe.add("ingles");
        tipoprofe.add("profesor");
        tipoprofe.add("psicomotricidad");
        tipoprofe.add("secretaria");

        tipoprofe.add("psicopedagogia");
        tipoprofe.add("musica");
        tipoprofe.add("cuentos");
        tipoprofe.add("sugerencias");
        tiposede=new ArrayList<String>();
        tiposede.add("Millan");
        tiposede.add("Olivar");

        adaperspinner= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,tipoprofe);
        spinnercursos.setAdapter(adaperspinner);


        adaperspinner2= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,tiposede);
        spineersede.setAdapter(adaperspinner2);

        btnregistrrar=(Button)findViewById(R.id.butonregistrar);

        btnregistrrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre=etnombre.getText().toString();
                String apellido=etapellido.getText().toString();
                String correo=etcorreo.getText().toString();
                String clave="crayolas2020";
                String telefono=etnumero.getText().toString();
                String tipoprofesor=spinnercursos.getSelectedItem().toString();
                String tiposede=spineersede.getSelectedItem().toString();
                String nombresalon=etnombresalon.getText().toString();
                String edadsalon=etedadsalon.getText().toString();
                registrarprofe(nombre,apellido,correo,clave,telefono,tipoprofesor,nombresalon,tiposede,edadsalon);

            }
        });
    }

    private void registrarprofe(final String  nombre, final String apellido, final String correo, String clave, final String telefono, final String tipoprofesor, final String nombresalon, final String sede, final String edadsalon) {

        progressDialog = new ProgressDialog(this);

        if (TextUtils.isEmpty(telefono)){
            SweetToast.error(getBaseContext(), "Telefono Requerido.");
        } else if (TextUtils.isEmpty(correo)){
            SweetToast.error(getBaseContext(), "Correo Requerido");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            SweetToast.error(getBaseContext(), "Correo no Valido.");

        }
        else if (TextUtils.isEmpty(clave)){
            SweetToast.error(getBaseContext(), "No hay clave");
        }
        else  if (TextUtils.isEmpty(tipoprofesor)){
            Toast.makeText(this, "falta tipo", Toast.LENGTH_SHORT).show();
        }
        else  if (TextUtils.isEmpty(sede)){
            Toast.makeText(this, "falta sede", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(edadsalon)){
            Toast.makeText(this, "falta edad", Toast.LENGTH_SHORT).show();
        }
        else {
            //NOw ready to create a user a/c
            mAuth.createUserWithEmailAndPassword(correo, clave)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                String deviceToken = FirebaseInstanceId.getInstance().getToken();

                                String current_userID =  mAuth.getCurrentUser().getUid();
                                reference = FirebaseDatabase.getInstance().getReference().child("Profesores").child(current_userID);
                                // String id = reference.push().getKey();
                                reference.child("idprofe").setValue(current_userID);
                                reference.child("nombre").setValue(nombre);
                                reference.child("apellido").setValue(apellido);
                                reference.child("cant_alu").setValue("0");
                                reference.child("tipo").setValue(tipoprofesor);
                                reference.child("nombresalon").setValue(nombresalon);
                                reference.child("verified").setValue("false");
                                reference.child("telefono").setValue(telefono);
                                reference.child("sede").setValue(sede);
                                reference.child("correo").setValue(correo);
                                reference.child("created_at").setValue(ServerValue.TIMESTAMP);
                                reference.child("user_status").setValue("Hi, I'm new uMe user");
                                reference.child("edadsalon").setValue(edadsalon);
                                reference.child("user_image").setValue("default_image"); // Original image
                                reference.child("device_token").setValue(deviceToken)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    // SENDING VERIFICATION EMAIL TO THE REGISTERED USER'S EMAIL
                                                    user = mAuth.getCurrentUser();
                                                    if (user != null){
                                                        user.sendEmailVerification()
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()){
                                                                            Toast.makeText(RegistroProfesores.this, "Registrado profesor", Toast.LENGTH_SHORT).show();

                                                                        } else {
                                                                            mAuth.signOut();
                                                                        }
                                                                    }
                                                                });
                                                    }

                                                }
                                            }
                                        });

                            } else {
                                String message = task.getException().getMessage();
                                SweetToast.error(getApplicationContext(), "Error occurred : " + message);
                            }

                            progressDialog.dismiss();

                        }
                    });


            //config progressbar
            progressDialog.setTitle("Creating new account");
            progressDialog.setMessage("Please wait a moment....");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
        }
    }
}
