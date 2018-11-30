package com.monkeybit.routability;

import android.content.Context;
import android.util.Log;
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

import static com.android.volley.VolleyLog.TAG;

public class DBConnect  implements Response.Listener<JSONObject>, Response.ErrorListener{
    RequestQueue rq;
    JsonRequest jrq;
    JSONObject jso;

    /*
    //public JSONObject route;
    Route route;
    RequestQueue requestQueue;
    JsonRequest jrq;

    public DBConnect(){
        //route = null;
        requestQueue = null;
        route = new Route();
    }

    //public JSONObject getRoute(final Context context){
    public Route getRoute(Context context){

        // Crear nueva cola de peticiones
        requestQueue = Volley.newRequestQueue(context);
        String url = "http://192.168.1.39/login/checkRoutes.php?Name=fasefas";

        jrq = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray = response.optJSONArray("datos");
                        JSONObject jsonObject = null;

                        try {
                            jsonObject = jsonArray.getJSONObject(0);
                            //route = jsonObject.getJSONObject("datos");
                            //route = jsonArray.getJSONObject(0);

                            route.setEmailText(jsonObject.optString("Email"));
                            route.setMadeByText(jsonObject.optString("MadeBy"));
                            route.setNameText(jsonObject.optString("Name"));
                            route.setDescriptionText(jsonObject.optString("Description"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }
        );

        requestQueue.add(jrq);
        return route;
    }*/

    @Override
    public void onErrorResponse(VolleyError error) {

    }


    @Override
    public void onResponse(JSONObject response) {
        // Esto es para recuperar la información que va a llegar al JsonArray

        //JSONArray jsonArray = response.optJSONArray("datos");
        //JSONObject jsonObject = null;


        // En el objeto route, se guarda una instancia de User, con los datos que
        // hayamos recuperado del JSONArray
        try {
            JSONArray jsonArray = response.optJSONArray("datos");
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            jso = jsonObject;
            /*
            emailBox.setText(jsonObject.optString("Email"));
            madeByBox.setText(jsonObject.optString("MadeBy"));
            nameBox.setText(jsonObject.optString("Name"));
            descriptionBox.setText(jsonObject.optString("Description"));*/

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public JSONObject iniciarSesion(Context context) {
        // Añadir la IP. O subir a un servidor o usar la que devuelve ipconfig
        // Y de ahí ponerle la ruta de la API
        // En este caso, en cajaUser y cajaPwd es donde se definio lo que se quería consultar
        rq = Volley.newRequestQueue(context);
        String url = "http://192.168.1.39/login/checkRoutes.php?Name=fasefas";
        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);
        /*
        DBConnect db = new DBConnect();

        //myjs = db.getRoute(getContext());

        route = db.getRoute(getContext());

        /*
        route.setEmailText(myjs.optString("Email"));
        route.setMadeByText(myjs.optString("MadeBy"));
        route.setNameText(myjs.optString("Name"));
        route.setDescriptionText(myjs.optString("Description"));
        emailBox.setText(myjs.optString("Email"));
        madeByBox.setText(myjs.optString("Email"));
        nameBox.setText(myjs.optString("Email"));
        descriptionBox.setText(myjs.optString("Email"));*/

        return this.jso;

    }
}
