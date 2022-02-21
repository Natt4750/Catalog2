package com.example.catalog2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.catalog2.Model.Team;
import com.example.catalog2.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class DetailsActivity extends AppCompatActivity {
    private Team team;
    private ImageView teamImageID_details;
    private TextView teamNameID_details, teamIdID_details;
    private RequestQueue queue;
    private String teamId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        teamNameID_details= findViewById(R.id.teamNameID_details);
        teamIdID_details= findViewById(R.id.teamIdID_details);
        teamImageID_details=findViewById(R.id.teamImageID_details);

        Intent intent = getIntent();
        String nameS = intent.getStringExtra("name");
        String idS = intent.getStringExtra("id");
        String logoS = intent.getStringExtra("logo");



        teamNameID_details.setText(nameS);
        teamIdID_details.setText(idS);

        Picasso.get()
                .load(logoS)
                .fit()
                .into(teamImageID_details);



        /*queue = Volley.newRequestQueue(this);

        team = (Team) getIntent().getSerializableExtra("team");
        teamId = team.getId();
        teamNameID_details=findViewById(R.id.teamNameID);
        teamIdID_details=findViewById(R.id.teamIdID_details);
        teamImageID_details=findViewById(R.id.teamImageID_details);

        getTeamDetails(teamId);*/
    }

    /*private void getTeamDetails(String id) {
        String myUrl = "https://api-basketball.p.rapidapi.com/teams?league=" + id + "&season=2019-2020";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {


                   teamNameID_details.setText("Name: " + response.getString("name"));
                   teamIdID_details.setText("ID: " + response.getString("id"));

                    Picasso.get()
                            .load(response.getString("Poster"))
                            .fit()
                            .into(teamImageID_details);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Erreur", "Err" + error);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("X-RapidAPI-Host", "api-basketball.p.rapidapi.com");
                params.put("X-RapidAPI-Key", "7775b315e2mshcc5c2aa25e66d12p134555jsnfce02c9ba0af");
                return params;
            }
        };


        ;
        queue.add(jsonObjectRequest);

    }*/

    }
