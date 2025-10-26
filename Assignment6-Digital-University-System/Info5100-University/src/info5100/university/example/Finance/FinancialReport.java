/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Finance;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sandeep Patil
 */
public class FinancialReport {
    
    private String semester;
    private double totalRevenue;
    private double totalOutstanding;
    private Map<String, Double> departmentRevenue;
    private int totalStudentsEnrolled;
    private int studentsPaidInFull;
    
    public FinancialReport(String semester) {
        this.semester = semester;
        this.departmentRevenue = new HashMap<>();
        this.totalRevenue = 0.0;
        this.totalOutstanding = 0.0;
        this.totalStudentsEnrolled = 0;
        this.studentsPaidInFull = 0;
    }
    
    // Add revenue for a department
    public void addDepartmentRevenue(String departmentName, double amount) {
        departmentRevenue.put(departmentName, 
            departmentRevenue.getOrDefault(departmentName, 0.0) + amount);
        totalRevenue += amount;
    }
    
    // Add outstanding balance
    public void addOutstandingBalance(double amount) {
        totalOutstanding += amount;
    }
    
    // Increment student counts
    public void incrementTotalStudents() {
        totalStudentsEnrolled++;
    }
    
    public void incrementPaidStudents() {
        studentsPaidInFull++;
    }
    
    // Getters
    public String getSemester() {
        return semester;
    }
    
    public double getTotalRevenue() {
        return totalRevenue;
    }
    
    public double getTotalOutstanding() {
        return totalOutstanding;
    }
    
    public Map<String, Double> getDepartmentRevenue() {
        return departmentRevenue;
    }
    
    public int getTotalStudentsEnrolled() {
        return totalStudentsEnrolled;
    }
    
    public int getStudentsPaidInFull() {
        return studentsPaidInFull;
    }
    
    public int getStudentsWithBalance() {
        return totalStudentsEnrolled - studentsPaidInFull;
    }
    
    public double getCollectionRate() {
        if (totalRevenue + totalOutstanding == 0) {
            return 0.0;
        }
        return (totalRevenue / (totalRevenue + totalOutstanding)) * 100.0;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Financial Report for ").append(semester).append("\n");
        sb.append("=====================================\n");
        sb.append("Total Revenue: $").append(String.format("%.2f", totalRevenue)).append("\n");
        sb.append("Total Outstanding: $").append(String.format("%.2f", totalOutstanding)).append("\n");
        sb.append("Collection Rate: ").append(String.format("%.2f", getCollectionRate())).append("%\n");
        sb.append("Students Enrolled: ").append(totalStudentsEnrolled).append("\n");
        sb.append("Students Paid in Full: ").append(studentsPaidInFull).append("\n");
        sb.append("\nRevenue by Department:\n");
        for (Map.Entry<String, Double> entry : departmentRevenue.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(": $")
              .append(String.format("%.2f", entry.getValue())).append("\n");
        }
        return sb.toString();
    }
}