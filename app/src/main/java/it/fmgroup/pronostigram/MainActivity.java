package it.fmgroup.pronostigram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

import controller.Database;
import model.Match;
import model.Pronostico;
import model.User;

public class MainActivity extends AppCompatActivity {
    private Database db = new Database();


    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textview);
        tv.setText("prova");
/*
        User u = new User("user", "email@prova.it", "password", "nome",
                "cognome", 10, 12);

        Match m = new Match(1,"squadracasa","squadraospite", new Date());
        Pronostico p = new Pronostico("idpronostico",u.getUsername(),"descrizione", m, "1x");
        db.addPronostico(p);
*/
    }


    public void buttonClicked(View view) {
 /*      User follower = new User("userFollower2", "email@follower.it2", "passwordfollower2",
                "nomefollower2", "cognomefollower2", 1, 0);
        User u = new User("user", "email@prova.it", "password", "nome",
                "cognome", 10, 12);

        Match m = new Match(1,"squadracasa","squadraospite", new Date());
        Pronostico p = new Pronostico("idpronostic1o",u.getUsername(),"descrizione", m, "1x");
        db.addFollower(p, follower.getUsername());
*/

/*
       User us = new User("user", "email@prova.it", "password", "nome",
                "cognome", 10, 12);
        db.addUtente(us);

*/


   }





}
