package SMSGUI;

import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int rollNumber;
    private String className;
    private String section;
    private String motherName;
    private String fatherName;
    private String parentPhoneNumber;
    private int grade;

    public Student(String name, int rollNumber, String className, String section, String motherName, String fatherName, String parentPhoneNumber, int grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.className = className;
        this.section = section;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.parentPhoneNumber = parentPhoneNumber;
        this.grade = grade;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getParentPhoneNumber() {
        return parentPhoneNumber;
    }

    public void setParentPhoneNumber(String parentPhoneNumber) {
        this.parentPhoneNumber = parentPhoneNumber;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Class: " + className + ", Section: " + section + ", Mother's Name: " + motherName + ", Father's Name: " + fatherName + ", Parent's Phone Number: " + parentPhoneNumber + ", Grade: " + grade;
    }
}



