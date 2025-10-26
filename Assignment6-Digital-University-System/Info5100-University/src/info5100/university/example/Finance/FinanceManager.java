/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Finance;
import info5100.university.example.Persona.StudentProfile;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sandeep Patil
 */
public class FinanceManager {
    
    private Map<String, TuitionAccount> studentAccounts; // Key: student person ID
    
    public FinanceManager() {
        this.studentAccounts = new HashMap<>();
    }
    
    /**
     * Get or create tuition account for a student
     * @param student
     * @return TuitionAccount
     */
    public TuitionAccount getStudentAccount(StudentProfile student) {
        String studentId = student.toString(); // Using toString as unique identifier
        
        if (!studentAccounts.containsKey(studentId)) {
            TuitionAccount account = new TuitionAccount(student);
            studentAccounts.put(studentId, account);
        }
        
        return studentAccounts.get(studentId);
    }
    
    /**
     * Charge tuition when student registers for a course
     * @param student
     * @param amount
     * @param courseNumber
     * @param semester
     */
    public void chargeTuitionForCourse(StudentProfile student, double amount, 
                                       String courseNumber, String semester) {
        TuitionAccount account = getStudentAccount(student);
        account.chargeTuition(amount, courseNumber, semester);
    }
    
    /**
     * Process payment from student
     * @param student
     * @param amount
     * @return true if successful
     */
    public boolean processPayment(StudentProfile student, double amount) {
        TuitionAccount account = getStudentAccount(student);
        return account.makePayment(amount);
    }
    
    /**
     * Issue refund when student drops a course
     * @param student
     * @param amount
     * @param courseNumber
     * @param semester
     */
    public void issueRefund(StudentProfile student, double amount, 
                           String courseNumber, String semester) {
        TuitionAccount account = getStudentAccount(student);
        account.issueRefund(amount, courseNumber, semester);
    }
    
    /**
     * Check if student has paid tuition
     * @param student
     * @return true if paid
     */
    public boolean hasStudentPaidTuition(StudentProfile student) {
        TuitionAccount account = getStudentAccount(student);
        return account.isTuitionPaid();
    }
    
    /**
     * Get student's outstanding balance
     * @param student
     * @return balance
     */
    public double getStudentBalance(StudentProfile student) {
        TuitionAccount account = getStudentAccount(student);
        return account.getBalance();
    }
    
    /**
     * Get all student accounts
     * @return map of accounts
     */
    public Map<String, TuitionAccount> getAllAccounts() {
        return studentAccounts;
    }
    
    /**
     * Generate financial report for a semester
     * @param semester
     * @param departmentName
     * @return FinancialReport
     */
    public FinancialReport generateSemesterReport(String semester, String departmentName) {
        FinancialReport report = new FinancialReport(semester);
        
        for (TuitionAccount account : studentAccounts.values()) {
            // Get semester-specific transactions
            double semesterCharges = 0.0;
            double semesterPayments = 0.0;
            
            for (TuitionTransaction txn : account.getTransactionsBySemester(semester)) {
                if (txn.getType() == TuitionTransaction.TransactionType.CHARGE) {
                    semesterCharges += txn.getAmount();
                } else if (txn.getType() == TuitionTransaction.TransactionType.PAYMENT) {
                    semesterPayments += txn.getAmount();
                }
            }
            
            if (semesterCharges > 0) {
                report.incrementTotalStudents();
                report.addDepartmentRevenue(departmentName, semesterPayments);
                
                double balance = semesterCharges - semesterPayments;
                if (balance > 0) {
                    report.addOutstandingBalance(balance);
                } else {
                    report.incrementPaidStudents();
                }
            }
        }
        
        return report;
    }
    
    /**
     * Get total revenue collected
     * @return total revenue
     */
    public double getTotalRevenue() {
        double total = 0.0;
        for (TuitionAccount account : studentAccounts.values()) {
            total += account.getTotalPaid();
        }
        return total;
    }
    
    /**
     * Get total outstanding balance
     * @return total outstanding
     */
    public double getTotalOutstanding() {
        double total = 0.0;
        for (TuitionAccount account : studentAccounts.values()) {
            if (account.getBalance() > 0) {
                total += account.getBalance();
            }
        }
        return total;
    }
}
