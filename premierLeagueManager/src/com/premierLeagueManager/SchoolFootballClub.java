package com.premierLeagueManager;

public class SchoolFootballClub  extends FootballClub{

    private String nameOfTheSchool;

    public SchoolFootballClub(String clubCode, String clubName, String location, int points, int numberOfMatchesPlayed, int goalsScored, int goalsReceived, int wins, int defeats, int draws, String nameOfTheSchool) {
        super(clubCode, clubName, location, points, numberOfMatchesPlayed, goalsScored, goalsReceived, wins, defeats, draws);
        this.nameOfTheSchool = nameOfTheSchool;
    }

    public String getNameOfTheSchool() {
        return nameOfTheSchool;
    }

    public void setNameOfTheSchool(String nameOfTheSchool) {
        this.nameOfTheSchool = nameOfTheSchool;
    }
}
