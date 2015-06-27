package de.szut.ita13.app.schulapp.timetable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Michelé on 27.06.2015.
 */
public class TimeTableSubjectActivity extends ActionBarActivity {

    public static final String TEACHER_KEY = "teacher";
    public static final String ROOM_KEY = "room";
    public static final String SUBJECT_KEY = "subject";

    private EditText subject;
    private EditText teacher;
    private EditText room;
    private TimeTableDataSource timeTableDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_add_subject);

        subject = (EditText) findViewById(R.id.subjectEditText);
        teacher = (EditText) findViewById(R.id.teacherEditText);
        room = (EditText) findViewById(R.id.roomEditText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timetable_add_subject, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_subject:
                Log.d("Switch", "addItem");
                //TODO vorher noch ueberpruefung der einzelnen Werte
                TimeTableItem timeTableItem = new TimeTableItem.Builder(null)
                        .setTeacher(teacher.getText().toString())
                        .setRoom(room.getText().toString())
                        .setSubject(subject.getText().toString())
                        .build();
                timeTableDataSource =  new TimeTableDataSource(getApplicationContext());
                timeTableDataSource.open();
                timeTableDataSource.insertTimetableItem(timeTableItem);
                TimeTableActivity.subjects = timeTableDataSource.getItems();
                timeTableDataSource.close();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
