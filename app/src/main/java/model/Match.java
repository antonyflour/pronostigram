package model;

import java.util.Date;

/**
 * Created by Luigi on 11/01/2018.
 */

public class Match {

    private int matchID;
    private String squadraCasa, squadraOspite;
    private Date dataMatch;

    public Match(){}

    public Match(int matchID, String squadraCasa, String squadraOspite, Date dataMatch) {
        this.matchID = matchID;
        this.squadraCasa = squadraCasa;
        this.squadraOspite = squadraOspite;
        this.dataMatch = dataMatch;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public String getSquadraCasa() {
        return squadraCasa;
    }

    public void setSquadraCasa(String squadraCasa) {
        this.squadraCasa = squadraCasa;
    }

    public String getSquadraOspite() {
        return squadraOspite;
    }

    public void setSquadraOspite(String squadraOspite) {
        this.squadraOspite = squadraOspite;
    }

    public Date getDataMatch() {
        return dataMatch;
    }

    public void setDataMatch(Date dataMatch) {
        this.dataMatch = dataMatch;
    }

    @Override
    public String toString(){
        return (squadraCasa + " - " + squadraOspite);
    }
}
