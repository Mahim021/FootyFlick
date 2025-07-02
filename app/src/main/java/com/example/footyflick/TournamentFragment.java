
package com.example.footyflick;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.footyflick.R;

public class TournamentFragment extends Fragment {

    public TournamentFragment() {
        // Required empty public constructor
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] options = getResources().getStringArray(R.array.tournament_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.list_item, options);

        AutoCompleteTextView tournamentType = view.findViewById(R.id.selectTypeACT1);
        tournamentType.setAdapter(adapter);

        Button button = view.findViewById(R.id.selectPlayerButton);
        button.setOnClickListener(v -> {
            Fragment TournamentTeamDetails = new tournamentTeamDetails();

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, TournamentTeamDetails) // replace with your container ID
                    .addToBackStack(null) // Optional: adds to back stack so you can press back
                    .commit();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tournament, container, false);
    }
}