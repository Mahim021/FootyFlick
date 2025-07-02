package com.example.footyflick;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class running_match_controller extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.running_match);

        MaterialButton nextEventBtn = findViewById(R.id.Next_Event_button);

        nextEventBtn.setOnClickListener(v -> {
            View eventView = LayoutInflater.from(this).inflate(R.layout.popup_event_select, null);
            AlertDialog eventDialog = new AlertDialog.Builder(this).setView(eventView).create();
            eventDialog.show();

            Spinner eventSpinner = eventView.findViewById(R.id.event_spinner);
            Spinner teamSpinner = eventView.findViewById(R.id.team_spinner);
            Button continueButton = eventView.findViewById(R.id.continue_button);

            String[] events = {"Goal", "Shot on Target", "Offside", "Save", "Foul", "Yellow Card", "Red Card"};
            String[] teams = {"Team A", "Team B"};

            eventSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, events));
            teamSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teams));

            continueButton.setOnClickListener(btn -> {
                String selectedEvent = eventSpinner.getSelectedItem().toString();
                String selectedTeam = teamSpinner.getSelectedItem().toString();
                eventDialog.dismiss();

                openPlayerPopup(selectedTeam, selectedEvent);
            });
        });

        ImageView rotatingBall = findViewById(R.id.rotatingball);
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.running_match_ball);
        rotatingBall.startAnimation(rotation);

        Button endMatchButton = findViewById(R.id.end_match_button);
        TextView team1ScoreView = findViewById(R.id.running_match_team_1_score);
        TextView team2ScoreView = findViewById(R.id.running_match_team_2_score);

        endMatchButton.setOnClickListener(v -> {
            int scoreA = Integer.parseInt(team1ScoreView.getText().toString());
            int scoreB = Integer.parseInt(team2ScoreView.getText().toString());

            new AlertDialog.Builder(running_match_controller.this)
                    .setTitle("End Match")
                    .setMessage("Are you sure you want to end the match?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        Intent intent = new Intent(running_match_controller.this, match_finished.class);
                        intent.putExtra("scoreTeamA", scoreA);
                        intent.putExtra("scoreTeamB", scoreB);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    private void openPlayerPopup(String team, String event) {
        View playerView = LayoutInflater.from(this).inflate(R.layout.popup_event_select_player, null);
        AlertDialog playerDialog = new AlertDialog.Builder(this).setView(playerView).create();
        playerDialog.show();

        TextView prompt = playerView.findViewById(R.id.player_prompt);
        Spinner playerSpinner = playerView.findViewById(R.id.player_spinner);
        Button confirmButton = playerView.findViewById(R.id.confirm_button);

        String[] players = team.equals("Team A") ?
                playerstore.teamAPlayers.toArray(new String[0]) :
                playerstore.teamBPlayers.toArray(new String[0]);

        prompt.setText("Select player for " + event.toLowerCase());
        playerSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, players));

        confirmButton.setOnClickListener(v -> {
            String selectedPlayer = playerSpinner.getSelectedItem().toString();
            playerDialog.dismiss();

            Toast.makeText(this, event + " by " + selectedPlayer + " (" + team + ")", Toast.LENGTH_SHORT).show();
        });
    }
}
