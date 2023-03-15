/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Session {
    private int id;
    private String name;
    private Date date;
    private TimeSlot slot;
    private Instructor lecturer;
    private Group group;
    private Room room;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Session(int id, String name, Date date, TimeSlot slot, Instructor lecturer, Group group, Room room) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.slot = slot;
        this.lecturer = lecturer;
        this.group = group;
        this.room = room;
    }

    public Session() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public void setSlot(TimeSlot slot) {
        this.slot = slot;
    }

    public Instructor getLecturer() {
        return lecturer;
    }

    public void setLecturer(Instructor lecturer) {
        this.lecturer = lecturer;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
