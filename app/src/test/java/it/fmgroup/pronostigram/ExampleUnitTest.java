package it.fmgroup.pronostigram;

import org.junit.Test;

import java.util.Date;

import controller.Database;
import model.Pronostico;
import model.User;
import model.Match;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
/*
    @Test
    public void prova(){
        User u = new User("user", "email@prova.it", "password", "nome", "cognome", 10, 12);
        User follower = new User("userFollower", "email@follower.it", "passwordfollower",
                "nomefollower", "cognomefollower", 1, 0);
        Match m = new Match(1,"squadracasa","squadraospite", new Date());
        Pronostico p = new Pronostico("idpronostico",u.getUsername(),"descrizione", m, "1x");
        assertEquals("gli id uguali", "idpronostico", p.getId());
    }
    */
}
