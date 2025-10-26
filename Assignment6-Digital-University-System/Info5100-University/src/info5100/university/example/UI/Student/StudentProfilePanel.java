/*
 * Student Profile Panel - Manage personal profile
 * Author: Student Module Implementation
 * NUID: [Your NUID]
 */
package info5100.university.example.UI.Student;

import info5100.university.example.AccessControl.*;
import info5100.university.example.Persona.StudentProfile;
import javax.swing.*;
import java.awt.*;

public class StudentProfilePanel extends javax.swing.JPanel {
    
    private StudentProfile currentStudent;
    private User currentUser;
    private UserDirectory userDirectory;
    private JPanel cardPanel;
    
    public StudentProfilePanel(StudentProfile student, User user, 
                              UserDirectory userDir, JPanel cardPanel) {
        this.currentStudent = student;
        this.currentUser = user;
        this.userDirectory = userDir;
        this.cardPanel = cardPanel;
        initComponents();
        loadProfileData();
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        lblTitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        pnlPersonalInfo = new javax.swing.JPanel();
        lblStudentId = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblFirstName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        lblLastName = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        pnlAcademicInfo = new javax.swing.JPanel();
        lblProgram = new javax.swing.JLabel();
        lblEnrollmentDate = new javax.swing.JLabel();
        lblExpectedGraduation = new javax.swing.JLabel();
        lblAdvisor = new javax.swing.JLabel();
        btnUpdateProfile = new javax.swing.JButton();
        btnChangePassword = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20));
        lblTitle.setText("My Profile");

        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        // Personal Information Panel
        pnlPersonalInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(
            null, "Personal Information", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new java.awt.Font("Segoe UI", 1, 14)));
        pnlPersonalInfo.setBackground(new java.awt.Color(245, 245, 245));

        lblStudentId.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblStudentId.setText("Student ID: 002000001");

        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblUsername.setText("Username: student1");

        lblFirstName.setText("First Name:");

        lblLastName.setText("Last Name:");

        lblEmail.setText("Email:");

        lblPhone.setText("Phone:");

        lblAddress.setText("Address:");

        javax.swing.GroupLayout pnlPersonalLayout = new javax.swing.GroupLayout(pnlPersonalInfo);
        pnlPersonalInfo.setLayout(pnlPersonalLayout);
        pnlPersonalLayout.setHorizontalGroup(
            pnlPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPersonalLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPersonalLayout.createSequentialGroup()
                        .addComponent(lblStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPersonalLayout.createSequentialGroup()
                        .addGroup(pnlPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(pnlPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlPersonalLayout.setVerticalGroup(
            pnlPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPersonalLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStudentId)
                    .addComponent(lblUsername))
                .addGap(20, 20, 20)
                .addGroup(pnlPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnlPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLastName)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnlPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnlPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhone)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnlPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        // Academic Information Panel
        pnlAcademicInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(
            null, "Academic Information", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new java.awt.Font("Segoe UI", 1, 14)));
        pnlAcademicInfo.setBackground(new java.awt.Color(245, 245, 245));

        lblProgram.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblProgram.setText("Program: Master of Science in Information Systems (MSIS)");

        lblEnrollmentDate.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblEnrollmentDate.setText("Enrollment Date: Fall 2024");

        lblExpectedGraduation.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblExpectedGraduation.setText("Expected Graduation: Spring 2026");

        lblAdvisor.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblAdvisor.setText("Academic Advisor: Dr. John Smith");

        javax.swing.GroupLayout pnlAcademicLayout = new javax.swing.GroupLayout(pnlAcademicInfo);
        pnlAcademicInfo.setLayout(pnlAcademicLayout);
        pnlAcademicLayout.setHorizontalGroup(
            pnlAcademicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcademicLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlAcademicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProgram)
                    .addComponent(lblEnrollmentDate)
                    .addComponent(lblExpectedGraduation)
                    .addComponent(lblAdvisor))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlAcademicLayout.setVerticalGroup(
            pnlAcademicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcademicLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblProgram)
                .addGap(15, 15, 15)
                .addComponent(lblEnrollmentDate)
                .addGap(15, 15, 15)
                .addComponent(lblExpectedGraduation)
                .addGap(15, 15, 15)
                .addComponent(lblAdvisor)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnUpdateProfile.setText("Update Profile");
        btnUpdateProfile.setBackground(new java.awt.Color(0, 102, 204));
        btnUpdateProfile.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProfileActionPerformed(evt);
            }
        });

        btnChangePassword.setText("Change Password");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addComponent(pnlPersonalInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAcademicInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUpdateProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(pnlPersonalInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pnlAcademicInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        cardPanel.remove(this);
        ((java.awt.CardLayout) cardPanel.getLayout()).previous(cardPanel);
    }

    private void btnUpdateProfileActionPerformed(java.awt.event.ActionEvent evt) {
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String address = txtAddress.getText().trim();
        
        // Validate email format
        if (!email.isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid email address.",
                "Invalid Email",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Update profile (would save to database)
        JOptionPane.showMessageDialog(this,
            "Profile updated successfully!",
            "Update Successful",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {
        // Show password change dialog
        JPasswordField oldPassword = new JPasswordField();
        JPasswordField newPassword = new JPasswordField();
        JPasswordField confirmPassword = new JPasswordField();
        
        Object[] message = {
            "Current Password:", oldPassword,
            "