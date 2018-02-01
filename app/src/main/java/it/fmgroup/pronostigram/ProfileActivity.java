package it.fmgroup.pronostigram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.User;
import model.Util;

public class ProfileActivity extends AppCompatActivity {


    private ImageButton buttonBack;
    private ImageButton buttonSettings;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    private TextView tvEmail;
    private TextView textview_nome, textview_cognome, textview_pron_tot, textview_pron_vinti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        buttonBack = (ImageButton) findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonSettings = (ImageButton) findViewById(R.id.button_settings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();

        tvEmail = findViewById(R.id.textview_email);
        textview_cognome = findViewById(R.id.textview_cognome);
        textview_nome = findViewById(R.id.textview_nome);
        textview_pron_tot = findViewById(R.id.textview_pron_tot);
        textview_pron_vinti = findViewById(R.id.textview_pron_vinti);
        tvEmail.setText(email);

        aggiornaTextViews(email);
    }

    private void aggiornaTextViews(final String email){
        database.getReference("users/").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.getKey().equalsIgnoreCase(Util.generateUsername(email))) {
                        User u = ds.getValue(User.class);
                        textview_cognome.setText(u.getCognome());
                        textview_nome.setText(u.getNome());
                        textview_pron_tot.setText(String.valueOf(u.getPronosticiGiocati()));
                        textview_pron_vinti.setText(String.valueOf(u.getPronosticiVinti()));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
