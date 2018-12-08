package com.monkeybit.routability;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlaceView extends Fragment implements DBConnectInterface {

    View view;
    JSONObject elementsBD;
    private List<Comments> comments;
    private RecyclerView listcomments;
    private CommentsAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_place, container, false);

        /*final ImageButton fav_button = view.findViewById(R.id.pos_rt_fav);
        fav_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                //@Todo poner lugar de favoritos
                //@Todo poner estrella en amarillo
                //if(está en favoritos){
                //
                // Toast.makeText(getContext(),R.string.remove_favorites,Toast.LENGTH_SHORT).show();
                //
                // }
                //@Todo quitar lugar de favoritos
                //@Todo poner estrella en gris
                //else{
                //
                //
                // Toast.makeText(getContext(),R.string.add_favorites,Toast.LENGTH_SHORT).show();
                //
                // }
            }
        });*/

        listcomments = view.findViewById(R.id.list_comments);
        LinearLayoutManager lim = new LinearLayoutManager(getContext());
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        listcomments.setLayoutManager(lim);

        /*try {
            SetView(elementsBD);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        dataComments();
        initializedAdapter();
        DBConnect.getPlace(getContext(),this,"1");

        return view;
    }

    public void dataComments(){
        comments = new ArrayList<>();
        comments.add(new Comments("https://i.ytimg.com/vi/UAKUu9vNV60/maxresdefault.jpg", "Pepe", "Espectacular lugar"));
        comments.add(new Comments("https://i.ytimg.com/vi/UAKUu9vNV60/maxresdefault.jpg", "Pepe", "Espectacular lugar"));
        comments.add(new Comments("https://i.ytimg.com/vi/UAKUu9vNV60/maxresdefault.jpg", "Pepe", "Espectacular lugar"));
        comments.add(new Comments("https://i.ytimg.com/vi/UAKUu9vNV60/maxresdefault.jpg", "Pepe", "Espectacular lugar"));
        comments.add(new Comments("https://i.ytimg.com/vi/UAKUu9vNV60/maxresdefault.jpg", "Pepe", "Espectacular lugar"));
        comments.add(new Comments("https://i.ytimg.com/vi/UAKUu9vNV60/maxresdefault.jpg", "Pepe", "Espectacular lugar"));
        comments.add(new Comments("https://i.ytimg.com/vi/UAKUu9vNV60/maxresdefault.jpg", "Pepe", "Espectacular lugar"));
        comments.add(new Comments("https://i.ytimg.com/vi/UAKUu9vNV60/maxresdefault.jpg", "Pepe", "Espectacular lugar"));
        comments.add(new Comments("https://i.ytimg.com/vi/UAKUu9vNV60/maxresdefault.jpg", "Pepe", "Espectacular lugar"));
        comments.add(new Comments("https://i.ytimg.com/vi/UAKUu9vNV60/maxresdefault.jpg", "Pepe", "Espectacular lugar"));
    }

    public void  initializedAdapter(){
        adapter = new CommentsAdapter(comments);
        listcomments.setAdapter(adapter);
    }

    //@Todo mandar id y que busque aquí en la base de datos
    public void SetView(JSONObject bdelements ) throws JSONException {
        //(bdelements != null){
            TextView tittle =  view.findViewById(R.id.tittlePlace);
            if(tittle != null)
                tittle.setText(bdelements.getString("name"));
                //tittle.setText("Titulo del lugar buscado");

            ImageView image =  view.findViewById(R.id.imagePlace);
            if(image != null)
                Picasso.get().load(bdelements.getString("image")).into(image);
                //Picasso.get().load("https://www.lavanguardia.com/r/GODO/LV/p3/WebSite/2016/05/20/Recortada/img_mbigas_20160520-135524_imagenes_lv_getty_taj-k6e-U401920311809rVB-992x558@LaVanguardia-Web.jpg")
                // .into(image);

            TextView description =  view.findViewById(R.id.descriptionPlace);
            if(description != null)
                description.setText(bdelements.getString("description"));
                //description.setText("Esto es una prueba de la descripcion dfvlfdjhvkjsfnkjfsdnvjkfnvkjsfnvkjfnvkjndfdf \n fgdfgfdgfdgdgfdg\nsvsfsdfds\ndsfsdfsdf");

            TextView rating =  view.findViewById(R.id.ratingPlace);
            if(rating != null) {
                //rating.setText(bdelements.getString("Rating"));
                rating.setText("La valoracion es genial");
            }
        //}
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //Toast.makeText(getContext(), "Error al buscar lugar", Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Place place = new Place(response);

        Toast.makeText(getContext(), "Error lugar", Toast.LENGTH_SHORT).show();


        try {
            SetView(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
