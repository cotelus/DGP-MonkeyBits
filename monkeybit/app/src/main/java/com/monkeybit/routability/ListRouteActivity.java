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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListRouteActivity extends Fragment implements DBConnectInterface{

    View view;

    int tam = 10;
    private int currentPageIndex = 0;
    private int result = 0;
    private Button nextPageButton,previousPageButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_routes, container, false);

        DBConnect.getRoutes(getContext(),this,currentPageIndex);

        nextPageButton = view.findViewById(R.id.next_places_button);
        nextPageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showNextPage();
            }
        });

        previousPageButton = view.findViewById(R.id.previous_places_button);
        previousPageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showPreviousPage();
            }
        });

        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }



    protected void Conf_List_Route(ArrayList<ListRoute> dataList){

        ListView list = view.findViewById(R.id.list_rt);
        //adapt to the list
        if(list!=null) {
            list.setAdapter(new AdapterList(getContext(), R.layout.post_rute,dataList){
                @Override
                public void onPost(Object post, View view){
                    if(post != null){
                        TextView pt_tittle =  view.findViewById(R.id.post_tittle);
                        if(pt_tittle != null)
                            pt_tittle.setText(((ListRoute) post).get_Tittle());

                        TextView pt_desc =  view.findViewById(R.id.post_desc);
                        if(pt_desc != null)
                            pt_desc.setText(((ListRoute) post).get_Description());

                        ImageView img = view.findViewById(R.id.post_img);
                        if (img != null && ((ListRoute) post).get_idImagen() != null) {
                            Picasso.get().load(((ListRoute) post).get_idImagen()).into(img);
                            img.setContentDescription(((ListRoute) post).getImageDescription());
                        }
                    }

                }
            });

            list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> post, View view, int pos, long id) {
                    ListRoute choosen = (ListRoute) post.getItemAtPosition(pos);
                    RouteView route = new RouteView();
                    Bundle bundle = new Bundle();
                    bundle.putString("routeId", choosen.get_idRoute());
                    route.setArguments(bundle);
                    //Log.d("Debug","choosen: "+choosen);
                    if (route != null) {

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_rp_view, route).commit(); //go to the fragment

                    }
                }
            });


        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        /*Toast errorText = Toast.makeText(getContext(),getContext().getString(R.string.errorListRoute),Toast.LENGTH_SHORT);
        errorText.show();*/
        //Log.d("Debug",getContext().getString(R.string.errorListRoute));
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            if (response.has("OPERATIONS")) {
                for (int i = 0; i < response.getJSONArray("OPERATIONS").length(); i++) {
                    String operation = response.getJSONArray("OPERATIONS").getString(i);
                    if (response.has(operation)) { // Si no lo cumple, significa que no ha devuelto tuplas

                        if (operation.equals("GET_ROUTES")) {
                            JSONArray operationResult = response.getJSONArray("GET_ROUTES"); // Este elemento tendrá la/s tupla/s
                            SetView(operationResult);
                        }
                        
                    }
                    else if (operation.equals("GET_ROUTES")) {
                        currentPageIndex -=tam;
                        Toast.makeText(getContext(),getString(R.string.infoNextButton), Toast.LENGTH_SHORT).show();
                    }

                }

            } else {
                Toast.makeText(getContext(),"ERROR", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
    }

    private void SetView(JSONArray operationResult) throws JSONException {
        ArrayList<ListRoute> list = new ArrayList<>();

        for(int i=0;i<operationResult.length();i++){
            try {
                JSONObject json = operationResult.getJSONObject(i);
                //Get and save data

                String idImage = json.getString("Image");
                String tittle = json.getString("Name");
                String description = json.getString("Description");

                String idRoute = json.getString("IdRoute");
               // Log.d("Debug",idImage);
                String desc_imagen = json.getString("ImageDescription");
                list.add(new ListRoute(idImage, tittle,description , idRoute,desc_imagen));



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
       // currentPageIndex = currentPageIndex + tam;

        this.Conf_List_Route(list);

    }


    private void showNextPage() {
        currentPageIndex += tam;
        this.fillRoutes();
    }

    private void showPreviousPage() {
        if (currentPageIndex >= tam) {
            currentPageIndex -= tam;
            if (currentPageIndex < 0) {
                currentPageIndex = 0;

            }
            this.fillRoutes();

        } else {
            Toast.makeText(getContext(), "No hay páginas anteriores", Toast.LENGTH_SHORT).show();
        }
    }

    private void fillRoutes() {
        DBConnect.getRoutes(getContext(),this,currentPageIndex);
    }
    
}
