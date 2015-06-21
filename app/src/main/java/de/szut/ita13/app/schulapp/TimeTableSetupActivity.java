package de.szut.ita13.app.schulapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.picker.CalendarTimePicker;
import de.szut.ita13.app.schulapp.picker.ValuePicker;
import de.szut.ita13.app.schulapp.TimeTableSetupBundle;

/**
 * Created by Rene on 19.06.2015.
 */
public class TimeTableSetupActivity extends ActionBarActivity {

    public static final String START_TIME_KEY =  "starttime";
    public static final String BREAK_LENGTH_KEY = "breaklength";
    public static final String BREAK_INTERVAL_KEY = "breakinterval";
    public static final String TWO_WEEKS_RHYTHM_KEY = "twoweeksrhythm";
    public static final String LESSON_LENGTH_KEY = "lessonlength";
    public static final String LESSON_COUNT_KEY = "lessoncount";

    private CalendarTimePicker startTimePicker;
    private ValuePicker lessonCountPicker;
    private ValuePicker lessonLengthPicker;
    private ValuePicker breakLengthPicker;
    private ValuePicker breakIntervalPicker;
    private CheckBox twoWeekRhythmCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_setup_layout);

        startTimePicker = (CalendarTimePicker)findViewById(R.id.setup_firstlesson_picker);
        lessonCountPicker = (ValuePicker)findViewById(R.id.setup_lessonamount_picker);
        lessonLengthPicker = (ValuePicker)findViewById(R.id.setup_lessonlength_picker);
        breakLengthPicker = (ValuePicker)findViewById(R.id.setup_breaklength_picker);
        breakIntervalPicker = (ValuePicker)findViewById(R.id.setup_breakinterval_picker);
        twoWeekRhythmCheckBox = (CheckBox)findViewById(R.id.setup_twoweekrhythm_picker);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setup, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_set:
                //TODO vorher noch ueberpruefung der einzelnen Werte
                Intent resultIntent = new Intent();
                resultIntent.putExtra(START_TIME_KEY, startTimePicker.getCalendarTime().getTimeString());
                resultIntent.putExtra(BREAK_LENGTH_KEY, breakLengthPicker.getCurrentValue());
                resultIntent.putExtra(BREAK_INTERVAL_KEY, breakIntervalPicker.getCurrentValue());
                resultIntent.putExtra(TWO_WEEKS_RHYTHM_KEY, twoWeekRhythmCheckBox.isChecked());
                resultIntent.putExtra(LESSON_LENGTH_KEY, lessonLengthPicker.getCurrentValue());
                resultIntent.putExtra(LESSON_COUNT_KEY, lessonCountPicker.getCurrentValue());
                setResult(RESULT_OK, resultIntent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
