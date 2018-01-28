package model;

import java.util.UUID;

/**
 * Created by Luigi on 28/01/2018.
 */

public class Util {

    public static String generateUsername(String mEmail){
        String username = mEmail.substring(0,mEmail.indexOf('@'));
        username = username.replaceAll("\\.", "-");
        return username;
    }

    public static String generateMatchID(){
        return UUID.randomUUID().toString();
    }

    public static String generatePronosticoID(){
        return UUID.randomUUID().toString();
    }
}
