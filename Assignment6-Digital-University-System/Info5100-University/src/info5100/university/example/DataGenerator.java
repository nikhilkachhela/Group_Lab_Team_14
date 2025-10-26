/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example;
import info5100.university.example.AccessControl.*;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseCatalog.CourseCatalog;
import info5100.university.example.CourseSchedule.*;
import info5100.university.example.Department.Department;
import info5100.university.example.Finance.FinanceManager;
import info5100.university.example.Grading.*;
import info5100.university.example.Persona.*;
import info5100.university.example.Persona.Faculty.FacultyDirectory;
import info5100.university.example.Persona.Faculty.FacultyProfile;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Sandeep Patil
 */
public class DataGenerator {
    
    private Department department;
    private PersonDirectory personDirectory;
    private StudentDirectory studentDirectory;
    private FacultyDirectory facultyDirectory;
    private UserDirectory userDirectory;
    private CourseCatalog courseCatalog;
    private FinanceManager financeManager;
    private Random random;
    
    // Store references for easy access
    private ArrayList<StudentProfile> students;
    private ArrayList<FacultyProfile> faculties;
    private ArrayList<Course> courses;
    
    public DataGenerator() {
        this.random = new Random();
        this.students = new ArrayList<>();
        this.faculties = new ArrayList<>();
        this.courses = new ArrayList<>();
    }
    
    /**
     * Main method to generate all data
     * @return Department with all data populated
     */
    public Department generateData() {
        System.out.println("=== Starting Data Generation for Northeastern MSIS ===\n");
        
        // Step 1: Create Department
        createDepartment();
        
        // Step 2: Create Courses (MSIS courses)
        createCourses();
        
        // Step 3: Create Persons (30 total)
        createPersons();
        
        // Step 4: Create Students (10)
        createStudents();
        
        // Step 5: Create Faculty (10)
        createFaculty();
        
        // Step 6: Create Admin User (1)
        createAdmin();
        
        // Step 7: Create Registrar User (1)
        createRegistrar();
        
        // Step 8: Create Course Schedule for Fall 2024
        createCourseSchedule();
        
        // Step 9: Assign Faculty to Courses
        assignFacultyToCourses();
        
        // Step 10: Register Students in Courses
        registerStudentsInCourses();
        
        // Step 11: Create Assignments and Grades
        createAssignmentsAndGrades();
        
        System.out.println("\n=== Data Generation Complete ===");
        printSummary();
        
        return department;
    }
    
    // Step 1: Create Department
    private void createDepartment() {
        department = new Department("Information Systems");
        personDirectory = department.getPersonDirectory();
        studentDirectory = department.getStudentDirectory();
        courseCatalog = department.getCourseCatalog();
        
        // Initialize directories
        facultyDirectory = new FacultyDirectory(department);
        userDirectory = new UserDirectory();
        financeManager = new FinanceManager();
        
        System.out.println("✓ Created Department: Information Systems");
    }
    
    // Step 2: Create MSIS Courses
    private void createCourses() {
        System.out.println("\n--- Creating MSIS Courses ---");
        
        // Core Course
        Course info5100 = courseCatalog.newCourse("Application Engineering and Development", "INFO 5100", 4);
        department.addCoreCourse(info5100);
        courses.add(info5100);
        System.out.println("✓ Created Core: INFO 5100 - Application Engineering and Development");
        
        // Elective Courses
        Course info6205 = courseCatalog.newCourse("Program Structure and Algorithms", "INFO 6205", 4);
        department.addElectiveCourse(info6205);
        courses.add(info6205);
        System.out.println("✓ Created Elective: INFO 6205 - Program Structure and Algorithms");
        
        Course info6250 = courseCatalog.newCourse("Web Development Tools and Methods", "INFO 6250", 4);
        department.addElectiveCourse(info6250);
        courses.add(info6250);
        System.out.println("✓ Created Elective: INFO 6250 - Web Development Tools and Methods");
        
        Course info7500 = courseCatalog.newCourse("Cryptocurrency and Smart Contract Engineering", "INFO 7500", 4);
        department.addElectiveCourse(info7500);
        courses.add(info7500);
        System.out.println("✓ Created Elective: INFO 7500 - Cryptocurrency and Smart Contract");
        
        Course info6150 = courseCatalog.newCourse("Web Design and User Experience", "INFO 6150", 4);
        department.addElectiveCourse(info6150);
        courses.add(info6150);
        System.out.println("✓ Created Elective: INFO 6150 - Web Design and User Experience");
        
        Course info7245 = courseCatalog.newCourse("Big Data Systems and Intelligence Analytics", "INFO 7245", 4);
        department.addElectiveCourse(info7245);
        courses.add(info7245);
        System.out.println("✓ Created Elective: INFO 7245 - Big Data Systems");
        
        Course info7255 = courseCatalog.newCourse("Advanced Data Sciences and Architecture", "INFO 7255", 4);
        department.addElectiveCourse(info7255);
        courses.add(info7255);
        System.out.println("✓ Created Elective: INFO 7255 - Advanced Data Sciences");
    }
    
