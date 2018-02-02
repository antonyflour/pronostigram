package it.fmgroup.pronostigram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.Esiti;
import model.Match;
import model.Pronostico;
import util.Util;

public class PronosticoActivity extends AppCompatActivity {


    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private String matchID;
    private TextView tv_titolo;
    private Spinner spinner;
    private Button button;
    private EditText desc;
    private Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pronostico);

        tv_titolo = findViewById(R.id.textView_scegli_pronostico);
        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button_conferma_pronostico);
        desc = findViewById(R.id.editText);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        Intent i = getIntent();
        matchID = i.getStringExtra(getString(R.string.matchId));
        if (matchID == null){
            Log.e("ERRORE", "Errore nel trasferimento del matchID");
            Toast.makeText(this, "Errore nel trasferimento del matchID", Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(this, FeedActivity.class));
        }
        else {
            setTextview();
            selezioneRisultato();
        }
    }

    private void setTextview(){
        database.getReference("matches/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Match m = ds.getValue(Match.class);
                    if (matchID.equals(m.getMatchID())){
                        tv_titolo.setText(m.toString());
                        match = m;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void onClickConfermaPronostico(View view) {
        Pronostico p = new Pronostico();
        p.setCreator(Util.generateUsername(auth.getCurrentUser().getEmail()));
        p.setDescrizione(desc.getText().toString());
        p.setEsito(spinner.getSelectedItem().toString());
        p.setMatch(matchID);
        p.setId(Util.generatePronosticoID());
        database.getReference("pronostici/").child(p.getId()).setValue(p);
        Toast.makeText(this,"Pronostico condiviso!", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this, FeedActivity.class));
    }

    private void selezioneRisultato(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Esiti.getEsiti());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                button.setClickable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                button.setClickable(false);
            }
        });

    }
}
