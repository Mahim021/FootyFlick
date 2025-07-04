package com.example.footyflick;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class TournamentFragment extends Fragment {

    public TournamentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tournament, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup dropdown for tournament type
        String[] options = getResources().getStringArray(R.array.tournament_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.list_item, options);

        AutoCompleteTextView tournamentType = view.findViewById(R.id.selectTypeACT1);
        tournamentType.setAdapter(adapter);

        // Get team number and button
        EditText teamNumberInput = view.findViewById(R.id.teamNumberInput);
        MaterialButton selectPlayerButton = view.findViewById(R.id.selectPlayerButton);

        selectPlayerButton.setOnClickListener(v -> {
            String numberText = teamNumberInput.getText().toString().trim();

            if (!numberText.isEmpty()) {
                int teamCount = Integer.parseInt(numberText);

                Bundle bundle = new Bundle();
                bundle.putInt("TEAM_COUNT", teamCount);
                bundle.putInt("CURRENT_TEAM_INDEX", 1); // Start from team 1

                Fragment teamDetailsFragment = new tournamentTeamDetails();
                teamDetailsFragment.setArguments(bundle);

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, teamDetailsFragment)
                        .addToBackStack(null)
                        .commit();

            } else {
                Toast.makeText(getContext(), "Please enter number of teams", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
