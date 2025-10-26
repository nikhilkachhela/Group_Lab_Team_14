/*
 * Assignment Management Panel - View and submit assignments
 * Works with CourseGradeBook, Assignment, and AssignmentSubmission classes
 * Author: Student Module Implementation
 */
package info5100.university.example.UI.Student;

import info5100.university.example.CourseSchedule.*;
import info5100.university.example.Department.Department;
import info5100.university.example.Grading.*;
import info5100.university.example.Persona.StudentProfile;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class AssignmentManagementPanel extends javax.swing.JPanel {
    
    private StudentProfile currentStudent;
    private Department department;
    private JPanel cardPanel;
    private CourseLoad currentCourseLoad;
    private ArrayList<CourseGradeBook> gradeBooks;
    
    // Components
    private JLabel lblTitle;
    private JButton btnBack;
    private JLabel lblSelectCourse;
    private JComboBox<String> cmbCourses;
    private JTable tblAssignments;
    private JScrollPane scrollPaneAssignments;
    private JButton btnSubmitAssignment;
    private JButton btnViewSubmission;
    private JPanel pnlGradeSummary;
    private JLabel lblCourseGrade;
    private JLabel lblTotalPoints;
    private JLabel lblPercentage;
    private JTextArea txtSubmissionArea;
    private JScrollPane scrollPaneSubmission;
    
    public AssignmentManagementPanel(StudentProfile student, Department dept, JPanel cardPanel) {
        this.currentStudent = student;
        this.department = dept;
        this.cardPanel = cardPanel;
        this.gradeBooks = new ArrayList<>();
        initComponents();
        loadCourses();
    }
    
    private void initComponents() {
        setBackground(new Color(255, 255, 255));
        setLayout(null);
        
        // Title
        lblTitle = new JLabel("Assignments & Grades");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBounds(30, 20, 300, 35);
        add(lblTitle);
        
        // Back button
        btnBack = new JButton("<< Back");
        btnBack.setBounds(750, 20, 100, 30);
        btnBack.addActionListener(evt -> btnBackActionPerformed());
        add(btnBack);
        
        // Course selection
        lblSelectCourse = new JLabel("Select Course:");
        lblSelectCourse.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSelectCourse.setBounds(30, 70, 100, 25);
        add(lblSelectCourse);
        
        cmbCourses = new JComboBox<>();
        cmbCourses.setBounds(140, 70, 300, 25);
        cmbCourses.addActionListener(evt -> loadAssignments());
        add(cmbCourses);
        
        // Grade Summary Panel
        pnlGradeSummary = new JPanel();
        pnlGradeSummary.setLayout(null);
        pnlGradeSummary.setBorder(BorderFactory.createTitledBorder("Grade Summary"));
        pnlGradeSummary.setBackground(new Color(245, 245, 245));
        pnlGradeSummary.setBounds(500, 70, 350, 100);
        add(pnlGradeSummary);
        
        lblCourseGrade = new JLabel("Current Grade: N/A");
        lblCourseGrade.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCourseGrade.setBounds(20, 25, 300, 20);
        pnlGradeSummary.add(lblCourseGrade);
        
        lblTotalPoints = new JLabel("Total Points: 0 / 0");
        lblTotalPoints.setBounds(20, 50, 150, 20);
        pnlGradeSummary.add(lblTotalPoints);
        
        lblPercentage = new JLabel("Percentage: 0%");
        lblPercentage.setBounds(180, 50, 150, 20);
        pnlGradeSummary.add(lblPercentage);
        
        // Assignments Table
        JLabel lblAssignments = new JLabel("Course Assignments:");
        lblAssignments.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblAssignments.setBounds(30, 110, 200, 25);
        add(lblAssignments);
        
        String[] columns = {"Assignment", "Max Points", "Points Earned", "Percentage", "Status", "Due Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblAssignments = new JTable(model);
        tblAssignments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblAssignments.setRowHeight(25);
        scrollPaneAssignments = new JScrollPane(tblAssignments);
        scrollPaneAssignments.setBounds(30, 140, 820, 200);
        add(scrollPaneAssignments);
        
        // Action buttons
        btnSubmitAssignment = new JButton("Submit Assignment");
        btnSubmitAssignment.setBounds(30, 350, 150, 30);
        btnSubmitAssignment.setBackground(new Color(0, 102, 204));
        btnSubmitAssignment.setForeground(Color.WHITE);
        btnSubmitAssignment.addActionListener(evt -> submitAssignment());
        add(btnSubmitAssignment);
        
        btnViewSubmission = new JButton("View Submission");
        btnViewSubmission.setBounds(190, 350, 150, 30);
        btnViewSubmission.addActionListener(evt -> viewSubmission());
        add(btnViewSubmission);
        
        // Submission Area
        JLabel lblSubmission = new JLabel("Assignment Submission:");
        lblSubmission.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSubmission.setBounds(30, 390, 200, 25);
        add(lblSubmission);
        
        txtSubmissionArea = new JTextArea();
        txtSubmissionArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtSubmissionArea.setLineWrap(true);
        txtSubmissionArea.setWrapStyleWord(true);
        scrollPaneSubmission = new JScrollPane(txtSubmissionArea);
        scrollPaneSubmission.setBounds(30, 420, 820, 150);
        add(scrollPaneSubmission);
    }
    
    private void loadCourses() {
        cmbCourses.removeAllItems();
        
        // Get current semester courses
        currentCourseLoad = currentStudent.getCourseLoadBySemester("Fall2024");
        
        if (currentCourseLoad != null) {
            for (SeatAssignment sa : currentCourseLoad.getSeatAssignments()) {
                CourseOffer offer = sa.getCourseOffer();
                Course course = offer.getSubjectCourse();
                cmbCourses.addItem(course.getCOurseNumber() + " - " + course.getCourseName());
                
                // Create gradebook for each course
                CourseGradeBook gradeBook = new CourseGradeBook(offer);
                createSampleAssignments(gradeBook);
                gradeBooks.add(gradeBook);
            }
        }
        
        if (cmbCourses.getItemCount() > 0) {
            loadAssignments();
        }
    }
    
    private void createSampleAssignments(CourseGradeBook gradeBook) {
        // Create sample assignments
        gradeBook.createAssignment("Homework 1", "Basic concepts assignment", 100);
        gradeBook.createAssignment("Midterm Project", "Comprehensive mid-semester project", 200);
        gradeBook.createAssignment("Final Project", "Final comprehensive project", 300);
        gradeBook.createAssignment("Class Participation", "Active participation in class", 100);
    }
    
    private void loadAssignments() {
        int selectedIndex = cmbCourses.getSelectedIndex();
        if (selectedIndex < 0 || selectedIndex >= gradeBooks.size()) {
            return;
        }
        
        CourseGradeBook gradeBook = gradeBooks.get(selectedIndex);
        DefaultTableModel model = (DefaultTableModel) tblAssignments.getModel();
        model.setRowCount(0);
        
        int totalPossible = 0;
        int totalEarned = 0;
        
        for (Assignment assignment : gradeBook.getAssignments()) {
            AssignmentSubmission submission = gradeBook.getSubmission(assignment, currentStudent);
            
            String status = "Not Submitted";
            String pointsEarned = "-";
            String percentage = "-";
            
            if (submission.isGraded()) {
                status = "Graded";
                pointsEarned = String.valueOf(submission.getPointsEarned());
                percentage = String.format("%.1f%%", submission.getPercentage());
                totalEarned += submission.getPointsEarned();
            } else if (submission.getStatus() == AssignmentSubmission.SubmissionStatus.SUBMITTED) {
                status = "Submitted";
            }
            
            totalPossible += assignment.getMaxPoints();
            
            model.addRow(new Object[]{
                assignment.getTitle(),
                assignment.getMaxPoints(),
                pointsEarned,
                percentage,
                status,
                "TBD" // Due date
            });
        }
        
        // Update grade summary
        updateGradeSummary(totalEarned, totalPossible);
    }
    
    private void updateGradeSummary(int earned, int possible) {
        lblTotalPoints.setText("Total Points: " + earned + " / " + possible);
        
        if (possible > 0) {
            double percentage = (earned * 100.0) / possible;
            lblPercentage.setText(String.format("Percentage: %.1f%%", percentage));
            
            String letterGrade = GradeCalculator.getLetterGrade(percentage);
            lblCourseGrade.setText("Current Grade: " + letterGrade);
            
            // Color code the grade
            if (letterGrade.startsWith("A")) {
                lblCourseGrade.setForeground(new Color(0, 153, 51));
            } else if (letterGrade.startsWith("B")) {
                lblCourseGrade.setForeground(new Color(0, 102, 204));
            } else if (letterGrade.startsWith("C")) {
                lblCourseGrade.setForeground(new Color(255, 153, 0));
            } else {
                lblCourseGrade.setForeground(new Color(204, 0, 0));
            }
        } else {
            lblPercentage.setText("Percentage: 0%");
            lblCourseGrade.setText("Current Grade: N/A");
        }
    }
    
    private void submitAssignment() {
        int selectedRow = tblAssignments.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Please select an assignment to submit.",
                "Selection Required",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String submissionText = txtSubmissionArea.getText().trim();
        if (submissionText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter your submission in the text area below.",
                "Submission Required",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int selectedCourse = cmbCourses.getSelectedIndex();
        CourseGradeBook gradeBook = gradeBooks.get(selectedCourse);
        Assignment assignment = gradeBook.getAssignments().get(selectedRow);
        AssignmentSubmission submission = gradeBook.getSubmission(assignment, currentStudent);
        
        // Submit the assignment
        submission.submit(submissionText);
        
        // For demo, automatically grade with random score
        int randomScore = (int) (Math.random() * assignment.getMaxPoints() * 0.7) + 
                         (int) (assignment.getMaxPoints() * 0.3);
        submission.grade(randomScore, "Good work!");
        
        JOptionPane.showMessageDialog(this,
            "Assignment submitted successfully!\n" +
            "Grade: " + randomScore + " / " + assignment.getMaxPoints(),
            "Submission Successful",
            JOptionPane.INFORMATION_MESSAGE);
        
        txtSubmissionArea.setText("");
        loadAssignments();
    }
    
    private void viewSubmission() {
        int selectedRow = tblAssignments.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                "Please select an assignment to view.",
                "Selection Required",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int selectedCourse = cmbCourses.getSelectedIndex();
        CourseGradeBook gradeBook = gradeBooks.get(selectedCourse);
        Assignment assignment = gradeBook.getAssignments().get(selectedRow);
        AssignmentSubmission submission = gradeBook.getSubmission(assignment, currentStudent);
        
        if (submission.getSubmissionText() == null || submission.getSubmissionText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No submission found for this assignment.",
                "No Submission",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String message = "Assignment: " + assignment.getTitle() + "\n\n";
        message += "Submission:\n" + submission.getSubmissionText() + "\n\n";
        
        if (submission.isGraded()) {
            message += "Grade: " + submission.getPointsEarned() + " / " + assignment.getMaxPoints() + "\n";
            message += "Feedback: " + submission.getFeedback();
        } else {
            message += "Status: Awaiting grading";
        }
        
        JTextArea textArea = new JTextArea(message);
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        
        JOptionPane.showMessageDialog(this, scrollPane,
            "Assignment Submission",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void btnBackActionPerformed() {
        cardPanel.remove(this);
        ((CardLayout) cardPanel.getLayout()).previous(cardPanel);
    }
}