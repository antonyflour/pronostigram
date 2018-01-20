package it.fmgroup.pronostigram;

import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

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
            listMatch.add(new Match(i, "Squadra1", "Squadra2", new Date()));
        }


        FeedAdapter adapter = new FeedAdapter(this, listPronostici, listMatch);

        // Assign adapter to ListView
        listViewFeed.setAdapter(adapter);
    }

    //prova
    public void onButtonShowPopupWindowClick(View view) {

        // get a reference to the already created main layout
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linear_layout_feed);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

}
