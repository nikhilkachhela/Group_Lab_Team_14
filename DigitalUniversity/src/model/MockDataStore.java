/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samee
 * Fake database for sample data used by the Faculty dashboard.
 */
public class MockDataStore {

    // Pretend "logged in" faculty
    public static Faculty FACULTY_1;

    // Lists to hold demo data
    public static List<Course> courses = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();
    public static List<EnrollmentRecord> enrollments = new ArrayList<>();

    // Initialize fake data
    public static void init() {

        // 1️⃣ Create sample faculty (the one logged in)
        FACULTY_1 = new Faculty(
                "F001",
                "Dr. Ada Lovelace",
                "ada.lovelace@northeastern.edu",
                "Information Systems",
                "Tue 2–4 PM, Room 301",
                "prof1",
                "pass123"
        );

        // 2️⃣ Create sample students
        Student s1 = new Student("S001", "Alex Kim", "alex.kim@northeastern.edu");
        Student s2 = new Student("S002", "Priya Shah", "priya.shah@northeastern.edu");
        Student s3 = new Student("S003", "Marco Diaz", "marco.diaz@northeastern.edu");
        students.add(s1);
        students.add(s2);
        students.add(s3);

        // 3️⃣ Create sample courses (now uses your short constructor)
        Course c1 = new Course("INFO5100", "Application Engineering", 30, "Mon/Wed 2–4 PM", FACULTY_1);
        Course c2 = new Course("INFO6150", "Web Design and UX", 25, "Tue/Thu 11–1 PM", FACULTY_1);
        courses.add(c1);
        courses.add(c2);

        // 4️⃣ Enrollment and grades
        enrollments.add(new EnrollmentRecord(s1, c1, 95.0));
        enrollments.add(new EnrollmentRecord(s2, c1, 88.5));
        enrollments.add(new EnrollmentRecord(s3, c1, 76.0));
        enrollments.add(new EnrollmentRecord(s1, c2, 91.0));
        enrollments.add(new EnrollmentRecord(s2, c2, 84.0));
        // Marco didn't take c2
    }
}


    

