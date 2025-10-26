/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Grading;

import info5100.university.example.CourseSchedule.CourseOffer;
import java.util.Date;

/**
 *
 * @author Sandeep Patil
 */
public class Assignment {
    
    private static int idCounter = 1;
    
    private String assignmentId;
    private String title;
    private String description;
    private CourseOffer courseOffer;
    private int maxPoints;
    private Date dueDate;
    private boolean isActive;
    
    public Assignment(String title, String description, CourseOffer courseOffer, int maxPoints) {
        this.assignmentId = "ASGN" + String.format("%04d", idCounter++);
        this.title = title;
        this.description = description;
        this.courseOffer = courseOffer;
        this.maxPoints = maxPoints;
        this.isActive = true;
    }
    
    // Getters and Setters
    public String getAssignmentId() {
        return assignmentId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public CourseOffer getCourseOffer() {
        return courseOffer;
    }
    
    public int getMaxPoints() {
        return maxPoints;
    }
    
    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }
    
    public Date getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    @Override
    public String toString() {
        return title + " (" + maxPoints + " pts)";
    }
}
