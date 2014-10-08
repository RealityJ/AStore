public class AppleQueue implements Queue {

  private int        capacity;
  private Customer[] line;
  private int        end = -1;

  public AppleQueue(int qCap) {
    line = new Customer[qCap];
  }

  @Override public boolean enqueue(Object customer) {
    for (int i = (line.length - 1); i >= 0; i--) {
      if (line[i] != null) {
        line[i + 1] = (Customer) customer;
        System.out.println("added to queue" + i);
        return true;
      }
    }
    return false;
  }

  @Override public Customer dequeue() {
    Customer tempCustomer = line[0];    // gets the customer at the front of the line
    System.out.println("line length= " + line.length);
    for (int i = 0; (i + 1) < line.length; i++) {    // everyone moves up one spot
      if (line[i] != null) {
        System.out.println(i);
        line[i] = line[i + 1];
      }
    }
    if (tempCustomer != null) {
      System.out.println(tempCustomer.getArrivalTime());
    }
    return tempCustomer;    // returns the front customer
  }

  @Override public int size() {
    return line.length;   // returns the number of people in line
  }

  @Override public void doubleQueue() {
    Customer[] newQueArray = new Customer[line.length * 2];    // makes a new line with twice the cap
    System.out.println("newQueArray Created with a size of " + newQueArray.length);
    for (int i = 0; i < line.length; i++) {   // moves all customers into the new line
      newQueArray[i] = line[i];
    }
    line = newQueArray;     // makes the new line the name of the old line so we can do this again later
    capacity = line.length;
  }

  @Override public boolean isFull() {
    if (line[line.length - 1] != null) {   // if the end spot reference equals the capacity
      return true;
    }
    return false;
  }

  @Override public boolean isEmpty() {
    if (line[0] == null) {   // if the first spot in line is empty
      return true;
    }
    return false;
  }

}
