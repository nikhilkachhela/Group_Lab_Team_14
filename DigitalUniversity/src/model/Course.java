/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author samee
 */


public class Course {

    // ---------- Fields ----------
    private String courseId;
    private String title;
    private String description;
    private String schedule;
    private int capacity;
    private boolean enrollmentOpen;
    private String syllabusText;
    private Faculty instructor;

    // ---------- Full 8-parameter constructor ----------
    public Course(String courseId,
                  String title,
                  String description,
                  String schedule,
                  int capacity,
                  boolean enrollmentOpen,
                  String syllabusText,
                  Faculty instructor) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.schedule = schedule;
        this.capacity = capacity;
        this.enrollmentOpen = enrollmentOpen;
        this.syllabusText = syllabusText;
        this.instructor = instructor;
    }

    // ---------- Short 5-parameter constructor (for older calls) ----------
    // This lets you call new Course("INFO5100", "App Eng", 30, "Mon/Wed", faculty)
    // without errors.  It automatically fills the missing values with defaults.
    public Course(String courseId,
                  String title,
                  int capacity,
                  String schedule,
                  Faculty instructor) {
        this(courseId, title, "", schedule, capacity, true, "", instructor);
    }

    // ---------- Getters & Setters ----------
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public boolean isEnrollmentOpen() { return enrollmentOpen; }
    public void setEnrollmentOpen(boolean enrollmentOpen) { this.enrollmentOpen = enrollmentOpen; }

    public String getSyllabusText() { return syllabusText; }
    public void setSyllabusText(String syllabusText) { this.syllabusText = syllabusText; }

    public Faculty getInstructor() { return instructor; }
    public void setInstructor(Faculty instructor) { this.instructor = instructor; }

    // ---------- For displaying in dropdowns or tables ----------
    @Override
    public String toString() {
        return courseId + " - " + title;
    }
}


