package de.szut.ita13.app.schulapp.timetable;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Michelé on 28.06.2015.
 */
public class TimeTableChooserSubjectDialog extends DialogFragment {

    public static final String TAG = TimeTableItemDialog.class.getSimpleName();

    private Context context;
    private int row;
    private int column;
    private TimeTableItemDialog itemDialog;

    public TimeTableChooserSubjectDialog(Context context, int row, int column, TimeTableItemDialog itemDialog){
        this.context = context;
        this.row = row;
        this.column = column;
        this.itemDialog = itemDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timetable_show_subjects_list_layout, container,
                false);
        ListView listView = (ListView)rootView.findViewById(R.id.showSubjectList);
        TimeTableChooserSubjectAdapter timeTableChooseSubjecAdapter= new TimeTableChooserSubjectAdapter(context, row, column, this);
        listView.setAdapter(timeTableChooseSubjecAdapter);
        getDialog().setTitle("Faecher");
        return rootView;
    }

    public TimeTableItemDialog getItemDialog() {
        return itemDialog;
    }
}
