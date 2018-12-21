package com.monkeybit.routability;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class ListPlaceActivity extends Fragment implements DBConnectInterface {
    View view;
    int pagCurent;
    int tam = 10;
    private int currentPageIndex = 0;
    private int result = 0;
    private Button nextPageButton,previousPageButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_place, container, false);
        currentPageIndex = 0;

        DBConnect.getPlaces(getContext(),this,currentPageIndex);
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
    }

    public void LoadArray(JSONArray jsonArray){
        ArrayList<ListPlace> list = new ArrayList<>();

        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject json = jsonArray.getJSONObject(i);
                //Get and save data

                String idImage = json.getString("Image");
                String tittle = json.getString("Name");
                String description = json.getString("Description");
                String imageDescription = json.getString("ImageDescription");
                String idPlace = json.getString("IdPlace");

                list.add(new ListPlace(idPlace,idImage, tittle,description,imageDescription));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        currentPageIndex = currentPageIndex + tam;

        this.Conf_List_Place(list);
    }

    protected void Conf_List_Place(ArrayList<ListPlace> dataList){

        ListView list = view.findViewById(R.id.listPlace);
        //adapt to the list

        if(list!=null) {
            list.setAdapter(new AdapterList(getContext(), R.layout.post_place, dataList) {
                @Override
                public void onPost(Object post, View view) {
                    if (post != null) {
                        TextView tittle = view.findViewById(R.id.tittlePlace);
                        if (tittle != null)
                            tittle.setText(((ListPlace) post).getTittle());

                        TextView description = view.findViewById(R.id.descriptionPlace);
                        if (description != null)
                            description.setText(((ListPlace) post).getDescription());

                        ImageView img = view.findViewById(R.id.imgPlacee);
                        if (img != null) {
                            Picasso.get().load(((ListPlace) post).getIdImage()).into(img);
                            img.setContentDescription( ((ListPlace) post).getImageDescription() );
                        }
                    }
                }
            });

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> post, View view, int pos, long id) {
                    //Toast toast = Toast.makeText(getContext()," Pulsado", Toast.LENGTH_SHORT);
                    //toast.show();

                    ListPlace choosen = (ListPlace) post.getItemAtPosition(pos);
                    PlaceView place = new PlaceView();
                    Bundle bundle = new Bundle();
                    bundle.putString("placeId", choosen.getIdPlace());
                    place.setArguments(bundle);

                    if (place != null) {

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_rp_view, place).commit(); //go to the fragment

                    }


                }
            });
        }
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Error" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            /*if (response.has("GET_PLACES")) {
                JSONArray operationResult = response.getJSONArray("GET_PLACES"); // Este elemento tendrá la/s tupla/s
                LoadArray(operationResult);
            }*/

            for (int i = 0; i < response.getJSONArray("OPERATIONS").length(); i++) {
                String operation = response.getJSONArray("OPERATIONS").getString(i);
                if (response.has(operation)) { // Si no lo cumple, significa que no ha devuelto tuplas

                    if (operation.equals("GET_PLACES")) {
                        JSONArray operationResult = response.getJSONArray("GET_PLACES"); // Este elemento tendrá la/s tupla/s
                        LoadArray(operationResult);
                    }

                }
                else if (operation.equals("GET_PLACES")) {
                    currentPageIndex -=tam;
                    Toast.makeText(getContext(),getString(R.string.infoNextButton), Toast.LENGTH_SHORT).show();
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showNextPage() {
        currentPageIndex += tam;
        this.fillPlaces();
    }

    private void showPreviousPage() {
        if (currentPageIndex >= tam) {
            currentPageIndex -= tam;
            if (currentPageIndex < 0) {
                currentPageIndex = 0;

            }
            this.fillPlaces();

        } else {
            Toast.makeText(getContext(), "No hay páginas anteriores", Toast.LENGTH_SHORT).show();
        }
    }

    private void fillPlaces() {
        DBConnect.getPlaces(getContext(),this,currentPageIndex);
    }
}
