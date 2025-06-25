package com.example.footyflick;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        setContentView(R.layout.running_match); //  links the controller to XML



        MaterialButton nextEventBtn = findViewById(R.id.Next_Event_button); // your button ID

        nextEventBtn.setOnClickListener(v -> {
            View eventView = LayoutInflater.from(this).inflate(R.layout.popup_event_select, null);
            AlertDialog eventDialog = new AlertDialog.Builder(this).setView(eventView).create();
            eventDialog.show();

            Spinner eventSpinner = eventView.findViewById(R.id.event_spinner);
            Spinner teamSpinner = eventView.findViewById(R.id.team_spinner);
            Button continueButton = eventView.findViewById(R.id.continue_button);

            String[] events = {"Goal", "Shot on Target","offside","Save", "Foul", "Yellow Card", "Red Card"};
            String[] teams = {"Team A", "Team B"};

            eventSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, events));
            teamSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teams));

            continueButton.setOnClickListener(btn -> {
                String selectedEvent = eventSpinner.getSelectedItem().toString();
                String selectedTeam = teamSpinner.getSelectedItem().toString();
                eventDialog.dismiss();

                // Step 2: Open player selection
                openPlayerPopup(selectedTeam, selectedEvent);
            });
        });


    }

    private void openPlayerPopup(String team, String event) {
        View playerView = LayoutInflater.from(this).inflate(R.layout.popup_event_select_player, null);
        AlertDialog playerDialog = new AlertDialog.Builder(this).setView(playerView).create();
        playerDialog.show();

        TextView prompt = playerView.findViewById(R.id.player_prompt);
        Spinner playerSpinner = playerView.findViewById(R.id.player_spinner);
        Button confirmButton = playerView.findViewById(R.id.confirm_button);

        // Replace with your real player lists
        String[] teamAPlayers = {"Antu", "Mahim", "Tahmid"};
        String[] teamBPlayers = {"neymar", "ronaldo", "messi"};

        String[] players = team.equals("Team A") ? teamAPlayers : teamBPlayers;
        prompt.setText("Select player for " + event.toLowerCase());

        playerSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, players));

        confirmButton.setOnClickListener(v -> {
            String selectedPlayer = playerSpinner.getSelectedItem().toString();
            playerDialog.dismiss();

            // You can now handle the event with:
            // selectedTeam, selectedEvent, selectedPlayer
            Toast.makeText(this, event + " by " + selectedPlayer + " (" + team + ")", Toast.LENGTH_SHORT).show();
        });
    }






}
