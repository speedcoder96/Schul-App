package de.szut.ita13.app.schulapp.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.CalendarAppointment;
import de.szut.ita13.app.schulapp.calendar.container.CalendarDate;
import de.szut.ita13.app.schulapp.calendar.container.CalendarTime;
import de.szut.ita13.app.schulapp.calendar.views.CalendarDateViewer;

/**
 * Created by Rene on 11.05.2015.
 */
public class CalendarAppointmentListAdapter extends BaseAdapter {

    private CalendarDateViewer viewer;
    private CalendarDate calendarDate;
    private ArrayList<CalendarAppointment> calendarAppointments;
    private LayoutInflater calendarAppointmentListInflater;

    public CalendarAppointmentListAdapter(CalendarDateViewer viewer) {
        CalendarAppointment.calendarAppointmentListAdapter = this;
        this.viewer = viewer;
        this.calendarDate = viewer.getCalendarDate();
        this.calendarAppointments = calendarDate.getCalendarAppointments();
        this.calendarAppointmentListInflater = (LayoutInflater) viewer.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return calendarAppointments.size();
    }

    @Override
    public Object getItem(int position) {
        return calendarAppointments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        view = calendarAppointmentListInflater.inflate(R.layout.calendardate_appointmentlist_layout, parent, false);
        view.setOnClickListener(viewer);

        TextView cTime = (TextView) view.findViewById(R.id.calendardate_time);
        TextView cSubject = (TextView) view.findViewById(R.id.calendardate_subject);
        CalendarAppointment appointment = calendarAppointments.get(position);
        CalendarTime time = appointment.getStartTime();
        String subject = appointment.getSubject();
        cTime.setText(time.getTimeString());
        cSubject.setText(subject);
        view.setTag(appointment);
        return view;
    }
}
