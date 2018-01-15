package model;

/**
 * Created by Luigi on 11/01/2018.
 */

public class User {

    private String username;
    private String email;
    private String password;
    private String nome, cognome;
    private int pronosticiVinti, pronosticiGiocati;

    public User(){}

    public User(String username, String email, String password, String nome, String cognome, int pronosticiVinti, int pronosticiGiocati) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.pronosticiVinti = pronosticiVinti;
        this.pronosticiGiocati = pronosticiGiocati;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", pronosticiVinti=" + pronosticiVinti +
                ", pronosticiGiocati=" + pronosticiGiocati +
                '}';
    }

    public int getPronosticiVinti() {
        return pronosticiVinti;
    }

    public void setPronosticiVinti(int pronosticiVinti) {
        this.pronosticiVinti = pronosticiVinti;
    }

    public int getPronosticiGiocati() {
        return pronosticiGiocati;
    }

    public void setPronosticiGiocati(int pronosticiGiocati) {
        this.pronosticiGiocati = pronosticiGiocati;
    }

    @Override
    public boolean equals(Object o){
        User u = (User) o;
        return u.getUsername().equalsIgnoreCase(this.getUsername());
    }

}
