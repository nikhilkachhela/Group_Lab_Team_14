/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Grading;
import info5100.university.example.Persona.StudentProfile;
import java.util.Date;

/**
 *
 * @author Sandeep Patil
 */
public class AssignmentSubmission {
    
    private Assignment assignment;
    private StudentProfile student;
    private String submissionText;
    private Date submissionDate;
    private Integer pointsEarned; // null means not graded yet
    private String feedback;
    private SubmissionStatus status;
    
    public enum SubmissionStatus {
        NOT_SUBMITTED,
        SUBMITTED,
        GRADED,
        LATE
    }
    
    public AssignmentSubmission(Assignment assignment, StudentProfile student) {
        this.assignment = assignment;
        this.student = student;
        this.status = SubmissionStatus.NOT_SUBMITTED;
    }
    
    // Submit assignment
    public void submit(String submissionText) {
        this.submissionText = submissionText;
        this.submissionDate = new Date();
        this.status = SubmissionStatus.SUBMITTED;
    }
    
    // Grade assignment
    public void grade(int points, String feedback) {
        if (points < 0 || points > assignment.getMaxPoints()) {
            throw new IllegalArgumentException("Points must be between 0 and " + assignment.getMaxPoints());
        }
        this.pointsEarned = points;
        this.feedback = feedback;
        this.status = SubmissionStatus.GRADED;
    }
    
    // Calculate percentage
    public double getPercentage() {
        if (pointsEarned == null) {
            return 0.0;
        }
        return (pointsEarned * 100.0) / assignment.getMaxPoints();
    }
    
    // Getters and Setters
    public Assignment getAssignment() {
        return assignment;
    }
    
    public StudentProfile getStudent() {
        return student;
    }
    
    public String getSubmissionText() {
        return submissionText;
    }
    
    public void setSubmissionText(String submissionText) {
        this.submissionText = submissionText;
    }
    
    public Date getSubmissionDate() {
        return submissionDate;
    }
    
    public Integer getPointsEarned() {
        return pointsEarned;
    }
    
    public void setPointsEarned(Integer pointsEarned) {
        this.pointsEarned = pointsEarned;
    }
    
    public String getFeedback() {
        return feedback;
    }
    
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    public SubmissionStatus getStatus() {
        return status;
    }
    
    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }
    
    public boolean isGraded() {
        return status == SubmissionStatus.GRADED;
    }
}
