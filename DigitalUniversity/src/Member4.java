/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author samee
 */
import model.MockDataStore;

public class Member4 {

    public static void main(String[] args) {

        // Initialize fake data
        MockDataStore.init();

        // Print sample outputs
        System.out.println("Faculty: " + MockDataStore.FACULTY_1.getName());
        System.out.println("Department: " + MockDataStore.FACULTY_1.getDepartment());
        System.out.println("Courses: " + MockDataStore.courses.size());
        System.out.println("Students: " + MockDataStore.students.size());
        System.out.println("Enrollments: " + MockDataStore.enrollments.size());
    }
}

