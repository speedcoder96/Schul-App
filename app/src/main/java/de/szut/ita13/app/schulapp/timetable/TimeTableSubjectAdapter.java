package de.szut.ita13.app.schulapp.timetable;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Michelé on 24.06.2015.
 */
public class TimeTableSubjectAdapter extends BaseAdapter {

    private View view;

    public TimeTableSubjectAdapter(View view){
        this.view = view;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       //TODO add ListView the Items from the Database
        /**
         * View subject = inflater.inflate(R.layout.timetable_subject_layout, container, false);
         TextView roomView = (TextView) subject.findViewById(R.id.room);
         roomView.setText("124");
         TextView teacherView = (TextView) subject.findViewById(R.id.teacher);
         teacherView.setText("Kuczi");
         TextView subjectView = (TextView)subject.findViewById(R.id.subject);
         subjectView.setText("ProgT");
         */
        return null;
    }
}
