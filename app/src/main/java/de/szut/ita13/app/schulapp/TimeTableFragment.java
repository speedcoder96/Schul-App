package de.szut.ita13.app.schulapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Rene on 21.06.2015.
 */
public class TimeTableFragment extends Fragment {
    private TimeTableAdapter timeTableAdapter;

    public TimeTableFragment(Context context, TimeTable timeTable){
        timeTableAdapter = new TimeTableAdapter(context, timeTable);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timetable_pagefragment_layout, container, false);
        ListView listView = (ListView) view.findViewById(R.id.timetableList);
        listView.setAdapter(timeTableAdapter);
        return view;
    }
}
