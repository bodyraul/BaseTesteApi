package com.example.testapi;

import static android.widget.Toast.makeText;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

public class WeatherDataService {

    public static final String QUERY_INFORMATION_PERSONNAGE="https://swapi.dev/api/people/?search=";

    Context context;
    String detailPersonnage ="";

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(String NomPersonnage) throws JSONException;
    }

    public void getCityID(String NomPersonnage,VolleyResponseListener volleyResponseListener){
        String url = QUERY_INFORMATION_PERSONNAGE+NomPersonnage;

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        detailPersonnage =response;
                        try {
                            volleyResponseListener.onResponse(detailPersonnage);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            makeText(context, "Le personnage n'existe pas ou est mal saisis", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "pas de données", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("non");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);


    }
}
