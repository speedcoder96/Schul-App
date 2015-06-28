package de.szut.ita13.app.schulapp.timetable;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Michele on 21.06.2015.
 */
public class TimeTableAdapter extends BaseAdapter {

    private TimeTableMatrix timeTableMatrix;
    private LayoutInflater layoutInflater;
    private TimeTableActivity timeTableActivity;

    public TimeTableAdapter(TimeTableActivity timeTableActivity, TimeTableMatrix timeTableMatrix) {
        this.timeTableActivity = timeTableActivity;
        this.timeTableMatrix = timeTableMatrix;
        layoutInflater = (LayoutInflater)timeTableActivity.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return timeTableMatrix.getRowCount();
    }

    @Override
    public Object getItem(int i) {
        return timeTableMatrix.getRowAt(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Log.d("TimeTableAdapter", "Tes");
        if(position == 0){

            view = layoutInflater.inflate(R.layout.timetable_header_layout, viewGroup, false);

            for(int i = 0; i < TimeTableRowItem.IDS.length; i++){
                TextView textView = (TextView) view.findViewById(TimeTableRowItem.IDS[i]);
                textView.setText(TimeTableRowItem.LABELS[i]);
            }
        } else{

            view = layoutInflater.inflate(R.layout.timetable_row_layout, viewGroup, false);
            TimeTableRowItem rowItem = timeTableMatrix.getRowAt(position);
            TextView timeTextView = (TextView) view.findViewById(TimeTableRowItem.IDS[0]);
            timeTextView.setText(rowItem.getStartTime().getTimeString());

            for(int i = 0; i < timeTableMatrix.getColumnCount(position); i++){
                TextView itemTextView = (TextView) view.findViewById(TimeTableRowItem.IDS[i + 1]);
                itemTextView.setOnClickListener(timeTableActivity);
                if(timeTableMatrix.getItemAt(position, i) == null){
                    itemTextView.setText("" + "\n" + "");
                } else {
                    itemTextView.setText(TimeTableActivity.subjectMap.get(timeTableMatrix.getItemAt(position, i).getSubjectID()).getInformation());
                }
                itemTextView.setTag(new int[]{position,i});
            }
        }
        return view;
    }
}
