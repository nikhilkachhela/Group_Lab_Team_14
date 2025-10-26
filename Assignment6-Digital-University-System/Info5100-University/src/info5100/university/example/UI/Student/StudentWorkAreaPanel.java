/*
 * Student Work Area Panel - Main Dashboard
 * Author: Student Module Implementation
 * NUID: [Your NUID]
 */
package info5100.university.example.UI.Student;

import info5100.university.example.AccessControl.*;
import info5100.university.example.Department.Department;
import info5100.university.example.Finance.FinanceManager;
import info5100.university.example.Persona.StudentProfile;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * Main dashboard panel for student work area
 */
public class StudentWorkAreaPanel extends javax.swing.JPanel {
    
    private Department department;
    private UserDirectory userDirectory;
    private FinanceManager financeManager;
    private JPanel cardPanel;
    private AuthenticationService authService;
    private StudentProfile currentStudent;
    private User currentUser;
    
    /**
     * Creates new StudentWorkAreaPanel
     */
    public StudentWorkAreaPanel(Department dept, UserDirectory userDir, 
                                FinanceManager finMgr, JPanel cardPanel,
                                AuthenticationService authService, User user) {
        this.department = dept;
        this.userDirectory = userDir;
        this.financeManager = finMgr;
        this.cardPanel = cardPanel;
        this.authService = authService;
        this.currentUser = user;
        
        // Get the student profile from the user
        if (user.getProfileReference() instanceof StudentProfile) {
            this.currentStudent = (StudentProfile) user.getProfileReference();
        }
        
        initComponents();
        updateWelcomeMessage();
    }
    
    private void updateWelcomeMessage() {
        if (currentUser != null) {
            lblWelcome.setText("Welcome, " + currentUser.getUsername() + " (Student)");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        btnCourseRegistration = new javax.swing.JButton();
        btnViewTranscript = new javax.swing.JButton();
        btnGraduationAudit = new javax.swing.JButton();
        btnPayTuition = new javax.swing.JButton();
        btnMyProfile = new javax.swing.JButton();
        btnCoursework = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblQuickInfo = new javax.swing.JLabel();
        lblCurrentGPA = new javax.swing.JLabel();
        lblCreditsEarned = new javax.swing.JLabel();
        lblTuitionStatus = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTitle.setText("Student Dashboard");
        lblTitle.setForeground(new java.awt.Color(0, 102, 204));

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblWelcome.setText("Welcome, Student");

        btnCourseRegistration.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnCourseRegistration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/register.png")));
        btnCourseRegistration.setText("Course Registration");
        btnCourseRegistration.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCourseRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCourseRegistrationActionPerformed(evt);
            }
        });

        btnViewTranscript.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnViewTranscript.setText("View Transcript");
        btnViewTranscript.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnViewTranscript.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewTranscriptActionPerformed(evt);
            }
        });

        btnGraduationAudit.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnGraduationAudit.setText("Graduation Audit");
        btnGraduationAudit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGraduationAudit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraduationAuditActionPerformed(evt);
            }
        });

        btnPayTuition.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnPayTuition.setText("Financial Management");
        btnPayTuition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPayTuition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayTuitionActionPerformed(evt);
            }
        });

        btnMyProfile.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnMyProfile.setText("My Profile");
        btnMyProfile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMyProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMyProfileActionPerformed(evt);
            }
        });

        btnCoursework.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnCoursework.setText("Coursework Management");
        btnCoursework.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCoursework.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCourseworkActionPerformed(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnLogout.setText("Logout");
        btnLogout.setForeground(new java.awt.Color(204, 0, 0));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblQuickInfo.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblQuickInfo.setText("Quick Information");

        lblCurrentGPA.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblCurrentGPA.setText("Current GPA: 0.00");

        lblCreditsEarned.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblCreditsEarned.setText("Credits Earned: 0 / 32");

        lblTuitionStatus.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblTuitionStatus.setText("Tuition Status: $0.00 Due");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWelcome)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblQuickInfo)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCurrentGPA, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCreditsEarned, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTuitionStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCourseRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGraduationAudit, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPayTuition, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnViewTranscript, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCoursework, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMyProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWelcome)
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblQuickInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCurrentGPA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCreditsEarned)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTuitionStatus)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCourseRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewTranscript, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGraduationAudit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCoursework, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPayTuition, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMyProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void btnCourseRegistrationActionPerformed(java.awt.event.ActionEvent evt) {
        CourseRegistrationPanel panel = new CourseRegistrationPanel(
            department, currentStudent, financeManager, cardPanel
        );
        cardPanel.add(panel, "CourseRegistration");
        ((java.awt.CardLayout) cardPanel.getLayout()).show(cardPanel, "CourseRegistration");
    }

    private void btnViewTranscriptActionPerformed(java.awt.event.ActionEvent evt) {
        // Check if tuition is paid before showing transcript
        if (financeManager.hasOutstandingBalance(currentStudent)) {
            JOptionPane.showMessageDialog(this, 
                "You must pay your outstanding tuition balance before viewing your transcript.",
                "Access Denied",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        TranscriptPanel panel = new TranscriptPanel(
            currentStudent, department, cardPanel
        );
        cardPanel.add(panel, "Transcript");
        ((java.awt.CardLayout) cardPanel.getLayout()).show(cardPanel, "Transcript");
    }

    private void btnGraduationAuditActionPerformed(java.awt.event.ActionEvent evt) {
        GraduationAuditPanel panel = new GraduationAuditPanel(
            currentStudent, department, cardPanel
        );
        cardPanel.add(panel, "GraduationAudit");
        ((java.awt.CardLayout) cardPanel.getLayout()).show(cardPanel, "GraduationAudit");
    }

    private void btnPayTuitionActionPerformed(java.awt.event.ActionEvent evt) {
        FinancialManagementPanel panel = new FinancialManagementPanel(
            currentStudent, financeManager, department, cardPanel
        );
        cardPanel.add(panel, "FinancialManagement");
        ((java.awt.CardLayout) cardPanel.getLayout()).show(cardPanel, "FinancialManagement");
    }

    private void btnMyProfileActionPerformed(java.awt.event.ActionEvent evt) {
        StudentProfilePanel panel = new StudentProfilePanel(
            currentStudent, currentUser, userDirectory, cardPanel
        );
        cardPanel.add(panel, "StudentProfile");
        ((java.awt.CardLayout) cardPanel.getLayout()).show(cardPanel, "StudentProfile");
    }

    private void btnCourseworkActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, 
            "Coursework Management - Assignment submission feature coming soon!",
            "Feature In Development",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?", 
            "Confirm Logout", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            authService.logout();
            
            // Clear the content panel and show welcome message
            cardPanel.removeAll();
            JLabel logoutLabel = new JLabel("Logged out successfully", JLabel.CENTER);
            logoutLabel.setFont(new Font("Segoe UI", 0, 24));
            cardPanel.add(logoutLabel);
            cardPanel.revalidate();
            cardPanel.repaint();
            
            JOptionPane.showMessageDialog(this, 
                "You have been logged out. Please login again.",
                "Logged Out",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnCourseRegistration;
    private javax.swing.JButton btnCoursework;
    private javax.swing.JButton btnGraduationAudit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMyProfile;
    private javax.swing.JButton btnPayTuition;
    private javax.swing.JButton btnViewTranscript;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCreditsEarned;
    private javax.swing.JLabel lblCurrentGPA;
    private javax.swing.JLabel lblQuickInfo;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTuitionStatus;
    private javax.swing.JLabel lblWelcome;
    // End of variables declaration
}