/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Group {
    private int id;
    private String name;
    private Course course;
    private ArrayList<Instructor> instructors;

    public Group() {
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

    

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(ArrayList<Instructor> instructors) {
        this.instructors = instructors;
    }

    public Group(int id, String name, Course courses, ArrayList<Instructor> instructors) {
        this.id = id;
        this.name = name;
        this.course = courses;
        this.instructors = instructors;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourses(Course course) {
        this.course = course;
    }
    
    
}
