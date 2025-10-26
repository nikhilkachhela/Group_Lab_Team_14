/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.faculty;

/**
 *
 * @author samee
 */
public class FacultyDashBoard extends javax.swing.JFrame {
        private model.Faculty currentFaculty;


    /**
     * Creates new form FacultyDashBoard
     */
    public FacultyDashBoard(model.Faculty faculty) {
    initComponents();
    this.currentFaculty = faculty;
    
     cmbCourseSelect.setModel(new javax.swing.DefaultComboBoxModel());
     
    loadProfileTab();
    loadCoursesTab();       
    setupCourseTableClick(); 
    loadStudentsTab();

}

private void loadProfileTab() {
    if (currentFaculty != null) {
        txtName.setText(currentFaculty.getName());
        txtEmail.setText(currentFaculty.getEmail());
        txtDepartment.setText(currentFaculty.getDepartment());
        txtOfficeHours.setText(currentFaculty.getOfficeHours());
    } else {
        txtName.setText("");
        txtEmail.setText("");
        txtDepartment.setText("");
        txtOfficeHours.setText("");
    }
}


private void loadCoursesTab() {
  
    javax.swing.table.DefaultTableModel courseModel =
        new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[] { "Course ID", "Title", "Schedule", "Capacity" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

  
    for (model.Course c : model.MockDataStore.courses) {
        if (c.getInstructor() == currentFaculty) {
            courseModel.addRow(new Object[]{
                c.getCourseId(),
                c.getTitle(),
                c.getSchedule(),
                c.getCapacity()
            });
        }
    }

    tblCourses.setModel(courseModel);

    if (tblCourses.getRowCount() > 0) {
        tblCourses.setRowSelectionInterval(0, 0);
        fillCourseEditorFromRow(0);
    }
}

private void fillCourseEditorFromRow(int row) {
    if (row < 0) {
        return;
    }

    Object idVal = tblCourses.getValueAt(row, 0);
    Object titleVal = tblCourses.getValueAt(row, 1);
    Object schedVal = tblCourses.getValueAt(row, 2);
    Object capVal = tblCourses.getValueAt(row, 3);

    if (idVal != null) {
        txtCourseId.setText(idVal.toString());
    }
    if (titleVal != null) {
        txtTitle.setText(titleVal.toString());
    }
    if (schedVal != null) {
        txtSchedule.setText(schedVal.toString());
    }
    if (capVal != null) {
        txtCapacity.setText(capVal.toString());
    }
}

private void setupCourseTableClick() {
    tblCourses.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int row = tblCourses.getSelectedRow();
            fillCourseEditorFromRow(row);
        }
    });
}

// ---------------- STUDENTS TAB: fill the dropdown and load table ----------------
private void loadStudentsTab() {
    // Clear any items currently in the dropdown
    cmbCourseSelect.removeAllItems();
    
    System.out.println("DEBUG loadStudentsTab: currentFaculty = " +
    (currentFaculty == null ? "null" : currentFaculty.getFacultyId()));

for (model.Course c : model.MockDataStore.courses) {
    System.out.println("DEBUG course " + c.getCourseId() +
        " taught by " + (c.getInstructor() == null ? "null" : c.getInstructor().getFacultyId()));
    
}


    // Add each course taught by this faculty
    for (model.Course c : model.MockDataStore.courses) {
        if (c.getInstructor() != null &&
            currentFaculty != null &&
            c.getInstructor().getFacultyId().equals(currentFaculty.getFacultyId())) {

            // We are adding String labels now (not Course objects)
            cmbCourseSelect.addItem(c.getCourseId() + " - " + c.getTitle());
        }
    }

    // After populating, select the first one and load data
    if (cmbCourseSelect.getItemCount() > 0) {
        cmbCourseSelect.setSelectedIndex(0);
        updateStudentTableAndStats();
    } else {
        // If no courses matched, clear table + GPA
        javax.swing.table.DefaultTableModel emptyModel =
            new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[] { "Student ID", "Student Name", "Grade %", "Letter" }
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        tblStudents.setModel(emptyModel);
        lblClassGpa.setText("0.00");
    }
}


