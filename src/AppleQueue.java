public class AppleQueue implements Queue {

  private int        capacity;
  private int        end = 0;
  private int        front;
  private Customer[] line;

  public AppleQueue(int qCap) {
    capacity = qCap;
    line = new Customer[1];
  }

  @Override public boolean enqueue(Object customer) {
    Customer[] newLine = new Customer[line.length + 1];
    for(int i = line.length - 1; i >= 0; i--) {
      newLine[i] = (Customer) customer;
      if(line[i] != null) {
        newLine[i +1] = line[i];
      }
      
    }
    line = newLine;
    for(int j = 0; j < line.length - 1; j++) {
      System.out.println("Customer who arrived at " + line[j].getArrivalTime() + " was entered in spot " + j);
    }
//    System.out.println("the resulting length was " + (line.length - 1));
    return false;
  }

  @Override public Customer dequeue() {
    Customer tempCustomer = line[0];    // gets the customer at the front of the line
    System.out.println("line length= " + (line.length - 1));
    for (int i = 0; i < line.length; i++) {    // everyone moves up one spot
      if (line[i] != null) {
//        System.out.println(i);
        line[i] = line[i + 1];
      }
    }
    if (tempCustomer != null) {
//      System.out.println("tempCust Arrival " + tempCustomer.getArrivalTime());
    }
    return tempCustomer;    // returns the front customer
  }

  @Override public int size() {
    return line.length - 1;   // returns the number of people in line
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
