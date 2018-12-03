package com.monkeybit.routability;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DBTestFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    RequestQueue rq;
    JsonRequest jrq;
    EditText idBox;
    EditText idBox2;
    TextView emailBox, madeByBox, nameBox, descriptionBox;
    Button btnCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);

        // Para relacionar cada elemento de la vista con su funcionalidad
        View vista = inflater.inflate(R.layout.fragment_dbtest, container, false);
        idBox =(EditText) vista.findViewById(R.id.idText);
        idBox2 = (EditText) vista.findViewById(R.id.idText2);
        emailBox=(TextView) vista.findViewById(R.id.emailText);
        madeByBox=(TextView) vista.findViewById(R.id.madeByText);
        nameBox=(TextView) vista.findViewById(R.id.nameText);
        descriptionBox=(TextView) vista.findViewById(R.id.descriptionText);

        btnCheck =(Button) vista.findViewById(R.id.btnCheck);

        rq = Volley.newRequestQueue(getContext());

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                try {
                    iniciarSesion();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
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

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;


        // En el objeto route, se guarda una instancia de User, con los datos que
        // hayamos recuperado del JSONArray
        try {
            jsonObject = jsonArray.getJSONObject(0);
            route.setEmailText(jsonObject.optString("Email"));
            route.setMadeByText(jsonObject.optString("MadeBy"));
            route.setNameText(jsonObject.optString("Name"));
            route.setDescriptionText(jsonObject.optString("Description"));
            emailBox.setText(route.getEmailText());
            madeByBox.setText(route.getMadeByText());
            nameBox.setText(route.getNameText());
            descriptionBox.setText(route.getDescriptionText());

        } catch (JSONException e){
            e.printStackTrace();
        }




    }

    private void iniciarSesion() throws JSONException, InterruptedException {
        /*
        // Añadir la IP. O subir a un servidor o usar la que devuelve ipconfig
        // Y de ahí ponerle la ruta de la API
        // En este caso, en cajaUser y cajaPwd es donde se definio lo que se quería consultar
        String url = "http://192.168.1.39/login/checkRoutes.php?IdRoute=" + idBox.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);
        */
        String id, id2;
        id = idBox.getText().toString();
        id2 = idBox2.getText().toString();
        DBConnect.addFavoritePlace(getContext(), this, this, id, id2);
    }
}