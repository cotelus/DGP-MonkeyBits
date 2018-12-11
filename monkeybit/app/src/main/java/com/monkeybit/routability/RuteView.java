package com.monkeybit.routability;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class RuteView extends Fragment implements DBConnectInterface{
    View view;
    String id = null;
    DBConnectInterface db_inter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.view_rute, container, false);

        FindRoute();

        final ImageButton like_button = view.findViewById(R.id.pos_rt_fav);
        like_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                //@Todo añadir a fav con bd
                //@Todo hacer un boton que cuando pulse se cambie y lo ponga a favoritos y si le da cuando esta en fav, que lo quite
                //getId() deberia de añadirse y es el id de rutas
                //if(está en favoritos)
                    //poner boton a color
                //AddToFavouriteRoute(array.getId());
                //else
                    //poner boton en blanco
                    //DeleteFromFavourite(array.getId())
                //@Todo quitar el selector
                String aux = "añadido";
                Toast info = Toast.makeText(getContext(),aux,Toast.LENGTH_SHORT);
                info.show();
            }
        });

        final Button button = view.findViewById(R.id.post_follow_bt_rt);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                //@Todo enviar a map
                String aux = "Map";
                Toast info = Toast.makeText(getContext(),aux,Toast.LENGTH_SHORT);
                info.show();
            }
        });

        db_inter = new DBConnectInterface() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast errorText = Toast.makeText(getContext(),getContext().getString(R.string.errorListRoute),Toast.LENGTH_SHORT);
                errorText.show();
            }

            @Override
            public void onResponse(JSONObject response) {
                //{} objects [] array
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("data");
                    SetView(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }


    private void FindRoute(){
        DBConnect.getRoute(getContext(),db_inter,id);
    }

    public void SetView(JSONArray response){

        try {

            //Get and save data
            String idImage = response.getJSONObject(0).optString("Image");
            String tittle = response.getJSONObject(0).optString("Name");
            String description = response.getJSONObject(0).optString("Description");


            TextView pt_tittle =  view.findViewById(R.id.post_tittle);
            if(pt_tittle != null)
                pt_tittle.setText(tittle);

            TextView pt_desc =  view.findViewById(R.id.post_desc);
            if(pt_desc != null)
                pt_desc.setText(description);

            TextView pt_rating =  view.findViewById(R.id.post_rt_rating);
            if(pt_rating != null) {
                pt_rating.setText("2");
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Once you have the route, you have to search the places

    }

    public void SetID(String choosen){
        id = choosen;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast errorText = Toast.makeText(getContext(),getContext().getString(R.string.errorListRoute),Toast.LENGTH_SHORT);
        errorText.show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            if(response.getJSONObject("OPERATION").toString() == "GET_ROUTE"){
                //We search the route
                JSONArray json = response.getJSONArray("data");
                SetView(json);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}

