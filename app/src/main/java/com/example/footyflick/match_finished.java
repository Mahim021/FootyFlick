package com.example.footyflick;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class match_finished extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_finished);

        MaterialButton playerStatButton = findViewById(R.id.player_stat_button);
        playerStatButton.setOnClickListener(v -> {
            Intent intent = new Intent(match_finished.this, player_stat.class);
            startActivity(intent);
        });
    }
}
