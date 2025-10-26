/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Finance;
import info5100.university.example.Persona.StudentProfile;
import java.util.ArrayList;

/**
 *
 * @author Sandeep Patil
 */
public class TuitionAccount {
    
    private StudentProfile student;
    private ArrayList<TuitionTransaction> transactions;
    private double balance; // Positive = owed, Negative = credit
    
    public TuitionAccount(StudentProfile student) {
        this.student = student;
        this.transactions = new ArrayList<>();
        this.balance = 0.0;
    }
    
    /**
     * Charge tuition for a course
     * @param amount
     * @param courseNumber
     * @param semester
     */
    public void chargeTuition(double amount, String courseNumber, String semester) {
        TuitionTransaction transaction = new TuitionTransaction(
            TuitionTransaction.TransactionType.CHARGE,
            amount,
            "Tuition for " + courseNumber,
            semester
        );
        transactions.add(transaction);
        balance += amount;
    }
    
    /**
     * Make a payment
     * @param amount
     * @return true if successful
     */
    public boolean makePayment(double amount) {
        if (amount <= 0) {
            System.out.println("Payment amount must be positive");
            return false;
        }
        
        if (balance <= 0) {
            System.out.println("No balance to pay");
            return false;
        }
        
        TuitionTransaction transaction = new TuitionTransaction(
            TuitionTransaction.TransactionType.PAYMENT,
            amount,
            "Payment received",
            "N/A"
        );
        transactions.add(transaction);
        balance -= amount;
        return true;
    }
    
    /**
     * Issue a refund (when course is dropped)
     * @param amount
     * @param courseNumber
     * @param semester
     */
    public void issueRefund(double amount, String courseNumber, String semester) {
        TuitionTransaction transaction = new TuitionTransaction(
            TuitionTransaction.TransactionType.REFUND,
            amount,
            "Refund for dropped course " + courseNumber,
            semester
        );
        transactions.add(transaction);
        balance -= amount;
    }
    
    /**
     * Check if tuition is paid (balance <= 0)
     * @return true if paid
     */
    public boolean isTuitionPaid() {
        return balance <= 0;
    }
    
    /**
     * Get outstanding balance
     * @return balance amount
     */
    public double getBalance() {
        return balance;
    }
    
    /**
     * Get all transactions
     * @return list of transactions
     */
    public ArrayList<TuitionTransaction> getTransactions() {
        return transactions;
    }
    
    /**
     * Get transactions for a specific semester
     * @param semester
     * @return filtered transactions
     */
    public ArrayList<TuitionTransaction> getTransactionsBySemester(String semester) {
        ArrayList<TuitionTransaction> filtered = new ArrayList<>();
        for (TuitionTransaction transaction : transactions) {
            if (transaction.getSemester().equals(semester)) {
                filtered.add(transaction);
            }
        }
        return filtered;
    }
    
    /**
     * Get total charged amount
     * @return total charges
     */
    public double getTotalCharged() {
        double total = 0.0;
        for (TuitionTransaction transaction : transactions) {
            if (transaction.getType() == TuitionTransaction.TransactionType.CHARGE) {
                total += transaction.getAmount();
            }
        }
        return total;
    }
    
    /**
     * Get total paid amount
     * @return total payments
     */
    public double getTotalPaid() {
        double total = 0.0;
        for (TuitionTransaction transaction : transactions) {
            if (transaction.getType() == TuitionTransaction.TransactionType.PAYMENT) {
                total += transaction.getAmount();
            }
        }
        return total;
    }
    
    /**
     * Get total refunded amount
     * @return total refunds
     */
    public double getTotalRefunded() {
        double total = 0.0;
        for (TuitionTransaction transaction : transactions) {
            if (transaction.getType() == TuitionTransaction.TransactionType.REFUND) {
                total += transaction.getAmount();
            }
        }
        return total;
    }
    
    public StudentProfile getStudent() {
        return student;
    }
}