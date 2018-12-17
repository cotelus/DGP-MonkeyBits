package com.monkeybit.routability;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private RecyclerView listComments;
    private CommentsAdapter adapter;
    private int result = 0;
    private boolean isFavorite = false;
    private String email;
    private String idPlace;
    private ImageButton favButton;
    private TextInputEditText commentText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_place, container, false);

        idPlace = getArguments().getString("placeId");
        email = ((MainActivity) getActivity()).getUserEmail();

        DBConnect.getFavoritePlaces(getContext(), this,email, 0);
        favButton = view.findViewById(R.id.placeFav);
        favButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (email != null) {
                    if (isFavorite) {
                        removeFavoritePlace();
                    } else {
                        addFavoritePlace();
                    }
                } else {
                    Toast.makeText(getContext(), R.string.should_login, Toast.LENGTH_SHORT).show();
                }
            }
        });

        commentText = view.findViewById(R.id.myComment);

        Button button = view.findViewById(R.id.sendCommentPlace);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email != null && !commentText.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Enviando comentario", Toast.LENGTH_SHORT).show();
                    addComment();
                }
                else if(email == null){
                    Toast.makeText(getContext(), R.string.should_login, Toast.LENGTH_SHORT).show();
                }
                else if(commentText.getText().toString().equals("")){
                    Toast.makeText(getContext(), R.string.empty_fields, Toast.LENGTH_SHORT).show();
                }
            }
        });

        listComments = view.findViewById(R.id.list_comments);
        LinearLayoutManager lim = new LinearLayoutManager(getContext());
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        listComments.setLayoutManager(lim);

        dataComments();
        //initializedAdapter();

        DBConnect.getPlace(getContext(),this,idPlace);

        return view;
    }

    public void addComment(){
        DBConnect.addPlaceComment(getContext(),this, idPlace, email, commentText.getText().toString());
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
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));
        comments.add(new Comments( "Pepe", "Espectacular lugar", "23/23/23", "12:22"));*/
    }

    public void  initializedAdapter(){

        if(comments.size() >=4){
            listComments.getLayoutParams().height = 1300;
        }
        adapter = new CommentsAdapter(comments);
        listComments.setAdapter(adapter);
    }

    //@Todo mandar id y que busque aquí en la base de datos
    public void SetView(JSONObject query){

        TextView tittle = view.findViewById(R.id.tittlePlace);
        if (tittle != null && query.has("Name"))
            tittle.setText(query.optString("Name"));

        ImageView image = view.findViewById(R.id.imagePlace);
        if (image != null && query.has("Image"))
            Picasso.get().load(query.optString("Image")).into(image);

        TextView description = view.findViewById(R.id.descriptionPlace);
        if (description != null && query.has("Description"))
            description.setText(query.optString("Description"));

         TextView location = view.findViewById(R.id.locationPlace);
         if (location != null && query.has("Localitation")) {
             location.setText(query.optString("Localitation"));
         }

         TextView accessibility = view.findViewById(R.id.accessibilityPlace);
         if (accessibility != null && query.has("RedMovility")) {

             String message = "";

             int mobility = query.optInt("RedMovility");
             int vision = query.optInt("RedVision");
             int deaf = query.optInt("Deaf");
             int colourblind = query.optInt("ColourBlind");
             int foreigner = query.optInt("Foreigner");

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
    }

    public void setComments(JSONArray query1) throws JSONException {

        for(int i=0; i<query1.length(); i++) {

            JSONObject query = query1.getJSONObject(i);

            //Comments
            String author = "";
            if (query.has("Email")) {
                author = query.optString("Email");
            }

            String comment = "";
            if (query.has("Content")) {
                comment = query.optString("Content");
            }

            String date = "";
            if (query.has("Date")) {
                date = query.optString("Date");
            }

            String time = "";
            if (query.has("Time")) {
                time = query.optString("Time");
                comments.add(new Comments(author, comment, date, time));
                //Toast.makeText(getContext(), time, Toast.LENGTH_SHORT).show();
            }
        }
        initializedAdapter();
    }

    public void setRating(int rating1){
        TextView rating = view.findViewById(R.id.ratingPlace);
        rating.setText(rating1 + "/5");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Error BD: " + error, Toast.LENGTH_SHORT).show();
        result ++;
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            if (response.has("OPERATIONS")) {
                for (int i = 0; i < response.getJSONArray("OPERATIONS").length(); i++) {
                    String operation = response.getJSONArray("OPERATIONS").getString(i);
                    if (response.has(operation)) { // Si no lo cumple, significa que no ha devuelto tuplas

                        if (operation.equals("GET_PLACE")) {
                            JSONObject operationResult = response.getJSONObject(operation); // Este elemento tendrá la/s tupla/s
                            this.SetView(operationResult);
                            //Toast.makeText(getContext(), "Lugar\n" + operationResult.toString(), Toast.LENGTH_LONG).show();
                        }
                        if (operation.equals("GET_COMMENTS_FROM_PLACE")) {
                            JSONArray operationResult = response.getJSONArray(operation); // Este elemento tendrá la/s tupla/s
                            this.setComments(operationResult);
                            //Toast.makeText(getContext(), "Comentarios\n" + operationResult.toString(), Toast.LENGTH_LONG).show();
                        }
                        if (operation.equals("GET_AVERAGE_SCORE_PLACE")) {
                            int operationResult = response.getInt(operation); // Este elemento tendrá la/s tupla/s
                            setRating(operationResult);
                            //Toast.makeText(getContext(), "Puntuación: " + operationResult, Toast.LENGTH_LONG).show();
                        }
                        if (operation.equals("GET_FAVORITE_PLACES")) {
                            JSONArray operationResult = response.getJSONArray(operation);
                            for (int j = 0; j < operationResult.length(); j++) {
                                String favPlace = operationResult.getJSONObject(j).getString("IdPlace");
                                if (idPlace.equals(favPlace)) {
                                    setFavButtonState(true);
                                }
                            }
                        }
                    }
                    // Estas operaciones, no necesitan datos de vuelta, por eso no están dentro del if anterior
                    if (operation.equals("ADD_FAVORITE_PLACE")) {
                        Toast.makeText(getContext(), R.string.added_favorites, Toast.LENGTH_SHORT).show();
                        setFavButtonState(true);
                    }
                    if (operation.equals("REMOVE_FAVORITE_PLACE")) {
                        Toast.makeText(getContext(), R.string.remove_favorites, Toast.LENGTH_SHORT).show();
                        setFavButtonState(false);
                    }
                    if(operation.equals("ADD_PLACE_COMMENT")){
                        Toast.makeText(getContext(), R.string.comment_sent, Toast.LENGTH_SHORT).show();
                        this.commentText.getText().clear();
                    }
                }
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