// ---------------- STUDENTS TAB: update table + GPA ----------------
private void updateStudentTableAndStats() {
    // 1. Figure out which course is selected in the combo box
    String selectedName = (String) cmbCourseSelect.getSelectedItem();
    if (selectedName == null) {
        return;
    }

    model.Course selectedCourse = null;
    for (model.Course c : model.MockDataStore.courses) {
        String label = c.getCourseId() + " - " + c.getTitle(); // same string we added to dropdown
        if (label.equals(selectedName)) {
            selectedCourse = c;
            break;
        }
    }

    if (selectedCourse == null) {
        return;
    }

    // 2. Build the table model for students in that course
    javax.swing.table.DefaultTableModel studentModel =
        new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[] { "Student ID", "Student Name", "Grade %", "Letter" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

    // We'll compute GPA using GPA points (0.0 - 4.0), not raw percent
    double totalGpaPoints = 0.0;
    int count = 0;

    for (model.EnrollmentRecord rec : model.MockDataStore.enrollments) {
        if (rec.getCourse() != null &&
            rec.getCourse().getCourseId().equals(selectedCourse.getCourseId())) {

            model.Student s = rec.getStudent();
            double grade = rec.getGrade();          // ex: 86.5
            String letter = letterFromGrade(grade); // ex: "B"
            double gpaPoints = gpaFromLetter(letter); // ex: 3.0

            // Add row in table
            studentModel.addRow(new Object[]{
                s.getStudentId(),
                s.getName(),
                grade,
                letter
            });

            totalGpaPoints += gpaPoints;
            count++;
        }
    }

    // Show rows in UI
    tblStudents.setModel(studentModel);

    // 3. Update GPA label at bottom
    if (count > 0) {
        double avgGpa = totalGpaPoints / count;      // ex: 3.25 out of 4.0
        lblClassGpa.setText(String.format("%.2f", avgGpa));
    } else {
        lblClassGpa.setText("0.00");
    }
}

// ---------------- helper: numeric grade to letter ----------------
private String letterFromGrade(double grade) {
    if (grade >= 90) return "A";
    if (grade >= 80) return "B";
    if (grade >= 70) return "C";
    if (grade >= 60) return "D";
    return "F";
    
  
}


  



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabMain = new javax.swing.JTabbedPane();
        pnlProfile = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblDepartment = new javax.swing.JLabel();
        lblOfficeHours = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDepartment = new javax.swing.JTextField();
        txtOfficeHours = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        pnlCourses = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCourses = new javax.swing.JTable();
        lblCourseId = new javax.swing.JLabel();
        txtCourseId = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        lblSchedule = new javax.swing.JLabel();
        txtSchedule = new javax.swing.JTextField();
        lblCapacity = new javax.swing.JLabel();
        txtCapacity = new javax.swing.JTextField();
        btnSaveCourseChanges = new javax.swing.JButton();
        pnlStudents = new javax.swing.JPanel();
        lblSelectCourse = new javax.swing.JLabel();
        cmbCourseSelect = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStudents = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lblClassGpa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Faculty Dashboard");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblName.setText("Name");

        lblEmail.setText("Email");

        lblDepartment.setText("Department");

        lblOfficeHours.setText("Office Hours");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        btnSave.setText("Save Profile");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProfileLayout = new javax.swing.GroupLayout(pnlProfile);
        pnlProfile.setLayout(pnlProfileLayout);
        pnlProfileLayout.setHorizontalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProfileLayout.createSequentialGroup()
                        .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblOfficeHours, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                        .addGap(61, 61, 61)
                        .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtName)
                            .addComponent(txtEmail)
                            .addComponent(txtDepartment)
                            .addComponent(txtOfficeHours, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
                    .addGroup(pnlProfileLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(btnSave)))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        pnlProfileLayout.setVerticalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDepartment)
                    .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOfficeHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOfficeHours))
                .addGap(37, 37, 37)
                .addComponent(btnSave)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        tabMain.addTab("Profile", pnlProfile);

        pnlCourses.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblCourses);

        pnlCourses.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 460, 200));

        lblCourseId.setText("Course ID");
        pnlCourses.add(lblCourseId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));
        pnlCourses.add(txtCourseId, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, -1, -1));

        lblTitle.setText("Title");
        pnlCourses.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, -1, -1));
        pnlCourses.add(txtTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 150, -1));

        lblSchedule.setText("Schedule");
        pnlCourses.add(lblSchedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, -1, -1));
        pnlCourses.add(txtSchedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, 150, -1));

        lblCapacity.setText("Capacity");
        pnlCourses.add(lblCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));
        pnlCourses.add(txtCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, -1));

        btnSaveCourseChanges.setText("Save Course Changes");
        btnSaveCourseChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveCourseChangesActionPerformed(evt);
            }
        });
        pnlCourses.add(btnSaveCourseChanges, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, -1, -1));

        tabMain.addTab("Courses", pnlCourses);

        pnlStudents.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSelectCourse.setText("Select Course");
        pnlStudents.add(lblSelectCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        cmbCourseSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCourseSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCourseSelectActionPerformed(evt);
            }
        });
        pnlStudents.add(cmbCourseSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        tblStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblStudents);

        pnlStudents.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 420, 220));

        jLabel2.setText("Class GPA:");
        pnlStudents.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        lblClassGpa.setText("0.00");
        pnlStudents.add(lblClassGpa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, -1, -1));

        tabMain.addTab("Students/Grades", pnlStudents);

        getContentPane().add(tabMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnSaveCourseChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveCourseChangesActionPerformed
        // TODO add your handling code here:
     String newId = txtCourseId.getText().trim();
    String newTitle = txtTitle.getText().trim();
    String newSchedule = txtSchedule.getText().trim();
    String newCapacityText = txtCapacity.getText().trim();

    // Try to parse capacity into an int
    Integer newCapacity = null;
    try {
        newCapacity = Integer.parseInt(newCapacityText);
    } catch (NumberFormatException ex) {
        System.out.println("Capacity is not a number. Leaving previous capacity.");
    }

    // 2. Find the matching Course in MockDataStore
    model.Course courseToUpdate = null;
    for (model.Course c : model.MockDataStore.courses) {
        if (c.getInstructor() == currentFaculty &&
            c.getCourseId().equals(newId)) {
            courseToUpdate = c;
            break;
        }
    }

    if (courseToUpdate == null) {
        System.out.println("No matching course found to update.");
        return;
    }

    // 3. Update in-memory course
    courseToUpdate.setTitle(newTitle);
    courseToUpdate.setSchedule(newSchedule);
    if (newCapacity != null) {
        courseToUpdate.setCapacity(newCapacity);
    }

    // 4. Write ALL courses to disk so it persists next run
    model.MockDataStore.saveCoursesToFile();

    // 5. Debug print for grading/demo
    System.out.println("Course updated and saved:");
    System.out.println("  ID:       " + courseToUpdate.getCourseId());
    System.out.println("  Title:    " + courseToUpdate.getTitle());
    System.out.println("  Schedule: " + courseToUpdate.getSchedule());
    System.out.println("  Capacity: " + courseToUpdate.getCapacity());

    // 6. Reload table from the updated in-memory list
    loadCoursesTab();



    }//GEN-LAST:event_btnSaveCourseChangesActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (currentFaculty != null) {
        currentFaculty.setName(txtName.getText());
        currentFaculty.setEmail(txtEmail.getText());
        currentFaculty.setDepartment(txtDepartment.getText());
        currentFaculty.setOfficeHours(txtOfficeHours.getText());

        System.out.println("Profile updated for: " + currentFaculty.getName());
        
        model.MockDataStore.saveFacultyToFile(currentFaculty);
        
        loadProfileTab();
    }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void cmbCourseSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCourseSelectActionPerformed
        // TODO add your handling code here:
        updateStudentTableAndStats();
    }//GEN-LAST:event_cmbCourseSelectActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveCourseChanges;
    private javax.swing.JComboBox<String> cmbCourseSelect;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCapacity;
    private javax.swing.JLabel lblClassGpa;
    private javax.swing.JLabel lblCourseId;
    private javax.swing.JLabel lblDepartment;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblOfficeHours;
    private javax.swing.JLabel lblSchedule;
    private javax.swing.JLabel lblSelectCourse;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlCourses;
    private javax.swing.JPanel pnlProfile;
    private javax.swing.JPanel pnlStudents;
    private javax.swing.JTabbedPane tabMain;
    private javax.swing.JTable tblCourses;
    private javax.swing.JTable tblStudents;
    private javax.swing.JTextField txtCapacity;
    private javax.swing.JTextField txtCourseId;
    private javax.swing.JTextField txtDepartment;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtOfficeHours;
    private javax.swing.JTextField txtSchedule;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
