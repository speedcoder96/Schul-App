package de.szut.ita13.app.schulapp.calendar.container;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.adapter.CalendarAdapter;
import de.szut.ita13.app.schulapp.calendar.dao.CalendarDataSource;

/**
 * Created by Rene on 29.04.2015.
 */
public class Calendar  {

    public static CalendarMap calendarMap;
    public static CalendarDataSource dataSource;

    private static FragmentActivity calendarActivity;
    private CalendarMonth[] calendarMonths;

    private CalendarViewPagerAdapter calendarViewPagerAdapter;
    private ViewPager viewPager;

    public Calendar(FragmentActivity calendarActivity) {
        this.calendarMap = new CalendarMap();
        dataSource = new CalendarDataSource(calendarActivity);

        Calendar.calendarActivity = calendarActivity;
        calendarActivity.setContentView(R.layout.activity_calendar);

        viewPager = (ViewPager)calendarActivity.findViewById(R.id.pager);

        calendarMonths = CalendarMonth.generateDefaultMonths(this, 12, 2015);

        setAdapter();
    }

    public static FragmentActivity getCalendarActivity() {
        return calendarActivity;
    }

    public CalendarMonth[] getCalendarMonths() {
        return calendarMonths;
    }

    public CalendarMonth getCalendarMonth(int pointer) {
        switch(pointer) {
            case CalendarMonth.PREVIOUS_MONTH:
                return calendarMonths[CalendarMonth.PREVIOUS_MONTH];
            case CalendarMonth.CURRENT_MONTH:
                return calendarMonths[CalendarMonth.CURRENT_MONTH];
            case CalendarMonth.NEXT_MONTH:
                return calendarMonths[CalendarMonth.NEXT_MONTH];
            default:
                return null;
        }
    }

    private void setAdapter() {
        calendarViewPagerAdapter = null;
        calendarViewPagerAdapter = new CalendarViewPagerAdapter(calendarActivity.getSupportFragmentManager(), this);
        viewPager.setAdapter(calendarViewPagerAdapter);
        Log.d("Calendar", "Child Count : " + viewPager.getChildCount());
        viewPager.setCurrentItem(1);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    calendarMonths = CalendarMonth.generateDefaultMonths(Calendar.this, calendarMonths[position].getMonthIndex(),
                            calendarMonths[position].getYear());
                    viewPager.setAdapter(null);
                    setAdapter();
                    calendarViewPagerAdapter.notifyDataSetChanged();
                } else if (position == 2) {
                    calendarMonths = CalendarMonth.generateDefaultMonths(Calendar.this, calendarMonths[position].getMonthIndex(),
                            calendarMonths[position].getYear());
                    viewPager.setAdapter(null);
                    setAdapter();
                    calendarViewPagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class CalendarViewPagerAdapter extends FragmentStatePagerAdapter {

        private Calendar calendar;

        public CalendarViewPagerAdapter(FragmentManager fm, Calendar calendar) {
            super(fm);
            this.calendar = calendar;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d("Calendar", calendar.getCalendarMonths()[position].getTitleString());
            return calendar.getCalendarMonths()[position].getTitleString();
        }

        @Override
        public Fragment getItem(int position) {
            CalendarMonth calendarMonth = calendar.getCalendarMonths()[position];
            CalendarViewPagerFragment fragment = new CalendarViewPagerFragment(calendar, calendarMonth);
            return fragment;
        }

        @Override
        public int getCount() {
            return calendar.getCalendarMonths().length;
        }
    }

    public class CalendarViewPagerFragment extends Fragment {

        public CalendarAdapter calendarAdapter;

        public CalendarViewPagerFragment(Calendar calendar, CalendarMonth calendarMonth) {
            calendarAdapter = new CalendarAdapter(getCalendarActivity(), calendar, calendarMonth);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.calendar_view_pager_fragment_layout, container, false);

            ListView listView = (ListView)rootView.findViewById(R.id.calendar);
            listView.setAdapter(calendarAdapter);


            return rootView;
        }
    }

}
