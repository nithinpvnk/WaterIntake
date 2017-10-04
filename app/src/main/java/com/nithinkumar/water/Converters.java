package com.nithinkumar.water;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nithinkumar on 5/31/17.
 */

public class Converters {

    public static String dateToString(Date date) {
        return new SimpleDateFormat("yyyy/MM/dd").format(date);
    }
}
