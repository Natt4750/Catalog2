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
        team = (Team) getIntent().getSerializableExtra("team");

        teamNameID_details.setText(team.getName());
        teamIdID_details.setText(team.getId());

        Picasso.get()
                .load(team.getLogo())
                .fit()
                .into(teamImageID_details);



    }



    }
