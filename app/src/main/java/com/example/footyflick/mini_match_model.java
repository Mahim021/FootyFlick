package com.example.footyflick;

public class mini_match_model {
    public String team1;
    public String team2;
    public String team1_score;
    public String team2_score;
    public String date;
    public boolean done = false;

    public mini_match_model (String team1, String team2, String team1_score, String team2_score, String date, boolean done) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1_score = team1_score;
        this.team2_score = team2_score;
        this.date = date;
        this.done = done;
    }
}
