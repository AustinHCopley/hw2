package controller;

import view.ExpenseTrackerView;

import java.awt.Color;
import java.util.List;



import model.ExpenseTrackerModel;
import model.Transaction;
// import view;
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

  // Other controller methods
  public boolean removeTransaction(Transaction t, int index) {
    model.removeTransaction(t);
    view.getTableModel().removeRow(index);
    refresh();
    return true;
  }
  
  public List<Transaction> applyFilter(TransactionFilter filter) {
    List<Transaction> filteredTransactions = filter.filter(transactions);

    // IMplement Highlight the filtered transactions in green
    // for (Transaction transaction : transactions) {

    // }
    // view.getTransactionsTable().setSelectionBackground(new Color(173,255,168));
    return filteredTransactions;
  }

}