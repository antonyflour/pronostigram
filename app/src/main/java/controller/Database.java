package controller;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Pronostico;
import model.User;

/**
 * Created by Luigi on 11/01/2018.
 */

public class Database {
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public Database(){
        if (database == null) {
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference();
        }
    }

    public void addFollower(final Pronostico pronostico, final String user){
        database.getReference("pronostici").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(pronostico.getId())){
                    pronostico.addFollower(user);
                    myRef = database.getReference("pronostici/"+pronostico.getId()+"/followers/");
                    myRef.child(user).setValue(user);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addPronostico(Pronostico p){
        myRef = database.getReference("pronostici/");
        myRef.child(p.getId()).setValue(p);
    }



    public void addUtente(User user){
        myRef = database.getReference("users/");
        myRef.child(user.getUsername()).setValue(user);
    }




}

