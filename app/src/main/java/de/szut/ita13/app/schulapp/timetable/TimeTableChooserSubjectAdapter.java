package de.szut.ita13.app.schulapp.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Michelé on 28.06.2015.
 */
public class TimeTableChooserSubjectAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private LayoutInflater layoutInflater;
    private TimeTableDataSource timeTableDataSource;
    private int row;
    private int column;
    private TimeTableChooserSubjectDialog subjectDialog;

    public TimeTableChooserSubjectAdapter(Context context, int row, int column, TimeTableChooserSubjectDialog subjectDialog){
        this.context = context;
        this.row = row;
        this.column = column;
        this.subjectDialog = subjectDialog;
        timeTableDataSource = new TimeTableDataSource(context);
        layoutInflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        view = layoutInflater.inflate(R.layout.timetable_chooser_item_layout, viewGroup, false);
        TimeTableItem item = TimeTableActivity.subjects.get(position);
        TextView currentSubject = (TextView) view.findViewById(R.id.itemChooserTextView);
        currentSubject.setOnClickListener(this);
        currentSubject.setTag(item);
        currentSubject.setText(TimeTableActivity.subjects.get(position).getInformation());
        return view;
    }


    @Override
    public void onClick(View view) {
        TimeTableLessonItem lessonItem = new TimeTableLessonItem();
        TimeTableItem tableItem = (TimeTableItem) view.getTag();
        lessonItem.setWeek(0);
        lessonItem.setSubjectID(tableItem.getID());
        lessonItem.setRow(row);
        lessonItem.setColumn(column);
        timeTableDataSource.open();
        timeTableDataSource.insertLessonItem(lessonItem);
        timeTableDataSource.close();
        subjectDialog.getDialog().dismiss();
        subjectDialog.getItemDialog().getDialog().dismiss();
    }
}
