/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.AccessControl;

/**
 *
 * @author Sandeep Patil
 */
public enum Role {
    ADMIN("Administrator"),
    FACULTY("Faculty Member"),
    STUDENT("Student"),
    REGISTRAR("Registrar");
    
    private final String displayName;
    
    Role(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}