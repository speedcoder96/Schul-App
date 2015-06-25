package de.szut.ita13.app.schulapp.timetable;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import de.szut.ita13.app.schulapp.R;

/**
 * Created by Michelé on 24.06.2015.
 */
public class TimeTableItemDialog extends DialogFragment implements AlertDialog.OnClickListener {

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

        return rootView;
    }


}
