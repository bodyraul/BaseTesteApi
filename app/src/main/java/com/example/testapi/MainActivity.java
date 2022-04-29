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




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);
                weatherDataService.getCityID(ecrire.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        makeText(MainActivity.this, "NON", LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String cityID) {
                        makeText(MainActivity.this, cityID, LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

}