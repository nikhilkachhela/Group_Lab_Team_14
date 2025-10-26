/*
 * Financial Management Panel - Handle tuition payments
 * Author: Student Module Implementation
 * NUID: [Your NUID]
 */
package info5100.university.example.UI.Student;

import info5100.university.example.Department.Department;
import info5100.university.example.Finance.*;
import info5100.university.example.Persona.StudentProfile;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FinancialManagementPanel extends javax.swing.JPanel {
    
    private StudentProfile currentStudent;
    private FinanceManager financeManager;
    private Department department;
    private JPanel cardPanel;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    
    public FinancialManagementPanel(StudentProfile student, FinanceManager finMgr, 
                                   Department dept, JPanel cardPanel) {
        this.currentStudent = student;
        this.financeManager = finMgr;
        this.department = dept;
        this.cardPanel = cardPanel;
        initComponents();
        loadFinancialData();
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        lblTitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        pnlAccountSummary = new javax.swing.JPanel();
        lblAccountBalance = new javax.swing.JLabel();
        lblTotalCharges = new javax.swing.JLabel();
        lblTotalPayments = new javax.swing.JLabel();
        lblCurrentDue = new javax.swing.JLabel();
        pnlPayment = new javax.swing.JPanel();
        lblPaymentAmount = new javax.swing.JLabel();
        txtPaymentAmount = new javax.swing.JTextField();
        btnPayTuition = new javax.swing.JButton();
        lblPaymentNote = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPaymentHistory = new javax.swing.JTable();
        lblHistoryTitle = new javax.swing.JLabel();
        btnRefreshHistory = new javax.swing.JButton();
        btnGenerateStatement = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20));
        lblTitle.setText("Financial Management");

        btnBack.setText("<< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        // Account Summary Panel
        pnlAccountSummary.setBorder(javax.swing.BorderFactory.createTitledBorder(
            null, "Account Summary", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new java.awt.Font("Segoe UI", 1, 14)));
        pnlAccountSummary.setBackground(new java.awt.Color(245, 245, 245));

        lblAccountBalance.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblAccountBalance.setText("Current Balance: $0.00");

        lblTotalCharges.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblTotalCharges.setText("Total Charges: $0.00");

        lblTotalPayments.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblTotalPayments.setText("Total Payments: $0.00");

        lblCurrentDue.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblCurrentDue.setText("Amount Due: $0.00");
        lblCurrentDue.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout pnlAccountLayout = new javax.swing.GroupLayout(pnlAccountSummary);
        pnlAccountSummary.setLayout(pnlAccountLayout);
        pnlAccountLayout.setHorizontalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAccountBalance)
                    .addGroup(pnlAccountLayout.createSequentialGroup()
                        .addComponent(lblTotalCharges, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(lblTotalPayments, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCurrentDue))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlAccountLayout.setVerticalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblAccountBalance)
                .addGap(15, 15, 15)
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCharges)
                    .addComponent(lblTotalPayments))
                .addGap(15, 15, 15)
                .addComponent(lblCurrentDue)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        // Payment Panel
        pnlPayment.setBorder(javax.swing.BorderFactory.createTitledBorder(
            null, "Make Payment", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new java.awt.Font("Segoe UI", 1, 14)));
        pnlPayment.setBackground(new java.awt.Color(245, 245, 245));

        lblPaymentAmount.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblPaymentAmount.setText("Payment Amount:");

        txtPaymentAmount.setFont(new java.awt.Font("Segoe UI", 0, 14));

        btnPayTuition.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnPayTuition.setText("Pay Tuition");
        btnPayTuition.setBackground(new java.awt.Color(0, 153, 51));
        btnPayTuition.setForeground(new java.awt.Color(255, 255, 255));
        btnPayTuition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayTuitionActionPerformed(evt);
            }
        });

        lblPaymentNote.setFont(new java.awt.Font("Segoe UI", 2, 12));
        lblPaymentNote.setText("Note: Your transcript will be available after all dues are cleared.");

        javax.swing.GroupLayout pnlPaymentLayout = new javax.swing.GroupLayout(pnlPayment);
        pnlPayment.setLayout(pnlPaymentLayout);
        pnlPaymentLayout.setHorizontalGroup(
            pnlPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPaymentLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPaymentNote)
                    .addGroup(pnlPaymentLayout.createSequentialGroup()
                        .addComponent(lblPaymentAmount)
                        .addGap(20, 20, 20)
                        .addComponent(txtPaymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnPayTuition, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlPaymentLayout.setVerticalGroup(
            pnlPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPaymentLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPaymentAmount)
                    .addComponent(txtPaymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPayTuition, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(lblPaymentNote)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        // Payment History
        lblHistoryTitle.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblHistoryTitle.setText("Payment History");

        tblPaymentHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Transaction ID", "Date", "Type", "Amount", "Balance", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblPaymentHistory.setRowHeight(25);
        jScrollPane1.setViewportView(tblPaymentHistory);

        btnRefreshHistory.setText("Refresh History");
        btnRefreshHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshHistoryActionPerformed(evt);
            }
        });

        btnGenerateStatement.setText("Generate Statement");
        btnGenerateStatement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateStatementActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addComponent(pnlAccountSummary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHistoryTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenerateStatement)
                        .addGap(10, 10, 10)
                        .addComponent(btnRefreshHistory))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(btnBack))
                .addGap(20, 20, 20)
                .addComponent(pnlAccountSummary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pnlPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHistoryTitle)
                    .addComponent(btnRefreshHistory)
                    .addComponent(btnGenerateStatement))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        cardPanel.remove(this);
        ((java.awt.CardLayout) cardPanel.getLayout()).previous(cardPanel);
    }

    private void btnPayTuitionActionPerformed(java.awt.event.ActionEvent evt) {
        String amountStr = txtPaymentAmount.getText().trim();
        
        if (amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a payment amount.",
                "Input Required",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            double amount = Double.parseDouble(amountStr);
            
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this,
                    "Please enter a valid positive amount.",
                    "Invalid Amount",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double currentBalance = financeManager.getStudentBalance(currentStudent);
            
            if (currentBalance <= 0) {
                JOptionPane.showMessageDialog(this,
                    "No balance to pay. Your account is clear!",
                    "No Outstanding Balance",
                    JOptionPane.INFORMATION_MESSAGE);
                txtPaymentAmount.setText("");
                return;
            }
            
            if (amount > currentBalance) {
                int confirm = JOptionPane.showConfirmDialog(this,
                    "You are paying more than the outstanding balance.\n" +
                    "Outstanding Balance: " + currencyFormat.format(currentBalance) + "\n" +
                    "Payment Amount: " + currencyFormat.format(amount) + "\n" +
                    "Excess amount will be credited to your account.\n\n" +
                    "Do you want to proceed?",
                    "Confirm Overpayment",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            
            // Process payment
            boolean success = financeManager.makePayment(currentStudent, amount);
            
            if (success) {
                JOptionPane.showMessageDialog(this,
                    "Payment Successful!\n\n" +
                    "Amount Paid: " + currencyFormat.format(amount) + "\n" +
                    "New Balance: " + currencyFormat.format(financeManager.getStudentBalance(currentStudent)) + "\n\n" +
                    "Thank you for your payment.",
                    "Payment Confirmation",
                    JOptionPane.INFORMATION_MESSAGE);
                
                txtPaymentAmount.setText("");
                loadFinancialData();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Payment processing failed. Please try again.",
                    "Payment Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid numeric amount.",
                "Invalid Format",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnRefreshHistoryActionPerformed(java.awt.event.ActionEvent evt) {
        loadFinancialData();
        JOptionPane.showMessageDialog(this,
            "Payment history refreshed!",
            "Refresh Complete",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnGenerateStatementActionPerformed(java.awt.event.ActionEvent evt) {
        String statement = generateStatement();
        
        JTextArea textArea = new JTextArea(statement);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane,
            "Account Statement",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadFinancialData() {
        // Get financial data from FinanceManager
        double balance = financeManager.getStudentBalance(currentStudent);
        double charges = financeManager.getTotalCharges(currentStudent);
        double payments = financeManager.getTotalPayments(currentStudent);
        
        // Update account summary
        lblAccountBalance.setText("Current Balance: " + currencyFormat.format(balance));
        lblTotalCharges.setText("Total Charges: " + currencyFormat.format(charges));
        lblTotalPayments.setText("Total Payments: " + currencyFormat.format(payments));
        
        if (balance > 0) {
            lblCurrentDue.setText("Amount Due: " + currencyFormat.format(balance));
            lblCurrentDue.setForeground(new Color(204, 0, 0));
        } else {
            lblCurrentDue.setText("No Amount Due");
            lblCurrentDue.setForeground(new Color(0, 153, 51));
        }
        
        // Load payment history
        loadPaymentHistory();
    }

    private void loadPaymentHistory() {
        DefaultTableModel model = (DefaultTableModel) tblPaymentHistory.getModel();
        model.setRowCount(0);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        
        // Sample transaction history (would come from FinanceManager)
        model.addRow(new Object[]{
            "TXN001",
            dateFormat.format(new Date()),
            "Tuition Charge",
            currencyFormat.format(5000),
            currencyFormat.format(5000),
            "Posted"
        });
        
        model.addRow(new Object[]{
            "TXN002",
            dateFormat.format(new Date()),
            "Payment",
            currencyFormat.format(-2000),
            currencyFormat.format(3000),
            "Completed"
        });
    }

    private String generateStatement() {
        StringBuilder statement = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        
        statement.append("STUDENT ACCOUNT STATEMENT\n");
        statement.append("==========================\n\n");
        statement.append("Student ID: ").append(currentStudent.getPerson().getPersonId()).append("\n");
        statement.append("Statement Date: ").append(dateFormat.format(new Date())).append("\n\n");
        
        statement.append("ACCOUNT SUMMARY\n");
        statement.append("---------------\n");
        statement.append("Total Charges: ").append(currencyFormat.format(financeManager.getTotalCharges(currentStudent))).append("\n");
        statement.append("Total Payments: ").append(currencyFormat.format(financeManager.getTotalPayments(currentStudent))).append("\n");
        statement.append("Current Balance: ").append(currencyFormat.format(financeManager.getStudentBalance(currentStudent))).append("\n\n");
        
        statement.append("TRANSACTION HISTORY\n");
        statement.append("-------------------\n");
        // Add transaction details here
        
        return statement.toString();
    }

    // Variables declaration
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnGenerateStatement;
    private javax.swing.JButton btnPayTuition;
    private javax.swing.JButton btnRefreshHistory;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAccountBalance;
    private javax.swing.JLabel lblCurrentDue;
    private javax.swing.JLabel lblHistoryTitle;
    private javax.swing.JLabel lblPaymentAmount;
    private javax.swing.JLabel lblPaymentNote;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalCharges;
    private javax.swing.JLabel lblTotalPayments;
    private javax.swing.JPanel pnlAccountSummary;
    private javax.swing.JPanel pnlPayment;
    private javax.swing.JTable tblPaymentHistory;
    private javax.swing.JTextField txtPaymentAmount;
}