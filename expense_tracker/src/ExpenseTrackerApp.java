import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;
import controller.AmountFilter;
import controller.CategoryFilter;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    view.getCatBtn().addActionListener(e -> {
      String categoryFilt = view.getCatFilter();
      if (!InputValidation.isValidCategory(categoryFilt)) {
        JOptionPane.showMessageDialog(view, "Invalid category to filter with");
      }
      boolean filtered = controller.applyFilter(new CategoryFilter(categoryFilt));
      if (!filtered) {
        JOptionPane.showMessageDialog(view, "No data to filter");
        view.toFront();
      }
    });

    view.getAmntBtn().addActionListener(e -> {
      double min = view.getAmntMin();
      double max = view.getAmntMax();
      if (!InputValidation.isValidAmount(min) || !InputValidation.isValidAmount(max)) {
        JOptionPane.showMessageDialog(view, "Invalid amount to filter with");
      }
      boolean filtered = controller.applyFilter(new AmountFilter(min, max));
      if (!filtered) {
        JOptionPane.showMessageDialog(view, "No data to filter");
        view.toFront();
      }
    });

  }

}