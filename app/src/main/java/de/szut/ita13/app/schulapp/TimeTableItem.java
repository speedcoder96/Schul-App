package de.szut.ita13.app.schulapp;

/**
 * Created by Rene on 21.06.2015.
 */
public class TimeTableItem{

    private int id;
    private TimeTableRowItem rowItem;
    private String subject;
    private String room;
    private String teacher;
    private int colorId;


    public TimeTableItem(TimeTableRowItem rowItem){
        this.rowItem = rowItem;
    }

    public String getInformation(){
        return subject + "\n" + room;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public TimeTableRowItem getRowItem() {
        return rowItem;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}
