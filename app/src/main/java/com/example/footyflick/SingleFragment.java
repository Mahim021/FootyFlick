package com.example.footyflick;

import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SingleFragment extends Fragment {

    public SingleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_single, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Match date picker
        EditText dateEditText = view.findViewById(R.id.matchDate);
        MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select a date")
                .build();

        dateEditText.setOnClickListener(v -> {
            materialDatePicker.show(getParentFragmentManager(), "MATERIAL_DATE_PICKER");
        });

        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String formattedDate = sdf.format(new Date(selection));
            dateEditText.setText(formattedDate);
        });

        // Team selector options
        String[] options = getResources().getStringArray(R.array.team_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.list_item, options);

        AutoCompleteTextView teamSelector1 = view.findViewById(R.id.teamSelector1);
        teamSelector1.setAdapter(adapter);

        AutoCompleteTextView teamSelector2 = view.findViewById(R.id.teamSelector2);
        teamSelector2.setAdapter(adapter);


        // Reference all input fields and buttons
        AutoCompleteTextView team1 = view.findViewById(R.id.teamSelector1);
        AutoCompleteTextView team2 = view.findViewById(R.id.teamSelector2);

        Button confirmBtn = view.findViewById(R.id.confirmMatchButton);
        Button cancelBtn = view.findViewById(R.id.cancelMatchButton);

        // ✅ 1. Confirm button click: Check required fields
        confirmBtn.setOnClickListener(v -> {
            if (team1.getText().toString().trim().isEmpty()) {
                Toast.makeText(requireContext(), "Please select Team 1", Toast.LENGTH_SHORT).show();
            } else if (team2.getText().toString().trim().isEmpty()) {
                Toast.makeText(requireContext(), "Please select Team 2", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Match confirmed!", Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        // ✅ 2. Cancel button click: go back
        cancelBtn.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }
}
