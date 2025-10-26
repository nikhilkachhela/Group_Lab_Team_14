/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author samee
 * Represents a student's enrollment in a course, including their grade.
 */
public class EnrollmentRecord {

    private Student student;
    private Course course;
    private double grade;   

    public EnrollmentRecord(Student student, Course course, double grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }
    
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return student.getName() + " enrolled in " + course.getTitle() + " (" + grade + ")";
    }
}
