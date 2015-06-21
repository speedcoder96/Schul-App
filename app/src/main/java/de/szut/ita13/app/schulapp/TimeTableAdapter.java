package de.szut.ita13.app.schulapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Michelé on 21.06.2015.
 */
public class TimeTableAdapter extends BaseAdapter {

    private TimeTable timeTable;
    private LayoutInflater layoutInflater;

    public TimeTableAdapter(Context context, TimeTable timeTable){
        this.timeTable = timeTable;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return timeTable.getRowCount();
    }

    @Override
    public Object getItem(int i) {
        return timeTable.getRowItem(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(position == 0){

            view = layoutInflater.inflate(R.layout.timetable_header_layout, viewGroup, false);

            for(int i = 0; i < TimeTable.RowItem.IDS.length; i++){
                TextView textView = (TextView) view.findViewById(TimeTable.RowItem.IDS[i]);
                textView.setText(TimeTable.RowItem.LABELS[i]);
            }
        } else{

            view = layoutInflater.inflate(R.layout.timetable_row_layout, viewGroup, false);
            TimeTable.RowItem rowItem = (TimeTable.RowItem) getItem(position);
            TextView timeTextView = (TextView) view.findViewById(TimeTable.RowItem.IDS[0]);
            timeTextView.setText(rowItem.getStartTime().getTimeString());

            for(int i = 0; i < rowItem.getItems().size(); i++){
                TextView itemTextView = (TextView) view.findViewById(TimeTable.RowItem.IDS[i + 1]);
                itemTextView.setText(rowItem.getItems().get(i).getInformation());
            }
        }
        return view;
    }
}
