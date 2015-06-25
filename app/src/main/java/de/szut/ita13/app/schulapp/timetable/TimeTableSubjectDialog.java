package de.szut.ita13.app.schulapp.timetable;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Michelé on 24.06.2015.
 */
public class TimeTableSubjectDialog extends DialogFragment{

    private View view;
    private TimeTableSubjectAdapter timeTableSubjectAdapter;

    public TimeTableSubjectDialog(View view){
        this.view = view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timetable_subject_list_layout, container,
                false);
        getDialog().setTitle("Fachauswahl");

        //Dem Dialog die Werte hizufügen
        //Fachname Raum Lehrer
        ListView listView = (ListView)rootView.findViewById(R.id.subjectList);
        timeTableSubjectAdapter = new TimeTableSubjectAdapter(view);
        listView.setAdapter(timeTableSubjectAdapter);


        return rootView;
    }
}
