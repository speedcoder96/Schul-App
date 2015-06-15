package de.szut.ita13.app.schulapp.timetable.container;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.timetable.adapter.TimeTableSubjectAdapter;

/**
 * Created by Michelé on 15.06.2015.
 */
public class TimeTableSubjectActivity extends ActionBarActivity {
    private TimeTableSubjectAdapter adapter;
    private ArrayList<TimeTableSubject> subjects;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_subject_list_layout);
        listView = (ListView) findViewById(R.id.subjectList);
        subjects = new ArrayList<TimeTableSubject>();
        TimeTableSubject subject = new TimeTableSubject(true);
        subject.setRoom("25b");
        subject.setTeacher("DünnSCHÄDEL");
        subject.setSubjectName("Sport");
        TimeTableSubject subject1 = new TimeTableSubject(true);
        subject1.setRoom("25b");
        subject1.setTeacher("DünnSCHÄDEL");
        subject1.setSubjectName("Sport");
        TimeTableSubject subject2 = new TimeTableSubject(true);
        subject2.setRoom("25b");
        subject2.setTeacher("DünnSCHÄDEL");
        subject2.setSubjectName("Sport");
        subjects.add(subject);
        subjects.add(subject1);
        subjects.add(subject2);
        adapter  = new TimeTableSubjectAdapter(subjects, this.getApplicationContext());
        listView.setAdapter(adapter);
    }

}
