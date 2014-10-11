/**
 * Queue.java
 * This interface represents a queue and its basic operations. Classes that
 * implement this interface must implement all its methods.
 * @author vangelis
 */
public interface Queue<Item> {
    
    /** Append item to the queue.
     * @param item. An item to add at the end of the queue.
     * @return true if the operation was successful and false if the operation
     * was unsuccessful, e.g. if the queue is full.
     */
    public boolean enqueue(Item item);

    /** Remove and return the front element of the queue.     
     * @return the front element of the queue.
     */
    public Item dequeue();

    /** Return the number of elements in queue. */
    public int size();

    
    /*
     * double the capacity of the queue
     */
    public void doubleQueue();
    
    
    /**
     * Checks if the queue is full.
     * @return true if the queue is full, i.e. if the number of elements in the
     * queue has reached its capacity.
     */
    public boolean isFull();

    /**
     * Checks if the queue is empty.
     * @return true if the queue is empty, i.e. if the number of elements in the
     * queue is 0.
     */
    public boolean isEmpty();
    
    /**
     * @return a String that contains the information in the queue.
     */
    public String toString();
}
