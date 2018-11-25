package com.monkeybit.routability;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
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
        SetPlaces();
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
        //Once you have the route, you have to search the places

    }

    public void Array(ListRoute choosen){
        array = choosen;
    }

    protected void SetPlaces(){
        ArrayList<ListRoute> places = new ArrayList<ListRoute>();

        places.add(new ListRoute(R.drawable.ic_monument_black_24dp, "Lugar 1", "Descripcion 1"));
        places.add(new ListRoute(R.drawable.ic_monument_black_24dp, "Lugar 2", "Descripcion 2 "));
        places.add(new ListRoute(R.drawable.ic_monument_black_24dp, "Lugar 3", "Descripcion 3 "));
        ListView lista = view.findViewById(R.id.post_rt_list_lug);
        lista.setAdapter(new AdapterList(getContext(), R.layout.post_rute, places){
            @Override
            public void onPost(Object post, View view) {
                if(post != null){
                    TextView pt_tittle =  view.findViewById(R.id.post_tittle);
                    if(pt_tittle != null)
                        pt_tittle.setText(((ListRoute) post).get_Tittle());

                    TextView pt_desc =  view.findViewById(R.id.post_desc);
                    if(pt_desc != null)
                        pt_desc.setText(((ListRoute) post).get_Description());

                    ImageView pt_img =  view.findViewById(R.id.post_img);
                    if(pt_img != null)
                        pt_img.setImageResource(((ListRoute) post).get_idImagen());


                }

            }
        });
        //Go to VerRuta
        /*lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> post, View view, int pos, long id) {
                //Toast toast = Toast.makeText(getContext()," Pulsado", Toast.LENGTH_SHORT);
                //toast.show();
                ListRoute choosen = (ListRoute) post.getItemAtPosition(pos);
                RuteView route = new RuteView();

                if(route != null){
                    route.Array(choosen); //set
                    //change the fragment
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_rp_view,route).commit(); //go to the fragment

                }



            }
        });*/
    }

}

