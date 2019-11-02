package com.example.tutorv3.FragmentoAdmin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tutorv3.AdaptadoresAdmin.AdapterTutores;
import com.example.tutorv3.ClasesAdmin.Usuarios;
import com.example.tutorv3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import xyz.hasnat.sweettoast.SweetToast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistroFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnabrir;
    EditText e1,e2,e3,e4,e5,ecorreo;
    EditText ee1,ee2,ee3,ee4,eciclo,eecorreo;
    RadioButton r1,r2;
    Spinner spinerciclo,spinerciclo1;
    ArrayList<String> Ciclos;
    ArrayAdapter<String> adapterciclos;
    ImageView botoncelular;

    private String selectipo = "";
    private ProgressDialog progressDialog;
    private DatabaseReference reference;
    private DatabaseReference reference2;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private OnFragmentInteractionListener mListener;

    ArrayList<Usuarios> listaPersonaje;
    AdapterTutores adapter;
    RecyclerView recycler;

    //private Context myContext = RegistroFragment.this;

    public RegistroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroFragment newInstance(String param1, String param2) {
        RegistroFragment fragment = new RegistroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View vista=  inflater.inflate(R.layout.fragment_registro, container, false);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(getContext());
        Ciclos=new ArrayList<String>();
        Ciclos.add("Alumno");
        Ciclos.add("Tutor");

        listaPersonaje = new ArrayList<>();
        recycler = (RecyclerView) vista.findViewById(R.id.recyclerId1);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapterciclos= new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,Ciclos);

        btnabrir=(Button)vista.findViewById(R.id.idagregarverdad);
        btnabrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view2 = inflater.inflate(R.layout.dialogo_agregar, null);
                builder.setView(view2)

                        .setPositiveButton("REGISTRAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                 Registrar(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),ecorreo.getText().toString(),e1.getText().toString(),spinerciclo.getSelectedItem().toString());

                            }
                        });

                builder.show();
                e1=(EditText)view2.findViewById(R.id.agrega); // codigo
                e2=(EditText)view2.findViewById(R.id.idnom); //nombre
                e3=(EditText)view2.findViewById(R.id.idape); ///apellido
                e4=(EditText)view2.findViewById(R.id.idtelefono); //Telefono
                ecorreo=(EditText)view2.findViewById(R.id.idcorreo);  //Coreo

               r1=(RadioButton)view2.findViewById(R.id.rbd1);
               r2=(RadioButton)view2.findViewById(R.id.rbd2);
                spinerciclo=(Spinner)view2.findViewById(R.id.spinerciclo);
                spinerciclo.setAdapter(adapterciclos);
            }
        });


        return vista;
    }



    private void Registrar(final String codigo, final String nombre, final String apellido, final String telefono, final String correo, String clave, final String tipo) {
        //Validation for empty fields
        if (TextUtils.isEmpty(codigo)) {
            SweetToast.error(getContext(), "Codigo Requerido.");
        } else if (TextUtils.isEmpty(codigo)){
            SweetToast.error(getContext(), "Nombre Requerido");
        } else if (TextUtils.isEmpty(telefono)){
            SweetToast.error(getContext(), "Telefono Requerido.");
        } else if (TextUtils.isEmpty(correo)){
            SweetToast.error(getContext(), "Correo Requerido");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            SweetToast.error(getContext(), "Correo no Valido.");

        } else if (correo.length() < 11){
            SweetToast.error(getContext(), "numero muy corto");

        } else if (TextUtils.isEmpty(tipo)){
            SweetToast.error(getContext(), "No hay Tipo");
        }
        else if (TextUtils.isEmpty(clave)){
            SweetToast.error(getContext(), "No hay clave");
        }
        else {
            //NOw ready to create a user a/c
            mAuth.createUserWithEmailAndPassword(correo, clave)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                String deviceToken = FirebaseInstanceId.getInstance().getToken();

                                // get and link storage
                                reference2 = FirebaseDatabase.getInstance().getReference().child("ChatGrupal");
                                String current_userID =  mAuth.getCurrentUser().getUid();
                                reference = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(current_userID);
                               // String id = reference.push().getKey();
                                reference.child("id").setValue(current_userID);
                                reference.child("codigo").setValue(codigo);
                                reference.child("nombre").setValue(nombre);
                                reference.child("apellido").setValue(apellido);
                                reference.child("tipo").setValue(tipo);
                                if(tipo.equals("Tutor")){
                                    Map<String,Object> map = new HashMap<>();
                                    map.put(current_userID, "");
                                    reference2.updateChildren(map);
                                }
                                reference.child("verified").setValue("false");
                                reference.child("search_name").setValue(nombre.toLowerCase());
                                reference.child("telefono").setValue(telefono);
                                reference.child("correo").setValue(correo);
                                reference.child("user_nickname").setValue("");
                                reference.child("user_gender").setValue("");
                                reference.child("user_profession").setValue("");
                                reference.child("created_at").setValue(ServerValue.TIMESTAMP);
                                reference.child("user_status").setValue("Hi, I'm new uMe user");
                                reference.child("user_image").setValue("default_image"); // Original image
                                reference.child("device_token").setValue(deviceToken);
                                reference.child("user_thumb_image").setValue("default_image")
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

                                                                            registerSuccessPopUp();

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
                                SweetToast.error(getContext(), "Error occurred : " + message);
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
    public void selectedGenderRB(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rbd1:
                if (checked){
                    selectipo = "Alumno";
                    //recheckGender.setVisibility(View.GONE);
                    break;
                }
            case R.id.rbd2:
                if (checked){
                    selectipo = "Tutor";
                    //recheckGender.setVisibility(View.GONE);
                    break;
                }
        }
    }
    private void registerSuccessPopUp() {
        // Custom Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.registro_existo, null);
        builder.setCancelable(false);
        builder.setView(view)
         .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        //attaching value event
        reference = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        Query q=reference.orderByChild("tipo").equalTo("Tutor");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listaPersonaje.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Usuarios artist = postSnapshot.getValue(Usuarios.class);
                    listaPersonaje.add(artist);
                }

                adapter = new AdapterTutores(listaPersonaje);
                recycler.setAdapter(adapter);
                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //    Toast.makeText(getContext(), "Selecciono"+listaPersonaje.get(recycler.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        LayoutInflater inflater = getActivity().getLayoutInflater();
                        View view2 = inflater.inflate(R.layout.modificarelimnar, null);
                        builder.setView(view2)

                                .setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getContext(), "hola", Toast.LENGTH_SHORT).show();

                                    }
                                });


                        builder.show();
                        ee1=(EditText)view2.findViewById(R.id.agrega1); // codigo
                        ee2=(EditText)view2.findViewById(R.id.idnom1);
                        ee3=(EditText)view2.findViewById(R.id.idape1);
                        ee4=(EditText)view2.findViewById(R.id.idtelefono1);
                        ecorreo=(EditText)view2.findViewById(R.id.idcorreo1);
                        // eciclo=(EditText)view2.findViewById(R.id.idcicloo1);
                        //   spinerciclo1=(Spinner)view2.findViewById(R.id.spinerciclo1);

                        ee1.setText(listaPersonaje.get(recycler.getChildAdapterPosition(view)).getCodigo());
                        ee2.setText(listaPersonaje.get(recycler.getChildAdapterPosition(view)).getNombre());
                        ee3.setText(listaPersonaje.get(recycler.getChildAdapterPosition(view)).getApellido());
                        ee4.setText(listaPersonaje.get(recycler.getChildAdapterPosition(view)).getTelefono());
                        ecorreo.setText(listaPersonaje.get(recycler.getChildAdapterPosition(view)).getCorreo());
                        //   eciclo.setText(listaPersonaje.get(recycler.getChildAdapterPosition(view)).getCiclo());
                        //    spinerciclo1.setT


                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    // TODO: Rename method, update argument and hook method into UI event

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
