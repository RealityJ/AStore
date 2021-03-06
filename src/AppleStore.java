import java.util.ArrayList;

class AppleStore {
  final int  _minToHourConversion = 60;
  int        _capacity;
  int        _sim;
  int        _cPerHour;
  Customer   _customer;
  int        _convertedHoursToMin = 0;
  AppleQueue _queue;
  int        _customersNeededForThisHour;
  Customer[] unsorted;
  Customer[] unsortedImpatient;

  int        averageCustomersPerHour;

  AppleQueue queue;
  AppleQueue impatientQueue;
  AppleQueue acceptedQueue;
  AppleQueue displayQueue;

  public AppleStore(int qCapacity, int simHours, int custPerHour) {
    _capacity = qCapacity;
    _sim = simHours;
    _cPerHour = custPerHour;
    _convertedHoursToMin = simHours * _minToHourConversion;
    queue = new AppleQueue(_capacity);
    impatientQueue = new AppleQueue(_capacity);
    acceptedQueue = new AppleQueue(_capacity);
    displayQueue = new AppleQueue(_capacity);
    _customersNeededForThisHour = _minToHourConversion / _cPerHour;
  }

  /**
   * This methods performs a simulation of a store operation using a queue and prints the statistics. For every minute,
   * the simulator 1) checks if there are new customers arriving; 2) adds the new customer into the waiting line or else
   * records the customer who chooses to leave; 3) continues to help the current customer if the current customer is not
   * finished yet, or else get the next person in the waiting line. The simulator starts at minute 0, and repeats every
   * minute until it finishes the requested simulation time.
   */
  public void simulation() {
    for (int i = _sim; i > 0; i--) {    // runs simulation for desired hours
      ArrayList<Customer> unsorted = new ArrayList<Customer>();    // creates a temp unsorted Customer array
      ArrayList<Customer> unsortedImpatient = new ArrayList<Customer>();
      int impatientCount = 0;
      int patientCount = 0;
      for (int j = 0; j < _cPerHour; j++) {   // creates Customers for the number that arrive per hour
        if (j >= 5) {
          if (Math.random() >= 0.5) {   // ~50% chance of turning away
            unsorted.add(new Customer((int) (Math.random() * 61), i)); // random int 0-60min
            patientCount++;
          }
          else {
            unsortedImpatient.add(new Customer((int) (Math.random() * 61), i));
            impatientCount++;
          }
        }
        else {
          Customer temp = new Customer((int) (Math.random() * 61), i);
          unsorted.add(temp);   // random int 0-60min
          patientCount++;
        }
      }
      sortImpatients(unsortedImpatient);
      sort(unsorted);   // sorts the unsorted customer array by arrival and enters them into queue
    }
  }

  /**
   * print the info of all accepted customers
   * 
   * @return
   */

  public void sort(ArrayList<Customer> unsorted2) {
    Customer[] sorted = new Customer[unsorted2.size()];
    int earliestPos = 0;
    for (int j = 0; j < unsorted2.size(); j++) {
      Customer earliest = null;
      for (int i = 0; i < unsorted2.size(); i++) {
        if (unsorted2.get(i) != null
            && (earliest == null || earliest.getArrivalTime() > unsorted2.get(i).getArrivalTime())) {
          earliest = unsorted2.get(i);
          earliestPos = i;
        }
      }
      sorted[j] = earliest;
      unsorted2.set(earliestPos, null);
    }
    for (int i = sorted.length - 1; i >= 0; i--) {
      queue.enqueue(sorted[i]);
      acceptedQueue.enqueue(sorted[i]);
      displayQueue.enqueue(sorted[i]);
    }
  }

  public void sortImpatients(ArrayList<Customer> unsortedImpatient2) {
    Customer[] sorted = new Customer[unsortedImpatient2.size()];
    int earliestPos = 0;
    for (int j = 0; j < unsortedImpatient2.size(); j++) {
      Customer earliest = null;
      for (int i = 0; i < unsortedImpatient2.size(); i++) {
        if (unsortedImpatient2.get(i) != null
            && (earliest == null || earliest.getArrivalTime() > unsortedImpatient2.get(i).getArrivalTime())) {
          earliest = unsortedImpatient2.get(i);
          earliestPos = i;
        }
      }
      sorted[j] = earliest;
      unsortedImpatient2.set(earliestPos, null);
    }
    for (int i = sorted.length - 1; i >= 0; i--) {
      impatientQueue.enqueue(sorted[i]);
    }
  }

