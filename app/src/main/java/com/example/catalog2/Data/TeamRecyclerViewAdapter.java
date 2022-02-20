package com.example.catalog2.Data;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catalog2.Activities.DetailsActivity;
import com.example.catalog2.Model.Team;
import com.example.catalog2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamRecyclerViewAdapter extends RecyclerView.Adapter<TeamRecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private List<Team> teamList;

    public TeamRecyclerViewAdapter(Context context, List<Team> teamList)
    {
        this.context=context;
        this.teamList=teamList;
    }

    public TeamRecyclerViewAdapter(SearchView.OnQueryTextListener onQueryTextListener, List<Team> teamList) {
        this.context=context;
        this.teamList=teamList;
    }

    @NonNull
    @Override
    public TeamRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nba, parent,false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamRecyclerViewAdapter.ViewHolder holder, int position)
    {
        Team team=teamList.get(position);
        String posterLink=team.getLogo();
        holder.name.setText("Name: " + team.getName());
        holder.id.setText("Id: " + team.getId());

        Picasso.get()
                .load(posterLink)
                .fit()
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(holder.logo);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name, id;
        ImageView logo;

        public ViewHolder(@NonNull View itemView,Context ctx) {
            super(itemView);
            context=ctx;
            name=itemView.findViewById(R.id.teamNameID);
            logo=itemView.findViewById(R.id.teamImageID);
            id=itemView.findViewById(R.id.teamIdID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Team team=teamList.get(getAdapterPosition());
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("team", team);
                    ctx.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}


