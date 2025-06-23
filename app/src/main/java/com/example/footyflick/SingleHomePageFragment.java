package com.example.footyflick;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

        // Initialize your views and logic here using `view.findViewById(...)`
        Button myButton = view.findViewById(R.id.createMatchButton);
        myButton.setOnClickListener(v -> {
            // Create an instance of your target fragment
            Fragment singleFragment = new SingleFragment();

            // Perform the fragment transaction
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, singleFragment) // replace with your container ID
                    .addToBackStack(null) // Optional: adds to back stack so you can press back
                    .commit();
        });

    }
}
