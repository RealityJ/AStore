public class AppleQueue implements Queue {

  private int        capacity;
  private Customer[] queArray;
  private int        front;
  private int        end;

  public AppleQueue(int qCap) {
    capacity = qCap;
    queArray = new Customer[capacity];
    front = -1;
    end = 0;
  }

  @Override public boolean  enqueue(Object customer) {
    if(end != capacity) {
      queArray[front] = (Customer) customer;
    }
    return false;
  }

  @Override public Object dequeue() {
    Customer firstCust = queArray[front++];
    if(front == capacity) {
      front = 0;
    }
    return firstCust;
  }

  @Override public int size() {
    if (end >= front) {
      return end - front;
    }
    else {
      return (capacity - front) + (end + 1);
    }
  }

  @Override public void doubleQueue() {
    capacity = capacity * 2;
    Customer[] newQueArray = new Customer[capacity];
    for(int i = 0; i < queArray.length; i++) {
      newQueArray[i] = queArray[i];
    }
    queArray = newQueArray;
  }

  @Override public boolean isFull() {
    if (front + capacity - 2 == end || end + 2 == front) {
      return true;
    }
    return false;
  }

  @Override public boolean isEmpty() {
    if (end + 1 == front || (front + capacity - 1 == end)) {
      return true;
    }
    return false;
  }

}
