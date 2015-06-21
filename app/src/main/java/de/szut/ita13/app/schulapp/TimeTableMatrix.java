package de.szut.ita13.app.schulapp;

/**
 * Created by Rene on 21.06.2015.
 */
public interface TimeTableMatrix {

    public TimeTableRowItem getRowAt(int index);

    public TimeTableItem getItemAt(int row, int column);

    public void setItemAt(int row, int column, TimeTableItem item);

    public void setRowAt(int row, TimeTableRowItem rowItem);

    public int getRowCount();

    public int getColumnCount(int row);


}
