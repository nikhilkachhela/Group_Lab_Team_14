/*
 * Transcript Panel - View academic history and grades
 * Author: Student Module Implementation
 * NUID: [Your NUID]
 */
package info5100.university.example.UI.Student;

import info5100.university.example.CourseSchedule.*;
import info5100.university.example.Department.Department;
import info5100.university.example.Persona.StudentProfile;
import info5100.university.example.Persona.Transcript;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TranscriptPanel extends javax.swing.JPanel {
    
    private StudentProfile currentStudent;
    private Department department;
    private JPanel cardPanel;
    private Map<String, Double> gradePointMap;
    private DecimalFormat df = new DecimalFormat("#.##");
    
    public TranscriptPanel(StudentProfile student, Department dept, JPanel cardPanel) {
        this.currentStudent = student;
        this.department = dept;
        this.cardPanel = cardPanel;
        initializeGradePointMap();
        initComponents();
        loadTranscriptData();
    }
    
    private void initializeGradePointMap() {
        gradePointMap = new HashMap<>();
        gradePointMap.put("A", 4.0);
        gradePointMap.put("A-", 3.7);
        gradePointMap.put("B+", 3.3);
        gradePointMap.put("B", 3.0);
        gradePointMap.put("B-", 2.7);
        gradePointMap.put("C+", 2.3);
        gradePointMap.put("C", 2.0);
        gradePointMap.put("C-", 1.7);
        gradePointMap.put("D+", 1.3);
        gradePointMap.put("D", 1.0);
        gradePointMap.put("F", 0.0);
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        lblTitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        lblSemesterFilter = new javax.swing.JLabel();
        cmbSemesterFilter = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTranscript = new javax.swing.JTable();
        lblOverallGPA = new javax.swing.JLabel();
        lblAcademicStanding = new javax.swing.JLabel();
        lblTotalCredits = new javax.swing.JLabel();
        lblTermGPA = new javax.swing.JLabel();
        btnPrintTranscript = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20));
        lblTitle.setText("Academic Transcript");

        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblSemesterFilter.setText("View by Semester:");

        cmbSemesterFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { 
            "All Semesters", "Fall2024", "Spring2025", "Summer2025", "Fall2025" 
        }));
        cmbSemesterFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSemesterFilterActionPerformed(evt);
            }
        });

        tblTranscript.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Term", "Course ID", "Course Name", "Credits", "Grade", "Quality Points", "Academic Standing"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblTranscript.setRowHeight(25);
        jScrollPane1.setViewportView(tblTranscript);

        lblOverallGPA.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblOverallGPA.setText("Overall GPA: 0.00");

        lblAcademicStanding.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblAcademicStanding.setText("Academic Standing: Good Standing");

        lblTotalCredits.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblTotalCredits.setText("Total Credits Earned: 0");

        lblTermGPA.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblTermGPA.setText("Term GPA: 0.00");

        btnPrintTranscript.setText("Print Transcript");
        btnPrintTranscript.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/print.png")));
        btnPrintTranscript.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintTranscriptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSemesterFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbSemesterFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrintTranscript, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOverallGPA, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTermGPA, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAcademicStanding, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
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
                    .addComponent(lblSemesterFilter)
                    .addComponent(cmbSemesterFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrintTranscript))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOverallGPA)
                    .addComponent(lblTotalCredits))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTermGPA)
                    .addComponent(lblAcademicStanding))
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        cardPanel.remove(this);
        ((java.awt.CardLayout) cardPanel.getLayout()).previous(cardPanel);
    }

    private void cmbSemesterFilterActionPerformed(java.awt.event.ActionEvent evt) {
        loadTranscriptData();
    }

    private void btnPrintTranscriptActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this,
            "Transcript print functionality will be available soon.\n" +
            "You can export your transcript to PDF format.",
            "Print Transcript",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadTranscriptData() {
        DefaultTableModel model = (DefaultTableModel) tblTranscript.getModel();
        model.setRowCount(0);
        
        String selectedSemester = (String) cmbSemesterFilter.getSelectedItem();
        
        double totalQualityPoints = 0;
        int totalCredits = 0;
        double termQualityPoints = 0;
        int termCredits = 0;
        
        // Get transcript data
        Transcript transcript = currentStudent.getTranscript();
        
        // Sample data for demonstration (would come from actual transcript)
        if (selectedSemester.equals("All Semesters") || selectedSemester.equals("Fall2024")) {
            // Sample courses for Fall 2024
            addCourseToTranscript(model, "Fall 2024", "INFO 5100", 
                "Application Engineering and Development", 4, "A", 4.0);
            addCourseToTranscript(model, "Fall 2024", "INFO 6150", 
                "Web Design and User Experience", 4, "A-", 3.7);
            
            if (selectedSemester.equals("Fall2024")) {
                termQualityPoints = (4 * 4.0) + (4 * 3.7);
                termCredits = 8;
            }
            
            totalQualityPoints += (4 * 4.0) + (4 * 3.7);
            totalCredits += 8;
        }
        
        if (selectedSemester.equals("All Semesters") || selectedSemester.equals("Spring2025")) {
            // Sample courses for Spring 2025
            addCourseToTranscript(model, "Spring 2025", "INFO 6105", 
                "Data Science Engineering Methods", 4, "B+", 3.3);
            addCourseToTranscript(model, "Spring 2025", "INFO 6205", 
                "Program Structure and Algorithms", 4, "A", 4.0);
            
            if (selectedSemester.equals("Spring2025")) {
                termQualityPoints = (4 * 3.3) + (4 * 4.0);
                termCredits = 8;
            }
            
            totalQualityPoints += (4 * 3.3) + (4 * 4.0);
            totalCredits += 8;
        }
        
        // Calculate GPAs
        double overallGPA = totalCredits > 0 ? totalQualityPoints / totalCredits : 0.0;
        double termGPA = termCredits > 0 ? termQualityPoints / termCredits : 0.0;
        
        // Update GPA labels
        lblOverallGPA.setText("Overall GPA: " + df.format(overallGPA));
        lblTermGPA.setText("Term GPA: " + df.format(termGPA));
        lblTotalCredits.setText("Total Credits Earned: " + totalCredits);
        
        // Determine academic standing
        String standing = determineAcademicStanding(overallGPA, termGPA);
        lblAcademicStanding.setText("Academic Standing: " + standing);
        
        // Color code academic standing
        if (standing.equals("Good Standing")) {
            lblAcademicStanding.setForeground(new Color(0, 153, 51));
        } else if (standing.equals("Academic Warning")) {
            lblAcademicStanding.setForeground(new Color(255, 153, 0));
        } else {
            lblAcademicStanding.setForeground(new Color(204, 0, 0));
        }
    }

    private void addCourseToTranscript(DefaultTableModel model, String term, 
                                       String courseId, String courseName, 
                                       int credits, String grade, double gradePoints) {
        double qualityPoints = credits * gradePoints;
        String standing = determineTermStanding(gradePoints);
        
        model.addRow(new Object[]{
            term,
            courseId,
            courseName,
            credits,
            grade,
            df.format(qualityPoints),
            standing
        });
    }

    private String determineAcademicStanding(double overallGPA, double termGPA) {
        if (overallGPA >= 3.0 && termGPA >= 3.0) {
            return "Good Standing";
        } else if (overallGPA >= 3.0 && termGPA < 3.0) {
            return "Academic Warning";
        } else {
            return "Academic Probation";
        }
    }

    private String determineTermStanding(double termGPA) {
        if (termGPA >= 3.0) {
            return "Good Standing";
        } else if (termGPA >= 2.0) {
            return "Academic Warning";
        } else {
            return "Academic Probation";
        }
    }

    // Variables declaration
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPrintTranscript;
    private javax.swing.JComboBox<String> cmbSemesterFilter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAcademicStanding;
    private javax.swing.JLabel lblOverallGPA;
    private javax.swing.JLabel lblSemesterFilter;
    private javax.swing.JLabel lblTermGPA;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalCredits;
    private javax.swing.JTable tblTranscript;
}