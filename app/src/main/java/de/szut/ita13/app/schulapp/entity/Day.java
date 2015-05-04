package de.szut.ita13.app.schulapp.entity;

/**
 * Created by Chris on 28.04.2015.
 */
public class Day implements Table{

    private Long id;

    private Long timeId;
    
    private String day;

    private String subject;

    private String shortcut;

    private String teacher;

    private String room;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimeId() {
        return timeId;
    }

    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", timeId=" + timeId +
                ", day='" + day + '\'' +
                ", subject='" + subject + '\'' +
                ", shortcut='" + shortcut + '\'' +
                ", teacher='" + teacher + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}

