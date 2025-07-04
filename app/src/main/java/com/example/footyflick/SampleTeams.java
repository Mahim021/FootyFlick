package com.example.footyflick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleTeams {

    // Returns a sample list of team names
    public static List<String> getTeams() {
        return new ArrayList<>(Arrays.asList(
                "Falcon FC",
                "Thunder Wolves",
                "Red Dragons",
                "Sky Hawks",
                "Iron Giants",
                "Shadow Ninjas",
                "Crimson Blades",
                "Blue Whales"
        ));
    }

    // You can also get a smaller or custom number of teams
    public static List<String> getTeams(int count) {
        List<String> allTeams = getTeams();
        return allTeams.subList(0, Math.min(count, allTeams.size()));
    }
}
