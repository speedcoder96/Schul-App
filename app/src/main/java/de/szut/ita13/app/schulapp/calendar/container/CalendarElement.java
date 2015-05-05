package de.szut.ita13.app.schulapp.calendar.container;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Rene on 29.04.2015.
 */
public interface CalendarElement {

    public static final int[] LAYOUT_IDS = {
        R.id.monday, R.id.tuesday, R.id.wednesday, R.id.thursday, R.id.friday, R.id.saturday, R.id.sunday
    };

    public Object getItem(int index);

    public int getSize();

    public int getLayoutID(int index);

}
