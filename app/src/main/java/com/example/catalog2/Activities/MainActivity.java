package com.example.catalog2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
//import android.widget.SearchView;



import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.catalog2.Data.TeamRecyclerViewAdapter;
import com.example.catalog2.Model.Team;
import com.example.catalog2.R;
import com.example.catalog2.Util.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TeamRecyclerViewAdapter teamRecyclerViewAdapter;
    private List<Team> teamList;
    private RequestQueue requestQueue;
     String searchTerm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue= Volley.newRequestQueue(this);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this.recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        teamList=new ArrayList<>();

        Prefs prefs=new Prefs(MainActivity.this);
        String search=prefs.getSearch();

        teamList=getTeams(search);
        teamRecyclerViewAdapter = new TeamRecyclerViewAdapter(this, teamList);
        recyclerView.setAdapter(teamRecyclerViewAdapter);

        teamRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.recherche, menu);
       MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Write the league number");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchTerm = query;
                teamList=getTeams(searchTerm);

                teamRecyclerViewAdapter.notifyDataSetChanged();


                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(getCurrentFocus() !=null)
                {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }

                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);

    }

    public List<Team> getTeams(String searchTerm) {
        teamList.clear();
        String myUrl = "https://api-basketball.p.rapidapi.com/teams?league=" + searchTerm + "&season=2019-2020";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray teamArray = response.getJSONArray("response");
                    for (int i = 0; i < teamArray.length(); i++) {
                        JSONObject teamObj = teamArray.getJSONObject(i);
                        Team team = new Team();
                        team.setName(teamObj.getString("name"));
                        team.setId(teamObj.getString("id"));
                        team.setLogo(teamObj.getString("logo"));
                        // Log.d("Teams =", team.getName());
                        teamList.add(team);
                    }
                    teamRecyclerViewAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Erruer", "Err" + error);
            }
        })
        {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("X-RapidAPI-Host", "api-basketball.p.rapidapi.com");
                params.put("X-RapidAPI-Key", "7775b315e2mshcc5c2aa25e66d12p134555jsnfce02c9ba0af");
                return params;
            }};


        ;
        requestQueue.add(jsonObjectRequest);
        return teamList;
    }
}
