package de.szut.ita13.app.schulapp.timetable;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


import de.szut.ita13.app.schulapp.R;

/**
 * Created by Michel� on 24.06.2015.
 */
public class TimeTableItemDialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG = TimeTableItemDialog.class.getSimpleName();

    private View view;
    private TimeTableItemAdapter timeTableItemAdapter;
    private Context context;

    public TimeTableItemDialog(View view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timetable_subject_list_layout, container,
                false);
        ListView listView = (ListView)rootView.findViewById(R.id.subjectList);
        timeTableItemAdapter = new TimeTableItemAdapter(view, context);
        listView.setAdapter(timeTableItemAdapter);
        getDialog().setTitle("Fachauswahl");

        Button addSubject = (Button) rootView.findViewById(R.id.addSubjectButton);
        addSubject.setOnClickListener(this);
        Button removeSubject = (Button) rootView.findViewById(R.id.removeSubjectButton);



        return rootView;
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, TimeTableSubjectActivity.class);
        context.startActivity(intent);

    }
}