    // Step 3: Create 30 Persons
    private void createPersons() {
        System.out.println("\n--- Creating 30 Persons ---");
        
        // We'll create persons as we create students, faculty, admin, registrar
        // This method is a placeholder to show the count
    }
    
    // Step 4: Create 10 Students
    private void createStudents() {
        System.out.println("\n--- Creating 10 Students ---");
        
        String[] firstNames = {"Emily", "Michael", "Sarah", "David", "Jessica", 
                               "James", "Ashley", "Daniel", "Lisa", "Matthew"};
        String[] lastNames = {"Chen", "Patel", "Johnson", "Kim", "Garcia", 
                             "Martinez", "Brown", "Lee", "Wilson", "Anderson"};
        
        for (int i = 0; i < 10; i++) {
            // Create Person
            String personId = "002" + String.format("%06d", i + 1);
            Person person = personDirectory.newPerson(personId);
            
            // Create Student Profile
            StudentProfile student = studentDirectory.newStudentProfile(person);
            students.add(student);
            
            // Create User Account for Student
            String username = (firstNames[i] + lastNames[i]).toLowerCase();
            String password = "student@123"; // Default password
            User user = userDirectory.newUser(person, username, password, Role.STUDENT);
            user.setProfileReference(student);
            
            System.out.println("✓ Created Student: " + username + " (ID: " + personId + ")");
        }
    }
    
    // Step 5: Create 10 Faculty
    private void createFaculty() {
        System.out.println("\n--- Creating 10 Faculty Members ---");
        
        String[] facultyFirstNames = {"Robert", "Jennifer", "William", "Linda", "Richard",
                                     "Patricia", "Thomas", "Maria", "Charles", "Nancy"};
        String[] facultyLastNames = {"Smith", "Jones", "Williams", "Davis", "Miller",
                                    "Moore", "Taylor", "Thomas", "White", "Martin"};
        
        for (int i = 0; i < 10; i++) {
            // Create Person
            String personId = "001" + String.format("%06d", i + 1);
            Person person = personDirectory.newPerson(personId);
            
            // Create Faculty Profile
            FacultyProfile faculty = facultyDirectory.newFacultyProfile(person);
            faculties.add(faculty);
            
            // Create User Account for Faculty
            String username = "prof" + facultyLastNames[i].toLowerCase();
            String password = "faculty@123"; // Default password
            User user = userDirectory.newUser(person, username, password, Role.FACULTY);
            user.setProfileReference(faculty);
            
            System.out.println("✓ Created Faculty: " + username + " (ID: " + personId + ")");
        }
    }
    
    // Step 6: Create Admin
    private void createAdmin() {
        System.out.println("\n--- Creating Administrator ---");
        
        String personId = "000000001";
        Person person = personDirectory.newPerson(personId);
        
        String username = "admin";
        String password = "admin@123";
        User user = userDirectory.newUser(person, username, password, Role.ADMIN);
        
        System.out.println("✓ Created Admin: " + username + " (ID: " + personId + ")");
    }
    
    // Step 7: Create Registrar
    private void createRegistrar() {
        System.out.println("\n--- Creating Registrar ---");
        
        String personId = "000000002";
        Person person = personDirectory.newPerson(personId);
        
        String username = "registrar";
        String password = "registrar@123";
        User user = userDirectory.newUser(person, username, password, Role.REGISTRAR);
        
        System.out.println("✓ Created Registrar: " + username + " (ID: " + personId + ")");
    }
    
    // Step 8: Create Course Schedule for Fall 2024
    private void createCourseSchedule() {
        System.out.println("\n--- Creating Course Schedule for Fall 2024 ---");
        
        CourseSchedule fall2024 = department.newCourseSchedule("Fall2024");
        
        // Create course offers for first 5 courses
        for (int i = 0; i < 5 && i < courses.size(); i++) {
            Course course = courses.get(i);
            CourseOffer courseOffer = fall2024.newCourseOffer(course.getCOurseNumber());
            
            if (courseOffer != null) {
                courseOffer.generatSeats(15); // 15 seats per course
                System.out.println("✓ Created Course Offer: " + course.getCOurseNumber() + " with 15 seats");
            }
        }
    }
    
