package com.example.tutorv3.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tutorv3.AdaptadoresAdmin.AdapterAlumnos2;
import com.example.tutorv3.ClasesAdmin.ClsAlumnos;
import com.example.tutorv3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Lista1Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

    int contador=0;
    private OnFragmentInteractionListener mListener;

    public Lista1Fragment() {
        // Required empty public constructor
    }


    public static Lista1Fragment newInstance(String param1, String param2) {
        Lista1Fragment fragment = new Lista1Fragment();
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
        View vista = inflater.inflate(R.layout.fragment_lista1, container, false);


        listaAlumnos=new ArrayList<>();
        recycler=vista.findViewById(R.id.recylcermisalumnos);
        txtcantidad=(TextView)vista.findViewById(R.id.cantidad);
        etbuscarnombre=(EditText) vista.findViewById(R.id.idetbuscarsalon);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        etbuscarnombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // searchPeopleProfile(etbuscarnombre.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {


                filtrar(s.toString());
            }
        });
        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    private void filtrar(String texto) {
        ArrayList<ClsAlumnos> filtradatos= new ArrayList<>();

        for(ClsAlumnos item :listaAlumnos){
            if (item.getCorreoapoderado1().contains(texto)){
                filtradatos.add(item);
            }
            adapter.filtrar(filtradatos);
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

    @Override
    public void onStart() {
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
    public void onPause() {
        super.onPause();
        contador=0;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
