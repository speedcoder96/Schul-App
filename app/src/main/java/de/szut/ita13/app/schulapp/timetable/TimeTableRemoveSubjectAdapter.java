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
 * Created by Michelé on 27.06.2015.
 */
public class TimeTableRemoveSubjectAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private LayoutInflater layoutInflater;
    private TimeTableDataSource timeTableDataSource;



    public TimeTableRemoveSubjectAdapter(Context context){
        TimeTableActivity.timeTableModifier.dataSource.open();
        this.context = context;
        TimeTableActivity.timeTableModifier.dataSource.close();
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        timeTableDataSource = new TimeTableDataSource(context);
    }

    @Override
    public int getCount() {
        return TimeTableActivity.subjects.size();
    }

    @Override
    public Object getItem(int i) {
        return TimeTableActivity.subjects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.timetable_remove_subject_layout, viewGroup, false);
        TimeTableItem item = TimeTableActivity.subjects.get(position);
        TextView currentSubject = (TextView)view.findViewById(R.id.removeCurrentSubject);
        currentSubject.setOnClickListener(this);
        currentSubject.setTag(item);
        currentSubject.setText("Fach : " + item.getSubject() + "  Raum : " + item.getRoom());
        return view;
    }

    @Override
    public void onClick(View view) {
        timeTableDataSource.open();
        timeTableDataSource.deleteTimeTableItem((TimeTableItem) view.getTag());
        TimeTableActivity.subjects = timeTableDataSource.getItems();
        timeTableDataSource.close();
        notifyDataSetChanged();
    }
}
