package com.example.footyflick;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class TeamFragment extends Fragment {

    public TeamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.team_homepage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Show the registered teams
        generateRegisteredTeam(view, "registeredTeamsContainer");

        // Set up the create new team button to navigate to the CreateTeamFragment
        MaterialButton createTeamButton = view.findViewById(R.id.createNewTeamButton);
        createTeamButton.setOnClickListener(v -> {
            // Create an instance of your target fragment
            Fragment createTeamFragment = new CreateTeamFragment();

            // Perform the fragment transaction
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, createTeamFragment)
                    .addToBackStack(null)
                    .commit();
        });

    }

    private void generateRegisteredTeam(View rootView, String containerId) {
        // Sample data
        List<mini_team_model> teamList = new ArrayList<>();
        teamList.add(new mini_team_model("FC Barcelona", 11));
        teamList.add(new mini_team_model("Real Madrid", 12));
        teamList.add(new mini_team_model("Manchester City", 10));
        teamList.add(new mini_team_model("Bayern Munich", 14));
        teamList.add(new mini_team_model("PSG", 9));
        teamList.add(new mini_team_model("Juventus", 13));

        // Get the container using the ID name
        int resID = getResources().getIdentifier(containerId, "id", requireContext().getPackageName());
        LinearLayout container = rootView.findViewById(resID);

        LayoutInflater inflater = LayoutInflater.from(requireContext());

        for (mini_team_model team : teamList) {
            // Inflate the mini_team_template
            View teamView = inflater.inflate(R.layout.mini_team_template, container, false);

            // Set team name
            TextView teamName = teamView.findViewById(R.id.teamName);
            teamName.setText(team.teamName);

            // Set number of players
            TextView numberOfPlayers = teamView.findViewById(R.id.numberOfPlayers);
            numberOfPlayers.setText(team.numberOfPlayers + " Players");

            // Set up delete and edit buttons (optional logic)
            MaterialButton deleteBtn = teamView.findViewById(R.id.deleteTeamButton);
            MaterialButton editBtn = teamView.findViewById(R.id.editTeamButton);

            // delete team button logic
            deleteBtn.setOnClickListener(v -> {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Delete Team")
                        .setMessage("Are you sure you want to delete " + team.teamName + "?")
                        .setPositiveButton("Delete", (dialog, which) -> {
                            // Animate: slide to the right and fade out
                            teamView.animate()
                                    .translationX(-teamView.getWidth()) // slide to left
                                    .alpha(0f)                         // fade out
                                    .setDuration(500)                  // animation duration
                                    .withEndAction(() -> {
                                        container.removeView(teamView); // remove after animation ends
                                        Toast.makeText(requireContext(), "Deleted " + team.teamName, Toast.LENGTH_SHORT).show();
                                    })
                                    .start();
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            });

            // edit team button logic
            editBtn.setOnClickListener(v -> {
                Toast.makeText(requireContext(), "Edit " + team.teamName, Toast.LENGTH_SHORT).show();
            });

            // Add the view to container
            container.addView(teamView);
        }
    }

}
