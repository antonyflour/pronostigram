package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Luigi on 11/01/2018.
 */

public class Match {

    private String matchID;
    private String squadraCasa, squadraOspite;
    private String dataMatch;

    public Match(){}

    public Match(String squadraCasa, String squadraOspite, String dataMatch) {
        this.matchID = Util.generateMatchID();
        this.squadraCasa = squadraCasa;
        this.squadraOspite = squadraOspite;
        this.dataMatch = dataMatch;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
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

    public String getDataMatch() {
        return dataMatch;
    }

    public void setDataMatch(String dataMatch) {
        this.dataMatch = dataMatch;
    }

    @Override
    public String toString(){
        return (squadraCasa + " - " + squadraOspite);
    }
}
