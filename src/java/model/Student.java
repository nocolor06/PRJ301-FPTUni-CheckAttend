/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class Student {
    private String id;
    private String name;
    private String img;
    private int absent;

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public String getId() {
        return id;
    }

    public Student(String id, String name, String img, int absent) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.absent = absent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Student() {
    }

    public Student(String id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }
    
}
