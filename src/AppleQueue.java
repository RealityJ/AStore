public class AppleQueue implements Queue {

  private int        capacity;
  private Customer[] line;
  private int        end = -1;

  public AppleQueue(int qCap) {
    line = new Customer[qCap];
  }

  @Override public boolean enqueue(Object customer) {
    if ((line[line.length - 1]) == null) {   // if the last slot of the line is an open slot will be possible
      end++;          // new end spot in line because of added customer
      line[end] = (Customer) customer;    // adds customer to end of the line
      return true;
    }
    System.out.println("line was full");
    return false;
  }

  @Override public Customer dequeue() {
    Customer tempCustomer = line[0];    //gets the customer at the front of the line
    for (int i = 0; i <= end; i++) {    //everyone moves up one spot
      line[i] = line[i + 1];
    }
    return tempCustomer;    //returns the front customer
  }

  @Override public int size() {
    return end + 1;   //returns the number of people in line
  }

  @Override public void doubleQueue() {
    capacity = capacity * 2;
    Customer[] newQueArray = new Customer[capacity];    //makes a new line with twice the cap
    for (int i = 0; i < line.length; i++) {   //moves all customers into the new line
      newQueArray[i] = line[i];
    }
    line = newQueArray;     //makes the new line the name of the old line so we can do this again later
  }

  @Override public boolean isFull() {
    if (end == capacity) {   //if the end spot reference equals the capacity
      return true;
    }
    return false;
  }

  @Override public boolean isEmpty() {
    if(line[0] == null) {   //if the first spot in line is empty
      return true;
    }
    return false;
  }

}
