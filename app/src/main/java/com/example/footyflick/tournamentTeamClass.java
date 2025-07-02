package com.example.footyflick;

import java.util.ArrayList;

public class tournamentTeamClass {
    public String teamName;
    ArrayList <player> teamPlayers;

    public tournamentTeamClass(String teamName, ArrayList<player> teamPlayers){
        this.teamName = teamName;
        this.teamPlayers = teamPlayers;
    }
}
