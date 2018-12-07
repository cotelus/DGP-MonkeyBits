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

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListRouteActivity extends Fragment {
    DBConnectInterface db_inter;
    View view;
    int pag;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_routes, container, false);

        pag = 0;
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
                    jsonArray = response.getJSONArray(" ");
                    CargarArray(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }


    public void CargarArray(JSONArray jsonArray){
        ArrayList<ListRoute> Lista = new ArrayList<>();

        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject json = jsonArray.getJSONObject(i);
                //Get and save data
                int idImage = json.getInt("image");
                String tittle = json.getString("tittle");
                String description = json.getString("description");
                String id = json.getString("id");

                Lista.add(new ListRoute(idImage, tittle, description, id);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        pag = pag + 1;
        this.Conf_List_Route(Lista);
    }

    protected void Conf_List_Route(ArrayList<ListRoute> dataList){

        ListView list = view.findViewById(R.id.list_rt);
        list.setAdapter(new AdapterList(getContext(), R.layout.post_rute,dataList){
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
