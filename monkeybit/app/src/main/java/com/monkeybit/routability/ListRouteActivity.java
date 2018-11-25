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

public class ListRouteActivity extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_routes, container, false);
        this.Conf_List_Route();

        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void Conf_List_Route(){

        //@TODO añadir las cosas de la bd

        ArrayList<ListRoute> datos = new ArrayList<ListRoute>();

        datos.add(new ListRoute(R.drawable.ic_accessible_black_24dp, "primero", "Descripcion 1"));
        datos.add(new ListRoute(R.drawable.ic_accessible_black_24dp, "segundo", "Descripcion 2 "));
        datos.add(new ListRoute(R.drawable.ic_accessible_black_24dp, "tercero", "Descripcion 3 "));
        ListView lista = view.findViewById(R.id.list_rt);
        lista.setAdapter(new AdapterList(getContext(), R.layout.post_rute, datos){
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

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        });
    }
}
