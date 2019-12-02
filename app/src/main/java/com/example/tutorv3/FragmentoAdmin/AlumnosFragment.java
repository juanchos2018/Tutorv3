package com.example.tutorv3.FragmentoAdmin;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutorv3.AdaptadoresAdmin.AdapterAlumnos;
import com.example.tutorv3.AdaptadoresAdmin.AdapterTutores;
import com.example.tutorv3.ClasesAdmin.Alumnos;
import com.example.tutorv3.ClasesAdmin.Usuarios;
import com.example.tutorv3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AlumnosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Alumnos> listaPersonaje;
    AdapterAlumnos adapter;
    RecyclerView recycler;

    private DatabaseReference reference;
    private OnFragmentInteractionListener mListener;

    public AlumnosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlumnosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlumnosFragment newInstance(String param1, String param2) {
        AlumnosFragment fragment = new AlumnosFragment();
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
        View vista = inflater.inflate(R.layout.fragment_alumnos, container, false);

        listaPersonaje = new ArrayList<>();
        recycler = (RecyclerView) vista.findViewById(R.id.recyclerId2);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return vista;
    }
    @Override
    public void onStart() {
        super.onStart();
        //attaching value event
        reference = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        Query q=reference.orderByChild("tipo").equalTo("Alumno");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listaPersonaje.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Alumnos artist = postSnapshot.getValue(Alumnos.class);
                    listaPersonaje.add(artist);
                }

                adapter = new AdapterAlumnos(listaPersonaje);
                recycler.setAdapter(adapter);
                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "hola", Toast.LENGTH_SHORT).show();

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

        void onFragmentInteraction(Uri uri);
    }
}
