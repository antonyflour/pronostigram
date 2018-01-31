package it.fmgroup.pronostigram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Match;
import model.Util;

public class MatchActivity extends AppCompatActivity {


    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private ListView lvMatches;
    private List<Match> listMatch;

    private MatchAdapter matchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

/*
        Match m1 = new Match(3,"napoli3", "juve3", new Date());
        Match m2 = new Match(2,"napoli2", "juve2", new Date());
        Match m3 = new Match(1,"napoli1", "juve1", new Date());
*/
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        lvMatches = (ListView) this.findViewById(R.id.listViewMatches);
        listMatch = new ArrayList<Match>();

        /*
        listMatch.add(m1);
        listMatch.add(m2);
        listMatch.add(m3);
*/
        matchAdapter = new MatchAdapter(this, listMatch);
        lvMatches.setAdapter(matchAdapter);
        database.getReference("matches/").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Match m = ds.getValue(Match.class);
                    try {
                        if (!Util.isBefore(m.getDataMatch(),new Date())){
                            listMatch.add(m);
                        }
                    } catch (ParseException e) {
                        Toast.makeText(MatchActivity.this, "Errore nella data", Toast.LENGTH_SHORT).show();
                        Log.e("ParseError", e.getMessage());
                    }
                    matchAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lvMatches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Match m = (Match) parent.getItemAtPosition(position);
                Log.d("sceltamatch", m.toString());

                Intent i = new Intent(MatchActivity.this, PronosticoActivity.class);
                i.putExtra(getString(R.string.matchId), m.getMatchID());
                startActivity(i);
            }
        });

    }
}
