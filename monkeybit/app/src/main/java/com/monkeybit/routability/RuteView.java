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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
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
import java.util.List;


public class RuteView extends Fragment implements DBConnectInterface{
    View view;
    String choosen = null;
    private List<Comments> comments;
    private RecyclerView listcomments;
    private CommentsAdapter adapter;
    private int result = 0;
    private boolean isFavorite = false;
    private String email;
    private ImageButton favButton;
    private DBConnectInterface dbInter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.view_rute, container, false);
        //Receive the id
        choosen = getArguments().getString("routeId");
        email = ((MainActivity) getActivity()).getUserEmail();
        dbInter = this;
        if(email != null) {
            DBConnect.getFavoriteRoutes(getContext(), this,email, 0);
            favButton = view.findViewById(R.id.posRtFav);
            favButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (isFavorite){
                        DBConnect.removeFavoriteRoutes(getContext(),dbInter ,email, choosen);
                    }
                    else{
                        DBConnect.addAsFavoriteRoute(getContext(),dbInter,email,choosen);
                    }
                }
            });
        } else {
            Toast.makeText(getContext(), R.string.should_login, Toast.LENGTH_SHORT);
        }


        final Button button = view.findViewById(R.id.postFollowBtRt);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                //@Todo enviar a map
                String aux = "Map";
                Toast info = Toast.makeText(getContext(),aux,Toast.LENGTH_SHORT);
                info.show();
            }
        });

        listcomments = view.findViewById(R.id.list_comments);
        LinearLayoutManager lim = new LinearLayoutManager(getContext());
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        listcomments.setLayoutManager(lim);

        dataComments();

        DBConnect.getPlace(getContext(),this,choosen);
        DBConnect.getAverageScorePlace(getContext(),this,choosen);
        DBConnect.getPlaceComments(getContext(),this,choosen);

        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void dataComments(){
        comments = new ArrayList<>();
        /*comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        /*comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));*/
    }
    private void SetRoutes(JSONArray jsonArray) {
        ArrayList<Place> Lista = new ArrayList<>();

        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject json = jsonArray.getJSONObject(i);
                //Get and save data

                Lista.add(new Place(jsonArray.getJSONObject(i)));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.Conf_List_Route(Lista);
    }
    protected void Conf_List_Route(ArrayList<Place> dataList) {

        ListView list = view.findViewById(R.id.postListLug);
        //adapt to the list
        list.setAdapter(new AdapterList(getContext(), R.layout.post_rute, dataList) {
            @Override
            public void onPost(Object post, View view) {
                if (post != null) {
                    TextView pt_tittle = view.findViewById(R.id.post_tittle);
                    if (pt_tittle != null)
                        pt_tittle.setText(((Place) post).getName());

                    TextView pt_desc = view.findViewById(R.id.post_desc);
                    if (pt_desc != null)
                        pt_desc.setText(((Place) post).getDescription());

                    //ImageView pt_img =  view.findViewById(R.id.post_img);
                    //if(pt_img != null)
                    //  pt_img.setImageResource(((Place) post).get_idImagen());


                }

            }
        });

    }

    public void initializedAdapter () {
        adapter = new CommentsAdapter(comments);
        listcomments.setAdapter(adapter);
    }


    public void SetView(JSONObject bdelements ) throws JSONException {

        if (!bdelements.has("data")) {
        }

        else{

            JSONArray query = bdelements.optJSONArray("data");

            TextView tittle = view.findViewById(R.id.postTittleRt);
            if (tittle != null && query.getJSONObject(0).has("Name"))
                tittle.setText(query.getJSONObject(0).optString("Name"));

            ImageView image = view.findViewById(R.id.imageRoute);
            if (image != null && query.getJSONObject(0).has("Image"))
                Picasso.get().load(query.getJSONObject(0).optString("Image")).into(image);

            TextView description = view.findViewById(R.id.postDescRt);
            if (description != null && query.getJSONObject(0).has("Description"))
                description.setText(query.getJSONObject(0).optString("Description"));

            TextView rating = view.findViewById(R.id.postRtRating);
            if (rating != null) {
                rating.setText(getString(R.string.notrating));
            }
            if (query.getJSONObject(0).has("AVG(Rating)") && !query.getJSONObject(0).optString("AVG(Rating)").equals("null")) {
                rating.setText(query.getJSONObject(0).optString("AVG(Rating)" + "/5"));
            }


            TextView accessibility = view.findViewById(R.id.accessibilityRoute);
            if (accessibility != null && query.getJSONObject(0).has("RedMovility")) {

                String message = "";

                int mobility = query.getJSONObject(0).optInt("RedMovility");
                int vision = query.getJSONObject(0).optInt("RedVision");
                int deaf = query.getJSONObject(0).optInt("Deaf");
                int colourblind = query.getJSONObject(0).optInt("ColourBlind");
                int foreigner = query.getJSONObject(0).optInt("Foreigner");

                if (mobility == 1) {
                    message += getString(R.string.red_mobility) + ", ";
                }
                if (vision == 1) {
                    message += getString(R.string.red_vision) + ", ";
                }
                if (colourblind == 1) {
                    message += getString(R.string.colour_blind) + ", ";
                }
                if (deaf == 1) {
                    message += getString(R.string.deaf) + ", ";
                }
                if (foreigner == 1) {
                    message += getString(R.string.foreigner);
                }

                if (mobility == 1 || vision == 1 || deaf == 1 || colourblind == 1 || foreigner == 1) {
                    accessibility.setText(getString(R.string.introduction) + " " + message);
                } else {
                    accessibility.setText(getString(R.string.notintroduction));
                }
            }

            //Comments
            String author="";
            if (query.getJSONObject(0).has("Email")) {
                author = query.getJSONObject(0).optString("Email");
            }

            String comment="";
            if (query.getJSONObject(0).has("Content")) {
                comment = query.getJSONObject(0).optString("Content");
            }

            String date="";
            if (query.getJSONObject(0).has("Date")) {
                date = query.getJSONObject(0).optString("Date");
            }

            String time="";
            if (query.getJSONObject(0).has("Time")) {
                time = query.getJSONObject(0).optString("Time");
                comments.add(new Comments(author,comment,date,time));
                //Toast.makeText(getContext(), time, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Error BD: " + error, Toast.LENGTH_SHORT).show();
        result++;
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            if (response.has("OPERATIONS")) {
                for (int i = 0; i < response.getJSONArray("OPERATIONS").length(); i++) {
                    String operation = response.getJSONArray("OPERATIONS").getString(i);
                    if (response.has(operation)) { // Si no lo cumple, significa que no ha devuelto tuplas

                        if (operation.equals("GET_ROUTE")) {
                            JSONObject operationResult = response.getJSONObject(operation); // Este elemento tendrá la/s tupla/s
                            //Toast.makeText(getContext(), "Lugar\n" + operationResult.toString(), Toast.LENGTH_LONG).show();
                        }
                        if (operation.equals("GET_ROUTE_COMMENTS")) {
                            JSONArray operationResult = response.getJSONArray(operation); // Este elemento tendrá la/s tupla/s
                            //Toast.makeText(getContext(), "Comentarios\n" + operationResult.toString(), Toast.LENGTH_LONG).show();
                        }
                        if (operation.equals("GET_AVERAGE_SCORE_ROUTE")) {
                            int operationResult = response.getInt(operation); // Este elemento tendrá la/s tupla/s
                            //Toast.makeText(getContext(), "Puntuación: " + operationResult, Toast.LENGTH_LONG).show();
                        }
                        if (operation.equals("GET_FAVORITE_ROUTES")) {
                            JSONArray operationResult = response.getJSONArray(operation);
                            for (int j = 0; j < operationResult.length(); j++) {
                                String favRoute = operationResult.getJSONObject(j).getString("IdRoute");
                                if (choosen.equals(favRoute)) {
                                    setFavButtonState(true);
                                }
                            }
                        }
                    }
                    // Estas operaciones, no necesitan datos de vuelta, por eso no están dentro del if anterior
                    if (operation.equals("ADD_FAVORITE_ROUTE")) {
                        Toast.makeText(getContext(), R.string.added_favorites, Toast.LENGTH_SHORT).show();
                        setFavButtonState(true);
                    }
                    if (operation.equals("REMOVE_FAVORITE_ROUTE")) {
                        Toast.makeText(getContext(), R.string.remove_favorites, Toast.LENGTH_SHORT).show();
                        setFavButtonState(false);
                    }
                }
                SetView(response);
            } else {
                Toast.makeText(getContext(),"ERROR", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        result ++;

        //@TODO se deberia mejorar esta comprobacion
        if (result >= 0){
            initializedAdapter();
            result = 0;
        }
    }

    private void setFavButtonState(boolean activate) {
        isFavorite = activate;
        favButton.setSelected(activate);
    }
}