    // Step 9: Assign Faculty to Courses
    private void assignFacultyToCourses() {
        System.out.println("\n--- Assigning Faculty to Courses ---");
        
        CourseSchedule fall2024 = department.getCourseSchedule("Fall2024");
        
        for (int i = 0; i < 5 && i < faculties.size(); i++) {
            Course course = courses.get(i);
            FacultyProfile faculty = faculties.get(i);
            
            CourseOffer courseOffer = fall2024.getCourseOfferByNumber(course.getCOurseNumber());
            if (courseOffer != null) {
                courseOffer.AssignAsTeacher(faculty);
                System.out.println("✓ Assigned Faculty to " + course.getCOurseNumber());
            }
        }
    }
    
    // Step 10: Register Students in Courses
    private void registerStudentsInCourses() {
        System.out.println("\n--- Registering Students in Courses ---");
        
        CourseSchedule fall2024 = department.getCourseSchedule("Fall2024");
        
        // Each student registers for 2-3 courses (8 credits max as per requirements)
        for (StudentProfile student : students) {
            CourseLoad courseLoad = student.newCourseLoad("Fall2024");
            
            // Random 2 courses per student
            int numCourses = 2;
            ArrayList<Integer> selectedCourses = new ArrayList<>();
            
            for (int i = 0; i < numCourses; i++) {
                int courseIndex;
                do {
                    courseIndex = random.nextInt(5);
                } while (selectedCourses.contains(courseIndex));
                
                selectedCourses.add(courseIndex);
                
                Course course = courses.get(courseIndex);
                CourseOffer courseOffer = fall2024.getCourseOfferByNumber(course.getCOurseNumber());
                
                if (courseOffer != null) {
                    SeatAssignment sa = courseLoad.newSeatAssignment(courseOffer);
                    
                    if (sa != null) {
                        // Charge tuition
                        double tuition = course.getCoursePrice();
                        financeManager.chargeTuitionForCourse(student, tuition, 
                                                             course.getCOurseNumber(), "Fall2024");
                        
                        System.out.println("✓ Registered student in " + course.getCOurseNumber());
                    }
                }
            }
        }
    }
    
    // Step 11: Create Assignments and Grades
    private void createAssignmentsAndGrades() {
        System.out.println("\n--- Creating Assignments and Sample Grades ---");
        
        CourseSchedule fall2024 = department.getCourseSchedule("Fall2024");
        
        for (int i = 0; i < 5 && i < courses.size(); i++) {
            Course course = courses.get(i);
            CourseOffer courseOffer = fall2024.getCourseOfferByNumber(course.getCOurseNumber());
            
            if (courseOffer != null) {
                // Create gradebook for this course
                CourseGradeBook gradeBook = new CourseGradeBook(courseOffer);
                
                // Create 3 assignments
                Assignment a1 = gradeBook.createAssignment("Assignment 1", 
                        "First assignment", 100);
                Assignment a2 = gradeBook.createAssignment("Midterm Project", 
                        "Mid-semester project", 200);
                Assignment a3 = gradeBook.createAssignment("Final Project", 
                        "Final project", 300);
                
                System.out.println("✓ Created 3 assignments for " + course.getCOurseNumber());
            }
        }
    }
    
    // Print Summary
    private void printSummary() {
        System.out.println("\n========================================");
        System.out.println("NORTHEASTERN MSIS DATA SUMMARY");
        System.out.println("========================================");
        System.out.println("Department: Information Systems");
        System.out.println("Semester: Fall 2024");
        System.out.println("----------------------------------------");
        System.out.println("Total Persons Created: 22");
        System.out.println("  - Students: 10");
        System.out.println("  - Faculty: 10");
        System.out.println("  - Admin: 1");
        System.out.println("  - Registrar: 1");
        System.out.println("----------------------------------------");
        System.out.println("Total Courses: " + courses.size());
        System.out.println("  - Core Courses: 1 (INFO 5100)");
        System.out.println("  - Elective Courses: " + (courses.size() - 1));
        System.out.println("----------------------------------------");
        System.out.println("Course Offers (Fall 2024): 5");
        System.out.println("Total Seats Available: 75 (15 per course)");
        System.out.println("========================================\n");
        
        System.out.println("DEFAULT LOGIN CREDENTIALS:");
        System.out.println("  Admin: username='admin', password='admin@123'");
        System.out.println("  Registrar: username='registrar', password='registrar@123'");
        System.out.println("  Faculty: username='prof[lastname]', password='faculty@123'");
        System.out.println("  Student: username='[firstname][lastname]', password='student@123'");
        System.out.println("  Example: username='emilychen', password='student@123'");
        System.out.println("========================================\n");
    }
    
    // Getters for generated data
    public Department getDepartment() {
        return department;
    }
    
    public UserDirectory getUserDirectory() {
        return userDirectory;
    }
    
    public FinanceManager getFinanceManager() {
        return financeManager;
    }
    
    public FacultyDirectory getFacultyDirectory() {
        return facultyDirectory;
    }
}