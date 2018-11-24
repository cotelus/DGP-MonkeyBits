package com.monkeybit.routability;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;



public class RuteView extends Fragment {
    View view;
    ListRoute array = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.view_rute, container, false);

        SetView(array);
        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }


    //@Todo mandar id y que busque aquí en la base de datos
    public void SetView(ListRoute choosen ){
        if(choosen != null){
            TextView pt_tittle =  view.findViewById(R.id.post_tittle_rt);
            if(pt_tittle != null)
                pt_tittle.setText(choosen.get_Tittle());

            TextView pt_desc =  view.findViewById(R.id.post_desc_rt);
            if(pt_desc != null)
                pt_desc.setText(choosen.get_Description());
        }


    }

    public void Array(ListRoute choosen){
        array = choosen;
    }

}

