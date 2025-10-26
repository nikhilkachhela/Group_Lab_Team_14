/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author samee

 */
package model;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MockDataStore {

    public static Faculty FACULTY_1;
    public static ArrayList<Course> courses = new ArrayList<>();
    public static ArrayList<Student> students = new ArrayList<>();
    public static ArrayList<EnrollmentRecord> enrollments = new ArrayList<>();

    // ---------------------------
    // INIT: called from Member4
    // ---------------------------
    public static void init() {
        // 1. Faculty: load from file or create default
        FACULTY_1 = loadFacultyFromFile();
        if (FACULTY_1 == null) {
            FACULTY_1 = new Faculty(
                "F001",
                "Dr. Ada Lovelace",
                "ada@northeastern.edu",
                "Information Systems",
                "Mon 2-4pm, Room 123",
                "ada123",
                "password123"
            );
        }

        // 2. Students (static demo data)
        students = new ArrayList<>();
        students.add(new Student("S001", "Alice Johnson", "alice@uni.edu"));
        students.add(new Student("S002", "Bob Patel", "bob@uni.edu"));
        students.add(new Student("S003", "Carlos Gomez", "carlos@uni.edu"));

        // 3. Courses: load from file or create default
        courses = loadCoursesFromFile();
        if (courses == null || courses.isEmpty()) {
            courses = new ArrayList<>();
            Course c1 = new Course("INFO5100", "Application Engineering", "Mon/Wed 2–4 PM", 30, FACULTY_1);
            Course c2 = new Course("INFO6150", "Web Design and UX", "Tue/Thu 11–1 PM", 25, FACULTY_1);
            courses.add(c1);
            courses.add(c2);
        }

        // 4. Enrollments: link students to courses
        enrollments = new ArrayList<>();
        // assume courses.get(0) = INFO5100, courses.get(1) = INFO6150, etc.
        Course c1ref = findCourseById("INFO5100");
        Course c2ref = findCourseById("INFO6150");

        if (c1ref != null) {
            enrollments.add(new EnrollmentRecord(students.get(0), c1ref, 95.0));
            enrollments.add(new EnrollmentRecord(students.get(1), c1ref, 88.5));
            enrollments.add(new EnrollmentRecord(students.get(2), c1ref, 76.0));
        }

        if (c2ref != null) {
            enrollments.add(new EnrollmentRecord(students.get(0), c2ref, 91.0));
            enrollments.add(new EnrollmentRecord(students.get(1), c2ref, 84.0));
        }
    }

    // helper: find course by ID
    public static Course findCourseById(String id) {
        for (Course c : courses) {
            if (c.getCourseId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    // ============================================================
    // FACULTY SAVE / LOAD (profile persistence)
    // ============================================================

    public static void saveFacultyToFile(Faculty f) {
        if (f == null) return;

        try {
            FileWriter fw = new FileWriter("faculty_profile.txt");
            fw.write(esc(f.getFacultyId()) + "|"
                   + esc(f.getName()) + "|"
                   + esc(f.getEmail()) + "|"
                   + esc(f.getDepartment()) + "|"
                   + esc(f.getOfficeHours()) + "|"
                   + esc(f.getUsername()) + "|"
                   + esc(f.getPassword()));
            fw.close();
            System.out.println("Faculty profile saved to file.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static Faculty loadFacultyFromFile() {
        File file = new File("faculty_profile.txt");
        if (!file.exists()) {
            return null;
        }

        try {
            Scanner sc = new Scanner(file);
            if (!sc.hasNextLine()) {
                sc.close();
                return null;
            }
            String line = sc.nextLine();
            sc.close();

            String[] parts = line.split("\\|", -1);
            if (parts.length < 7) {
                return null;
            }

            String facultyId   = unesc(parts[0]);
            String name        = unesc(parts[1]);
            String email       = unesc(parts[2]);
            String department  = unesc(parts[3]);
            String officeHours = unesc(parts[4]);
            String username    = unesc(parts[5]);
            String password    = unesc(parts[6]);

            return new Faculty(
                facultyId,
                name,
                email,
                department,
                officeHours,
                username,
                password
            );
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // ============================================================
    // COURSES SAVE / LOAD (course persistence)
    // ============================================================

    // Save all courses to disk
    // Each line = courseId|title|schedule|capacity
    // Only saves courses taught by FACULTY_1 (your current faculty)
    public static void saveCoursesToFile() {
        try {
            FileWriter fw = new FileWriter("courses_data.txt");

            for (Course c : courses) {
                // we only support one faculty login right now anyway
                if (c.getInstructor() == FACULTY_1) {
                    fw.write(
                        esc(c.getCourseId()) + "|" +
                        esc(c.getTitle()) + "|" +
                        esc(c.getSchedule()) + "|" +
                        c.getCapacity() +
                        "\n"
                    );
                }
            }

            fw.close();
            System.out.println("Courses saved to file.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Load courses from disk
    // Recreates Course objects and assigns instructor = FACULTY_1
    private static ArrayList<Course> loadCoursesFromFile() {
        File file = new File("courses_data.txt");
        if (!file.exists()) {
            return null;
        }

        ArrayList<Course> result = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\|", -1);
                if (parts.length < 4) continue;

                String id       = unesc(parts[0]);
                String title    = unesc(parts[1]);
                String schedule = unesc(parts[2]);
                String capStr   = parts[3];

                int capVal = 0;
                try {
                    capVal = Integer.parseInt(capStr);
                } catch (NumberFormatException e) {
                    capVal = 0;
                }

                Course c = new Course(id, title, schedule, capVal, FACULTY_1);
                result.add(c);
            }
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return result;
    }

    // ============================================================
    // helpers for escaping pipes
    // ============================================================

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("|", "\\|");
    }

    private static String unesc(String s) {
        if (s == null) return "";
        return s.replace("\\|", "|");
    }
}
