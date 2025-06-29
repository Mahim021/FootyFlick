package com.example.footyflick;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SingleHomePageFragment extends Fragment {

    public SingleHomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.single_homepage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up the create new match button to navigate to the SingleFragment
        Button myButton = view.findViewById(R.id.createMatchButton);
        myButton.setOnClickListener(v -> {
            // Create an instance of your target fragment
            Fragment singleFragment = new SingleFragment();

            // Perform the fragment transaction
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, singleFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // Generate undone matches
        generateMatch(false, view, "undoneMatchesContainer");

        // Generate done matches
        generateMatch(true, view, "doneMatchesContainer");



    }




    // Generates matches based on the done status
    private void generateMatch(boolean done, View rootView, String containerId) {
        // Sample match list
        List<mini_match_model> matches = new ArrayList<>();
        matches.add(new mini_match_model("Real Madrid", "Manchester City", "2", "1", "10 Sep, 2025", true));
        matches.add(new mini_match_model("Barcelona", "Bayern Munich", "-", "-", "11 Sep, 2025", false));
        matches.add(new mini_match_model("Liverpool", "PSG", "1", "3", "12 Sep, 2025", true));
        matches.add(new mini_match_model("Chelsea", "Juventus", "-", "-", "13 Sep, 2025", false));
        matches.add(new mini_match_model("Arsenal", "Inter Milan", "2", "2", "14 Sep, 2025", true));
        matches.add(new mini_match_model("AC Milan", "Borussia Dortmund", "-", "-", "15 Sep, 2025", false));
        matches.add(new mini_match_model("Atletico Madrid", "Napoli", "0", "1", "16 Sep, 2025", true));
        matches.add(new mini_match_model("Tottenham", "Porto", "-", "-", "17 Sep, 2025", false));
        matches.add(new mini_match_model("Benfica", "Sevilla", "3", "2", "18 Sep, 2025", true));
        matches.add(new mini_match_model("Ajax", "RB Leipzig", "-", "-", "19 Sep, 2025", false));
        matches.add(new mini_match_model("Shakhtar Donetsk", "Celtic", "2", "0", "20 Sep, 2025", true));
        matches.add(new mini_match_model("Galatasaray", "Sporting CP", "-", "-", "21 Sep, 2025", false));

        int resId = getResources().getIdentifier(containerId, "id", requireContext().getPackageName());
        LinearLayout container = rootView.findViewById(resId);
        if (container == null) {
            throw new IllegalArgumentException("Container with ID " + containerId + " not found in layout.");
        }

        LayoutInflater inflater = LayoutInflater.from(getContext());

        for (mini_match_model match : matches) {
            if (match.done == done) {
                View matchView = inflater.inflate(R.layout.mini_match_template, container, false);

                TextView matchTitle = matchView.findViewById(R.id.matchTitle);
                TextView matchDate = matchView.findViewById(R.id.matchDate);
                TextView score = matchView.findViewById(R.id.score);

                matchTitle.setText(match.team1 + " Vs " + match.team2);
                matchDate.setText(match.date);
                score.setText(match.team1_score + " - " + match.team2_score);

                container.addView(matchView);
            }
        }
    }

}
