package com.example.testapi;

import static android.widget.Toast.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    EditText ecrire;
    TextView afficherNom,afficherSexe,afficherPoids,afficherTaille;
    JSONObject json = null,json3=null;
    JSONArray json2 = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        ecrire = findViewById(R.id.editTextTextEcrire);
        afficherNom = findViewById(R.id.textViewNom);
        afficherPoids = findViewById(R.id.textViewPoids);
        afficherSexe = findViewById(R.id.textViewSexe);
        afficherTaille = findViewById(R.id.textViewTaille);

        ecrire.setText("");
        try {
            modifierTexte(json3,1);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    modifierTexte(json3, 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String url = "https://swapi.dev/api/people/?search="+ecrire.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    json = new JSONObject(response);
                                    json2 = json.getJSONArray("results");
                                    json3 = json2.getJSONObject(0);

                                    /*p1.setName(json.getString("name"));*/
                                    modifierTexte(json3,1);
                                } catch (JSONException e) {
                                    Toast.makeText(MainActivity.this,"non" , Toast.LENGTH_LONG).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "pas de données", Toast.LENGTH_SHORT).show();
                    }
                });
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);


            }
        });


    }

    public void modifierTexte(JSONObject jsonObject, int a) throws JSONException {
        if(jsonObject == null){
            afficherNom.setText("");
            afficherSexe.setText("");
            afficherTaille.setText("");
            afficherPoids.setText("");
        }else{
            afficherNom.setText("Nom : "+jsonObject.getString("name"));
            afficherSexe.setText("Nom : "+jsonObject.getString("gender"));
            afficherTaille.setText("Taille : "+json3.getString("height")+" Cm");
            afficherPoids.setText("Poids : "+json3.getString("mass")+" Kg");
        }


        if (a==0){
            afficherNom.setVisibility(View.INVISIBLE);
            afficherSexe.setVisibility(View.INVISIBLE);
            afficherPoids.setVisibility(View.INVISIBLE);
            afficherTaille.setVisibility(View.INVISIBLE);
        }else{
            afficherNom.setVisibility(View.VISIBLE);
            afficherSexe.setVisibility(View.VISIBLE);
            afficherPoids.setVisibility(View.VISIBLE);
            afficherTaille.setVisibility(View.VISIBLE);
        }
    }

}