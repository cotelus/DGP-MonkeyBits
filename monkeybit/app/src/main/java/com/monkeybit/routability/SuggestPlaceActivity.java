package com.monkeybit.routability;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class SuggestPlaceActivity extends Fragment implements DBConnectInterface {

    private TextInputEditText newName;
    private TextInputEditText newDescription;
    private TextInputEditText newLocalization;
    private Button suggestButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_suggest_place, container, false);

        newName = (TextInputEditText) view.findViewById(R.id.new_name);
        newDescription = (TextInputEditText) view.findViewById(R.id.description);
        newLocalization = (TextInputEditText) view.findViewById(R.id.localization);
        suggestButton = view.findViewById(R.id.suggestButton);
        suggestButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OnSuggest(v);
            }
        });

        return view;
    }

    protected void OnSuggest(android.view.View view) {
        String name = newName.getText().toString();
        String description = newDescription.getText().toString();
        String localization = newLocalization.getText().toString();

        if (!(name.isEmpty() || description.isEmpty() || localization.isEmpty())) {
            String userId = ((MainActivity) getActivity()).getUserEmail();

            //@TODO: Añadir imagen y accesibilidad
            PlaceToSuggest newPlace = new PlaceToSuggest(userId, name, description, localization, "Imagen", "To Chunga");
            try {
                DBConnect.suggestPlace(getContext(), this, newPlace.toJson());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(getActivity(), "Se enviarán los datos a la base de datos...", Toast.LENGTH_SHORT).show();
        } else {
            Toast toast = Toast.makeText(getActivity(), getString(R.string.empty_fields), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Error al sugerir ruta", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "Ruta sugerida correctamente", Toast.LENGTH_SHORT).show();
    }
}
