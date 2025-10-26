/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.AccessControl;

/**
 *
 * @author Sandeep Patil
 */
public class AuthenticationService {
    
    private UserDirectory userDirectory;
    private User currentUser; // Currently logged-in user
    
    public AuthenticationService(UserDirectory userDirectory) {
        this.userDirectory = userDirectory;
        this.currentUser = null;
    }
    
    /**
     * Authenticate user with username and password
     * @param username
     * @param password
     * @return User object if successful, null otherwise
     */
    public User login(String username, String password) {
        // Find user by username
        User user = userDirectory.findUserByUsername(username);
        
        // Check if user exists
        if (user == null) {
            System.out.println("User not found!");
            return null;
        }
        
        // Check if user is active
        if (!user.isActive()) {
            System.out.println("User account is inactive!");
            return null;
        }
        
        // Validate password
        if (!user.validatePassword(password)) {
            System.out.println("Invalid password!");
            return null;
        }
        
        // Successful login
        currentUser = user;
        System.out.println("Login successful! Welcome " + user.getRole().getDisplayName());
        return user;
    }
    
    /**
     * Logout current user
     */
    public void logout() {
        if (currentUser != null) {
            System.out.println("User " + currentUser.getUsername() + " logged out.");
            currentUser = null;
        }
    }
    
    /**
     * Get currently logged-in user
     * @return Current user or null
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Check if user is logged in
     * @return true if logged in
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    
    /**
     * Check if current user has specific role
     * @param role
     * @return true if user has the role
     */
    public boolean hasRole(Role role) {
        if (currentUser == null) {
            return false;
        }
        return currentUser.getRole() == role;
    }
}
