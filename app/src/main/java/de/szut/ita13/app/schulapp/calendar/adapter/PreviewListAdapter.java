package de.szut.ita13.app.schulapp.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.CalendarAppointment;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;
import de.szut.ita13.app.schulapp.calendar.container.Preview;

/**
 * Created by Rene on 15.06.2015.
 */
public class PreviewListAdapter extends BaseAdapter {

    private Preview preview;
    private final LayoutInflater layoutInflater;

    public PreviewListAdapter(Context context, Preview preview) {
        this.preview = preview;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return preview.size();
    }

    @Override
    public Object getItem(int position) {
        return preview.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.calendar_preview_entry_layout, parent, false);
        TextView subjectTextView = (TextView)convertView.findViewById(R.id.subject);
        TextView startTimeTextView = (TextView)convertView.findViewById(R.id.start_time);
        TextView endTimeTextView = (TextView)convertView.findViewById(R.id.end_time);
        TextView subjectDateTextView = (TextView)convertView.findViewById(R.id.subjectDate);

        CalendarAppointment appointment = (CalendarAppointment)getItem(position);
        subjectTextView.setText(appointment.getSubject());
        startTimeTextView.setText(appointment.getStartTime().getTimeString());
        endTimeTextView.setText(appointment.getEndTime().getTimeString());
        subjectDateTextView.setText(appointment.getCalendarDate().
                getDateString(CalendarDate.DEFAULT_DATE_FORMAT));


        return convertView;
    }
}
