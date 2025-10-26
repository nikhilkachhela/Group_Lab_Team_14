/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.UI.Common;
import info5100.university.example.AccessControl.PasswordValidator;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 *
 * @author Sandeep Patil
 */
public class PasswordDialog extends JDialog {
    
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JLabel lblLength;
    private JLabel lblUppercase;
    private JLabel lblLowercase;
    private JLabel lblDigit;
    private JLabel lblSpecial;
    private JLabel lblMatch;
    private JButton btnOk;
    private JButton btnCancel;
    private String password = null;
    
    // Colors
    private final Color COLOR_MET = new Color(0, 153, 51);      // Green
    private final Color COLOR_NOT_MET = new Color(204, 0, 0);   // Red
    private final Color COLOR_NEUTRAL = new Color(102, 102, 102); // Gray
    
    public PasswordDialog(Frame parent, String title) {
        super(parent, title, true);
        initComponents();
        setLocationRelativeTo(parent);
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setSize(500, 450);
        
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel lblTitle = new JLabel("Password Requirements:");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lblTitle);
        
        mainPanel.add(Box.createVerticalStrut(10));
        
        // Requirements labels with icons
        lblLength = createRequirementLabel("✗ At least 8 characters");
        lblUppercase = createRequirementLabel("✗ At least one uppercase letter (A-Z)");
        lblLowercase = createRequirementLabel("✗ At least one lowercase letter (a-z)");
        lblDigit = createRequirementLabel("✗ At least one digit (0-9)");
        lblSpecial = createRequirementLabel("✗ At least one special character (!@#$%^&*)");
        
        mainPanel.add(lblLength);
        mainPanel.add(lblUppercase);
        mainPanel.add(lblLowercase);
        mainPanel.add(lblDigit);
        mainPanel.add(lblSpecial);
        
        mainPanel.add(Box.createVerticalStrut(20));
        
        // Password field
        JLabel lblPassword = new JLabel("Enter Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lblPassword);
        
        mainPanel.add(Box.createVerticalStrut(5));
        
        txtPassword = new JPasswordField(20);
        txtPassword.setMaximumSize(new Dimension(450, 35));
        txtPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(txtPassword);
        
        mainPanel.add(Box.createVerticalStrut(15));
        
        // Confirm password field
        JLabel lblConfirm = new JLabel("Confirm Password:");
        lblConfirm.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblConfirm.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lblConfirm);
        
        mainPanel.add(Box.createVerticalStrut(5));
        
        txtConfirmPassword = new JPasswordField(20);
        txtConfirmPassword.setMaximumSize(new Dimension(450, 35));
        txtConfirmPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(txtConfirmPassword);
        
        mainPanel.add(Box.createVerticalStrut(10));
        
        // Password match label
        lblMatch = new JLabel(" ");
        lblMatch.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblMatch.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lblMatch);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 15, 20));
        
        btnOk = new JButton("Create Password");
        btnOk.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnOk.setBackground(new Color(0, 153, 51));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFocusPainted(false);
        btnOk.setPreferredSize(new Dimension(150, 35));
        btnOk.addActionListener(e -> validateAndClose());
        
        btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(100, 35));
        btnCancel.addActionListener(e -> {
            password = null;
            dispose();
        });
        
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnOk);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Add real-time validation listeners
        txtPassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validatePasswordRealTime();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                validatePasswordRealTime();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                validatePasswordRealTime();
            }
        });
        
        txtConfirmPassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkPasswordMatch();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                checkPasswordMatch();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkPasswordMatch();
            }
        });
    }
    
    private JLabel createRequirementLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(COLOR_NOT_MET);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    
    private void validatePasswordRealTime() {
        String pwd = new String(txtPassword.getPassword());
        
        // Check length
        if (pwd.length() >= 8) {
            updateRequirement(lblLength, "✓ At least 8 characters", true);
        } else {
            updateRequirement(lblLength, "✗ At least 8 characters", false);
        }
        
        // Check uppercase
        if (pwd.matches(".*[A-Z].*")) {
            updateRequirement(lblUppercase, "✓ At least one uppercase letter (A-Z)", true);
        } else {
            updateRequirement(lblUppercase, "✗ At least one uppercase letter (A-Z)", false);
        }
        
        // Check lowercase
        if (pwd.matches(".*[a-z].*")) {
            updateRequirement(lblLowercase, "✓ At least one lowercase letter (a-z)", true);
        } else {
            updateRequirement(lblLowercase, "✗ At least one lowercase letter (a-z)", false);
        }
        
        // Check digit
        if (pwd.matches(".*[0-9].*")) {
            updateRequirement(lblDigit, "✓ At least one digit (0-9)", true);
        } else {
            updateRequirement(lblDigit, "✗ At least one digit (0-9)", false);
        }
        
        // Check special character
        if (pwd.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            updateRequirement(lblSpecial, "✓ At least one special character (!@#$%^&*)", true);
        } else {
            updateRequirement(lblSpecial, "✗ At least one special character (!@#$%^&*)", false);
        }
        
        // Check match with confirm password
        checkPasswordMatch();
    }
    
    private void checkPasswordMatch() {
        String pwd = new String(txtPassword.getPassword());
        String confirmPwd = new String(txtConfirmPassword.getPassword());
        
        if (confirmPwd.isEmpty()) {
            lblMatch.setText(" ");
            lblMatch.setForeground(COLOR_NEUTRAL);
        } else if (pwd.equals(confirmPwd)) {
            lblMatch.setText("✓ Passwords match");
            lblMatch.setForeground(COLOR_MET);
        } else {
            lblMatch.setText("✗ Passwords do not match");
            lblMatch.setForeground(COLOR_NOT_MET);
        }
    }
    
    private void updateRequirement(JLabel label, String text, boolean met) {
        label.setText(text);
        label.setForeground(met ? COLOR_MET : COLOR_NOT_MET);
    }
    
    private void validateAndClose() {
        String pwd = new String(txtPassword.getPassword());
        String confirmPwd = new String(txtConfirmPassword.getPassword());
        
        // Check if passwords match
        if (!pwd.equals(confirmPwd)) {
            JOptionPane.showMessageDialog(this, 
                "Passwords do not match!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            txtConfirmPassword.setText("");
            txtConfirmPassword.requestFocus();
            return;
        }
        
        // Validate password
        PasswordValidator.ValidationResult result = PasswordValidator.validatePassword(pwd);
        
        if (!result.isValid()) {
            JOptionPane.showMessageDialog(this, 
                result.getMessage(), 
                "Invalid Password", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Password is valid
        password = pwd;
        dispose();
    }
    
    /**
     * Show dialog and get password
     * @return Password if valid, null if cancelled
     */
    public String getPassword() {
        setVisible(true);
        return password;
    }
}
