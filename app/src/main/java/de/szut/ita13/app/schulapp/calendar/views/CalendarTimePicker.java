package de.szut.ita13.app.schulapp.calendar.views;

import android.content.Context;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.logging.SimpleFormatter;

import de.szut.ita13.app.schulapp.R;


/**
 * Created by ramazan and rene on 12.05.2015.
 */
public class CalendarTimePicker extends RelativeLayout implements TextWatcher {

    public static final String DEFAULT_SEPERATOR = ":";
    public static final int MAX_TIME_LENGTH = 5;

    private EditText time;
    private Button hoursIncrement, hoursDecrement;
    private Button minutesIncrement, minutesDecrement;

    public CalendarTimePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        inflate(getContext(), R.layout.calendar_timepicker, this);

        time = (EditText)findViewById(R.id.time);
        time.setText("00:00");
        time.setSelection(0);
        time.setClickable(false);
        time.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_TIME_LENGTH)});
        time.addTextChangedListener(this);


        hoursIncrement = (Button)findViewById(R.id.hours_increment);
        hoursDecrement = (Button)findViewById(R.id.hours_decrement);
        minutesIncrement = (Button)findViewById(R.id.minutes_increment);
        minutesDecrement = (Button)findViewById(R.id.minutes_decrement);



    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
