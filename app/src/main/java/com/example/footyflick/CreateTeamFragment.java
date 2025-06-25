package com.example.footyflick;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CreateTeamFragment extends Fragment {

    public CreateTeamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.create_team_page, container, false);
    }

    private MiniPlayerController miniPlayerController;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // dummy users
        ArrayList<mini_player_model> players = new ArrayList<>();

        players.add(new mini_player_model("Lionel Messi", 98));
        players.add(new mini_player_model("Cristiano Ronaldo", 97));
        players.add(new mini_player_model("Kylian Mbappé", 96));
        players.add(new mini_player_model("Erling Haaland", 95));
        players.add(new mini_player_model("Kevin De Bruyne", 94));
        players.add(new mini_player_model("Luka Modrić", 93));
        players.add(new mini_player_model("Neymar Jr", 92));
        players.add(new mini_player_model("Mohamed Salah", 91));
        players.add(new mini_player_model("Harry Kane", 90));
        players.add(new mini_player_model("Robert Lewandowski", 89));


        // Set up the button to add a new player template
        LinearLayout playersContainer = view.findViewById(R.id.playersContainer);
        Button addPlayerButton = view.findViewById(R.id.addPlayerButton);

        miniPlayerController = new MiniPlayerController(requireContext(), playersContainer, players);

        addPlayerButton.setOnClickListener(v -> miniPlayerController.addPlayerView());

        // --- TEAM CONFIRM BUTTON ---
        EditText teamNameEditText = view.findViewById(R.id.teamName); // Make sure the ID is correct
        Button confirmButton = view.findViewById(R.id.confirmTeamButton);
        confirmButton.setOnClickListener(v -> {
            String name = teamNameEditText.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Team name cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Team confirmed!", Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().popBackStack(); // Go back
            }
        });

        // --- CANCEL TEAM BUTTON ---
        Button cancelButton = view.findViewById(R.id.cancelTeamButton);
        cancelButton.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Cancel Team?")
                    .setMessage("Are you sure you want to cancel team creation?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        requireActivity().getSupportFragmentManager().popBackStack(); // Go back
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}
