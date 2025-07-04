package com.example.footyflick;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentController;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class viewpageradapter_runningtournament extends FragmentStateAdapter {
    public viewpageradapter_runningtournament(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0: return new running_tournament_table();
            case 1: return new running_tournament_matches();
            case 2: return new running_tournament_stat();
            default: return new running_tournament_table();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
