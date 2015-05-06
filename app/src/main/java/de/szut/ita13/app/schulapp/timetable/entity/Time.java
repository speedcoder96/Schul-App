package de.szut.ita13.app.schulapp.timetable.entity;

/**
 * Created by Chris on 28.04.2015.
 */
public class Time implements Table {

    private Integer id;

    private String start;

    private String end;

    private String pause;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getPause() {
        return pause;
    }

    public void setPause(String pause) {
        this.pause = pause;
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", pause='" + pause + '\'' +
                '}';
    }
}
