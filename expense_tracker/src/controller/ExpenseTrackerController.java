package controller;

import view.ExpenseTrackerView;

import java.awt.Color;
import java.util.List;



import model.ExpenseTrackerModel;
import model.Transaction;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  private List<Transaction> transactions;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }

  public boolean removeTransaction(Transaction t, int index) {
    model.removeTransaction(t);
    view.getTableModel().removeRow(index);
    refresh();
    return true;
  }
  
  // Other controller methods
  public List<Transaction> applyFilter(TransactionFilter filter) {
        List<Transaction> filteredTransactions = filter.filter(transactions);

        // IMplement Highlight the filtered transactions in green

        return filteredTransactions;
    }
}