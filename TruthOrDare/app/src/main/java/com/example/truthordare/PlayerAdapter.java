package com.example.truthordare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<String> playerNames;

    public PlayerAdapter(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        String playerName = playerNames.get(position);
        holder.tvPlayerName.setText(playerName);
    }

    @Override
    public int getItemCount() {
        return playerNames.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlayerName;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            tvPlayerName = itemView.findViewById(android.R.id.text1);
        }
    }
}