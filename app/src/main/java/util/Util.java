package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static boolean isBefore(String date1, Date date2) throws ParseException {
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
        return d1.before(date2);
    }
}
