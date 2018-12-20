package com.monkeybit.routability;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.VolleyError;

import org.json.JSONObject;



public class SearchActivity extends Fragment implements View.OnClickListener{

    private String petition;
    Button search;
    RadioButton radioButtonFilterPlaces;
    RadioButton radioButtonFilterRoutes;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootview =  inflater.inflate(R.layout.activity_search, container, false);

        radioButtonFilterRoutes = rootview.findViewById(R.id.filterCheckBoxRoutes);
        radioButtonFilterPlaces = rootview.findViewById(R.id.filterCheckBoxPlaces);

        search = rootview.findViewById(R.id.searchButton);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ADEMAS HAZLE UN getPetition() A CADA UNO PARA ENVIARLE LA PETICION
                if(radioButtonFilterRoutes.isChecked()){
                    Fragment selectedFragment = new SearchRoutesResult();
                    ((SearchRoutesResult) selectedFragment).setPetition("petition");
                    getFragmentManager().beginTransaction().replace(R.id.frame_rp_view, selectedFragment).commit();
                    //Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
                }else{
                    Fragment selectedFragment = new SearchPlacesResult();
                    ((SearchPlacesResult) selectedFragment).setPetition("petition");
                    getFragmentManager().beginTransaction().replace(R.id.frame_rp_view, selectedFragment).commit();
                    //Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootview;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchButton:
                Fragment selectedFragment = new SearchPlacesResult();
                getFragmentManager().beginTransaction().replace(R.id.frame_rp_view, selectedFragment).commit();

                break;
        }
    }

    private void collectPetition(){

    }
    /*
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.home, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }*/
}
