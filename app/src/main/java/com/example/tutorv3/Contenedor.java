package com.example.tutorv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.tutorv3.fragment.Lista1Fragment;
import com.example.tutorv3.fragment.Lista2Fragment;

public class Contenedor extends AppCompatActivity  implements Lista1Fragment.OnFragmentInteractionListener, Lista2Fragment.OnFragmentInteractionListener {

    Lista1Fragment fragment1;
    Lista2Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor);


        fragment1=new Lista1Fragment();
        fragment2=new Lista2Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragment,fragment1).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onClick(View view) {

        FragmentTransaction Transaction=getSupportFragmentManager().beginTransaction();

        switch (view.getId()){
            case R.id.btn1:
                Transaction.replace(R.id.contenedorFragment,fragment1);

                break;
            case R.id.btn2:
                Transaction.replace(R.id.contenedorFragment,fragment2);
                break;


        }
        Transaction.commit();

    }
}
