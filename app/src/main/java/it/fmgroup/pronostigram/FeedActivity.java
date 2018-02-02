package it.fmgroup.pronostigram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Match;
import model.Pronostico;
import util.Util;

public class FeedActivity extends AppCompatActivity {

    private ImageButton buttonProfile;
    private ImageButton buttonSettings;
    private ImageButton buttonSearch;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private FirebaseDatabase database;
    private List<Pronostico> pronostici = new ArrayList<>();
    private List<Match> incontri = new ArrayList<>();
    private FeedAdapter adapter;
    private ListView listViewFeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);



        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();
            }
        });

        database = FirebaseDatabase.getInstance();

        downloadMatches();
        downloadPronostici();

        buttonProfile = (ImageButton) findViewById(R.id.button_profile);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(FeedActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        buttonSettings = (ImageButton) findViewById(R.id.button_settings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(FeedActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        });


        listViewFeed = (ListView) findViewById(R.id.list_view_feed);

        adapter = new FeedAdapter(this, pronostici, incontri);
        // Assign adapter to ListView
        listViewFeed.setAdapter(adapter);
    }


    public void onButtonStartMatchActivity(View view) {
        startActivity(new Intent(this, MatchActivity.class));
    }


    private void downloadMatches(){
        DatabaseReference ref = database.getReference("matches");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for ( DataSnapshot ds : dataSnapshot.getChildren()){
                    Match m = ds.getValue(Match.class);
                    try {
                        if (!Util.isBefore(m.getDataMatch(),new Date())){
                            incontri.add(m);
                        }
                    } catch (ParseException e) {
                        Toast.makeText(FeedActivity.this, "Errore nella data", Toast.LENGTH_SHORT).show();
                        Log.e("ParseError", e.getMessage());
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.d("Downlaod","Download Matches effettuato");
    }

    private void downloadPronostici(){
        DatabaseReference ref = database.getReference("pronostici");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for ( DataSnapshot ds : dataSnapshot.getChildren()){
                    Pronostico p = ds.getValue(Pronostico.class);
                    pronostici.add(p);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.d("Downlaod","Download Pronostici effettuato");
    }

}
