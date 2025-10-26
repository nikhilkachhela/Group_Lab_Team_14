/*
 * Course Registration Panel - Handle course enrollment and search
 * Author: Student Module Implementation
 * NUID: [Your NUID]
 */
package info5100.university.example.UI.Student;

import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseSchedule.*;
import info5100.university.example.Department.Department;
import info5100.university.example.Finance.FinanceManager;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import info5100.university.example.Persona.StudentProfile;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class CourseRegistrationPanel extends javax.swing.JPanel {
    
    private Department department;
    private StudentProfile currentStudent;
    private FinanceManager financeManager;
    private JPanel cardPanel;
    private CourseSchedule currentSchedule;
    private ArrayList<CourseOffer> availableCourses;
    private int totalCreditsEnrolled = 0;
    
    public CourseRegistrationPanel(Department dept, StudentProfile student, 
                                  FinanceManager finMgr, JPanel cardPanel) {
        this.department = dept;
        this.currentStudent = student;
        this.financeManager = finMgr;
        this.cardPanel = cardPanel;
        initComponents();
        loadCourseData();
        updateCreditInfo();
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        lblTitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        lblSemester = new javax.swing.JLabel();
        cmbSemester = new javax.swing.JComboBox<>();
        lblSearch = new javax.swing.JLabel();
        cmbSearchType = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnShowAll = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCourses = new javax.swing.JTable();
        btnEnroll = new javax.swing.JButton();
        btnDrop = new javax.swing.JButton();
        lblCreditInfo = new javax.swing.JLabel();
        lblEnrolledCourses = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEnrolledCourses = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20));
        lblTitle.setText("Course Registration");

        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblSemester.setText("Select Semester:");

        cmbSemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { 
            "Fall2024", "Spring2025", "Summer2025", "Fall2025" 
        }));
        cmbSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSemesterActionPerformed(evt);
            }
        });

        lblSearch.setText("Search Courses:");

        cmbSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { 
            "By Course ID", "By Course Name", "By Faculty", "By Credits" 
        }));

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnShowAll.setText("Show All");
        btnShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAllActionPerformed(evt);
            }
        });

        tblCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Course ID", "Course Name", "Faculty", "Credits", "Seats Available", "Schedule", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblCourses.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblCourses);

        btnEnroll.setText("Enroll in Selected Course");
        btnEnroll.setBackground(new java.awt.Color(0, 153, 51));
        btnEnroll.setForeground(new java.awt.Color(255, 255, 255));
        btnEnroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnrollActionPerformed(evt);
            }
        });

        btnDrop.setText("Drop Selected Course");
        btnDrop.setBackground(new java.awt.Color(204, 0, 0));
        btnDrop.setForeground(new java.awt.Color(255, 255, 255));
        btnDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDropActionPerformed(evt);
            }
        });

        lblCreditInfo.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblCreditInfo.setText("Credits Enrolled: 0 / 8 (Maximum)");
        lblCreditInfo.setForeground(new java.awt.Color(0, 102, 204));

        lblEnrolledCourses.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblEnrolledCourses.setText("My Enrolled Courses:");

        tblEnrolledCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Course ID", "Course Name", "Credits", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblEnrolledCourses);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSemester)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(lblCreditInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnroll, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnDrop, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblEnrolledCourses)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(btnBack))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSemester)
                    .addComponent(cmbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCreditInfo))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearch)
                    .addComponent(cmbSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnShowAll))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnroll, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDrop, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(lblEnrolledCourses)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        cardPanel.remove(this);
        ((java.awt.CardLayout) cardPanel.getLayout()).previous(cardPanel);
    }

    private void cmbSemesterActionPerformed(java.awt.event.ActionEvent evt) {
        loadCourseData();
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        String searchType = (String) cmbSearchType.getSelectedItem();
        String searchText = txtSearch.getText().trim().toLowerCase();
        
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter search criteria", 
                "Search Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) tblCourses.getModel();
        model.setRowCount(0);
        
        for (CourseOffer offer : availableCourses) {
            Course course = offer.getSubjectCourse();
            boolean match = false;
            
            switch (searchType) {
                case "By Course ID":
                    match = course.getCOurseNumber().toLowerCase().contains(searchText);
                    break;
                case "By Course Name":
                    match = course.getCourseName().toLowerCase().contains(searchText);
                    break;
                case "By Faculty":
                    FacultyProfile faculty = offer.getFacultyProfile();
                    if (faculty != null) {
                        match = true; // Simplified - would check faculty name
                    }
                    break;
                case "By Credits":
                    try {
                        int searchCredits = Integer.parseInt(searchText);
                        match = (course.getCourseCredits() == searchCredits);
                    } catch (NumberFormatException e) {
                        // Invalid number
                    }
                    break;
            }
            
            if (match) {
                addCourseToTable(model, offer);
            }
        }
        
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No courses found matching your criteria", 
                "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btnShowAllActionPerformed(java.awt.event.ActionEvent evt) {
        loadCourseData();
        txtSearch.setText("");
    }

    private void btnEnrollActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tblCourses.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a course to enroll", 
                "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String courseId = (String) tblCourses.getValueAt(selectedRow, 0);
        int credits = (Integer) tblCourses.getValueAt(selectedRow, 3);
        
        // Check credit limit
        if (totalCreditsEnrolled + credits > 8) {
            JOptionPane.showMessageDialog(this, 
                "Cannot enroll: This would exceed the 8 credit hour limit per semester.\n" +
                "Current credits: " + totalCreditsEnrolled + "\n" +
                "Course credits: " + credits,
                "Credit Limit Exceeded", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Find the course offer and enroll
        for (CourseOffer offer : availableCourses) {
            if (offer.getSubjectCourse().getCOurseNumber().equals(courseId)) {
                // Check for available seats
                boolean enrolled = false;
                for (Seat seat : offer.getSeatList()) {
                    if (!seat.isOccupied()) {
                        // Create seat assignment
                        CourseLoad courseLoad = currentStudent.getCurrentCourseLoad();
                        if (courseLoad == null) {
                            courseLoad = currentStudent.newCourseLoad(
                                (String) cmbSemester.getSelectedItem()
                            );
                        }
                        
                        SeatAssignment assignment = courseLoad.newSeatAssignment(offer);
                        seat.setOccupied(true);
                        enrolled = true;
                        
                        // Add tuition charge
                        double tuition = offer.getSubjectCourse().getCoursePrice();
                        financeManager.addTuitionCharge(currentStudent, tuition);
                        
                        JOptionPane.showMessageDialog(this, 
                            "Successfully enrolled in " + offer.getSubjectCourse().getCourseName() + 
                            "\nTuition charge: $" + tuition + " added to your account.",
                            "Enrollment Successful", 
                            JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
                
                if (!enrolled) {
                    JOptionPane.showMessageDialog(this, 
                        "No seats available in this course",
                        "Enrollment Failed", 
                        JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
        }
        
        loadCourseData();
        updateCreditInfo();
        loadEnrolledCourses();
    }

    private void btnDropActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = tblEnrolledCourses.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a course to drop", 
                "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String courseId = (String) tblEnrolledCourses.getValueAt(selectedRow, 0);
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to drop this course?\n" +
            "If you have paid tuition, it will be refunded.",
            "Confirm Drop",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        
        // Drop the course
        CourseLoad courseLoad = currentStudent.getCurrentCourseLoad();
        if (courseLoad != null) {
            ArrayList<SeatAssignment> assignments = courseLoad.getSeatAssignments();
            for (SeatAssignment assignment : assignments) {
                if (assignment.getCourseOffer().getSubjectCourse().getCOurseNumber().equals(courseId)) {
                    // Free the seat
                    assignment.getSeat().setOccupied(false);
                    
                    // Refund tuition if paid
                    double refund = assignment.getCourseOffer().getSubjectCourse().getCoursePrice();
                    financeManager.refundTuition(currentStudent, refund);
                    
                    // Remove assignment
                    assignments.remove(assignment);
                    
                    JOptionPane.showMessageDialog(this, 
                        "Course dropped successfully.\nRefund of $" + refund + " processed.",
                        "Course Dropped", 
                        JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }
        
        loadCourseData();
        updateCreditInfo();
        loadEnrolledCourses();
    }

    private void loadCourseData() {
        String semester = (String) cmbSemester.getSelectedItem();
        currentSchedule = department.getCourseSchedule(semester);
        
        if (currentSchedule == null) {
            currentSchedule = department.newCourseSchedule(semester);
        }
        
        availableCourses = currentSchedule.getSchedule();
        
        DefaultTableModel model = (DefaultTableModel) tblCourses.getModel();
        model.setRowCount(0);
        
        for (CourseOffer offer : availableCourses) {
            addCourseToTable(model, offer);
        }
        
        loadEnrolledCourses();
    }

    private void addCourseToTable(DefaultTableModel model, CourseOffer offer) {
        Course course = offer.getSubjectCourse();
        int availableSeats = 0;
        
        for (Seat seat : offer.getSeatList()) {
            if (!seat.isOccupied()) {
                availableSeats++;
            }
        }
        
        String facultyName = "TBA";
        if (offer.getFacultyProfile() != null) {
            facultyName = "Faculty Assigned";
        }
        
        model.addRow(new Object[]{
            course.getCOurseNumber(),
            course.getCourseName(),
            facultyName,
            course.getCourseCredits(),
            availableSeats + " / " + offer.getSeatList().size(),
            "Mon/Wed 2:00-3:30", // Sample schedule
            "$" + course.getCoursePrice()
        });
    }

    private void loadEnrolledCourses() {
        DefaultTableModel model = (DefaultTableModel) tblEnrolledCourses.getModel();
        model.setRowCount(0);
        
        CourseLoad courseLoad = currentStudent.getCurrentCourseLoad();
        if (courseLoad != null) {
            for (SeatAssignment assignment : courseLoad.getSeatAssignments()) {
                Course course = assignment.getCourseOffer().getSubjectCourse();
                model.addRow(new Object[]{
                    course.getCOurseNumber(),
                    course.getCourseName(),
                    course.getCourseCredits(),
                    "Enrolled"
                });
            }
        }
    }

    private void updateCreditInfo() {
        totalCreditsEnrolled = 0;
        CourseLoad courseLoad = currentStudent.getCurrentCourseLoad();
        
        if (courseLoad != null) {
            for (SeatAssignment assignment : courseLoad.getSeatAssignments()) {
                totalCreditsEnrolled += assignment.getCourseOffer().getSubjectCourse().getCourseCredits();
            }
        }
        
        lblCreditInfo.setText("Credits Enrolled: " + totalCreditsEnrolled + " / 8 (Maximum)");
        
        if (totalCreditsEnrolled >= 8) {
            lblCreditInfo.setForeground(new java.awt.Color(204, 0, 0));
        } else {
            lblCreditInfo.setForeground(new java.awt.Color(0, 102, 204));
        }
    }

    // Variables declaration
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDrop;
    private javax.swing.JButton btnEnroll;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnShowAll;
    private javax.swing.JComboBox<String> cmbSearchType;
    private javax.swing.JComboBox<String> cmbSemester;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCreditInfo;
    private javax.swing.JLabel lblEnrolledCourses;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSemester;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblCourses;
    private javax.swing.JTable tblEnrolledCourses;
    private javax.swing.JTextField txtSearch;
}