package de.szut.ita13.app.schulapp.timetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import javax.security.auth.Subject;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.timetable.container.DFragment;
import de.szut.ita13.app.schulapp.timetable.container.TimeTableSubject;

/**
 * Created by Michel� on 15.06.2015.
 */
public class TimeTableSubjectAdapter extends BaseAdapter {
    private ArrayList<TimeTableSubject> subjects;
    private LayoutInflater inflator;
    private DFragment dialog;
    public TimeTableSubjectAdapter(ArrayList<TimeTableSubject> subjects, DFragment dialog){
        this.subjects = subjects;
        inflator = (LayoutInflater) dialog.getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        view.setTag(getItem(i));
        view.setOnClickListener(dialog);
        TextView tw = (TextView) view.findViewById(R.id.room);
        TextView tw1 = (TextView) view.findViewById(R.id.name);
        TextView tw2 = (TextView) view.findViewById(R.id.teacher);

        TimeTableSubject tts = (TimeTableSubject) getItem(i);
        tw.setText(tts.getRoom());
        tw1.setText(tts.getSubjectName());
        tw2.setText(tts.getTeacher());

        return view;
    }
}
