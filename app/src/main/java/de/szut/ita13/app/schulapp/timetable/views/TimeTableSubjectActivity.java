package de.szut.ita13.app.schulapp.timetable.views;

import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.timetable.dialogs.TimeTableSubjectDialog;

/**
 * Created by Michel� on 15.06.2015.
 */
public class TimeTableSubjectActivity extends ActionBarActivity {

    private FragmentManager fm;
    private ArrayList<TimeTableSubject> subjects;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getFragmentManager();

        setContentView(R.layout.activity_main);
        Button dfragbutton = (Button) findViewById(R.id.dfragbutton);

        subjects = new ArrayList<TimeTableSubject>();
        TimeTableSubject subject = new TimeTableSubject(true);
        subject.setRoom("25b");
        subject.setTeacher("D?nnSCH?DEL");
        subject.setSubjectName("Sport");
        subject.setColor(this.getResources().getColor(R.color.green));
        TimeTableSubject subject1 = new TimeTableSubject(true);
        subject1.setRoom("25b");
        subject1.setTeacher("UTF8?");
        subject1.setSubjectName("Sport");
        subject1.setColor(this.getResources().getColor(R.color.green));
        TimeTableSubject subject2 = new TimeTableSubject(true);
        subject2.setRoom("25b");
        subject2.setTeacher("Kuczi");
        subject2.setSubjectName("Sport");
        subject2.setColor(this.getResources().getColor(R.color.green));
        subjects.add(subject);
        subjects.add(subject1);
        subjects.add(subject2);

        dfragbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                TimeTableSubjectDialog dialog = new TimeTableSubjectDialog(TimeTableSubjectActivity.this, subjects);
                dialog.show(fm, "DialogFragment");
            }
        });
    }

}
