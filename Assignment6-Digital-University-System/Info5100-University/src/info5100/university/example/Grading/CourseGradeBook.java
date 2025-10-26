/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Grading;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.SeatAssignment;
import info5100.university.example.Persona.StudentProfile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sandeep Patil
 */
public class CourseGradeBook {
    
    private CourseOffer courseOffer;
    private ArrayList<Assignment> assignments;
    private Map<String, ArrayList<AssignmentSubmission>> submissions; // Key: studentId
    
    public CourseGradeBook(CourseOffer courseOffer) {
        this.courseOffer = courseOffer;
        this.assignments = new ArrayList<>();
        this.submissions = new HashMap<>();
    }
    
    // Add new assignment
    public Assignment createAssignment(String title, String description, int maxPoints) {
        Assignment assignment = new Assignment(title, description, courseOffer, maxPoints);
        assignments.add(assignment);
        return assignment;
    }
    
    // Get all assignments
    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }
    
    // Get submissions for a specific student
    public ArrayList<AssignmentSubmission> getStudentSubmissions(String studentId) {
        return submissions.getOrDefault(studentId, new ArrayList<>());
    }
    
    // Get submission for specific assignment and student
    public AssignmentSubmission getSubmission(Assignment assignment, StudentProfile student) {
        String studentId = student.isMatch(student.toString()) ? student.toString() : "unknown";
        ArrayList<AssignmentSubmission> studentSubs = submissions.get(studentId);
        
        if (studentSubs != null) {
            for (AssignmentSubmission sub : studentSubs) {
                if (sub.getAssignment().equals(assignment)) {
                    return sub;
                }
            }
        }
        
        // Create new submission if doesn't exist
        AssignmentSubmission newSub = new AssignmentSubmission(assignment, student);
        if (studentSubs == null) {
            studentSubs = new ArrayList<>();
            submissions.put(studentId, studentSubs);
        }
        studentSubs.add(newSub);
        return newSub;
    }
    
    // Calculate total possible points
    public int getTotalPossiblePoints() {
        int total = 0;
        for (Assignment assignment : assignments) {
            if (assignment.isActive()) {
                total += assignment.getMaxPoints();
            }
        }
        return total;
    }
    
    // Calculate student's total earned points
    public int getStudentTotalPoints(String studentId) {
        ArrayList<AssignmentSubmission> studentSubs = getStudentSubmissions(studentId);
        int total = 0;
        
        for (AssignmentSubmission sub : studentSubs) {
            if (sub.isGraded() && sub.getAssignment().isActive()) {
                total += sub.getPointsEarned();
            }
        }
        return total;
    }
    
    // Calculate student's percentage
    public double getStudentPercentage(String studentId) {
        int totalPossible = getTotalPossiblePoints();
        if (totalPossible == 0) {
            return 0.0;
        }
        int earned = getStudentTotalPoints(studentId);
        return (earned * 100.0) / totalPossible;
    }
    
    // Calculate student's letter grade
    public String getStudentLetterGrade(String studentId) {
        double percentage = getStudentPercentage(studentId);
        return GradeCalculator.getLetterGrade(percentage);
    }
    
    // Get course average
    public double getCourseAverage() {
        // This would need to iterate through all enrolled students
        // Implementation depends on how we access enrolled students
        return 0.0; // Placeholder
    }
}