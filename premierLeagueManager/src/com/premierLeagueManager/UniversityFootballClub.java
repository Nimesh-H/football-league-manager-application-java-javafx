package com.premierLeagueManager;

public class UniversityFootballClub extends FootballClub {

    private String nameOfTheUniversity;

    public UniversityFootballClub(String clubCode, String clubName, String location, int points, int numberOfMatchesPlayed, int goalsScored, int goalsReceived, int wins, int defeats, int draws, String nameOfTheUniversity) {
        super(clubCode, clubName, location, points, numberOfMatchesPlayed, goalsScored, goalsReceived, wins, defeats, draws);
        this.nameOfTheUniversity = nameOfTheUniversity;
    }

    public String getNameOfTheUniversity() {
        return nameOfTheUniversity;
    }

    public void setNameOfTheUniversity(String nameOfTheUniversity) {
        this.nameOfTheUniversity = nameOfTheUniversity;
    }
}
