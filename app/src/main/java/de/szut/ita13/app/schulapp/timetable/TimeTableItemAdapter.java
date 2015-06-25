package de.szut.ita13.app.schulapp.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Michelé on 24.06.2015.
 */
public class TimeTableItemAdapter extends BaseAdapter {

    private View view;
    private LayoutInflater layoutInflater;
    private ArrayList<TimeTableItem> items;

    public TimeTableItemAdapter(View view, Context context) {
        this.view = view;
        TimeTableActivity.timeTableModifier.dataSource.open();
        items = TimeTableActivity.timeTableModifier.dataSource.getItems();
        TimeTableActivity.timeTableModifier.dataSource.close();
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.timetable_subject_layout, viewGroup, false);

        TimeTableItem item = (TimeTableItem)getItem(position);

        TextView roomView = (TextView)view.findViewById(R.id.room);
        TextView teacherView = (TextView)view.findViewById(R.id.teacher);
        TextView subjectNameView = (TextView)view.findViewById(R.id.name);

        roomView.setText(item.getRoom());
        teacherView.setText(item.getTeacher());
        subjectNameView.setText(item.getSubject());


        return view;
    }
}
