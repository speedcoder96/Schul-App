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


import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Michelé on 24.06.2015.
 */
public class RemoveSubjectDialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG = TimeTableItemDialog.class.getSimpleName();

    private TimeTableRemoveSubjectAdapter timeTableRemoveSubjectAdapter;
    private Context context;

    public RemoveSubjectDialog(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timetable_show_subjects_list_layout, container,
                false);
        ListView listView = (ListView)rootView.findViewById(R.id.showSubjectList);
        timeTableRemoveSubjectAdapter = new TimeTableRemoveSubjectAdapter(context);
        listView.setAdapter(timeTableRemoveSubjectAdapter);
        getDialog().setTitle("Fach entfernen");
        return rootView;
    }


    @Override
    public void onClick(View view) {

    }
}
