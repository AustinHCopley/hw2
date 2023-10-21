package controller;

import view.ExpenseTrackerView;

import java.awt.Color;
import java.util.List;



import model.ExpenseTrackerModel;
import model.Transaction;
import controller.InputValidation;
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

  // For undo functionality:
  // public boolean removeTransaction(Transaction t, int index) {
  //   model.removeTransaction(t);
  //   view.getTableModel().removeRow(index);
  //   refresh();
  //   return true;
  // }
  
  public boolean applyFilter(TransactionFilter filter) {

    List<Transaction> filteredTransactions = filter.filter(model.getTransactions());
    // check if the transactions table has any values
    if (view.getTransactionsTable() == null || model.getTransactions() == null) {
      return false;
    }
    // clear previously selected rows
    view.getTransactionsTable().clearSelection();
    // initialize new variables with dummy values
    double amount = 0.0;
    String category = "none";

    // check every transaction in filtered and total against eachother if they match
    for (int i = 0; i < view.getTableModel().getRowCount() - 1; i++) {
      // set the variables to the current row's values
      amount = (double) view.getTableModel().getValueAt(i,1);
      category = (String) view.getTableModel().getValueAt(i,2);

      for (Transaction filtTrans : filteredTransactions) {
        if ( filtTrans.getAmount() == amount && filtTrans.getCategory().equals(category)) {
          // if they match, add them to the selection interval
          view.getTransactionsTable().addRowSelectionInterval(i, i);
        }
      }
    }
    // update the color of the selection
    view.getTransactionsTable().setSelectionBackground(new Color(173,255,168));
    return true;
  }
}