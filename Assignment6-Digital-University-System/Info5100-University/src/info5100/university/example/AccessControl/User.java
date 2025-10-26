/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.AccessControl;
import info5100.university.example.Persona.Person;


/**
 *
 * @author Sandeep Patil
 */
public class User {
    private Person person;
    private String username;
    private String password;
    private Role role;
    private boolean isActive;
    
    // For linking to specific profiles
    private Object profileReference; // Can be StudentProfile, FacultyProfile, etc.
    
    public User(Person person, String username, String password, Role role) {
        this.person = person;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActive = true;
    }
    
    // Getters and Setters
    public Person getPerson() {
        return person;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public Object getProfileReference() {
        return profileReference;
    }
    
    public void setProfileReference(Object profileReference) {
        this.profileReference = profileReference;
    }
    
    // Validation method
    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
    
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", role=" + role.getDisplayName() +
                ", active=" + isActive +
                '}';
    }
}
