package it.fmgroup.pronostigram;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements InitialFragment.OnFragmentInteractionListener {

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
        ft.commit();

        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}

