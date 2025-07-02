package com.example.footyflick;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class tournamentTeamDetails extends Fragment {

    public tournamentTeamDetails() {

    }

    private teamPlayerController addTeamPlayers;
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<player> teamPlayers = new ArrayList<>();

        LinearLayout playersContainer = view.findViewById(R.id.playersContainerTournament);
        Button addPlayerButton = view.findViewById(R.id.addPlayerButton);

        addTeamPlayers = new teamPlayerController(requireContext(), playersContainer, teamPlayers);

        addPlayerButton.setOnClickListener(v -> addTeamPlayers.addPlayer());

        // confirm button
        EditText teamNameInput = view.findViewById(R.id.teamTitleInput); // Make sure the ID is correct
        Button confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(v -> {
            String name = teamNameInput.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Team name cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Team confirmed!", Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().popBackStack(); // Go back
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tournament_team_details, container, false);
    }
}