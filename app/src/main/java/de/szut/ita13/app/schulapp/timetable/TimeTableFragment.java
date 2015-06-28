package de.szut.ita13.app.schulapp.timetable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Rene on 21.06.2015.
 */
public class TimeTableFragment extends Fragment implements TimeTableUIUpdater {

    private TimeTableAdapter timeTableAdapter;
    private TimeTablePagerAdapter timeTablePagerAdapter;

    public TimeTableFragment(TimeTableActivity timeTableActivity, TimeTable timeTable){
        timeTableAdapter = new TimeTableAdapter(timeTableActivity, timeTable);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timetable_pagefragment_layout, container, false);
        ListView listView = (ListView) view.findViewById(R.id.timetableList);
        listView.setAdapter(timeTableAdapter);
        return view;
    }

    public void setTimeTablePagerAdapter(TimeTablePagerAdapter timeTablePagerAdapter) {
        this.timeTablePagerAdapter = timeTablePagerAdapter;
    }

    @Override
    public void changedUI() {
        timeTableAdapter.notifyDataSetChanged();
    }
}
