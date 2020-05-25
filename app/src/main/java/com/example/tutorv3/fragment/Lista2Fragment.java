package com.example.tutorv3.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tutorv3.AdaptadoresAdmin.AdapterAlumnos2;
import com.example.tutorv3.AdaptadoresAdmin.AdapterAlumnos3;
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


public class Lista2Fragment extends Fragment {
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
    ArrayList<ClsAlumnos> listaAlumnos2;

    AdapterAlumnos3 adapter;
    TextView t;
    int contador=0;
    private OnFragmentInteractionListener mListener;

    public Lista2Fragment() {
        // Required empty public constructor
    }


    public static Lista2Fragment newInstance(String param1, String param2) {
        Lista2Fragment fragment = new Lista2Fragment();
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
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_lista2, container, false);

        listaAlumnos=new ArrayList<>();
        listaAlumnos2=new ArrayList<>();
        recycler=vista.findViewById(R.id.recylcermisalumnos1);

        t=(TextView)vista.findViewById(R.id.idfaltnaates) ;

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        reference = FirebaseDatabase.getInstance().getReference().child("Alumnos");
        Query q=reference.orderByChild("e").equalTo("false");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaAlumnos.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ClsAlumnos artist = postSnapshot.getValue(ClsAlumnos.class);
                    listaAlumnos.add(artist);
                    contador++;
                  //  listaAlumnos2.add(artist);
                }
                //adapter.comprar();
                t.setText(""+contador +" alumnos");
                 adapter= new AdapterAlumnos3(listaAlumnos);
                recycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
    public void onPause() {
        super.onPause();
        contador=0;

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
