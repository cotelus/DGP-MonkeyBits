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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class DBConnect {

    private DBConnect() {}

    public static void getRoute(Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener, String id) {
        // Añadir la IP. O subir a un servidor o usar la que devuelve ipconfig
        // Y de ahí ponerle la ruta de la API
        // En este caso, en cajaUser y cajaPwd es donde se definio lo que se quería consultar
        String url = "http://192.168.1.39/RoutabilityDB/getRoute.php?IdRoute=" + id;
        RequestQueue rq = Volley.newRequestQueue(context);
        JsonRequest jrq = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, errorListener);
        rq.add(jrq);
    }

    public static void addFavoritePlace(Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener, String IdPlace, String Email) {
        // Añadir la IP. O subir a un servidor o usar la que devuelve ipconfig
        // Y de ahí ponerle la ruta de la API
        // En este caso, en cajaUser y cajaPwd es donde se definio lo que se quería consultar
        String url = "http://192.168.1.39/RoutabilityDB/addFavoritePlace.php?IdPlace=" + IdPlace + "&Email=" + Email;
        RequestQueue rq = Volley.newRequestQueue(context);
        JsonRequest jrq = new JsonObjectRequest(Request.Method.PUT, url, null, responseListener, errorListener);
        rq.add(jrq);
    }

    public static void addFavoriteRoute(Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener, String IdRoute, String Email) {
        // Añadir la IP. O subir a un servidor o usar la que devuelve ipconfig
        // Y de ahí ponerle la ruta de la API
        // En este caso, en cajaUser y cajaPwd es donde se definio lo que se quería consultar
        String url = "http://192.168.1.39/RoutabilityDB/addFavoriteRoute.php?IdRoute=" + IdRoute + "&Email=" + Email;
        RequestQueue rq = Volley.newRequestQueue(context);
        JsonRequest jrq = new JsonObjectRequest(Request.Method.PUT, url, null, responseListener, errorListener);
        //JsoonRequest t = new JsonObjectRequest(Request.Method.)
        rq.add(jrq);
    }

    public static void getPlace(Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener, String id) {
        // Añadir la IP. O subir a un servidor o usar la que devuelve ipconfig
        // Y de ahí ponerle la ruta de la API
        // En este caso, en cajaUser y cajaPwd es donde se definio lo que se quería consultar
        String url = "http://192.168.1.39/RoutabilityDB/getPlace.php?IdPlace=" + id;
        RequestQueue rq = Volley.newRequestQueue(context);
        JsonRequest jrq = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, errorListener);
        rq.add(jrq);
    }
}

