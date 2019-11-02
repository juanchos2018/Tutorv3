package com.example.tutorv3.Admin;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tutorv3.FragmentoAdmin.AlumnosFragment;
import com.example.tutorv3.FragmentoAdmin.RegistroFragment;
import com.example.tutorv3.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Gestion extends AppCompatActivity  implements RegistroFragment.OnFragmentInteractionListener,AlumnosFragment.OnFragmentInteractionListener {

    private FragmentManager fragmentManager;

private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
        = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:

                RegistroFragment fragment = new RegistroFragment();

                Cargar(fragment,fragmentManager);
                return true;
            case R.id.navigation_dashboard:
                AlumnosFragment fragment1 = new AlumnosFragment();
                //  Cargar(fragment1,fragmentManager);

                //  RegistrarTutorFragment fragment1 = new RegistrarTutorFragment();

//                Cargar(fragment1,fragmentManager);
                //  Cargar(R.id.idcontainerfragment,fragmentManager);
              getSupportFragmentManager().beginTransaction().replace(R.id.idcontainerfragment,fragment1).commit();
                return true;
        }
        return false;
    }
};

    private void Cargar(RegistroFragment f1, FragmentManager fm) {
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.idcontainerfragment,f1).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);
        BottomNavigationView navView = findViewById( R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager = getSupportFragmentManager();
        RegistroFragment fragment = new RegistroFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.idcontainerfragment,fragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
