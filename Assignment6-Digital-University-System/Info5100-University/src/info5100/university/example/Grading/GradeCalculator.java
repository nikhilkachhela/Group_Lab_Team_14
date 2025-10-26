/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Grading;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Sandeep Patil
 */
public class GradeCalculator {
    
    // Grade point mappings as per assignment requirements
    private static final Map<String, Double> GRADE_POINTS = new HashMap<>();
    
    static {
        GRADE_POINTS.put("A", 4.0);
        GRADE_POINTS.put("A-", 3.7);
        GRADE_POINTS.put("B+", 3.3);
        GRADE_POINTS.put("B", 3.0);
        GRADE_POINTS.put("B-", 2.7);
        GRADE_POINTS.put("C+", 2.3);
        GRADE_POINTS.put("C", 2.0);
        GRADE_POINTS.put("C-", 1.7);
        GRADE_POINTS.put("F", 0.0);
    }
    
    /**
     * Convert percentage to letter grade
     * @param percentage (0-100)
     * @return Letter grade
     */
    public static String getLetterGrade(double percentage) {
        if (percentage >= 93) return "A";
        else if (percentage >= 90) return "A-";
        else if (percentage >= 87) return "B+";
        else if (percentage >= 83) return "B";
        else if (percentage >= 80) return "B-";
        else if (percentage >= 77) return "C+";
        else if (percentage >= 73) return "C";
        else if (percentage >= 70) return "C-";
        else return "F";
    }
    
    /**
     * Get grade points for a letter grade
     * @param letterGrade
     * @return Grade points (0.0 - 4.0)
     */
    public static double getGradePoints(String letterGrade) {
        return GRADE_POINTS.getOrDefault(letterGrade, 0.0);
    }
    
    /**
     * Calculate GPA from total quality points and total credits
     * @param totalQualityPoints
     * @param totalCredits
     * @return GPA rounded to 2 decimal places
     */
    public static double calculateGPA(double totalQualityPoints, int totalCredits) {
        if (totalCredits == 0) {
            return 0.0;
        }
        return Math.round((totalQualityPoints / totalCredits) * 100.0) / 100.0;
    }
    
    /**
     * Calculate quality points for a course
     * @param letterGrade
     * @param creditHours
     * @return Quality points
     */
    public static double calculateQualityPoints(String letterGrade, int creditHours) {
        return getGradePoints(letterGrade) * creditHours;
    }
    
    /**
     * Get all available grade options
     * @return Array of letter grades
     */
    public static String[] getGradeOptions() {
        return new String[]{"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "F"};
    }
    
    /**
     * Determine academic standing based on GPA
     * @param termGPA
     * @param overallGPA
     * @return Academic standing string
     */
    public static String getAcademicStanding(double termGPA, double overallGPA) {
        if (overallGPA < 3.0) {
            return "Academic Probation";
        } else if (termGPA < 3.0) {
            return "Academic Warning";
        } else {
            return "Good Standing";
        }
    }
}