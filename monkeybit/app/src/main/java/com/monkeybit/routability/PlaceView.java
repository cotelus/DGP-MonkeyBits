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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlaceView extends Fragment implements DBConnectInterface{

    View view;
    private List<Comments> comments;
    private RecyclerView listcomments;
    private CommentsAdapter adapter;
    private int result = 0;
    private boolean isFavorite = false;
    private String email;
    private String idPlace;
    private ImageButton favButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_place, container, false);

        idPlace = getArguments().getString("placeId");
        email = ((MainActivity) getActivity()).getUserEmail();

        if(email != null) {
            DBConnect.getFavoritePlaces(getContext(), this,email, 0);

            favButton = view.findViewById(R.id.placeFav);
            favButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (isFavorite){
                        removeFavoritePlace();
                    }
                    else{
                        addFavoritePlace();
                    }
                }
            });
        } else {
            Toast.makeText(getContext(), R.string.should_login, Toast.LENGTH_SHORT);
        }

        listcomments = view.findViewById(R.id.list_comments);
        LinearLayoutManager lim = new LinearLayoutManager(getContext());
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        listcomments.setLayoutManager(lim);

        dataComments();

        //DBConnect.getPlace(getContext(),this,idPlace);
        //DBConnect.getAverageScorePlace(getContext(),this,idPlace);
        DBConnect.getPlaceComments(getContext(),this,idPlace);

        return view;
    }

    public void addFavoritePlace(){
        DBConnect.addAsFavoritePlace(getContext(), this, email, idPlace);
    }

    public void removeFavoritePlace(){
       DBConnect.removeFavoritePlace(getContext(), this, email, idPlace);
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

    public void  initializedAdapter(){
        adapter = new CommentsAdapter(comments);
        listcomments.setAdapter(adapter);
    }

    //@Todo mandar id y que busque aquí en la base de datos
    public void SetView(JSONObject bdelements ) throws JSONException {

        if (!bdelements.has("data")) {
        }

        else{

            JSONArray query = bdelements.optJSONArray("data");

            TextView tittle = view.findViewById(R.id.tittlePlace);
            if (tittle != null && query.getJSONObject(0).has("Name"))
                tittle.setText(query.getJSONObject(0).optString("Name"));

            ImageView image = view.findViewById(R.id.imagePlace);
            if (image != null && query.getJSONObject(0).has("Image"))
                Picasso.get().load(query.getJSONObject(0).optString("Image")).into(image);

            TextView description = view.findViewById(R.id.descriptionPlace);
            if (description != null && query.getJSONObject(0).has("Description"))
                description.setText(query.getJSONObject(0).optString("Description"));

            TextView rating = view.findViewById(R.id.ratingPlace);
            if (rating != null) {
                rating.setText(getString(R.string.notrating));
            }
            if (query.getJSONObject(0).has("AVG(Rating)") && !query.getJSONObject(0).optString("AVG(Rating)").equals("null")) {
                rating.setText(query.getJSONObject(0).optString("AVG(Rating)" + "/5"));
            }

            TextView location = view.findViewById(R.id.locationPlace);
            if (location != null && query.getJSONObject(0).has("Localitation")) {
                location.setText(query.getJSONObject(0).optString("Localitation"));
            }

            TextView accessibility = view.findViewById(R.id.accessibilityPlace);
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
                Toast.makeText(getContext(), time, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Error BD: " + error, Toast.LENGTH_SHORT).show();
        result ++;
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            if(response.has("OPERATION")) {
                if (response.getString("OPERATION") == "ADD_FAVORITE_PLACE") {
                    Toast.makeText(getContext(), R.string.added_favorites, Toast.LENGTH_SHORT).show();
                    setFavButtonState(true);
                }
                else if (response.getString("OPERATION") == "REMOVE_FAVORITE_PLACE") {
                    Toast.makeText(getContext(), R.string.remove_favorites, Toast.LENGTH_SHORT).show();
                    setFavButtonState(false);
                }
                else if (response.getString("OPERATION") == "GET_FAVORITE_PLACES" && response.has("data")) {
                    JSONObject place = new JSONObject();
                    place.put("IdPlace", idPlace);
                    place.put("Email", email);
                    for (int i = 0; i < response.getJSONArray("data").length(); i++) {
                        if (response.getJSONArray("data").getJSONObject(i).equals(place)) {
                            setFavButtonState(true);
                        }
                    }
                }
                else {
                    SetView(response);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        result ++;

        if (result >= 3){
            initializedAdapter();
            //result = 0;
        }
    }

    private void setFavButtonState(boolean activate) {
        isFavorite = activate;
        favButton.setSelected(activate);
    }
}
