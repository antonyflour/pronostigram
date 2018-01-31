package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luigi on 27/01/2018.
 */

public class Esiti {

    private static String[] esitiBase = {
            "1",
            "X",
            "2",
            "1X",
            "X2",
            "12",
            "UNDER 2.5",
            "OVER 2.5",
            "GOAL",
            "NOGOAL",
            "1 1T",
            "2 1T",
            "X 1T",
            "1 2T",
            "2 2T",
            "X 2T"
    };

    public static String[] getEsiti() {
        return esitiBase;
    }
}
