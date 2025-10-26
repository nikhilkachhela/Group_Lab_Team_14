/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Finance;
import java.util.Date;

/**
 *
 * @author Sandeep Patil
 */
public class TuitionTransaction {
    
    private static int transactionCounter = 1;
    
    private String transactionId;
    private TransactionType type;
    private double amount;
    private Date transactionDate;
    private String description;
    private String semester;
    
    public enum TransactionType {
        CHARGE,     // Tuition charged
        PAYMENT,    // Payment made
        REFUND      // Refund issued
    }
    
    public TuitionTransaction(TransactionType type, double amount, String description, String semester) {
        this.transactionId = "TXN" + String.format("%06d", transactionCounter++);
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.semester = semester;
        this.transactionDate = new Date();
    }
    
    // Getters
    public String getTransactionId() {
        return transactionId;
    }
    
    public TransactionType getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public Date getTransactionDate() {
        return transactionDate;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getSemester() {
        return semester;
    }
    
    @Override
    public String toString() {
        return transactionId + " - " + type + ": $" + amount + " (" + description + ")";
    }
}