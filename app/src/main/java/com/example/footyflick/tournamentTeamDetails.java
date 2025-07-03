package com.example.footyflick;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
        EditText teamNameInput = view.findViewById(R.id.teamTitleInput);
        Button confirmButton = view.findViewById(R.id.confirmButton);

        addTeamPlayers = new teamPlayerController(requireContext(), playersContainer, teamPlayers);
        addPlayerButton.setOnClickListener(v -> addTeamPlayers.addPlayer());

        // Get data from bundle
        Bundle args = getArguments();
        int teamCount = args != null ? args.getInt("TEAM_COUNT", 0) : 0;
        int currentTeam = args != null ? args.getInt("CURRENT_TEAM_INDEX", 1) : 1;

        confirmButton.setOnClickListener(v -> {
            String name = teamNameInput.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Team name cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(requireContext(), "Team " + currentTeam + " confirmed!", Toast.LENGTH_SHORT).show();

            if (currentTeam < teamCount) {
                // Load next team input fragment
                Bundle nextBundle = new Bundle();
                nextBundle.putInt("TEAM_COUNT", teamCount);
                nextBundle.putInt("CURRENT_TEAM_INDEX", currentTeam + 1);

                Fragment nextTeamFragment = new tournamentTeamDetails();
                nextTeamFragment.setArguments(nextBundle);

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, nextTeamFragment)
                        .commit(); // No addToBackStack
            } else {
                // All teams added
                Toast.makeText(requireContext(), "All teams added!", Toast.LENGTH_LONG).show();

                // Optionally clear the stack or go back to the main fragment
                // Clear the entire back stack (optional if stack isn't needed)
                requireActivity().getSupportFragmentManager()
                        .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

// Navigate to Tournament Home Page
                Fragment homeFragment = new TournamentHomePageFragment(); // Use your actual class name here

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, homeFragment)
                        .commit(); // No addToBackStack


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