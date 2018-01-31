package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luigi on 11/01/2018.
 */

public class Pronostico {

    private String id;
    private String creator;
    private String descrizione;
    private String match;
    private String esito;
    private List<String> followers;

    public Pronostico() {}

    public Pronostico(String id, String creator, String descrizione, String match, String esito) {
        this.id = id;
        this.creator = creator;
        this.descrizione = descrizione;
        this.match = match;
        this.esito = esito;
        this.followers = new ArrayList<String>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getEsito() {
        return esito;
    }

    public void setEsito(String esito) {
        this.esito = esito;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public boolean isFollower(User u){
        for(String tmp  : followers) {
            if (tmp.equals(u.getUsername()))
                return true;
        }
        return false;
    }

    public void addFollower(String user){
        followers.add(user);
    }
}
