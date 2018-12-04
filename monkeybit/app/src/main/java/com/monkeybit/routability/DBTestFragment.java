package com.monkeybit.routability;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DBTestFragment extends Fragment implements DBConnectInterface {
    EditText idBox;
    TextView emailBox, madeByBox, nameBox, descriptionBox;
    Button btnCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Para relacionar cada elemento de la vista con su funcionalidad
        View vista = inflater.inflate(R.layout.fragment_dbtest, container, false);
        idBox =(EditText) vista.findViewById(R.id.idText);
        emailBox=(TextView) vista.findViewById(R.id.emailText);
        madeByBox=(TextView) vista.findViewById(R.id.madeByText);
        nameBox=(TextView) vista.findViewById(R.id.nameText);
        descriptionBox=(TextView) vista.findViewById(R.id.descriptionText);

        btnCheck =(Button) vista.findViewById(R.id.btnCheck);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                try {
                    getRoute();
                } catch (JSONException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        return vista;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se encontró el id" + error.toString(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onResponse(JSONObject response) {
        // Esto es para recuperar la información que va a llegar al JsonArray
        Route route = new Route();
        Toast.makeText(getContext(), "Se ha encontrado el ID: " + idBox.getText().toString(), Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("data");
        JSONObject jsonObject = null;

        // En el objeto route, se guarda una instancia de User, con los datos que
        // hayamos recuperado del JSONArray
        try {
            jsonObject = jsonArray.getJSONObject(0);
            route.setEmail(jsonObject.optString("Email"));
            route.setMadeBy(jsonObject.optString("MadeBy"));
            route.setName(jsonObject.optString("Name"));
            route.setDescription(jsonObject.optString("Description"));
            emailBox.setText(route.getEmail());
            madeByBox.setText(route.getMadeBy());
            nameBox.setText(route.getName());
            descriptionBox.setText(route.getDescription());
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void getRoute() throws JSONException, InterruptedException {
        DBConnect.getRoute(getContext(), this, "1");
    }
}