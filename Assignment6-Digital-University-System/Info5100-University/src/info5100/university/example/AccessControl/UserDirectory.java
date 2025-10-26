/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.AccessControl;

import info5100.university.example.Persona.Person;
import java.util.ArrayList;

/**
 *
 * @author Sandeep Patil
 */
public class UserDirectory {
    
    private ArrayList<User> userList;
    
    public UserDirectory() {
        userList = new ArrayList<>();
    }
    
    // Create a new user
    public User newUser(Person person, String username, String password, Role role) {
        // Check if username already exists
        if (findUserByUsername(username) != null) {
            System.out.println("Username already exists!");
            return null;
        }
        
        User user = new User(person, username, password, role);
        userList.add(user);
        return user;
    }
    
    // Find user by username
    public User findUserByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }
    
    // Find user by person ID
    public User findUserByPersonId(String personId) {
        for (User user : userList) {
            if (user.getPerson().getPersonId().equals(personId)) {
                return user;
            }
        }
        return null;
    }
    
    // Get all users
    public ArrayList<User> getAllUsers() {
        return userList;
    }
    
    // Get users by role
    public ArrayList<User> getUsersByRole(Role role) {
        ArrayList<User> filteredUsers = new ArrayList<>();
        for (User user : userList) {
            if (user.getRole() == role) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }
    
    // Delete user
    public boolean deleteUser(String username) {
        User user = findUserByUsername(username);
        if (user != null) {
            userList.remove(user);
            return true;
        }
        return false;
    }
    
    // Get total active users
    public int getTotalActiveUsers() {
        int count = 0;
        for (User user : userList) {
            if (user.isActive()) {
                count++;
            }
        }
        return count;
    }
    
    // Get count by role
    public int getCountByRole(Role role) {
        return getUsersByRole(role).size();
    }
}