package de.szut.ita13.app.schulapp.timetable.container;


import de.szut.ita13.app.schulapp.R;

/**
 * Created by Rene on 23.02.2015.
 */
public class TimeTableElement {

    private static final int[] LAYOUT_IDS = {
            R.id.time, R.id.monday, R.id.tuesday, R.id.wednesday, R.id.thursday, R.id.friday
    };

    public int getLayoutId(int index) {
        return LAYOUT_IDS[index];
    }

    public int getLayoutIdCount() {
        return LAYOUT_IDS.length;
    }

}
