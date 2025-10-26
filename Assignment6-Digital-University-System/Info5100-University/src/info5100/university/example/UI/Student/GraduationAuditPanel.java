/*
 * Graduation Audit Panel - Track progress toward graduation
 * Author: Student Module Implementation
 */
package info5100.university.example.UI.Student;

import info5100.university.example.CourseSchedule.*;
import info5100.university.example.Department.Department;
import info5100.university.example.Persona.StudentProfile;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraduationAuditPanel extends javax.swing.JPanel {
    
    private StudentProfile currentStudent;
    private Department department;
    private JPanel cardPanel;
    
    // MSIS Program Requirements
    private static final int TOTAL_CREDITS_REQUIRED = 32;
    private static final String CORE_COURSE = "INFO 5100";
    private static final int CORE_COURSE_CREDITS = 4;
    
    private int creditsEarned = 0;
    private int electiveCredits = 0;
    private boolean coreCourseCompleted = false;
    private boolean readyToGraduate = false;
    
    // Components
    private JLabel lblTitle;
    private JButton btnBack;
    private JPanel pnlSummary;
    private JLabel lblProgramTitle;
    private JLabel lblTotalRequired;
    private JLabel lblCreditsEarned;
    private JLabel lblCreditsRemaining;
    private JProgressBar progressBar;
    private JLabel lblProgressPercent;
    private JPanel pnlCoreRequirement;
    private JLabel lblCoreTitle;
    private JLabel lblCoreStatus;
    private JPanel pnlElectives;
    private JLabel lblElectivesTitle;
    private JLabel lblElectivesStatus;
    private JPanel pnlGraduationStatus;
    private JLabel lblGraduationStatus;
    private JLabel lblGraduationMessage;
    private JButton btnGenerateReport;
    
    public GraduationAuditPanel(StudentProfile student, Department dept, JPanel cardPanel) {
        this.currentStudent = student;
        this.department = dept;
        this.cardPanel = cardPanel;
        initComponents();
        performGraduationAudit();
    }
    
    private void initComponents() {
        setBackground(new Color(255, 255, 255));
        setLayout(null);
        
        // Title
        lblTitle = new JLabel("Graduation Audit - MSIS Program");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBounds(30, 20, 400, 35);
        add(lblTitle);
        
        // Back button
        btnBack = new JButton("<< Back");
        btnBack.setBounds(750, 20, 100, 30);
        btnBack.addActionListener(evt -> btnBackActionPerformed());
        add(btnBack);
        
        // Summary Panel
        pnlSummary = new JPanel();
        pnlSummary.setLayout(null);
        pnlSummary.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            "Degree Progress Summary",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.BOLD, 14)));
        pnlSummary.setBackground(new Color(245, 245, 245));
        pnlSummary.setBounds(30, 70, 820, 160);
        add(pnlSummary);
        
        lblProgramTitle = new JLabel("Master of Science in Information Systems (MSIS)");
        lblProgramTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblProgramTitle.setBounds(20, 25, 500, 25);
        pnlSummary.add(lblProgramTitle);
        
        lblTotalRequired = new JLabel("Total Credits Required: 32");
        lblTotalRequired.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTotalRequired.setBounds(20, 55, 200, 20);
        pnlSummary.add(lblTotalRequired);
        
        lblCreditsEarned = new JLabel("Credits Earned: 0");
        lblCreditsEarned.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblCreditsEarned.setBounds(250, 55, 150, 20);
        pnlSummary.add(lblCreditsEarned);
        
        lblCreditsRemaining = new JLabel("Credits Remaining: 32");
        lblCreditsRemaining.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblCreditsRemaining.setBounds(430, 55, 150, 20);
        pnlSummary.add(lblCreditsRemaining);
        
        progressBar = new JProgressBar();
        progressBar.setMaximum(TOTAL_CREDITS_REQUIRED);
        progressBar.setStringPainted(true);
        progressBar.setBounds(20, 90, 760, 30);
        pnlSummary.add(progressBar);
        
        lblProgressPercent = new JLabel("0% Complete");
        lblProgressPercent.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblProgressPercent.setBounds(20, 125, 200, 20);
        pnlSummary.add(lblProgressPercent);
        
        // Core Requirement Panel
        pnlCoreRequirement = new JPanel();
        pnlCoreRequirement.setLayout(null);
        pnlCoreRequirement.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            "Core Course Requirement",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.BOLD, 14)));
        pnlCoreRequirement.setBackground(new Color(245, 245, 245));
        pnlCoreRequirement.setBounds(30, 240, 820, 90);
        add(pnlCoreRequirement);
        
        lblCoreTitle = new JLabel("INFO 5100 - Application Engineering and Development (4 credits)");
        lblCoreTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblCoreTitle.setBounds(20, 25, 500, 20);
        pnlCoreRequirement.add(lblCoreTitle);
        
        lblCoreStatus = new JLabel("Status: Not Completed");
        lblCoreStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCoreStatus.setForeground(new Color(204, 0, 0));
        lblCoreStatus.setBounds(20, 50, 300, 20);
        pnlCoreRequirement.add(lblCoreStatus);
        
        // Electives Panel
        pnlElectives = new JPanel();
        pnlElectives.setLayout(null);
        pnlElectives.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            "Elective Courses",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.BOLD, 14)));
        pnlElectives.setBackground(new Color(245, 245, 245));
        pnlElectives.setBounds(30, 340, 820, 90);
        add(pnlElectives);
        
        lblElectivesTitle = new JLabel("28 credits of elective courses required");
        lblElectivesTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblElectivesTitle.setBounds(20, 25, 500, 20);
        pnlElectives.add(lblElectivesTitle);
        
        lblElectivesStatus = new JLabel("Credits Completed: 0 / 28");
        lblElectivesStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblElectivesStatus.setBounds(20, 50, 300, 20);
        pnlElectives.add(lblElectivesStatus);
        
        // Graduation Status Panel
        pnlGraduationStatus = new JPanel();
        pnlGraduationStatus.setLayout(null);
        pnlGraduationStatus.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            "Graduation Status",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.BOLD, 14)));
        pnlGraduationStatus.setBackground(new Color(245, 245, 245));
        pnlGraduationStatus.setBounds(30, 440, 820, 90);
        add(pnlGraduationStatus);
        
        lblGraduationStatus = new JLabel("NOT READY TO GRADUATE");
        lblGraduationStatus.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblGraduationStatus.setForeground(new Color(204, 0, 0));
        lblGraduationStatus.setBounds(20, 25, 400, 25);
        pnlGraduationStatus.add(lblGraduationStatus);
        
        lblGraduationMessage = new JLabel("You need to complete additional requirements");
        lblGraduationMessage.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblGraduationMessage.setBounds(20, 50, 700, 20);
        pnlGraduationStatus.add(lblGraduationMessage);
        
        // Generate Report Button
        btnGenerateReport = new JButton("Generate Full Audit Report");
        btnGenerateReport.setBounds(30, 550, 200, 35);
        btnGenerateReport.addActionListener(evt -> generateReport());
        add(btnGenerateReport);
    }
    
    private void performGraduationAudit() {
        // Calculate credits from all semesters
        creditsEarned = 0;
        coreCourseCompleted = false;
        electiveCredits = 0;
        
        // Check all course loads
        ArrayList<SeatAssignment> allCourses = currentStudent.getCourseList();
        
        for (SeatAssignment sa : allCourses) {
            Course course = sa.getAssociatedCourse();
            int credits = course.getCredits();
            
            creditsEarned += credits;
            
            // Check if core course
            if (course.getCOurseNumber().equals(CORE_COURSE)) {
                coreCourseCompleted = true;
            } else {
                electiveCredits += credits;
            }
        }
        
        // Check if ready to graduate
        readyToGraduate = (creditsEarned >= TOTAL_CREDITS_REQUIRED) && coreCourseCompleted;
        
        // Update UI
        updateDisplay();
    }
    
    private void updateDisplay() {
        lblCreditsEarned.setText("Credits Earned: " + creditsEarned);
        lblCreditsRemaining.setText("Credits Remaining: " + Math.max(0, TOTAL_CREDITS_REQUIRED - creditsEarned));
        
        progressBar.setValue(Math.min(creditsEarned, TOTAL_CREDITS_REQUIRED));
        int percentComplete = (creditsEarned * 100) / TOTAL_CREDITS_REQUIRED;
        lblProgressPercent.setText(percentComplete + "% Complete");
        
        // Core course status
        if (coreCourseCompleted) {
            lblCoreStatus.setText("Status: Completed");
            lblCoreStatus.setForeground(new Color(0, 153, 51));
        } else {
            lblCoreStatus.setText("Status: Not Completed");
            lblCoreStatus.setForeground(new Color(204, 0, 0));
        }
        
        // Electives status
        lblElectivesStatus.setText("Credits Completed: " + electiveCredits + " / 28");
        if (electiveCredits >= 28) {
            lblElectivesStatus.setForeground(new Color(0, 153, 51));
        } else {
            lblElectivesStatus.setForeground(new Color(204, 0, 0));
        }
        
        // Graduation status
        if (readyToGraduate) {
            lblGraduationStatus.setText("READY TO GRADUATE!");
            lblGraduationStatus.setForeground(new Color(0, 153, 51));
            lblGraduationMessage.setText("Congratulations! You have met all graduation requirements.");
            pnlGraduationStatus.setBackground(new Color(230, 255, 230));
        } else {
            lblGraduationStatus.setText("NOT READY TO GRADUATE");
            lblGraduationStatus.setForeground(new Color(204, 0, 0));
            
            String message = "Requirements remaining: ";
            if (!coreCourseCompleted) {
                message += "Core course (INFO 5100), ";
            }
            if (creditsEarned < TOTAL_CREDITS_REQUIRED) {
                message += (TOTAL_CREDITS_REQUIRED - creditsEarned) + " credits";
            }
            lblGraduationMessage.setText(message);
        }
    }
    
    private void generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("GRADUATION AUDIT REPORT\n");
        report.append("========================\n\n");
        report.append("Student ID: ").append(currentStudent.getPerson().getPersonId()).append("\n");
        report.append("Program: Master of Science in Information Systems (MSIS)\n\n");
        
        report.append("DEGREE REQUIREMENTS\n");
        report.append("-------------------\n");
        report.append("Total Credits Required: ").append(TOTAL_CREDITS_REQUIRED).append("\n");
        report.append("Credits Earned: ").append(creditsEarned).append("\n");
        report.append("Credits Remaining: ").append(Math.max(0, TOTAL_CREDITS_REQUIRED - creditsEarned)).append("\n\n");
        
        report.append("CORE COURSE REQUIREMENT\n");
        report.append("-----------------------\n");
        report.append("INFO 5100 - Application Engineering (4 credits): ");
        report.append(coreCourseCompleted ? "COMPLETED" : "NOT COMPLETED").append("\n\n");
        
        report.append("ELECTIVE REQUIREMENTS\n");
        report.append("---------------------\n");
        report.append("Elective Credits Required: 28\n");
        report.append("Elective Credits Earned: ").append(electiveCredits).append("\n\n");
        
        report.append("GRADUATION STATUS\n");
        report.append("-----------------\n");
        report.append(readyToGraduate ? "ELIGIBLE FOR GRADUATION" : "NOT ELIGIBLE FOR GRADUATION").append("\n");
        
        JTextArea textArea = new JTextArea(report.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane,
            "Graduation Audit Report",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void btnBackActionPerformed() {
        cardPanel.remove(this);
        ((CardLayout) cardPanel.getLayout()).previous(cardPanel);
    }
}