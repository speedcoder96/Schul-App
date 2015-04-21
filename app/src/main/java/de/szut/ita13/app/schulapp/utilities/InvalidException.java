package de.szut.ita13.app.schulapp.utilities;

/**
 * Created by Rene on 14.02.2015.
 */
public class InvalidException extends Exception {

    public static final String INVALID_TIME = "invalid time";

    public static final String INVALID_DATE = "invalid date";

    public InvalidException(String message) {
        super(message);
    }

}
