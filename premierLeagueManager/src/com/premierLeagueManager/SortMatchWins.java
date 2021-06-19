package com.premierLeagueManager;

import java.util.Comparator;

public class SortMatchWins implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub club01, FootballClub club02) {
        if (club01.getWins() < club02.getWins()){
            return 1;
        }else if (club01.getWins() > club02.getWins()){
            return -1;
        }else
            return 0;
    }
}
