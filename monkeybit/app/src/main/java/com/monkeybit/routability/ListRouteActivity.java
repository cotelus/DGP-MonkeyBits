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

import java.util.ArrayList;

public class ListRouteActivity extends Fragment implements DBConnectInterface{

    View view;
    int pag;
    int tam = 10;
    private int result = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_routes, container, false);
        pag = 0;
        DBConnect.getRoutes(getContext(),this,pag);
        
        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }


    public void CargarArray(JSONArray jsonArray){
        ArrayList<Route> Lista = new ArrayList<>(); //@TODO si lo hacemos con rote tal cual

        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject json = jsonArray.getJSONObject(i);
                //Get and save data
                String idImage = json.getString("Image");
                String tittle = json.getString("tittle");
                String description = json.getString("description");
                String id = json.getString("idRoute");

                Lista.add(new Route(id,tittle,description,idImage));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        pag = pag + tam;
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
                //Toast toast = Toast.makeText(getContext()," Pulsado", Toast.LENGTH_SHORT);
              /*  //toast.show();
                ListRoute choosen = (ListRoute) post.getItemAtPosition(pos);
                RuteView route = new RuteView();
                @TODO: mandar
                if(route != null){
                   // route.SetChoosen(choosen.getId); //set
                    //change the fragment
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_rp_view,route).commit(); //go to the fragment

                }*/



            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast errorText = Toast.makeText(getContext(),getContext().getString(R.string.errorListRoute),Toast.LENGTH_SHORT);
        errorText.show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            if (response.has("OPERATIONS")) {
                for (int i = 0; i < response.getJSONArray("OPERATIONS").length(); i++) {
                    String operation = response.getJSONArray("OPERATIONS").getString(i);
                    if (response.has(operation)) { // Si no lo cumple, significa que no ha devuelto tuplas

                        if (operation.equals("GET_ROUTES")) {
                            JSONObject operationResult = response.getJSONObject(operation); // Este elemento tendrá la/s tupla/s
                            //Toast.makeText(getContext(), "Lugar\n" + operationResult.toString(), Toast.LENGTH_LONG).show();
                            SetView(operationResult);
                        }
                        
                    }

                }

            } else {
                Toast.makeText(getContext(),"ERROR", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
    }

    private void SetView(JSONObject operationResult) {
    }

}
