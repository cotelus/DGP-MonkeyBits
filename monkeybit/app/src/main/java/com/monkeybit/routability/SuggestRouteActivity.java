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
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SuggestRouteActivity extends Fragment implements DBConnectInterface {

    private Button suggestButton;
    private RecyclerView addedPlaces;
    private RecyclerView availablePlaces;
    ArrayList<Place> arrayAddedPlaces = new ArrayList<>();
    ArrayList<Place> arrayAvailablePlaces = new ArrayList<>();
    View view;
    private TextInputEditText newName;
    private TextInputEditText newDescription;
    private TextInputEditText newImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_suggest_route, container, false);

        newName = (TextInputEditText) view.findViewById(R.id.new_name);
        newDescription = (TextInputEditText) view.findViewById(R.id.description);
        newImage = (TextInputEditText) view .findViewById(R.id.image);

        suggestButton = view.findViewById(R.id.suggestButton);
        suggestButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OnSuggest(v);
            }
        });

        addedPlaces = view.findViewById(R.id.added_places);
        LinearLayoutManager lim = new LinearLayoutManager(getContext());
        if(lim != null) {
            lim.setOrientation(LinearLayoutManager.VERTICAL);
            addedPlaces.setLayoutManager(lim);
        }

        availablePlaces = view.findViewById(R.id.available_places);
        LinearLayoutManager lim2 = new LinearLayoutManager(getContext());
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        availablePlaces.setLayoutManager(lim2);

        DBConnect.getPlaces(getContext(), this, 1);

        this.initializePlaceAdapter(arrayAddedPlaces, addedPlaces);
        this.initializePlaceAdapter(arrayAvailablePlaces,availablePlaces);

        return view;
    }

    protected void OnSuggest(android.view.View view) {
        String name = newName.getText().toString();
        String description = newDescription.getText().toString();
        String image = newImage.getText().toString();

        if (!(name.isEmpty() || description.isEmpty() || image.isEmpty() || arrayAddedPlaces.isEmpty())) {
            String userId = ((MainActivity) getActivity()).getUserEmail();
            if (userId != null) {
                RouteToSuggest newRoute = new RouteToSuggest(userId, name, description, image);
                newRoute.setPlaces(arrayAddedPlaces);
                Toast.makeText(getActivity(), "Json a enviar: " + newRoute.toJson() + "\n" + newRoute.getPlacesInJson(), Toast.LENGTH_SHORT).show();
                try {
                    DBConnect.suggestRoute(getContext(), this, newRoute.toJson(), newRoute.getPlacesInJson());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(), "Se enviarán los datos a la base de datos...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Error al sugerir ruta: "+ error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            if (response.has("SUGGEST_ROUTE")) {
                Toast.makeText(getContext(), getString(R.string.suggested_route), Toast.LENGTH_SHORT).show();
            }
            if (response.has("GET_PLACES")) {
                JSONArray operationResult = response.getJSONArray("GET_PLACES"); // Este elemento tendrá la/s tupla/s
                /*for (int i = 0; i < operationResult.length(); i++) {
                    Place place = new Place(operationResult.getJSONObject(i));
                    arrayAvailablePlaces.add(place);
                }*/
                this.fillAvailablePlaced(operationResult);
                this.initializePlaceAdapter(arrayAvailablePlaces, availablePlaces);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void fillAvailablePlaced(JSONArray places) throws JSONException {
        for (int i = 0; i < places.length(); i++) {
            Place place = new Place(places.getJSONObject(i));
            // Para no añadir aquellos que ya están en la lista de añadidos (al modificar ruta existente)
            if (!arrayAddedPlaces.contains(place)) {
                arrayAvailablePlaces.add(place);
            }
        }
    }

    public void  initializePlaceAdapter(ArrayList<Place> places, final RecyclerView placeView){
        if(places.size() >=4){
            placeView.getLayoutParams().height = 1300;
        }
        else if(places.size() < 4){
            placeView.getLayoutParams().height = RecyclerView.LayoutParams.WRAP_CONTENT; ///Se coge el wrap content para ajustar el tamaño
        }
        final PlaceAdapter adapter = new PlaceAdapter(places);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = placeView.getChildAdapterPosition(v); // gets item position
                if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                    if(placeView == addedPlaces){
                        arrayAvailablePlaces.add(arrayAddedPlaces.get(position));
                        arrayAddedPlaces.remove(position);
                    }
                    else if(placeView == availablePlaces){
                        arrayAddedPlaces.add(arrayAvailablePlaces.get(position));
                        arrayAvailablePlaces.remove(position);
                    }
                    initializePlaceAdapter(arrayAddedPlaces, addedPlaces);
                    initializePlaceAdapter(arrayAvailablePlaces,availablePlaces);
                }
            }
        });
        placeView.setAdapter(adapter);
    }
}
