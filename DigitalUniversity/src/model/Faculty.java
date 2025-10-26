/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author samee
 */
public class Faculty {
    private String facultyId;
    private String name;
    private String email;
    private String department;
    private String officeHours;

    private String username; // for login
    private String password; // for login

    public Faculty(String facultyId,
                   String name,
                   String email,
                   String department,
                   String officeHours,
                   String username,
                   String password) {
        this.facultyId = facultyId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.officeHours = officeHours;
        this.username = username;
        this.password = password;
    }

    // Getters + setters
    public String getFacultyId() { return facultyId; }
    public void setFacultyId(String facultyId) { this.facultyId = facultyId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getOfficeHours() { return officeHours; }
    public void setOfficeHours(String officeHours) { this.officeHours = officeHours; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
