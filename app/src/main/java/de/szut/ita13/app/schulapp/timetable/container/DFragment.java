package de.szut.ita13.app.schulapp.timetable.container;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.timetable.adapter.TimeTableSubjectAdapter;

/**
 * Created by Michelé on 18.06.2015.
 */
public class DFragment extends DialogFragment implements View.OnClickListener {

    private ArrayList<TimeTableSubject> subjects;
    private ListView listView;
    private TimeTableSubjectAdapter adapter;
    private Context context;

    public DFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timetable_subject_list_layout, container,
                false);
        getDialog().setTitle("DialogFragment Tutorial");

        listView = (ListView) rootView.findViewById(R.id.subjectList);
        subjects = new ArrayList<TimeTableSubject>();
        TimeTableSubject subject = new TimeTableSubject(true);
        subject.setRoom("25b");
        subject.setTeacher("D?nnSCH?DEL");
        subject.setSubjectName("Sport");
        TimeTableSubject subject1 = new TimeTableSubject(true);
        subject1.setRoom("25b");
        subject1.setTeacher("UTF8?");
        subject1.setSubjectName("Sport");
        TimeTableSubject subject2 = new TimeTableSubject(true);
        subject2.setRoom("25b");
        subject2.setTeacher("Kuczi");
        subject2.setSubjectName("Sport");
        subjects.add(subject);
        subjects.add(subject1);
        subjects.add(subject2);
        adapter = new TimeTableSubjectAdapter(subjects, this);
        listView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        TimeTableSubject subject = (TimeTableSubject) view.getTag();
        Toast.makeText(context, ":O "+ subject.getTeacher(), Toast.LENGTH_LONG).show();
    }
}