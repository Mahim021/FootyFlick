package com.example.footyflick;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //onclick for dbbtn
        view.findViewById(R.id.dbbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SupabaseApi api = RetrofitClient.getClient().create(SupabaseApi.class);

                Call<List<datamodel_user>> call = api.getUsers();

                call.enqueue(new Callback<List<datamodel_user>>() {
                    @Override
                    public void onResponse(Call<List<datamodel_user>> call, Response<List<datamodel_user>> response) {
                        if (response.isSuccessful()) {
                            List<datamodel_user> users = response.body();
                            if (users != null) {
                                for (datamodel_user user : users) {
                                    Toast.makeText(requireContext(), "Username: " + user.getUsername(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(requireContext(), "No users found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(requireContext(), "Failed to retrieve users: " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<datamodel_user>> call, Throwable t) {
                        Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
