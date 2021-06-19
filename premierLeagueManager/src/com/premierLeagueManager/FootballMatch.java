package com.premierLeagueManager;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class FootballMatch implements Comparable<FootballMatch>,Serializable {

    private LocalDateTime playingDate;
    private FootballClub playingClub01;     //To uniquely identify the one of the playing club in a match
    private FootballClub playingClub02;     //TO uniquely identify the other playing club ina match
    private int score01;
    private int score02;
    private int receivedPoints01;
    private int receivedPoints02;


    public FootballMatch(LocalDateTime playingDate, FootballClub playingClub01, FootballClub playingClub02, int score01, int score02, int receivedPoints01, int receivedPoints02) {
        this.playingDate = playingDate;
        this.playingClub01 = playingClub01;
        this.playingClub02 = playingClub02;
        this.score01 = score01;
        this.score02 = score02;
        this.receivedPoints01 = receivedPoints01;
        this.receivedPoints02 = receivedPoints02;
    }

    public LocalDateTime getPlayingDate() {
        return playingDate;
    }

    public void setPlayingDate(LocalDateTime playingDate) {
        this.playingDate = playingDate;
    }

    public FootballClub getPlayingClub01() {
        return playingClub01;
    }

    public void setPlayingClub01(FootballClub playingClub01) {
        this.playingClub01 = playingClub01;
    }

    public FootballClub getPlayingClub02() {
        return playingClub02;
    }

    public void setPlayingClub02(FootballClub playingClub02) {
        this.playingClub02 = playingClub02;
    }

    public int getScore01() {
        return score01;
    }

    public void setScore01(int score01) {
        this.score01 = score01;
    }

    public int getScore02() {
        return score02;
    }

    public void setScore02(int score02) {
        this.score02 = score02;
    }

    public int getReceivedPoints01() {
        return receivedPoints01;
    }

    public void setReceivedPoints01(int receivedPoints01) {
        this.receivedPoints01 = receivedPoints01;
    }

    public int getReceivedPoints02() {
        return receivedPoints02;
    }

    public void setReceivedPoints02(int receivedPoints02) {
        this.receivedPoints02 = receivedPoints02;
    }


    @Override
    public int compareTo(FootballMatch o) {
        if (this.getPlayingDate().compareTo(o.getPlayingDate()) > 0){
            return 1;
        }else if (this.getPlayingDate().compareTo(o.getPlayingDate()) < 0){
            return -1;
        }else
            return 0;
    }
}
