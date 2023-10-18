package model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerModel {

  private final List<Transaction> transactions;

  public ExpenseTrackerModel() {
    transactions = new ArrayList<>(); 
  }

  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }

  public List<Transaction> getTransactions() {
    // create a deep copy to return
    List<Transaction> copyTransactions = new ArrayList<Transaction>();
    for (Transaction transaction : transactions) {
      Transaction copyTransaction = new Transaction(transaction.getAmount(), transaction.getCategory());
      copyTransactions.add(copyTransaction);
    }
    return copyTransactions;
  }

}