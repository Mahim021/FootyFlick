package com.example.footyflick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class teamPlayerController {

    private final Context context;
    private final LinearLayout playersContainerTournament;
    private final ArrayAdapter<String> suggestionAdapter;

    public teamPlayerController(Context context, LinearLayout playersContainerTournament, ArrayList<player> players) {
        this.context = context;
        this.playersContainerTournament = playersContainerTournament;

        // Prepare name suggestions from provided player list
        ArrayList<String> names = new ArrayList<>();
        for (player player : players) {
            names.add(player.playerName);
        }

        suggestionAdapter = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, names);
    }

    public void addPlayer() {
        // Inflate the player input layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View playerView = inflater.inflate(R.layout.player_info_bar, playersContainerTournament, false);

        // Delete button logic
        Button deleteBtn = playerView.findViewById(R.id.deletePlayerButton);
        deleteBtn.setOnClickListener(v -> {
            playersContainerTournament.removeView(playerView);
            Toast.makeText(context, "Player removed", Toast.LENGTH_SHORT).show();
        });

        // Player name input with suggestions
        AutoCompleteTextView playerNameInput = playerView.findViewById(R.id.playerNameInput);
        playerNameInput.setAdapter(suggestionAdapter);
        playerNameInput.setThreshold(1); // Start suggesting after 1 character

        // Player role input using dropdown (from string-array)
        AutoCompleteTextView playerRole = playerView.findViewById(R.id.selectRoleAC);
        String[] roles = context.getResources().getStringArray(R.array.player_role);
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, roles);
        playerRole.setAdapter(roleAdapter);

        // Add the new player input row to the container
        playersContainerTournament.addView(playerView);
    }
}
