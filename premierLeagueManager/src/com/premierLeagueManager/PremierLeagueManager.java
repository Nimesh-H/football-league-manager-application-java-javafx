package com.premierLeagueManager;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PremierLeagueManager implements LeagueManager {

    private static PremierLeagueManager premierLeague;

    public static final int MAX_CLUBS = 20;

    private List<FootballClub> clubsList = new ArrayList<>();
    private List<FootballMatch> matchList = new ArrayList<>();

    private PremierLeagueManager(){}

    public static PremierLeagueManager getInstance(){
        if (premierLeague == null){
            synchronized (PremierLeagueManager.class){
                if (premierLeague == null){
                    premierLeague = new PremierLeagueManager();
                }
            }
        }
        return premierLeague;
    }

    public List<FootballClub> getClubsList() {
        return clubsList;
    }

    public List<FootballMatch> getMatchList() {
        return matchList;
    }

    @Override
    public void creatNewFootballClub(SportsClub footballClub) {

        boolean checkClubPresence = false;

        if (clubsList.size() == MAX_CLUBS){
            System.out.println("Maximum Number Of Clubs Have Been Added");
        }

        for (FootballClub club : clubsList){
            if (footballClub.equals(club)){
                System.out.println("Club is Already In The Premier League");
                checkClubPresence = true;
                break;
            }
        }

        if (!checkClubPresence) {
            clubsList.add((FootballClub) footballClub);
        }

    }

    @Override
    public void deleteFootballClub(SportsClub footballClub) {

        boolean checkClubPresence = false;

        for (FootballClub clubToDelete : clubsList){

            if (clubToDelete.getClubCode().equals(footballClub.getClubCode()) && clubToDelete.getClubName().equals(footballClub.getClubName()) && clubToDelete.getLocation().equals(footballClub.getLocation())){
                clubsList.remove(clubToDelete);

                System.out.println("Club '" + footballClub.getClubName() + "' Is Deleted");
                checkClubPresence = true;
                break;
            }
        }

        if (!checkClubPresence) {
            System.out.println("Please Try Again. Given Details Are Incorrect.");

        }

    }

    @Override
    public void displayStatistics(SportsClub footballClub) {

        boolean checkClubPresence = false;

                for (FootballClub clubStatistics : clubsList){
                    if (clubStatistics.getClubCode().equals(footballClub.getClubCode()) && clubStatistics.getClubName().equals(footballClub.getClubName()) && clubStatistics.getLocation().equals(footballClub.getLocation())){
                        System.out.println();
                        System.out.println("Football Club Name              : "+clubStatistics.getClubName());
                        System.out.println("------------------Club Statistics----------------");
                        System.out.println("Number Of Matches Played        : "+clubStatistics.getNumberOfMatchesPlayed());
                        System.out.println("Total Number of Points          : "+clubStatistics.getPoints());
                        System.out.println("Number Of Goals Scored          : "+clubStatistics.getGoalsScored());
                        System.out.println("Number Of Goals Received        : "+clubStatistics.getGoalsReceived());
                        System.out.println("Number Of Wins By The Club      : "+clubStatistics.getWins());
                        System.out.println("Number Of Defeats By The Club   : "+clubStatistics.getDefeats());
                        System.out.println("Number Of Draws By The Club     : "+clubStatistics.getDraws());
                        System.out.println("-------------------------------------------------");
                        checkClubPresence = true;
                        break;

                    }
                }
        if (!checkClubPresence) {
            System.out.println();
            System.out.println("Please Try Again. Given Details Are Incorrect.");

        }
    }


    @Override
    public void displayPremierLeagueTable() {

        if (clubsList.isEmpty()){
            System.out.println("No Clubs In The Premier League");
        }else {

            Collections.sort(clubsList);
            System.out.println();
            System.out.printf("| %20s | %15s | %15s | %15s | %15s | %15s |%n", "Club Name    ","Club Code","Scored Goals","Received Goals","Goal Difference","Total Points");
            System.out.println();
            for (FootballClub displayClubs : clubsList){

                System.out.format("| %20s | %15s | %15s | %15s | %15s | %15s |%n",displayClubs.getClubName(),displayClubs.getClubCode(),displayClubs.getGoalsScored(),displayClubs.getGoalsReceived(),(displayClubs.getGoalsScored() - displayClubs.getGoalsReceived()),displayClubs.getPoints());
            }

            System.out.println();

        }


    }

    @Override
    public void addPlayedMatch(FootballClub team01, FootballClub team02) {

        LocalDateTime dateTime = LocalDateTime.now();

        FootballClub club01 = null;
        FootballClub club02 = null;

        while (true){


            if (team01.getGoalsScored() > team02.getGoalsScored()){

                for (FootballClub footballClub : clubsList){

                    if (team01.getClubCode().equals(footballClub.getClubCode()) && team01.getClubName().equals(footballClub.getClubName()) && team01.getLocation().equals(footballClub.getLocation())){
                        club01 = footballClub;
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed()  + 1);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team01.getGoalsScored());
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team02.getGoalsScored());
                        footballClub.setWins(footballClub.getWins() +1);
                        footballClub.setPoints(footballClub.getPoints() + team01.getPoints());

                    }

                    if (team02.getClubCode().equals(footballClub.getClubCode()) && team02.getClubName().equals(footballClub.getClubName()) && team02.getLocation().equals(footballClub.getLocation())){
                        club02 = footballClub;
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed()  + 1);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team02.getGoalsScored());
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team01.getGoalsScored());
                        footballClub.setDefeats(footballClub.getDefeats() + 1);
                        footballClub.setPoints(footballClub.getPoints() + team02.getPoints());

                    }

                }

            }else if (team01.getGoalsScored() < team02.getGoalsScored()){

                for (FootballClub footballClub : clubsList) {
                    if (team02.getClubCode().equals(footballClub.getClubCode()) && team02.getClubName().equals(footballClub.getClubName()) && team02.getLocation().equals(footballClub.getLocation())){
                        club02 = footballClub;
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() + 1);
                        footballClub.setWins(footballClub.getWins() + 1);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team02.getGoalsScored());
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team01.getGoalsScored());
                        footballClub.setPoints(footballClub.getPoints() + team02.getPoints());

                    }

                    if (team01.getClubCode().equals(footballClub.getClubCode()) && team01.getClubName().equals(footballClub.getClubName()) && team01.getLocation().equals(footballClub.getLocation())){

                        club01 = footballClub;
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() + 1);
                        footballClub.setDefeats(footballClub.getDefeats() + 1);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team01.getGoalsScored());
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team02.getGoalsScored());
                        footballClub.setPoints(footballClub.getPoints() + team01.getPoints());


                    }
                }
            }else {
                for (FootballClub footballClub : clubsList){

                    if (team01.getClubCode().equals(footballClub.getClubCode()) && team01.getClubName().equals(footballClub.getClubName()) && team01.getLocation().equals(footballClub.getLocation())){
                        club01 = footballClub;
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() + 1);
                        footballClub.setDraws(footballClub.getDraws() + 1);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team01.getGoalsScored());
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team02.getGoalsScored());
                        footballClub.setPoints(footballClub.getPoints() + team01.getPoints());

                    }
                    if (team02.getClubCode().equals(footballClub.getClubCode()) && team02.getClubName().equals(footballClub.getClubName()) && team02.getLocation().equals(footballClub.getLocation())){
                        club02 = footballClub;
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() + 1);
                        footballClub.setDraws(footballClub.getDraws() + 1);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team02.getGoalsScored());
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team01.getGoalsScored());
                        footballClub.setPoints(footballClub.getPoints() + team02.getPoints());

                    }
                }
            }

            FootballMatch match = new FootballMatch(dateTime,club01,club02, team01.getGoalsScored(), team02.getGoalsScored(), team01.getPoints(), team02.getPoints());

            for (FootballClub checkClub : clubsList){
                if (clubsList.contains(club01)&& clubsList.contains(club02)){
                    System.out.println();
                    System.out.println("The Entered Match Has Been Added To The Data Records!");

                    matchList.add(match);
                    break;
                }else {
                    System.out.println();
                    System.out.println("Entered Club Details are Invalid. Please Check Again!");
                    System.out.println();
                    break;
                }
            }

            break;

        }

    }

    @Override
    public void saveData(File footballClubs, File playedMatches) throws IOException {

        FileOutputStream fileOutPut01 = new FileOutputStream(footballClubs);
        FileOutputStream fileOutPut02 = new FileOutputStream(playedMatches);

        ObjectOutputStream objectOutPut01 =  new ObjectOutputStream(fileOutPut01);
        ObjectOutputStream objectOutPut02 =  new ObjectOutputStream(fileOutPut02);

        for (FootballClub footballClub :clubsList ){
            objectOutPut01.writeObject(footballClub);
        }

        for (FootballMatch match : matchList){

            objectOutPut02.writeObject(match);
        }

        objectOutPut01.flush();
        objectOutPut02.flush();
        fileOutPut01.close();
        objectOutPut01.close();
        fileOutPut02.close();
        objectOutPut02.close();

        System.out.println();
        System.out.println("All Data Have Been saved To The File From The Application");
        System.out.println();


    }

    @Override
    public void loadData(File footballClubs, File playedMatches) throws IOException, ClassNotFoundException {

        if ((footballClubs.exists() && footballClubs.length() != 0) || (playedMatches.exists() && playedMatches.length() != 0)) {

            FileInputStream fileInput01 = new FileInputStream(footballClubs);
            ObjectInputStream objectInput01 = new ObjectInputStream(fileInput01);

            FileInputStream fileInput02 = new FileInputStream(playedMatches);
            ObjectInputStream objectInput02 = new ObjectInputStream(fileInput02);


            for (; ; ) {
                try {
                    FootballClub footballClub = (FootballClub) objectInput01.readObject();
                    clubsList.add(footballClub);
                } catch (EOFException e) {
                    break;
                }

            }
            for (; ; ) {
                try {
                    FootballMatch match = (FootballMatch) objectInput02.readObject();
                    matchList.add(match);
                } catch (EOFException e) {
                    break;
                }
            }
            fileInput01.close();
            objectInput01.close();
            fileInput02.close();
            objectInput02.close();

            System.out.println();
            System.out.println("All Data Have Been Load From The File To The Application");
            System.out.println();

        }
    }

    @Override
    public void displayClubsTable() {

        if (clubsList.isEmpty()){

            Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
            emptyAlert.setContentText("No Clubs In The Premier League");
            emptyAlert.show();
        }else {
            Stage mainStage = new Stage();
            mainStage.setTitle("Premier League Clubs Table");

            Label headerLabel = new Label("PREMIER LEAGUE POINTS TABLE");
            headerLabel.setStyle("-fx-text-fill:white;" + "-fx-font-weight: bold;" + "-fx-font-size: 30;");
            headerLabel.setLayoutX(500);
            headerLabel.setLayoutY(20);

            BorderPane mainBorderPane = new BorderPane();
            GridPane mainGridPane = new GridPane();

            //Labels for Display Statistics
            Label clubNameColumn = new Label("      Name Of The Club");
            clubNameColumn.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");
            Label clubLocationColumn = new Label("  Location");
            clubLocationColumn.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");
            Label goalsScoredColumn = new Label("   Goals Scored");
            goalsScoredColumn.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");
            Label goalsReceivedColumn = new Label("   Goals Received");
            goalsReceivedColumn.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");
            Label clubWinsColumn = new Label("    Club wins");
            clubWinsColumn.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");
            Label clubDefeatsColumn = new Label("   Club Defeats");
            clubDefeatsColumn.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");
            Label clubDrawsColumn = new Label("     Club Draws");
            clubDrawsColumn.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");
            Label clubPointsColumn = new Label("    Total Points");
            clubPointsColumn.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");
            Label matchesPlayed = new Label("  Matches Played");
            matchesPlayed.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");

            Button optionClose = new Button("   B A C K   ");
            optionClose.setLayoutX(20);
            optionClose.setLayoutY(40);


            Pane topPane = new Pane();
            topPane.setStyle("-fx-background-color: #3d195b;");

            Pane bottomPane = new Pane();
            bottomPane.setStyle("-fx-background-color:#3d195b");

            topPane.setPadding(new Insets(20));
            bottomPane.setPadding(new Insets(20));
            mainGridPane.setPadding(new Insets(20));


            ColumnConstraints column01 = new ColumnConstraints();
            column01.setPercentWidth(15);
            ColumnConstraints column02 = new ColumnConstraints();
            column02.setPercentWidth(10);
            ColumnConstraints column03 = new ColumnConstraints();
            column03.setPercentWidth(10);
            ColumnConstraints column04 = new ColumnConstraints();
            column04.setPercentWidth(10);
            ColumnConstraints column05 = new ColumnConstraints();
            column05.setPercentWidth(10);
            ColumnConstraints column06 = new ColumnConstraints();
            column06.setPercentWidth(10);
            ColumnConstraints column07 = new ColumnConstraints();
            column07.setPercentWidth(10);
            ColumnConstraints column08 = new ColumnConstraints();
            column08.setPercentWidth(12);
            ColumnConstraints column09 = new ColumnConstraints();
            column09.setPercentWidth(12);

            mainGridPane.getColumnConstraints().addAll(column01,column02,column03,column04,column05,column06,column07,column08,column09);
            mainGridPane.setStyle("-fx-grid-lines-visible:true"); //To Visible The Table Lines

            VBox value01 = new VBox();
            value01.setStyle("-fx-font-size: 12;");
            VBox value02 = new VBox();
            value02.setStyle("-fx-font-size: 12;");
            VBox value03 = new VBox();
            value03.setStyle("-fx-font-size: 12;");
            VBox value04 = new VBox();
            value04.setStyle("-fx-font-size: 12;");
            VBox value05 = new VBox();
            value05.setStyle("-fx-font-size: 12;");
            VBox value06 = new VBox();
            value06.setStyle("-fx-font-size: 12;");
            VBox value07 = new VBox();
            value07.setStyle("-fx-font-size: 12;");
            VBox value08 = new VBox();
            value08.setStyle("-fx-font-size: 12;");
            VBox value09 = new VBox();
            value09.setStyle("-fx-font-size: 12;");

            mainGridPane.add(clubNameColumn,0,0);
            mainGridPane.add(clubLocationColumn,1,0);
            mainGridPane.add(goalsScoredColumn,2,0);
            mainGridPane.add(goalsReceivedColumn,3,0);
            mainGridPane.add(clubWinsColumn,4,0);
            mainGridPane.add(clubDefeatsColumn,5,0);
            mainGridPane.add(clubDrawsColumn,6,0);
            mainGridPane.add(clubPointsColumn,7,0);
            mainGridPane.add(matchesPlayed,8,0);

            mainGridPane.add(value01,0,1);
            mainGridPane.add(value02,1,1);
            mainGridPane.add(value03,2,1);
            mainGridPane.add(value04,3,1);
            mainGridPane.add(value05,4,1);
            mainGridPane.add(value06,5,1);
            mainGridPane.add(value07,6,1);
            mainGridPane.add(value08,7,1);
            mainGridPane.add(value09,8,1);

            mainBorderPane.setTop(topPane);
            mainBorderPane.setCenter(mainGridPane);
            mainBorderPane.setBottom(bottomPane);

            value01.setPrefHeight(600);

            value01.setPadding(new Insets(20));
            value02.setPadding(new Insets(20));
            value03.setPadding(new Insets(20));
            value04.setPadding(new Insets(20));
            value05.setPadding(new Insets(20));
            value06.setPadding(new Insets(20));
            value07.setPadding(new Insets(20));
            value08.setPadding(new Insets(20));
            value09.setPadding(new Insets(20));

            topPane.getChildren().add(headerLabel);
            bottomPane.getChildren().add(optionClose);

            for (FootballClub footballClub : clubsList){
                Label clubName = new Label(footballClub.getClubName().toUpperCase());
                Label clubLocation = new Label(footballClub.getLocation().toUpperCase());
                Label goalsScored = new Label(Integer.toString(footballClub.getGoalsScored()));
                Label goalsReceived = new Label(Integer.toString(footballClub.getGoalsReceived()));
                Label clubWins = new Label(Integer.toString(footballClub.getWins()));
                Label clubDefeats = new Label(Integer.toString(footballClub.getDefeats()));
                Label clubDraws = new Label(Integer.toString(footballClub.getDraws()));
                Label clubPoints = new Label(Integer.toString(footballClub.getPoints()));
                Label totalMatchesPlayed = new Label(Integer.toString(footballClub.getNumberOfMatchesPlayed()));

                value01.getChildren().add(clubName);
                value02.getChildren().add(clubLocation);
                value03.getChildren().add(goalsScored);
                value04.getChildren().add(goalsReceived);
                value05.getChildren().add(clubWins);
                value06.getChildren().add(clubDefeats);
                value07.getChildren().add(clubDraws);
                value08.getChildren().add(clubPoints);
                value09.getChildren().add(totalMatchesPlayed);

            }
            optionClose.setOnAction(event -> {
                mainStage.close();
            });

            Scene mainScene = new Scene(mainBorderPane,1500,650);
            mainStage.setScene(mainScene);
            mainStage.setResizable(false);
            mainStage.showAndWait();



        }

    }

    @Override
    public void sortByScoredGoals() {

        if (clubsList.isEmpty()){
            Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
            emptyAlert.setContentText("No Clubs In The Premier League");
            emptyAlert.show();
        }else {
            Collections.sort(clubsList, new SortScoredGoals());
            Alert informAlert = new Alert(Alert.AlertType.INFORMATION);
            informAlert.setContentText("Premier League Table Successfully Sorted According To The Scored Goals");
            informAlert.show();
        }

    }

    @Override
    public void sortByNoOfWins() {

        if (clubsList.isEmpty()){
            Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
            emptyAlert.setContentText("No Clubs In The Premier League");
            emptyAlert.show();
        }else {
            Collections.sort(clubsList, new SortMatchWins());
            Alert informAlert = new Alert(Alert.AlertType.INFORMATION);
            informAlert.setContentText("Premier League Table Successfully Sorted According To The Club Wins");
            informAlert.show();
        }

    }

    @Override
    public void generateRandomMatch() {

        LocalDateTime dateTime = LocalDateTime.now();

        if (clubsList.isEmpty()){

            Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
            emptyAlert.setContentText("No Clubs In The Premier League");
            emptyAlert.show();
        }else {
            FootballClub randomTeam01;
            FootballClub randomTeam02;

            Random randomGenerator = new Random();

            randomTeam01 = clubsList.get(randomGenerator.nextInt(clubsList.size()));

            while (true){
                randomTeam02 = clubsList.get(randomGenerator.nextInt(clubsList.size()));
                if (randomTeam02.equals(randomTeam01)){
                    continue;
                }
                break;
            }
            Stage mainStage = new Stage();
            mainStage.setTitle("Random Matchday");

            BorderPane mainBorderPane = new BorderPane();
            GridPane mainGridPane = new GridPane();

            Pane topPane = new Pane();
            topPane.setStyle("-fx-background-color: #3d195b;");

            Pane bottomPane = new Pane();
            bottomPane.setStyle("-fx-background-color: #3d195b;");

            topPane.setPadding(new Insets(20));
            bottomPane.setPadding(new Insets(20));

            mainBorderPane.setTop(topPane);
            mainBorderPane.setCenter(mainGridPane);
            mainBorderPane.setBottom(bottomPane);

            VBox team01Values = new VBox();
            VBox team02Values = new VBox();
            team01Values.setPadding(new Insets(20));
            team02Values.setPadding(new Insets(20));
            team01Values.setPrefHeight(250);

            Label topiclable = new Label("Random Match");
            topiclable.setStyle("-fx-font-weight: bold;" + "-fx-text-fill:white;" +  "-fx-font-size: 30;");
            topiclable.setLayoutX(300);
            topiclable.setLayoutY(20);

            Label playingClub01 = new Label(randomTeam01.getClubName().toUpperCase());
            playingClub01.setPadding(new Insets(10,0,0,40));
            playingClub01.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 25;");

            Label playingClub02 = new Label(randomTeam02.getClubName().toUpperCase());
            playingClub02.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 25;");
            playingClub02.setPadding(new Insets(10,0,0,40));

            Label versus = new Label("VS");
            versus.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 40;");
            versus.setPadding(new Insets(0,20,50,0));

            Button optionClose = new Button("   B A C K   ");
            optionClose.setLayoutX(20);
            optionClose.setLayoutY(40);

            ColumnConstraints team01Column = new ColumnConstraints();
            team01Column.setPercentWidth(65);
            ColumnConstraints versusColumn = new ColumnConstraints();
            versusColumn.setPercentWidth(40);
            ColumnConstraints team02Column = new ColumnConstraints();
            team02Column.setPercentWidth(30);

            mainGridPane.getColumnConstraints().addAll(team01Column,versusColumn,team02Column);


            mainGridPane.add(playingClub01,0,0);
            mainGridPane.add(versus,1,1);
            mainGridPane.add(playingClub02,2,0);

            mainGridPane.add(team01Values,0,1);
            mainGridPane.add(team02Values,2,1);

            topPane.getChildren().add(topiclable);
            bottomPane.getChildren().add(optionClose);

            int team01Points = 0;
            int team02Points = 0;

            int team01Score = randomGenerator.nextInt(15);
            int team02Score = randomGenerator.nextInt(15);

            if (team01Score > team02Score){          //According To The PremierLeague Teams receive three points for a win and one point for a draw
                team01Points = 3;
            }else if (team02Score > team01Score){
                team02Points = 3;
            }else {
                team01Points = 1;
                team02Points = 1;
            }

            Label club01Score = new Label("Score: "+ team01Score);
            club01Score.setStyle("-fx-font-size: 15;");
            Label club02Score = new Label("Score: "+ team02Score);
            club02Score.setStyle("-fx-font-size: 15;");

            Label club01Points = new Label("Points: "+ team01Points);
            club01Points.setStyle("-fx-font-size: 15;");
            Label club02Points = new Label("Points: "+ team02Points);
            club02Points.setStyle("-fx-font-size: 15;");

            team01Values.getChildren().add(club01Score);
            team01Values.getChildren().add(club01Points);

            team02Values.getChildren().add(club02Score);
            team02Values.getChildren().add(club02Points);

            if (team01Score > team02Score){
                for (FootballClub footballClub : clubsList){
                    if (footballClub.getClubName().equals(randomTeam01.getClubName())){
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() +1);
                        footballClub.setPoints(footballClub.getPoints() + team01Points);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team01Score);
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team02Score);
                        footballClub.setWins(footballClub.getWins() + 1);
                    }
                    if (footballClub.getClubName().equals(randomTeam02.getClubName())){
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() +1);
                        footballClub.setPoints(footballClub.getPoints() + team02Points);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team02Score);
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team01Score);
                        footballClub.setDefeats(footballClub.getDraws() +1);

                    }
                }

            }else if (team02Score > team01Score){
                for (FootballClub footballClub : clubsList){
                    if (footballClub.getClubName().equals(randomTeam02.getClubName())){
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() +1);
                        footballClub.setPoints(footballClub.getPoints() + team02Points);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team02Score);
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team01Score);
                        footballClub.setWins(footballClub.getWins() + 1);
                    }
                    if (footballClub.getClubName().equals(randomTeam01.getClubName())){
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() +1);
                        footballClub.setPoints(footballClub.getPoints() + team01Points);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team01Score);
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team02Score);
                        footballClub.setDefeats(footballClub.getDraws() +1);
                    }
                }

            }else {
                for (FootballClub footballClub : clubsList){
                    if (footballClub.getClubName().equals(randomTeam02.getClubName())){
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() +1);
                        footballClub.setPoints(footballClub.getPoints() + team01Points);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team01Score);
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team02Score);
                        footballClub.setDraws(footballClub.getDraws() +1);
                    }
                    if (footballClub.getClubName().equals(randomTeam01.getClubName())){
                        footballClub.setNumberOfMatchesPlayed(footballClub.getNumberOfMatchesPlayed() +1);
                        footballClub.setPoints(footballClub.getPoints() + team02Points);
                        footballClub.setGoalsScored(footballClub.getGoalsScored() + team02Score);
                        footballClub.setGoalsReceived(footballClub.getGoalsReceived() + team01Score);
                        footballClub.setDraws(footballClub.getDraws() +1);
                    }
                }
            }

            FootballMatch match = new FootballMatch(dateTime,randomTeam01,randomTeam02,team01Score,team02Score,team01Points,team02Points);
            matchList.add(match);

            optionClose.setOnAction(event -> {
                mainStage.close();
            });

            Scene mainScene = new Scene(mainBorderPane,800,350);
            mainStage.setScene(mainScene);
            mainStage.setResizable(false);
            mainStage.showAndWait();
        }

    }

    @Override
    public void sortByPlayedDate() {
        if (matchList.isEmpty()){

            Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
            emptyAlert.setContentText("No Matches Have Played In The Premier League");
            emptyAlert.show();
        }else {

            Stage mainStage = new Stage();
            mainStage.setTitle("MATCHED PLAYED");

            Label headerLabel = new Label("PLAYED MATCHES");
            headerLabel.setStyle("-fx-text-fill:white;" + "-fx-font-weight: bold;" + "-fx-font-size: 30;");
            headerLabel.setLayoutX(400);
            headerLabel.setLayoutY(20);

            Button optionClose = new Button("   B A C K   ");
            optionClose.setLayoutX(20);
            optionClose.setLayoutY(40);

            Label dateLabel = new Label("   DATE PLAYED");
            dateLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");
            Label playedMatchLabel = new Label("  PLAYED MATCH");
            playedMatchLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");
            Label scoreLabel = new Label("  MATCH SCORE");
            scoreLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");


            BorderPane mainBorderPane = new BorderPane();

            GridPane mainGridPane = new GridPane();
            mainGridPane.setPadding(new Insets(20));

            Pane topPane = new Pane();
            topPane.setStyle("-fx-background-color: #3d195b;");
            topPane.setPadding(new Insets(20));
            topPane.getChildren().add(headerLabel);

            Pane bottomPane = new Pane();
            bottomPane.setStyle("-fx-background-color:#3d195b");
            bottomPane.setPadding(new Insets(20));
            bottomPane.getChildren().add(optionClose);

            mainBorderPane.setTop(topPane);
            mainBorderPane.setCenter(mainGridPane);
            mainBorderPane.setBottom(bottomPane);

            //Creating The Table
            ColumnConstraints dateColumn = new ColumnConstraints();
            dateColumn.setPercentWidth(25);
            ColumnConstraints matchColumn = new ColumnConstraints();
            matchColumn.setPercentWidth(30);
            ColumnConstraints matchScoreColumn = new ColumnConstraints();
            matchScoreColumn.setPercentWidth(45);

            mainGridPane.getColumnConstraints().addAll(dateColumn,matchColumn,matchScoreColumn);
            mainGridPane.setStyle("-fx-grid-lines-visible:true"); //To Visible The Table Lines

            VBox dateValue = new VBox();
            dateValue.setStyle("-fx-font-size: 12;");
            VBox playedMatchValue = new VBox();
            playedMatchValue.setStyle("-fx-font-size: 12;");
            VBox matchScoreValue = new VBox();
            matchScoreValue.setStyle("-fx-font-size: 12;");

            dateValue.setPadding(new Insets(20));
            playedMatchValue.setPadding(new Insets(20));
            matchScoreValue.setPadding(new Insets(20));

            dateValue.setPrefHeight(600);

            mainGridPane.add(dateLabel,0,0);
            mainGridPane.add(playedMatchLabel,1,0);
            mainGridPane.add(scoreLabel,2,0);
            mainGridPane.add(dateValue,0,1);
            mainGridPane.add(playedMatchValue,1,1);
            mainGridPane.add(matchScoreValue,2,1);

            Collections.sort(matchList);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            for (FootballMatch playedMatch : matchList){
                Label matchDate = new Label(dateFormatter.format(playedMatch.getPlayingDate()));
                Label playingTeams = new Label(playedMatch.getPlayingClub01().getClubName().toUpperCase() + " - " + playedMatch.getPlayingClub02().getClubName().toUpperCase());
                Label playedMatchScore = new Label(playedMatch.getPlayingClub01().getClubName().toUpperCase() +"  " + playedMatch.getScore01() + " - " + playedMatch.getScore02() + "  " + playedMatch.getPlayingClub02().getClubName().toUpperCase());

                dateValue.getChildren().add(matchDate);
                playedMatchValue.getChildren().add(playingTeams);
                matchScoreValue.getChildren().add(playedMatchScore);
            }

            optionClose.setOnAction(event -> {
                mainStage.close();
            });
            Scene mainScene = new Scene(mainBorderPane,1000,1000);
            mainStage.setScene(mainScene);
            mainStage.setResizable(false);
            mainStage.showAndWait();


        }

    }

    @Override
    public void searchMatch() {

        Stage topStage = new Stage();
        topStage.setTitle("Search Match");
        topStage.setResizable(false);

        Label inputDateLabel = new Label("Enter The Date");
        inputDateLabel.setStyle("-fx-text-fill:white;" + "-fx-font-weight: bold;" + "-fx-font-size: 15;");
        inputDateLabel.setLayoutX(50);
        inputDateLabel.setLayoutY(90);

        TextField inputDate = new TextField();
        inputDate.setLayoutX(175);
        inputDate.setLayoutY(90);

        Label dateFormatLabel = new Label("(YYYY/MM/DD)");
        dateFormatLabel.setStyle("-fx-text-fill:white;" + "-fx-font-weight: bold;" + "-fx-font-size: 12;");
        dateFormatLabel.setLayoutX(50);
        dateFormatLabel.setLayoutY(110);

        Button optionSearch = new Button("S E A R C H");
        optionSearch.setLayoutX(350);
        optionSearch.setLayoutY(90);

        Button topOptionClose = new Button("  B A C K  ");
        topOptionClose.setLayoutX(225);
        topOptionClose.setLayoutY(250);

        BorderPane topBorderPane = new BorderPane();

        Pane topSectionPane = new Pane();
        topSectionPane.setStyle("-fx-background-color: #3d195b;");
        topSectionPane.setPadding(new Insets(20));
        topSectionPane.getChildren().add(inputDate);
        topSectionPane.getChildren().add(topOptionClose);
        topSectionPane.getChildren().add(optionSearch);
        topSectionPane.getChildren().add(inputDateLabel);
        topSectionPane.getChildren().add(dateFormatLabel);

        topBorderPane.setTop(topSectionPane);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        optionSearch.setOnAction(event -> {

            Stage mainStage = new Stage();
            mainStage.setTitle("MATCHED PLAYED");
            mainStage.setResizable(false);

            Label headerLabel = new Label("PLAYED MATCHES ON " + inputDate.getText());
            headerLabel.setLayoutX(400);
            headerLabel.setLayoutY(20);

            Label dateLabel = new Label("   PLAYED DATE  ");
            dateLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");
            Label playedMatchLabel = new Label("  PLAYED MATCH");
            playedMatchLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");
            Label scoreLabel = new Label("  MATCH SCORE");
            scoreLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 15;");

            Button optionClose = new Button(" E X I T ");
            optionClose.setLayoutX(20);
            optionClose.setLayoutY(40);

            BorderPane mainBorderPane = new BorderPane();
            GridPane mainGridPane = new GridPane();

            Pane topPane = new Pane();
            topPane.setStyle("-fx-background-color: #3d195b;");
            topPane.setPadding(new Insets(20));
            topPane.getChildren().add(headerLabel);

            Pane bottomPane = new Pane();
            bottomPane.setStyle("-fx-background-color:#3d195b");
            bottomPane.setPadding(new Insets(20));
            bottomPane.getChildren().add(optionClose);

            ColumnConstraints dateColumn = new ColumnConstraints();
            dateColumn.setPercentWidth(25);
            ColumnConstraints matchColumn = new ColumnConstraints();
            matchColumn.setPercentWidth(30);
            ColumnConstraints matchScoreColumn = new ColumnConstraints();
            matchScoreColumn.setPercentWidth(45);

            mainGridPane.getColumnConstraints().addAll(dateColumn,matchColumn,matchScoreColumn);
            mainGridPane.setStyle("-fx-grid-lines-visible:true"); //To Visible The Table Lines

            mainGridPane.add(dateLabel,0,0);
            mainGridPane.add(playedMatchLabel,1,0);
            mainGridPane.add(scoreLabel,2,0);

            VBox dateValue = new VBox();
            dateValue.setStyle("-fx-font-size: 12;");
            dateValue.setPadding(new Insets(20));
            dateValue.setPrefHeight(600);

            VBox playedMatchValue = new VBox();
            playedMatchValue.setStyle("-fx-font-size: 12;");
            playedMatchValue.setPadding(new Insets(20));

            VBox matchScoreValue = new VBox();
            matchScoreValue.setStyle("-fx-font-size: 12;");
            matchScoreValue.setPadding(new Insets(20));

            mainGridPane.add(dateValue,0,1);
            mainGridPane.add(playedMatchValue,1,1);
            mainGridPane.add(matchScoreValue,2,1);

            mainBorderPane.setTop(topPane);
            mainBorderPane.setCenter(mainGridPane);
            mainBorderPane.setBottom(bottomPane);

            boolean searchMatch = false;

            for (FootballMatch searchplayedMatch : matchList) {
                if (inputDate.getText().equals(dateFormatter.format(searchplayedMatch.getPlayingDate()))) {
                    Label matchDate = new Label(dateFormatter.format(searchplayedMatch.getPlayingDate()));
                    Label playingTeams = new Label(searchplayedMatch.getPlayingClub01().getClubName().toUpperCase() + " - " + searchplayedMatch.getPlayingClub02().getClubName().toUpperCase());
                    Label playedMatchScore = new Label(searchplayedMatch.getPlayingClub01().getClubName().toUpperCase() + "  " + searchplayedMatch.getScore01() + " - " + searchplayedMatch.getScore02() + "  " + searchplayedMatch.getPlayingClub02().getClubName().toUpperCase());

                    dateValue.getChildren().add(matchDate);
                    playedMatchValue.getChildren().add(playingTeams);
                    matchScoreValue.getChildren().add(playedMatchScore);
                    searchMatch = true;
                }

            }

            optionClose.setOnAction(event1 -> {
            mainStage.close();
        });

        if (!searchMatch){

            Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
            emptyAlert.setContentText("No Played Matches on: " + inputDate.getText());
            emptyAlert.show();
        }else {
            Scene mainScene = new Scene(mainBorderPane,800,500);
            mainStage.setScene(mainScene);
            mainStage.showAndWait();
        }
        });

        topOptionClose.setOnAction(event -> {
            topStage.close();
        });

        Scene topScene = new Scene(topBorderPane,500,300);
        topStage.setScene(topScene);
        topStage.showAndWait();



    }
}