  public void displayAcceptedCustomers() {
    System.out.println("Average customers per hour " + queue.size() / _sim + "\n");
    System.out.println(queue.size() + " Accepted Customers:");
    while (0 < acceptedQueue.size()) {
      Customer temp = acceptedQueue.dequeue();
      System.out.println("Customer arrived at " + temp.getArrivalTime() + " asking for " + temp.getTimeForCustomer());
    }
    System.out.println("\n");
  }

  /**
   * print the info of all served customers
   */

  public void displayServedCustomers() {
    System.out.println("Displaying Served Customers");
    ArrayList<Customer> wait = new ArrayList<Customer>();
    int timeLeft = 0;
    int customerCount = 0;
    for (int mins = 0; mins < (60 * _sim); mins++) {
      Customer nextCustomer = displayQueue.dequeue();
      if (nextCustomer != null && nextCustomer.getArrivalTime() <= mins) {
        if (nextCustomer.getTimeForCustomer() == 0) {
          System.out.println("Customer who Arrived at " + nextCustomer.getArrivalTime() + " requested "
              + nextCustomer.getOriginalAskTime() + " and was finished at " + (mins));
        }
        else {
          nextCustomer.setTimeForCustomer(nextCustomer.getTimeForCustomer() - 1);
          displayQueue.enqueue(nextCustomer);
        }
      }
      else {
        displayQueue.enqueue(nextCustomer);
      }

    }
    System.out.println((queue.size() - displayQueue.size()) + " Customers were served during this simulation time");
    System.out.println("\n");
  }

  /**
   * print the info of all waiting customers
   */
  public void displayWaitingCustomers() {
    System.out.println("Displaying Waiting " + displayQueue.size() + " Customers: ");
    while (displayQueue.size() > 0) {
      Customer temp = displayQueue.dequeue();
      System.out.println("Customer who arrived at " + temp.getArrivalTime() + " is still in line asking for "
          + temp.getTimeForCustomer() + " more minutes");
    }
    System.out.println("\n");
  }

  /**
   * print the info of all turned away customers
   */
  public void displayTurnAwayCustomers() {
    System.out.println(impatientQueue.size() + " Customers turned away because they are impatient");
    while (0 < impatientQueue.size()) {
      Customer temp = impatientQueue.dequeue();
      if (temp != null) {
        System.out.println("impatient customer arrived at " + temp.getArrivalTime() + " and left");
      }
    }
  }

  public int getCapacity() {
    return _capacity;
  }

  public int getSimHours() {
    return _sim;
  }

  public int getCPerHour() {
    return _cPerHour;
  }
}

// ------------CUSTOMER------------//
class Customer {
  int _timeForCustomer;
  int _arrivalTime;
  int finishedTime;
  int originalTimeFor;
  int hourArrive;

  public Customer() {
    _arrivalTime = 0;
    _timeForCustomer = 0;
    originalTimeFor = 0;
  }

  public Customer(int timeArrived, int hour) {
    _arrivalTime = timeArrived + ((hour - 1) * 60);
    hourArrive = hour;
    _timeForCustomer = (int) (Math.random() * 3 + 1);
    originalTimeFor = _timeForCustomer;
  }

  public int getArrivalTime() {
    return _arrivalTime;
  }

  public int getTimeForCustomer() {
    return _timeForCustomer;
  }

  public int getFinishedTime() {
    return finishedTime;
  }

  public int getOriginalAskTime() {
    return originalTimeFor;
  }

  public void setArrivalTime(int arrivalTime) {
    _arrivalTime = arrivalTime;
  }

  public void setTimeForCustomer(int timeForCustomer) {
    _timeForCustomer = timeForCustomer;
  }

  public void setFinishedTime(int finished) {
    finishedTime = finished;
  }
}

class AppleQueue implements Queue {

  private int        capacity;
  private Customer[] line;

  public AppleQueue(int qCap) {
    capacity = qCap;
    line = new Customer[1];
  }

  @Override public boolean enqueue(Object customer) {
    Customer[] newLine = new Customer[line.length + 1];
    for (int i = line.length - 1; i >= 0; i--) {
      newLine[i] = (Customer) customer;
      if (line[i] != null) {
        newLine[i + 1] = line[i];
      }

    }
    line = newLine;
    for (int j = 0; j < line.length - 1; j++) {}
    return false;
  }

  @Override public Customer dequeue() {
    Customer tempCustomer = line[0];    // gets the customer at the front of the line
    for (int i = 0; i < line.length; i++) {    // everyone moves up one spot
      if (line[i] != null) {
        line[i] = line[i + 1];
      }
    }

    Customer[] temp = new Customer[line.length - 1];
    for (int j = 0; j < temp.length; j++) {
      temp[j] = line[j];
    }
    line = temp;
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
