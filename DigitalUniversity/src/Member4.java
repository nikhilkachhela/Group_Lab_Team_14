/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author samee
 */
import model.MockDataStore;
import ui.faculty.FacultyDashBoard;

public class Member4 {

    public static void main(String[] args) {

        // 1. Load the mock/sample data into memory
        MockDataStore.init();

        // 2. Open the Faculty dashboard for FACULTY_1
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FacultyDashBoard frame = new FacultyDashBoard(MockDataStore.FACULTY_1);
                frame.setVisible(true);
            }
        });
    }
}


