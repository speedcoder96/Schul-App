package de.szut.ita13.app.schulapp.timetable.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.timetable.container.TimeTableSubject;
import de.szut.ita13.app.schulapp.timetable.dialogs.TimeTableSubjectDialog;

/**
 * Created by Michel� on 15.06.2015.
 */
public class TimeTableSubjectAdapter extends BaseAdapter implements View.OnClickListener {

    private ArrayList<TimeTableSubject> subjects;
    private final LayoutInflater inflator;
    private TimeTableSubjectDialog dialog;
    private Context context;

    public TimeTableSubjectAdapter(ArrayList<TimeTableSubject> subjects, TimeTableSubjectDialog dialog){
        this.subjects = subjects;
        context = dialog.getActivity().getApplicationContext();
        inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dialog = dialog;
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int i) {
        return subjects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflator.inflate(R.layout.timetable_subject_layout, viewGroup, false);
        TimeTableSubject timeTableSubject = (TimeTableSubject) getItem(i);
        view.setTag(timeTableSubject);
        view.setOnClickListener(this);

        TextView tw = (TextView) view.findViewById(R.id.room);
        TextView tw1 = (TextView) view.findViewById(R.id.name);
        TextView tw2 = (TextView) view.findViewById(R.id.teacher);

        view.setBackgroundColor(timeTableSubject.getColor());
        tw.setText(timeTableSubject.getRoom());
        tw1.setText(timeTableSubject.getSubjectName());
        tw2.setText(timeTableSubject.getTeacher());

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
