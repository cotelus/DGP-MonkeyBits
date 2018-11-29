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
    EditText cajaUser, cajaPwd;
    Button btnConsultar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);

        // Para relacionar cada elemento de la vista con su funcionalidad
        View vista = inflater.inflate(R.layout.fragment_dbtest, container, false);
        cajaUser =(EditText) vista.findViewById(R.id.txtUser);
        cajaPwd =(EditText) vista.findViewById(R.id.txtPwd);
        btnConsultar =(Button) vista.findViewById(R.id.btnSesion);

        rq = Volley.newRequestQueue(getContext());

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                iniciarSesion();
            }
        });


        return vista;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se encontró el usuario" + error.toString(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onResponse(JSONObject response) {
        // Esto es para recuperar la información que va a llegar al JsonArray
        Route usuario = new Route();
        Toast.makeText(getContext(), "Se ha encontrado el usuario " + cajaUser.getText().toString(), Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;


        // En el objeto usuario, se guarda una instancia de User, con los datos que
        // hayamos recuperado del JSONArray
        try {
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setUser(jsonObject.optString("user"));
            usuario.setPwd(jsonObject.optString("pwd"));
            usuario.setId(jsonObject.optString("id"));
        } catch (JSONException e){
            e.printStackTrace();
        }




    }

    private void iniciarSesion() {
        // Añadir la IP. O subir a un servidor o usar la que devuelve ipconfig
        // Y de ahí ponerle la ruta de la API
        // En este caso, en cajaUser y cajaPwd es donde se definio lo que se quería consultar
        String url = "http://192.168.1.39/login/sesion.php?user=" + cajaUser.getText().toString() +
                "&pwd=" + cajaPwd.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);

    }
}