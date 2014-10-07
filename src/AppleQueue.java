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

  @Override public Object dequeue() {
    Customer tempCustomer = line[0];
    for (int i = 0; i <= end; i++) {
      line[i] = line[i + 1];
    }
    return tempCustomer;
  }

  @Override public int size() {
    return end + 1;
  }

  @Override public void doubleQueue() {
    capacity = capacity * 2;
    Customer[] newQueArray = new Customer[capacity];
    for (int i = 0; i < line.length; i++) {
      newQueArray[i] = line[i];
    }
    line = newQueArray;
  }

  @Override public boolean isFull() {
    if (end == line.length - 1) {
      return true;
    }
    return false;
  }

  @Override public boolean isEmpty() {
    if(line[0] == null) {
      return true;
    }
    return false;
  }

}
