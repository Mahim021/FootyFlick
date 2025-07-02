package com.example.footyflick;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class player_stat extends AppCompatActivity {

    private HashMap<String, Float> ratingMap = new HashMap<>();
    private float highestRating = 0.0f;
    private boolean highestRatedIsFromTeamA = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_stat);

        LinearLayout container = findViewById(R.id.player_rating_container);

        ArrayList<String> teamA = playerstore.teamAPlayers;
        ArrayList<String> teamB = playerstore.teamBPlayers;

        // Step 1: Calculate ratings and determine highest
        for (String player : teamA) {
            float rating = getSampleRating(player); // Replace with calculateRating()
            ratingMap.put(player, rating);
            if (rating > highestRating) {
                highestRating = rating;
                highestRatedIsFromTeamA = true;
            }
        }

        for (String player : teamB) {
            float rating = getSampleRating(player); // Replace with calculateRating()
            ratingMap.put(player, rating);
            if (rating > highestRating) {
                highestRating = rating;
                highestRatedIsFromTeamA = false;
            }
        }

        int maxPlayers = Math.max(teamA.size(), teamB.size());

        for (int i = 0; i < maxPlayers; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setPadding(20, 10, 20, 10);
            row.setBaselineAligned(false);

            // Alternate row background for readability
            if (i % 2 == 0) {
                row.setBackgroundColor(Color.BLACK);
            } else {
                row.setBackgroundColor(Color.DKGRAY);
            }

            // Common layout params for flexible items
            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            itemParams.gravity = Gravity.CENTER_VERTICAL;

            // ---------- TEAM A ----------
            String nameA = i < teamA.size() ? teamA.get(i) : "";
            float ratingA = ratingMap.getOrDefault(nameA, 0f);
            boolean showStarA = (ratingA == highestRating && highestRatedIsFromTeamA);

            // Name A
            TextView nameAView = new TextView(this);
            nameAView.setLayoutParams(itemParams);
            nameAView.setText(nameA);
            nameAView.setTextColor(getResources().getColor(R.color.white));
            nameAView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);

            // Star A
            ImageView starA = new ImageView(this);
            LinearLayout.LayoutParams starParamsA = new LinearLayout.LayoutParams(dpToPx(16), dpToPx(16));
            starParamsA.setMargins(dpToPx(4), 0, dpToPx(4), 0);
            starParamsA.gravity = Gravity.CENTER_VERTICAL;
            starA.setLayoutParams(starParamsA);
            starA.setImageResource(showStarA ? R.drawable.ic_star : android.R.color.transparent);

            // Rating A
            TextView ratingAView = new TextView(this);
            ratingAView.setLayoutParams(itemParams);
            ratingAView.setText(String.format("%.1f", ratingA));
            ratingAView.setTextColor(getRatingColor(ratingA));
            ratingAView.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);

            // ---------- SPACER ----------
            View spacer = new View(this);
            LinearLayout.LayoutParams spacerParams = new LinearLayout.LayoutParams(dpToPx(12), LinearLayout.LayoutParams.MATCH_PARENT);
            spacer.setLayoutParams(spacerParams);

            // ---------- TEAM B ----------
            String nameB = i < teamB.size() ? teamB.get(i) : "";
            float ratingB = ratingMap.getOrDefault(nameB, 0f);
            boolean showStarB = (ratingB == highestRating && !highestRatedIsFromTeamA);

            // Rating B
            TextView ratingBView = new TextView(this);
            ratingBView.setLayoutParams(itemParams);
            ratingBView.setText(String.format("%.1f", ratingB));
            ratingBView.setTextColor(getRatingColor(ratingB));
            ratingBView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);

            // Star B
            ImageView starB = new ImageView(this);
            LinearLayout.LayoutParams starParamsB = new LinearLayout.LayoutParams(dpToPx(16), dpToPx(16));
            starParamsB.setMargins(dpToPx(4), 0, dpToPx(4), 0);
            starParamsB.gravity = Gravity.CENTER_VERTICAL;
            starB.setLayoutParams(starParamsB);
            starB.setImageResource(showStarB ? R.drawable.ic_star : android.R.color.transparent);

            // Name B
            TextView nameBView = new TextView(this);
            nameBView.setLayoutParams(itemParams);
            nameBView.setText(nameB);
            nameBView.setTextColor(getResources().getColor(R.color.white));
            nameBView.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);

            // Add views to row
            row.addView(nameAView);
            row.addView(starA);
            row.addView(ratingAView);
            row.addView(spacer);
            row.addView(ratingBView);
            row.addView(starB);
            row.addView(nameBView);

            container.addView(row);
        }
    }

    // Sample rating (replace with real stat logic)
    private float getSampleRating(String playerName) {
        return (float) (Math.random() * 5 + 5); // 5.0 to 10.0
    }

    // Convert dp to px
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    // Color based on rating value
    private int getRatingColor(float rating) {
        if (rating >= 5.0f && rating <= 6.0f) {
            return Color.parseColor("#FF4444"); // Bright red
        } else if (rating > 6.0f && rating <= 8.5f) {
            return Color.parseColor("#00FF00"); // Bright green
        } else if (rating > 8.5f && rating <= 10.0f) {
            return Color.parseColor("#3399FF"); // Bright blue
        } else {
            return Color.WHITE;
        }
    }

    // Real logic (placeholder)
    /*
    private float calculateRating(String playerName) {
        int goals = getGoals(playerName);
        int assists = getAssists(playerName);
        int shots = getShots(playerName);

        float rating = goals * 2.0f + assists * 1.5f + shots * 0.5f;
        return Math.min(10.0f, rating);
    }
    */
}
