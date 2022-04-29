package com.example.testapi;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataService {

    public static final String QUERY_FOR_CITY_ID="https://www.metaweather.com//api/location/search/?query=";

    Context context;
    String cityID="";

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(String cityID);
    }

    public void getCityID(String city,VolleyResponseListener volleyResponseListener){
        String url = QUERY_FOR_CITY_ID+city;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONObject cityInfo= response.getJSONObject(0);
                            cityID= cityInfo.getString("woeid");
                        }catch (JSONException E){
                            E.printStackTrace();
                        }
                       //makeText(context, cityID, LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cityID);
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
