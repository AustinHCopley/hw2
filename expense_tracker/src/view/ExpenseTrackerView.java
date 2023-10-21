package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;

import controller.AmountFilter;
import controller.CategoryFilter;
import controller.ExpenseTrackerController;
import controller.InputValidation;
import controller.TransactionFilter;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private ExpenseTrackerController controller;
  private JButton amntBtn;
  private JButton catBtn;
  private JTextField catTxt;
  private JTextField amntMin;
  private JTextField amntMax;
  

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 600); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    // buttons
    addTransactionBtn = new JButton("Add Transaction");
    catBtn = new JButton("Filter");
    amntBtn = new JButton("Filter");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);
    
    // filter components
    JLabel filterLabel = new JLabel("Filters:");
    JLabel filterCatLabel = new JLabel("category");
    JLabel filterAmntLabel = new JLabel("amount");
    catTxt = new JTextField(10);
    JLabel minLabel = new JLabel("min");
    amntMin = new JTextField(10);
    JLabel maxLabel = new JLabel("max");
    amntMax = new JTextField(10);
    
    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
    
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
    
    
    JPanel catPanel = new JPanel();
    catPanel.add(filterCatLabel);
    catPanel.add(catTxt);
    catPanel.add(catBtn);
    
    JPanel amntPanel = new JPanel();
    amntPanel.add(filterAmntLabel);

    JPanel amntSelections = new JPanel();
    amntSelections.add(minLabel);
    amntSelections.add(amntMin);
    amntSelections.add(maxLabel);
    amntSelections.add(amntMax);

    amntPanel.add(amntSelections);
    amntPanel.add(amntBtn);
    
    JPanel filterPanel = new JPanel();
    filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
    filterPanel.add(filterLabel);
    filterPanel.add(catPanel);
    filterPanel.add(amntPanel);

    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
    add(filterPanel, BorderLayout.EAST);
  
    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  
  
  
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public JButton getCatBtn() {
    return catBtn;
  }
  public JButton getAmntBtn() {
    return amntBtn;
  }
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }
  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  public String getCatFilter() {
    return catTxt.getText();
  }
  
  public double getAmntMin() {
    if(amntMin.getText().isEmpty()) {
      return 0;
    } else {
      double min = Double.parseDouble(amntMin.getText());
      return min;
    }
  }

  public double getAmntMax() {
    if(amntMax.getText().isEmpty()) {
      return 0;
    }else {
      double max = Double.parseDouble(amntMax.getText());
      return max;
    }
  }

}
