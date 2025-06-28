package com.example.footyflick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MiniPlayerController {

    private final Context context;
    private final LinearLayout playersContainer;
    private final ArrayList<mini_player_model> players;
    private final ArrayAdapter<String> suggestionAdapter;

    public MiniPlayerController(Context context, LinearLayout playersContainer, ArrayList<mini_player_model> players) {
        this.context = context;
        this.playersContainer = playersContainer;
        this.players = players;

        // Prepare the list of player names for suggestions
        ArrayList<String> names = new ArrayList<>();
        for (mini_player_model player : players) {
            names.add(player.playerName);
        }

        suggestionAdapter = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, names);
    }

    public void addPlayerView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View playerView = inflater.inflate(R.layout.mini_player_template, playersContainer, false);

        // Set up delete button logic
        Button deleteBtn = playerView.findViewById(R.id.deletePlayerButton);
        deleteBtn.setOnClickListener(v -> {
            playersContainer.removeView(playerView);
            Toast.makeText(context, "Player removed", Toast.LENGTH_SHORT).show();
        });

        // Set up name suggestion and rating logic
        AutoCompleteTextView playerNameEditText = playerView.findViewById(R.id.playerNameEditText);
        TextView playerRatingText = playerView.findViewById(R.id.playerRating);

        playerNameEditText.setAdapter(suggestionAdapter);
        playerNameEditText.setThreshold(1); // Suggest after 1 character

        // When suggestion is selected, fill rating
        playerNameEditText.setOnItemClickListener((parent, view, position, id) -> {
            String selectedName = parent.getItemAtPosition(position).toString();
            int rating = getRatingByName(selectedName);
            playerRatingText.setText(String.valueOf(rating));
        });

        // When user types manually, reset rating unless exact match
        playerNameEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String input = playerNameEditText.getText().toString();
                int rating = getRatingByName(input);
                playerRatingText.setText(rating == -1 ? "--" : String.valueOf(rating));
            }
        });

        playersContainer.addView(playerView);
    }

    private int getRatingByName(String name) {
        for (mini_player_model player : players) {
            if (player.playerName.equalsIgnoreCase(name)) {
                return player.rating;
            }
        }
        return -1;
    }
}
