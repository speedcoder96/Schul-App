package de.szut.ita13.app.schulapp.calendar.container;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.adapter.CalendarAdapter;
import de.szut.ita13.app.schulapp.calendar.adapter.PreviewListAdapter;
import de.szut.ita13.app.schulapp.calendar.dao.CalendarDataSource;
import de.szut.ita13.app.schulapp.calendar.progress.CalendarProgressDialog;
import de.szut.ita13.app.schulapp.newutils.DateUtil;

/**
 * Created by Rene on 29.04.2015.
 */
public class Calendar  {

    public static CalendarMap calendarMap;
    public static CalendarDataSource dataSource;
    public static CalendarProgressDialog progressDialog;

    private static FragmentActivity calendarActivity;
    private CalendarMonth[] calendarMonths;

    private CalendarViewPagerAdapter calendarViewPagerAdapter;
    private ViewPager viewPager;

    private PreviewListAdapter previewListAdapter;

    public Calendar(FragmentActivity calendarActivity) {

        this.calendarMap = new CalendarMap();
        dataSource = new CalendarDataSource(calendarActivity);

        Calendar.calendarActivity = calendarActivity;
        calendarActivity.setContentView(R.layout.activity_calendar);

        viewPager = (ViewPager)calendarActivity.findViewById(R.id.pager);

        int[] actualDate = DateUtil.getActualDate();

        calendarMonths = CalendarMonth.generateDefaultMonths(this, actualDate[DateUtil.ACTUAL_DATE_MONTH],
                actualDate[DateUtil.ACTUAL_DATE_YEAR]);

        Preview preview = Preview.createPreview(calendarMonths);
        previewListAdapter = new PreviewListAdapter(calendarActivity.getApplicationContext(), preview);

        setAdapter();
    }

    public static FragmentActivity getCalendarActivity() {
        return calendarActivity;
    }

    public CalendarMonth[] getCalendarMonths() {
        return calendarMonths;
    }

    private void setAdapter() {
        calendarViewPagerAdapter = null;
        calendarViewPagerAdapter = new CalendarViewPagerAdapter(calendarActivity.getSupportFragmentManager(), this);
        viewPager.setAdapter(calendarViewPagerAdapter);
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

            TextView previewTitle = (TextView)rootView.findViewById(R.id.appointments_preview_title);
            int pCount = previewListAdapter.getCount();
            if(pCount != 0) {
                previewTitle.setText("Sie haben " + pCount + ((pCount == 1) ? " Termin" : " Termine") + " in den nächsten 7 Tagen:");
                ListView previewList = (ListView)rootView.findViewById(R.id.appointments_preview);
                previewList.setAdapter(previewListAdapter);
            }
            previewListAdapter.notifyDataSetChanged();

            return rootView;
        }
    }

}
