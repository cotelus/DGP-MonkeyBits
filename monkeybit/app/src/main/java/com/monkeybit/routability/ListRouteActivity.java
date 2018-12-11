package com.monkeybit.routability;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import java.util.ArrayList;

public class ListRouteActivity extends Fragment implements DBConnectInterface{
    DBConnectInterface db_inter;
    View view;
    int pag = 0;
    int max = 10;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_routes, container, false);
        pag = 0;
        //db_inter = new DBConnectInterface() {

        DBConnect.getRoutes(getContext(),this,pag);
        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }


    public void CargarArray(JSONArray jsonArray){
        ArrayList<Route> Lista = new ArrayList<>();

        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject json = jsonArray.getJSONObject(i);
                //Get and save data
                String idImage = json.getString("Image");
                String tittle = json.getString("Name");
                String description = json.getString("Description");
                String id = json.getString("IdRoute");

                Lista.add(new Route(id,tittle,description,idImage));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        pag = pag + max;
        this.Conf_List_Route(Lista);
    }

    protected void Conf_List_Route(ArrayList<Route> dataList){

        ListView list = view.findViewById(R.id.list_rt);
        //adapt to the list
        list.setAdapter(new AdapterList(getContext(), R.layout.post_rute,dataList){
            @Override
            public void onPost(Object post, View view){
                if(post != null){
                    TextView pt_tittle =  view.findViewById(R.id.post_tittle);
                    if(pt_tittle != null)
                        pt_tittle.setText(((Route) post).getName());

                    TextView pt_desc =  view.findViewById(R.id.post_desc);
                    if(pt_desc != null)
                        pt_desc.setText(((Route) post).getDescription());

                    //ImageView pt_img =  view.findViewById(R.id.post_img);
                    //if(pt_img != null)
                      //  pt_img.setImageResource(((ListRoute) post).get_idImagen());


                }

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> post, View view, int pos, long id) {
                //
                Route choosen = (Route) post.getItemAtPosition(pos);
                RuteView route = new RuteView();

                if(route != null){
                    route.SetID(choosen.getIdRoute()); //set
                    //change the fragment
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_rp_view,route).commit(); //go to the fragment

                }



            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (getContext() != null)
            Toast.makeText(getContext(), getString(R.string.errorListRoute),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        //{} objects [] array
        JSONArray jsonArray = null;
        try {
            jsonArray = response.getJSONArray("data");
            CargarArray(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
