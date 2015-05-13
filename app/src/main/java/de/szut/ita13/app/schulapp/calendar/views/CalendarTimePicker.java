package de.szut.ita13.app.schulapp.calendar.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.jar.Attributes;

import de.szut.ita13.app.schulapp.R;


/**
 * Created by ramazan and rene on 12.05.2015.
 */
public class CalendarTimePicker extends View implements View.OnClickListener {

    public static int MAX_TIME_LENGTH = 5;
    public static String DEFAULT_SEPERATOR = ":";

    private EditText time;
    private Button hoursIncrement, hoursDecrement;
    private Button minuteIncrement, minuteDecrement;

    private int hours;
    private int minutes;

    public CalendarTimePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_timepicker, null);

        TypedArray attributeArray = context.getTheme()
                .obtainStyledAttributes(attributeSet, R.styleable.CalendarTimePicker, 0, 0);

        hours = attributeArray.getInteger(R.styleable.CalendarTimePicker_hours, 0);
        minutes = attributeArray.getInteger(R.styleable.CalendarTimePicker_minute, 0);

        time = (EditText)findViewById(R.id.time);

        InputFilter lengthFilter = new InputFilter.LengthFilter(MAX_TIME_LENGTH);
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if(source.toString().contains(DEFAULT_SEPERATOR)) {
                    String[] input = source.toString().split(DEFAULT_SEPERATOR);
                    int hoursValue = isInteger(input[0]);
                    int minutesValue = isInteger(input[1]);
                    if(hoursValue == -1 || minutesValue == -1) {
                        source = "00:00";
                    } else {
                        hoursValue = (hoursValue < 0) ? 23 : hoursValue % 24;
                        minutesValue = (minutesValue < 0) ? 59 : minutesValue % 60;
                        hours = hoursValue;
                        minutes = minutesValue;
                        String hoursString = fillMissingDigit(hoursValue);
                        String minutesString = fillMissingDigit(minutesValue);
                        source = hoursString + DEFAULT_SEPERATOR + minutesString;
                    }
                }
                return source;
            }

            private int isInteger(String value) {
                try {
                    return Integer.parseInt(value);
                } catch(NumberFormatException e) {
                    return -1;
                }
            }
        };

        time.setFilters(new InputFilter[] {lengthFilter, filter});


        hoursIncrement = (Button)findViewById(R.id.hours_increment);
        hoursDecrement = (Button)findViewById(R.id.hours_decrement);
        minuteIncrement = (Button)findViewById(R.id.minutes_increment);
        minuteDecrement = (Button)findViewById(R.id.minutes_decrement);

        hoursIncrement.setOnClickListener(this);
        hoursDecrement.setOnClickListener(this);
        minuteIncrement.setOnClickListener(this);
        minuteDecrement.setOnClickListener(this);



    }

    @Override
    public void onClick(View button) {
        String hoursString = "";
        String minutesString = "";
        switch(button.getId()) {
            case R.id.hours_increment:
                hours = (hours + 1) % 24;
                hoursString = fillMissingDigit(hours);
                minutesString = fillMissingDigit(minutes);
                time.setText(String.valueOf(hoursString + DEFAULT_SEPERATOR + minutesString));
                break;
            case R.id.hours_decrement:
                hours = (hours - 1 < 0) ? hours = 23 : hours--;
                hoursString = fillMissingDigit(hours);
                minutesString = fillMissingDigit(minutes);
                time.setText(String.valueOf(hoursString + DEFAULT_SEPERATOR + minutesString));
                break;
            case R.id.minutes_increment:
                minutes = (minutes + 1) % 24;
                hoursString = fillMissingDigit(hours);
                minutesString = fillMissingDigit(minutes);
                time.setText(String.valueOf(hoursString + DEFAULT_SEPERATOR + minutesString));
                break;
            case R.id.minutes_decrement:
                minutes = (minutes - 1 < 0) ? minutes = 59 : minutes--;
                hoursString = fillMissingDigit(hours);
                minutesString = fillMissingDigit(minutes);
                time.setText(String.valueOf(hoursString + DEFAULT_SEPERATOR + minutesString));
                break;
        }
    }

    private String fillMissingDigit(int value) {
        String valueString = (value < 10) ? "0" + value : String.valueOf(value);
        return valueString;
    }

}
