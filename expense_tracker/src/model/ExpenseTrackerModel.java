package model;

import java.util.*;

public class ExpenseTrackerModel extends Observable {

  //encapsulation - data integrity
  private List<Transaction> transactions;
  private List<Integer> matchedFilterIndices;
  private Set<ExpenseTrackerModelListener> observers = new HashSet<>();

  // This is applying the Observer design pattern.                          
  // Specifically, this is the Observable class. 


    /**
     * The Model handles the transactions in the application
     * It has functions to add transaction, remove transactions, and filter the transaction fulfilling the conditions.
     */
  public ExpenseTrackerModel() {
    transactions = new ArrayList<Transaction>();
    matchedFilterIndices = new ArrayList<Integer>();
  }



  public void addTransaction(Transaction t) {
    // Perform input validation to guarantee that all transactions added are non-null.
    if (t == null) {
      throw new IllegalArgumentException("The new transaction must be non-null.");
    }
    transactions.add(t);
    // The previous filter is no longer valid.
    matchedFilterIndices.clear();
    stateChanged();
  }

  public void removeTransaction(Transaction t) {
    transactions.remove(t);
    // The previous filter is no longer valid.
    matchedFilterIndices.clear();
    stateChanged();
  }

  public List<Transaction> getTransactions() {
    //encapsulation - data integrity
    return Collections.unmodifiableList(new ArrayList<>(transactions));
  }

  public void setMatchedFilterIndices(List<Integer> newMatchedFilterIndices) {
      // Perform input validation
      if (newMatchedFilterIndices == null) {
	  throw new IllegalArgumentException("The matched filter indices list must be non-null.");
      }
      for (Integer matchedFilterIndex : newMatchedFilterIndices) {
	  if ((matchedFilterIndex < 0) || (matchedFilterIndex > this.transactions.size() - 1)) {
	      throw new IllegalArgumentException("Each matched filter index must be between 0 (inclusive) and the number of transactions (exclusive).");
	  }
      }
      // For encapsulation, copy in the input list
      this.matchedFilterIndices.clear();
      this.matchedFilterIndices.addAll(newMatchedFilterIndices);
      stateChanged();
  }

  public List<Integer> getMatchedFilterIndices() {
      // For encapsulation, copy out the output list
      List<Integer> copyOfMatchedFilterIndices = new ArrayList<Integer>();
      copyOfMatchedFilterIndices.addAll(this.matchedFilterIndices);
      return copyOfMatchedFilterIndices;
  }

  /**
   * Registers the given ExpenseTrackerModelListener for
   * state change events.
   *
   * @param listener The ExpenseTrackerModelListener to be registered
   * @return If the listener is non-null and not already registered,
   *         returns true. If not, returns false.
   */   
  public boolean register(ExpenseTrackerModelListener listener) {
      // For the Observable class, this is one of the methods.
      //
      // TODO: finished
      if (containsListener(listener) || listener == null) {
        return false;
      } else {
          this.observers.add(listener);
          return true;
      }
  }

  public int numberOfListeners() {
      // For testing, this is one of the methods.
      //
      //TODO: finished
      return this.observers.size();
  }

  public boolean containsListener(ExpenseTrackerModelListener listener) {
      // For testing, this is one of the methods.
      //
      //TODO: finished
      if (this.observers.contains(listener)) {
          return true;
      } else {
          return false;
      }
  }

  protected void stateChanged() {
      // For the Observable class, this is one of the methods.
      //
      //TODO: finished
      for (ExpenseTrackerModelListener observer : observers) {
          observer.update(this);
      }

  }
}
