package de.szut.ita13.app.schulapp.calendar.views;

import android.content.Context;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Calendar;
import java.util.GregorianCalendar;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;
import de.szut.ita13.app.schulapp.utilities.Date;


/**
 * Created by ramazan and rene on 12.05.2015.
 */
public class CalendarTimePicker extends RelativeLayout implements View.OnClickListener, View.OnFocusChangeListener {

    public static final int HOURS_PER_DAY = 24;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int MAX_TIME_LENGTH = 2;

    private EditText hour;
    private EditText minute;
    private Button hoursIncrement, hoursDecrement;
    private Button minutesIncrement, minutesDecrement;
    private LinearLayout linearlayout;

    public CalendarTimePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        inflate(getContext(), R.layout.calendar_timepicker, this);



        Calendar c = Calendar.getInstance();
        int hourValue = c.get(Calendar.HOUR_OF_DAY);
        int minuteValue = c.get(Calendar.MINUTE);
        hour = (EditText)findViewById(R.id.hour);
        hour.setText(fillMissingDigit(hourValue));
        hour.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_TIME_LENGTH)});
        hour.setOnFocusChangeListener(this);

        minute = (EditText)findViewById(R.id.minute);
        minute.setText(fillMissingDigit(minuteValue));
        minute.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_TIME_LENGTH)});
        minute.setOnFocusChangeListener(this);



        hoursIncrement = (Button)findViewById(R.id.hours_increment);
        hoursIncrement.setOnClickListener(this);
        hoursDecrement = (Button)findViewById(R.id.hours_decrement);
        hoursDecrement.setOnClickListener(this);
        minutesIncrement = (Button)findViewById(R.id.minutes_increment);
        minutesIncrement.setOnClickListener(this);
        minutesDecrement = (Button)findViewById(R.id.minutes_decrement);
        minutesDecrement.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        int currentHour;
        int currentMinute;
        switch (view.getId()){
            case R.id.hours_increment:
                currentHour = Integer.parseInt(hour.getText().toString());
                hour.setText(fillMissingDigit((currentHour+1)%HOURS_PER_DAY));
                break;
            case R.id.hours_decrement:
                currentHour = Integer.parseInt(hour.getText().toString());
                hour.setText(fillMissingDigit(((currentHour - 1) < 0) ? HOURS_PER_DAY - 1 : currentHour - 1));
                break;
            case R.id.minutes_increment:
                currentMinute = Integer.parseInt(minute.getText().toString());
                minute.setText(fillMissingDigit((currentMinute + 1) % MINUTES_PER_HOUR));
                break;
            case R.id.minutes_decrement:
                currentMinute = Integer.parseInt(minute.getText().toString());
                minute.setText(fillMissingDigit(((currentMinute-1) < 0) ? MINUTES_PER_HOUR-1 : currentMinute-1));
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        String s = ((EditText) view).getText().toString();
        if(!b) {
            if (view == hour) {
                int value = Integer.parseInt(s);
                value = value % HOURS_PER_DAY;
                hour.setText(fillMissingDigit(value));
            } else {
                int value = Integer.parseInt(s);
                value = value % MINUTES_PER_HOUR;
                minute.setText(fillMissingDigit(value));
            }
        }
    }

    private String fillMissingDigit(int value) {
        return (value < 10) ? "0" + value : String.valueOf(value);
    }

    public CalendarTime getCalendarTime() {
        return new CalendarTime(Integer.parseInt(hour.getText().toString()),
                Integer.parseInt(minute.getText().toString()));
    }

}
