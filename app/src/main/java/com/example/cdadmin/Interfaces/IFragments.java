package com.example.cdadmin.Interfaces;

import com.example.cdadmin.Fragments.AlumnoFragment;
import com.example.cdadmin.Fragments.CursoFragment;
import com.example.cdadmin.Fragments.ProfesorFragment;

public interface IFragments extends CursoFragment.OnFragmentInteractionListener,
        AlumnoFragment.OnFragmentInteractionListener,
        ProfesorFragment.OnFragmentInteractionListener{

}
