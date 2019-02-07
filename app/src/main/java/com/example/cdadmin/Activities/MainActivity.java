package com.example.cdadmin.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.cdadmin.Fragments.AlumnoFragment;
import com.example.cdadmin.Fragments.CursoFragment;
import com.example.cdadmin.Fragments.ProfesorFragment;
import com.example.cdadmin.Interfaces.IFragments;
import com.example.cdadmin.R;

public class MainActivity extends AppCompatActivity implements IFragments
{

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        Fragment frag = null;
        boolean fragmentSeleccionado = false;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profesor:
                    frag = new ProfesorFragment();
                    fragmentSeleccionado = true;
                    break;

                case R.id.navigation_alumno:
                    frag = new AlumnoFragment();
                    fragmentSeleccionado = true;
                    break;

                case R.id.navigation_curso:
                    frag = new CursoFragment();
                    fragmentSeleccionado = true;
                    break;
            }


            if(fragmentSeleccionado==true)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
            }
            return true;

        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Iniciar el fragment
        AlumnoFragment alumnoFragment = new AlumnoFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, alumnoFragment).commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
