package com.example.testapi;

import static android.widget.Toast.*;

import static com.example.testapi.R.*;
import static com.example.testapi.R.color.purple_200;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    public Button btn1;
    public EditText ecrire;
    public TextView afficherNom,afficherSexe,afficherPoids,afficherTaille,afficherEspece;
    JSONObject json =null,json2 =null;
    JSONArray jsonArray= null;
    Personnages ListePersonnages=new Personnages();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        btn1 = findViewById(id.btn1);
        ecrire = findViewById(id.editTextTextEcrire);
        afficherEspece = findViewById(id.textViewEspece);
        afficherNom = findViewById(id.textViewNom);
        afficherPoids = findViewById(id.textViewPoids);
        afficherSexe = findViewById(id.textViewSexe);
        afficherTaille = findViewById(id.textViewTaille);

        ListePersonnages.creationList();

        modifierVisibiliteTexte(0);

            ecrire.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(ecrire.getText().toString().isEmpty() ){
                        modifierVisibiliteTexte(0);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });




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
                    public void onResponse(String detailPersonnage) throws JSONException {
                        json= new JSONObject(detailPersonnage);
                        jsonArray = json.getJSONArray("results");
                        json2 = jsonArray.getJSONObject(0);

                        modifierTexte(json2);
                        modifierVisibiliteTexte(1);
                        testEspece(json2);

                    }

                });

            }
        });


    }

    public void testEspece(JSONObject json)throws JSONException{
        modifierColorTexte(color.AutreESpeces);
        afficherEspece.setText("Autre espèce");

        for (String element:ListePersonnages.getJediVert()) {
            if ((element.trim().equals(json.getString ( "name" ).trim()))){
                modifierColorTexte(color.JediSabreVert);
                afficherEspece.setText("Maître Jedi");
                return;
            }
        }
        for (String a:ListePersonnages.getSith()) {
            if ((a.trim().equals(json.getString ( "name" ).trim()))){
                modifierColorTexte(color.Sith);
                afficherEspece.setText("Seigneur Sith");
                return;
            }
        }
        for (String b:ListePersonnages.getJediBleu()) {
            if ((b.trim().equals(json.getString ( "name" ).trim()))){
                modifierColorTexte(color.JediSabreBleu);
                afficherEspece.setText("Maître Jedi");
                return;
            }
        }
        for (String c:ListePersonnages.getJediVilolet()) {
            if ((c.trim().equals(json.getString ( "name" ).trim()))){
                modifierColorTexte(color.JediSabreViolet);
                afficherEspece.setText("Maître Jedi");
                return;
            }
        }
        for (String d:ListePersonnages.getDroid()) {
            if ((d.trim().equals(json.getString ( "name" ).trim()))){
                modifierColorTexte(color.droide);
                afficherEspece.setText("Droid");
                return;
            }
        }
        for (String e:ListePersonnages.getHumain()) {
            if ((e.trim().equals(json.getString ( "name" ).trim()))){
                modifierColorTexte(color.humain);
                afficherEspece.setText("Humain");
                return;
            }
        }

    }






    public  void  modifierTexte ( JSONObject  jsonObject) throws  JSONException {
        if ( jsonObject == null ){
            afficherNom.setText("");
            afficherSexe.setText("");
            afficherTaille.setText("");
            afficherPoids.setText("");
            afficherEspece.setText("");
        } else {
            afficherNom.setText (  "nom : "+jsonObject.getString ( "name" ));
            afficherSexe.setText ( "sexe : " + jsonObject.getString ( "gender" ));
            afficherTaille.setText ( "Taille : " + jsonObject.getString ( "height" )+ " Cm" );
            afficherPoids.setText ( "Poids : " + jsonObject.getString ( "mass" )+ " Kg" );

        }
    }

    public  void  modifierVisibiliteTexte ( int a)  {
        if ( a==0 ){
            afficherNom.setVisibility(View.INVISIBLE);
            afficherSexe.setVisibility(View.INVISIBLE);
            afficherPoids.setVisibility(View.INVISIBLE);
            afficherTaille.setVisibility(View.INVISIBLE);
            afficherEspece.setVisibility(View.INVISIBLE);
        } else  {
            afficherNom.setVisibility(View.VISIBLE);
            afficherSexe.setVisibility(View.VISIBLE);
            afficherPoids.setVisibility(View.VISIBLE);
            afficherTaille.setVisibility(View.VISIBLE);
            afficherEspece.setVisibility(View.VISIBLE);
        }
    }

    public void modifierColorTexte(int a){
        afficherNom.setTextColor(ContextCompat.getColor(MainActivity.this,a));
        afficherSexe.setTextColor(ContextCompat.getColor(MainActivity.this,a));
        afficherTaille.setTextColor(ContextCompat.getColor(MainActivity.this,a));
        afficherPoids.setTextColor(ContextCompat.getColor(MainActivity.this,a));
        afficherEspece.setTextColor(ContextCompat.getColor(MainActivity.this,a));
    }


    }


