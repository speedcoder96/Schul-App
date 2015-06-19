package de.szut.ita13.app.schulapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
                TimeTableSetupBundle bundle = new TimeTableSetupBundle.Builder(this)
                        .setStartTime(startTimePicker.getCalendarTime())
                        .setBreakLength(breakLengthPicker.getCurrentValue())
                        .setBreakInterval(breakIntervalPicker.getCurrentValue())
                        .setTwoWeeksRhythm(twoWeekRhythmCheckBox.isActivated())
                        .setLessonLength(lessonLengthPicker.getCurrentValue())
                        .setLessonCount(lessonCountPicker.getCurrentValue())
                        .build();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
