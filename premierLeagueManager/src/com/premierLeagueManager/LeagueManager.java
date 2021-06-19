package com.premierLeagueManager;

import java.io.File;
import java.io.IOException;

public interface LeagueManager {

    void creatNewFootballClub(SportsClub footballClub);

    void deleteFootballClub(SportsClub footballClub);

    void displayStatistics(SportsClub footballClub);

    void displayPremierLeagueTable();

    void addPlayedMatch(FootballClub team01, FootballClub team02);

    void saveData(File footballClubs, File playedMatches) throws IOException;

    void loadData(File footballClubs, File playedMatches) throws IOException, ClassNotFoundException;

    //GUI Components

    void displayClubsTable();

    void sortByScoredGoals();

    void sortByNoOfWins();

    void generateRandomMatch();

    void sortByPlayedDate();

    void searchMatch();
}
