package com.premierLeagueManager;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {


    static File footballClubs = new File("footballClubsData.txt");
    static File playedMatches = new File("playedMatchesData.txt");

    static Scanner input = new Scanner(System.in);

    private static LeagueManager premierLeague = PremierLeagueManager.getInstance();

    public static void main(String[] args) throws Exception {

        premierLeague.loadData(footballClubs, playedMatches);
        launch();

    }

    public void start (Stage primaryStage) throws Exception {

        Scanner input = new Scanner(System.in);

        main_console:
        while (true){
            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.println("-----------------------------Welcome To The Premier League Manager--------------------------------");
            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("You Can Easily Manage The Premier League From This Application.");
            System.out.println("Check The Numbers Of The Processes And Enter The Suitable Number Of The Process That You Required ");
            System.out.println();
            System.out.println(" 1. Add A New Club To Premier League");
            System.out.println(" 2. Delete A Club From Premier League");
            System.out.println(" 3. Display Statistics Of A Club In Premier League");
            System.out.println(" 4. Display Premier League Table");
            System.out.println(" 5. Add A New Played Match");
            System.out.println(" 6. Save Data");
            System.out.println(" 7. Revoke GUI");
            System.out.println(" 8. Quit To Main Menu");
            System.out.println();

            System.out.print("Enter The Number Of Process That You wish to Proceed: ");
            String command1 = input.next();

            switch (command1){

                case "1":
                    creatNewFootballClub();
                    continue main_console;

                case "2":
                    deleteFootballClub();
                    continue main_console;

                case "3":
                    displayStatistics();
                    continue main_console;

                case "4":
                    displayPremierLeagueTable();
                    continue main_console;

                case "5":
                    addPlayedMatch();
                    continue main_console;

                case "6":
                    premierLeague.saveData(footballClubs,playedMatches);
                    continue main_console;

                case "7":
                    revokeGUI();
                    continue main_console;

                case "8":
                    premierLeague.saveData(footballClubs,playedMatches);
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Thank you For Using premier League Manager | Have A Nice Day");
                    System.out.println();
                    System.out.println("Successfully Exiting From The Application...");
                    System.out.println("------------------------------------------------------------");
                    break main_console;

                default:
                    System.out.println("Invalid Input.Please Try Again");
            }

        }
    }

    private static void revokeGUI() throws FileNotFoundException {

        Stage mainStage = new Stage();
        mainStage.setTitle("Premier League Manager Application");

        Label topic = new Label("Welcome To The Premier League Manager");
        topic.setStyle("-fx-text-fill:white;" + "-fx-font-weight: bold;" + "-fx-font-size: 25;");

        Label subTopic = new Label("You Can Choose Following options");
        subTopic.setStyle("-fx-text-fill:white;" + "-fx-font-weight: bold;" + "-fx-font-size: 15;");

        BorderPane borderPane = new BorderPane();

        Button option01 = new Button("Display Premier League Table    ");
        option01.setStyle("-fx-font-weight: bold;" + "-fx-background-color:  #84f5b7;" + "-fx-font-size: 14;" + "-fx-border-color: #636361");
        Button option02 = new Button("Sort According To Goals Scored  ");
        option02.setStyle("-fx-font-weight: bold;" + "-fx-background-color:  #abdea4;" + "-fx-font-size: 14;" + "-fx-border-color: #636361");
        Button option03 = new Button("Sort According To Number Of Wins");
        option03.setStyle("-fx-font-weight: bold;" + "-fx-background-color:  #84f5b7;" + "-fx-font-size: 14;" + "-fx-border-color: #636361");
        Button option04 = new Button("     Generate A Random Match    ");
        option04.setStyle("-fx-font-weight: bold;" + "-fx-background-color:  #abdea4;" + "-fx-font-size: 14;" + "-fx-border-color: #636361");
        Button option05 = new Button("        All played Matches      ");
        option05.setStyle("-fx-font-weight: bold;" + "-fx-background-color:  #84f5b7;" + "-fx-font-size: 14;" + "-fx-border-color: #636361");
        Button option06 = new Button("      Search A Played Match     ");
        option06.setStyle("-fx-font-weight: bold;" + "-fx-background-color:  #abdea4;" + "-fx-font-size: 14;" + "-fx-border-color: #636361");

        Button optionClose = new Button(" C L O S E ");
        optionClose.setStyle("-fx-font-weight: bold;" + "-fx-background-color: #e0f7a1;");

        FileInputStream input = new FileInputStream("images/1.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);


        Pane topPane = new Pane();
        topPane.setStyle("-fx-background-color: #3d195b;");

        Pane centerPane = new Pane();
        centerPane.setStyle("-fx-background-color: #d8c7f0;");

        Pane bottomPane = new Pane();
        bottomPane.setStyle("-fx-background-color: #3d195b;");


        borderPane.setTop(topPane);
        borderPane.setCenter(centerPane);
        borderPane.setBottom(bottomPane);

        //Creating top Pane
        topPane.getChildren().add(topic);
        topPane.getChildren().add(subTopic);

        topic.setLayoutX(250);
        topic.setLayoutY(30);

        imageView.setLayoutX(500);
        imageView.setLayoutY(-47);

        imageView.setFitHeight(550);
        imageView.setFitWidth(400);

        subTopic.setLayoutX(375);
        subTopic.setLayoutY(70);

        //Creating center Pane
        centerPane.getChildren().add(option01);
        centerPane.getChildren().add(option02);
        centerPane.getChildren().add(option03);
        centerPane.getChildren().add(option04);
        centerPane.getChildren().add(option05);
        centerPane.getChildren().add(option06);
        centerPane.getChildren().add(imageView);

        option01.setMinSize(255, 55);
        option02.setMinSize(255, 55);
        option03.setMinSize(255, 55);
        option04.setMinSize(255, 55);
        option05.setMinSize(255, 55);
        option06.setMinSize(255, 55);

        option01.setLayoutX(86);
        option01.setLayoutY(20);

        option02.setLayoutX(86);
        option02.setLayoutY(90);

        option03.setLayoutX(86);
        option03.setLayoutY(160);

        option04.setLayoutX(86);
        option04.setLayoutY(230);

        option05.setLayoutX(86);
        option05.setLayoutY(300);

        option06.setLayoutX(86);
        option06.setLayoutY(380);

        //Creating Bottom Pane
        bottomPane.getChildren().add(optionClose);

        optionClose.setLayoutX(86);
        optionClose.setLayoutY(5);

        //Padding
        topPane.setPadding(new Insets(20));
        centerPane.setPadding(new Insets(20));
        bottomPane.setPadding(new Insets(20));

        //Creating Actions

        option01.setOnAction(event -> {
            premierLeague.displayClubsTable();
        });

        option02.setOnAction(event -> {
            premierLeague.sortByScoredGoals();
        });

        option03.setOnAction(event -> {
            premierLeague.sortByNoOfWins();
        });

        option04.setOnAction(event -> {
            premierLeague.generateRandomMatch();
        });
        option05.setOnAction(event -> {
            premierLeague.sortByPlayedDate();
        });

        option06.setOnAction(event -> {
            premierLeague.searchMatch();
        });

        optionClose.setOnAction(event ->{
            mainStage.close();

        });



        Scene mainScene = new Scene(borderPane,1000,650);
        mainStage.setScene(mainScene);
        mainStage.setResizable(false);
        mainStage.showAndWait();

    }

    public static void creatNewFootballClub() {

        create:
        while (true){


            System.out.println("Creat a New Football Club from here!");
            System.out.println("To Exit To Menu Type 'exit'");
            System.out.println();

            System.out.println("1.  Football Club Code  : ");
            String clubCode = input.next();

            if (clubCode.equals("exit")){
                break;
            }

            System.out.println("2.  Football Club Name  : ");
            String clubName = input.next();

            System.out.println("3.  Football Club Location  : ");
            String location = input.next();


            SportsClub footballClub = new FootballClub(clubCode,clubName,location,0,0,0,0,0,0,0);

            premierLeague.creatNewFootballClub(footballClub);

            System.out.println("The New Football Club Has Been Added To The Premier League");
            System.out.println("----------------------------------------------------------");

            break;
        }
    }

    public static void deleteFootballClub(){
        while (true){


            System.out.println();
            System.out.println("Delete a Football Club from here!");
            System.out.println("To Exit To Menu Type 'exit'");


            System.out.println("1.  Football Club's Code That Needed To Delete:   ");
            String clubCode = input.next();

            if (clubCode.equals("exit")){
                break;
            }

            System.out.println("2.  Football Club's Name That Needed To Delete:    ");
            String clubName = input.next();

            System.out.println("3.  Football Club's Location That Needed To Delete:    ");
            String location = input.next();

            SportsClub footballClub = new SportsClub(clubCode,clubName,location);

            premierLeague.deleteFootballClub(footballClub);

            break;



        }
    }

    public static void displayStatistics(){
        while (true){


            System.out.println();
            System.out.println("Display Statistics Of A Football Club from here!");
            System.out.println("To Exit To Menu Type 'exit'");


            System.out.println("1.  Football Club's Code That Needed To Display Statistics:   ");
            String clubCode = input.next();

            if (clubCode.equals("exit")){
                break;
            }

            System.out.println("2.  Football Club Name That Needed To Display Statistics:    ");
            String clubName = input.next();

            System.out.println("3.  Football Club Location That Needed To Display Statistics:    ");
            String location = input.next();

            SportsClub footballClub = new SportsClub(clubCode,clubName,location);

            premierLeague.displayStatistics(footballClub);

            break;
        }
    }

    public static void displayPremierLeagueTable(){

        premierLeague.displayPremierLeagueTable();
    }

    public static void addPlayedMatch(){

        match:
        while(true){
            System.out.println();
            System.out.println("Add a Football Match from here!");
            System.out.println("To Exit To Menu Type 'exit'");


            System.out.print("1.1 Football Club 01 Code:   ");
            String team01Code = input.next();

            if (team01Code.equals("exit")){
                break;
            }

            System.out.print("1.2 Football Club 01 Name: ");
            String team01Name = input.next();

            System.out.print("1.3 Football Club 01 Location: ");
            String  team01Location = input.next();
            System.out.println();

            //------------------------------------------------

            System.out.print("2.1 Football Club 02 Code: ");
            String team02Code = input.next();

            if (team02Code.equals("exit")){
                break;
            }

            System.out.print("2.2 Football Club 02 Name: ");
            String team02Name = input.next();

            System.out.print("2.3 Football Club 02 Location: ");
            String  team02Location = input.next();
            System.out.println();

            //------------------------------------------------

            System.out.print("3.1 Team "+  team01Name +"'s Score: ");

            if (!input.hasNextInt()){

                System.out.println("invalid Score. Try Again:   ");
                input.next();
            }
            int team01Score = input.nextInt();
            input.nextLine();

            //------------------------------------------------

            System.out.print("3.2 Team "+  team02Name +"'s Score: ");

            if (!input.hasNextInt()){

                System.out.println("invalid Score. Try Again: ");
                input.next();
            }
            int team02Score = input.nextInt();
            input.nextLine();
            System.out.println();

            //------------------------------------------------

            System.out.print("4.1 Team "+  team01Name +"'s Points: ");

            if (!input.hasNextInt()){

                System.out.println("invalid points. Try Again:   ");
                input.next();
            }

            int team01Points = input.nextInt();
            input.nextLine();

            //------------------------------------------------

            System.out.print("4.2 Team "+  team02Name +"'s Points: ");

            if (!input.hasNextInt()){

                System.out.println("invalid Points. Try Again: ");
                input.next();
            }
            int team02Points = input.nextInt();
            input.nextLine();

            //------------------------------------------------

            FootballClub team01 = new FootballClub(team01Code,team01Name,team01Location,team01Points,0,team01Score,team02Score,0,0,0);
            FootballClub team02 = new FootballClub(team02Code,team02Name,team02Location,team02Points,0,team02Score,team01Score,0,0,0);

            premierLeague.addPlayedMatch(team01,team02);
            break;
        }
    }

}
