package it.fmgroup.pronostigram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Match;
import model.Pronostico;

public class FeedActivity extends AppCompatActivity {

    private ImageButton buttonProfile;
    private ImageButton buttonSettings;
    private ImageButton buttonSearch;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);


        database = FirebaseDatabase.getInstance();

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



        //lista dei feed
        /*
        String[] values = new String[100];
        for(int i=0; i<100; i++){
            values[i] = "Stringa "+i;
        }
        */

        ListView listViewFeed = (ListView) findViewById(R.id.list_view_feed);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
         //       R.layout.list_item, R.id.textview_match, values);


        List<Pronostico> listPronostici = new ArrayList<Pronostico>();
        List<Match>  listMatch = new ArrayList<Match>();

        for(int i=0; i<100; i++){
            listPronostici.add(new Pronostico( String.valueOf(i),"boh", "SquadraCasa - SquadraOspite "+i, null, "1X"));
            listMatch.add(new Match("Squadra1", "Squadra2", new Date()));
        }


        FeedAdapter adapter = new FeedAdapter(this, listPronostici, listMatch);

        // Assign adapter to ListView
        listViewFeed.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        //currentUser = mAuth.getCurrentUser();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();
                if (currentUser == null)
                    Toast.makeText(FeedActivity.this, "UTENTE NULLO", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(FeedActivity.this, currentUser.getEmail(), Toast.LENGTH_LONG).show();
            }
        });


    }

    //prova
    public void onButtonShowPopupWindowClick(View view) {

        startActivity(new Intent(this, MatchActivity.class));
/*
        // get a reference to the already created main layout
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linear_layout_feed);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);


//        DatabaseReference ref = database.getReference("matches/");


//        List<Match>  listMatch = new ArrayList<Match>();
        final ListView lvMatches = (ListView) popupView.findViewById(R.id.listViewMatchesPopup);
        database.getReference("matches/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Match>  listMatch = new ArrayList<Match>();
//                ListView lvMatches = (ListView) FeedActivity.this.findViewById(R.id.listViewMatchesPopup);
                for ( DataSnapshot ds : dataSnapshot.getChildren()){
                    Match m = ds.getValue(Match.class);
                    if (m.getDataMatch().before(new Date())){
                        listMatch.add(m);

                    }
                }
                MatchAdapter matchAdapter = new MatchAdapter(popupView.getContext(), listMatch);
                if (listMatch == null)
                    Toast.makeText(FeedActivity.this, "listMatch null DIOCANE", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(FeedActivity.this, "Lunghezza lista: "+listMatch.size(), Toast.LENGTH_LONG).show();
                if (matchAdapter == null)
                    Toast.makeText(FeedActivity.this, "matchAdapter null DIOCANE", Toast.LENGTH_LONG).show();
                if (lvMatches == null)
                    Toast.makeText(FeedActivity.this, "listviewMatch null DIOCANE", Toast.LENGTH_LONG).show();
                else
                    lvMatches.setAdapter(matchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

*/
    }

/*
    private List<Match> downloadMatches(){
        List<Match>  listMatch = new ArrayList<Match>();
        DatabaseReference ref = database.getReference("matches");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for ( DataSnapshot ds : dataSnapshot.getChildren()){
                    Match m = ds.getValue(Match.class);
                    if (m.getDataMatch().before(new Date()))
                        listMatch.add(m);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return listMatch;
    }
*/

}
