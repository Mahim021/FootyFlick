package com.example.footyflick;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SingleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SingleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SingleFragment newInstance(String param1, String param2) {
        SingleFragment fragment = new SingleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the date picker
        EditText dateEditText = view.findViewById(R.id.date);

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

        // Initialize the AutoCompleteTextView for both team selection
        AutoCompleteTextView teamSelector1 = view.findViewById(R.id.teamSelector1);

        String[] options = getResources().getStringArray(R.array.team_options);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                R.layout.list_item,
                options);

        teamSelector1.setAdapter(adapter);

        // Optional: To handle selection events
        teamSelector1.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedTeam = options[position];
            // Handle your selection here
        });

        AutoCompleteTextView teamSelector2 = view.findViewById(R.id.teamSelector2);
        teamSelector2.setAdapter(adapter);
        teamSelector2.setOnItemClickListener((parent, view2, position, id) -> {
            String selectedTeam = options[position];
            // Handle your selection here
        });

    }


}