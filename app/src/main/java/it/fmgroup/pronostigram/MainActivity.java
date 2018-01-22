package it.fmgroup.pronostigram;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.Database;
import model.Match;
import model.Pronostico;
import model.User;


public class MainActivity extends AppCompatActivity implements InitialFragment.OnFragmentInteractionListener {

    Button button_entra;

    InitialFragment fragment1 = new InitialFragment();

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.framelayout_initial, fragment1);
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();

/*
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference dr = fb.getReference();
        dr.child("prova-luigi1").setValue("prova1");
   */

        mAuth = FirebaseAuth.getInstance();

        Match m = null;
        try {
            m = new Match(11,"napoli113", "juve11", new SimpleDateFormat("dd/MM/yyyy").parse("20/02/2018"));
            FirebaseDatabase.getInstance().getReference("matches").child(String.valueOf(m.getMatchID())).setValue(m);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}

