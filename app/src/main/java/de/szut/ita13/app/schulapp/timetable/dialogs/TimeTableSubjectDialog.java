package de.szut.ita13.app.schulapp.timetable.dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.timetable.adapter.TimeTableSubjectAdapter;
import de.szut.ita13.app.schulapp.timetable.container.TimeTableSubject;

/**
 * Created by Michel� on 18.06.2015.
 */
public class TimeTableSubjectDialog extends DialogFragment implements View.OnClickListener {


    private ListView listView;
    private TimeTableSubjectAdapter adapter;
    private Context context;
    private ArrayList<TimeTableSubject> subjects;

    public TimeTableSubjectDialog(Context context, ArrayList<TimeTableSubject> subjects) {
        this.context = context;
        this.subjects = subjects;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("DialogFragment Tutorial");
        View rootView = inflater.inflate(R.layout.timetable_subject_list_layout, container,
                false);

        listView = (ListView) rootView.findViewById(R.id.subjectList);
        adapter = new TimeTableSubjectAdapter(subjects, this);
        listView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        TimeTableSubject subject = (TimeTableSubject) view.getTag();
        this.getDialog().cancel();
        Toast.makeText(context, ":O " + subject.getTeacher(), Toast.LENGTH_LONG).show();
    }
}
