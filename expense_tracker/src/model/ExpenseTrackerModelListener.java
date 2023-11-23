package model;


/**
 * The ExpenseTrackerModelListener class allows observers
 * of the Model to be notified whenever it has state changes.
 *
 * NOTE) This is applying the Observer design pattern.
 *       Specifically, this is the Observer interface.
 */
public interface ExpenseTrackerModelListener
{
    public void update(ExpenseTrackerModel model);
}
