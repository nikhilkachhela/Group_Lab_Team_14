/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.AccessControl;
import java.util.regex.Pattern;

/**
 *
 * @author Sandeep Patil
 */
public class PasswordValidator {
    
    // Password requirements
    private static final int MIN_LENGTH = 8;
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]");
    
    /**
     * Validate password against all requirements
     * @param password
     * @return ValidationResult
     */
    public static ValidationResult validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return new ValidationResult(false, "Password cannot be empty");
        }
        
        if (password.length() < MIN_LENGTH) {
            return new ValidationResult(false, 
                "Password must be at least " + MIN_LENGTH + " characters long");
        }
        
        if (!UPPERCASE_PATTERN.matcher(password).find()) {
            return new ValidationResult(false, 
                "Password must contain at least one uppercase letter (A-Z)");
        }
        
        if (!LOWERCASE_PATTERN.matcher(password).find()) {
            return new ValidationResult(false, 
                "Password must contain at least one lowercase letter (a-z)");
        }
        
        if (!DIGIT_PATTERN.matcher(password).find()) {
            return new ValidationResult(false, 
                "Password must contain at least one digit (0-9)");
        }
        
        if (!SPECIAL_CHAR_PATTERN.matcher(password).find()) {
            return new ValidationResult(false, 
                "Password must contain at least one special character (!@#$%^&* etc.)");
        }
        
        return new ValidationResult(true, "Password is valid");
    }
    
    /**
     * Get password requirements as formatted string
     * @return Requirements text
     */
    public static String getPasswordRequirements() {
        return "<html><b>Password Requirements:</b><br>" +
               "• At least " + MIN_LENGTH + " characters<br>" +
               "• At least one uppercase letter (A-Z)<br>" +
               "• At least one lowercase letter (a-z)<br>" +
               "• At least one digit (0-9)<br>" +
               "• At least one special character (!@#$%^&* etc.)</html>";
    }
    
    /**
     * Inner class to hold validation result
     */
    public static class ValidationResult {
        private final boolean valid;
        private final String message;
        
        public ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }
        
        public boolean isValid() {
            return valid;
        }
        
        public String getMessage() {
            return message;
        }
    }
}