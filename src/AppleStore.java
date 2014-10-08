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

  AppleQueue queue;
  AppleQueue impatientQueue;

  public AppleStore(int qCapacity, int simHours, int custPerHour) {
    _capacity = qCapacity;
    _sim = simHours;
    _cPerHour = custPerHour;
    _convertedHoursToMin = simHours * _minToHourConversion;
    queue = new AppleQueue(_capacity);
    impatientQueue = new AppleQueue(_capacity);
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
    for (int i = 0; i < _sim; i++) {    // runs simulation for desired hours
      // System.out.println(i + ":hour");
      ArrayList<Customer> unsorted = new ArrayList<Customer>();    // creates a temp unsorted Customer array
      ArrayList<Customer> unsortedImpatient = new ArrayList<Customer>();
      int impatientCount = 0;
      int patientCount = 0;
      for (int j = 0; j < _cPerHour; j++) {   // creates Customers for the number that arrive per hour
        if (j >= 5) {
          // System.out.println("more then 5 people per hour");
          if (Math.random() >= 0.5) {   // ~50% chance of turning away
            unsorted.add(new Customer((int) (Math.random() * 61))); // random int 0-60min
            // System.out.println(unsorted.get(patientCount).getArrivalTime() + " was patient");
            patientCount++;
          }
          else {
            unsortedImpatient.add(new Customer((int) (Math.random() * 61)));
            // System.out.println(unsortedImpatient.get(impatientCount).getArrivalTime() + " was NOT patient");
            impatientCount++;
          }
        }
        else {
          Customer temp = new Customer((int) (Math.random() * 61));
          unsorted.add(temp);   // random int 0-60min
          // System.out.println(unsorted.get(patientCount).getArrivalTime() + " arrived before the rush");
          patientCount++;
        }
      }
      sortImpatients(unsortedImpatient);
      sort(unsorted);   // sorts the unsorted customer array by arrival and enters them into queue
    }
  }

  // public void customerRunTimeSim() {
  // System.out.println("Beginning the store run sim");
  // Customer current = queue.dequeue();
  // System.out.println("size of customer queue " + queue.size());
  // System.out.println("current time " + current.getArrivalTime());
  // System.out.println("size of impatient queue " + impatientQueue.size());
  // Customer impatientArrival = impatientQueue.dequeue();
  // System.out.println("impatient time " + impatientArrival.getArrivalTime());
  // int currentTimeLeft = -1;
  // for (int hours = 0; hours < _sim; hours++) {
  // for (int mins = 0; mins <= 60; mins++) {
  // if (current.getArrivalTime() == mins) {
  // currentTimeLeft = current.getTimeForCustomer();
  // System.out.println("Customer arrived at " + current.getArrivalTime() + " requesting " + currentTimeLeft);
  // }
  // if (currentTimeLeft != -1) {
  // currentTimeLeft--;
  //
  // }
  // }
  // }
  // }

  /**
   * print the info of all accepted customers
   * 
   * @return
   */

  public void sort(ArrayList<Customer> unsorted2) {
    // System.out.println("sorting the patient customers");
    Customer[] sorted = new Customer[unsorted2.size()];
    int earliestPos = 0;
    for (int j = 0; j < unsorted2.size(); j++) {
      Customer earliest = null;
      // System.out.println("size of the patient array " + unsorted2.size());
      for (int i = 0; i < unsorted2.size(); i++) {
        if (unsorted2.get(i) != null
            && (earliest == null || earliest.getArrivalTime() > unsorted2.get(i).getArrivalTime())) {
          earliest = unsorted2.get(i);
          earliestPos = i;
        }
      }
      sorted[j] = earliest;
      unsorted2.set(earliestPos, null);
      // System.out.println("the earliest for this loop was " + sorted[j].getArrivalTime());
    }
    // System.out.println("length is " + sorted.length);
    for (int i = sorted.length - 1; i >= 0; i--) {
      queue.enqueue(sorted[i]);
      // System.out.println("pushed customer at " + sorted[i].getArrivalTime() + " to the queue");
    }
    // System.out.println("the resulting length was " + queue.size());
  }

  public void sortImpatients(ArrayList<Customer> unsortedImpatient2) {
    // System.out.println("sorting the IMPATIENT customers");
    Customer[] sorted = new Customer[unsortedImpatient2.size()];
    int earliestPos = 0;
    for (int j = 0; j < unsortedImpatient2.size(); j++) {
      Customer earliest = null;
      // System.out.println("size of the IMPATIENT array " + unsortedImpatient2.size());
      for (int i = 0; i < unsortedImpatient2.size(); i++) {
        if (unsortedImpatient2.get(i) != null
            && (earliest == null || earliest.getArrivalTime() > unsortedImpatient2.get(i).getArrivalTime())) {
          earliest = unsortedImpatient2.get(i);
          earliestPos = i;
        }
      }
      sorted[j] = earliest;
      unsortedImpatient2.set(earliestPos, null);
      // System.out.println("the earliest for this loop was " + sorted[j].getArrivalTime());
    }
    // System.out.println("length is " + sorted.length);
    for (int i = sorted.length - 1; i >= 0; i--) {
      // System.out.println(sorted[i].getArrivalTime());
      impatientQueue.enqueue(sorted[i]);
      // System.out.println("pushed customer at " + sorted[i].getArrivalTime() + " to the queue");
    }
  }

  public void displayAcceptedCustomers() {
    System.out.println(queue.size() + " Accepted Customers:");
    ArrayList<Customer> acceptedCustomer = new ArrayList<Customer>(queue.size());
    while (0 < queue.size()) {
      Customer temp = queue.dequeue();
      System.out.println("Customer arrived at " + temp.getArrivalTime() + " asking for " + temp.getTimeForCustomer());
      acceptedCustomer.add(temp);
    }
    for (int j = 0; j < acceptedCustomer.size(); j++) {
      queue.enqueue(acceptedCustomer.get(j));
    }
    while (0 < queue.size()) {
      Customer temp = queue.dequeue();
      acceptedCustomer.add(temp);
    }
    for (int j = 0; j < acceptedCustomer.size(); j++) {
      queue.enqueue(acceptedCustomer.get(j));
    }
    System.out.println("\n");
  }

  /**
   * print the info of all served customers
   */

  public void displayServedCustomers() {
    System.out.println("Displaying Served Customers");
    ArrayList<Customer> line = new ArrayList<Customer>();
    ArrayList<Customer> waiting = new ArrayList<Customer>();
    ArrayList<Customer> served = new ArrayList<Customer>();
    while (0 <= queue.size()) {
      line.add(queue.dequeue());
    }
    Customer current = null;
    int timeLeft = -1;
    int count = 0;
    for (int hour = 0; hour < _sim; hour++) {
      for (int min = 0; min < 60; min++) {
        timeLeft--;
        if (timeLeft == 0) {
          served.add(current);
        }
        if (!waiting.isEmpty() && timeLeft <= 0) {
          current = waiting.get(0);
          waiting.remove(0);
        }
        if (line.get(0).getArrivalTime() == min && timeLeft <= 0) {
          current = line.get(0);
          timeLeft = current.getTimeForCustomer();
          line.remove(0);
        }
        if (line.get(count).getArrivalTime() == min && timeLeft > 0) {
          waiting.add(line.get(count));
          count++;
        }
      }
    }
    for (int i = 0; i <= served.size(); i++) {
      System.out.println("Customer arrived at " + line.get(i).getArrivalTime() + " requesting "
          + line.get(i).getTimeForCustomer() + " and was finished at " + line.get(i).getFinishedTime());
    }
    System.out.println("\n");
  }

  /**
   * print the info of all waiting customers
   */
  public void displayWaitingCustomers() {

  }

  /**
   * print the info of all turned away customers
   */
  public void displayTurnAwayCustomers() {
    System.out.println(impatientQueue.size() + " Customers turned away because they are impatient FUCKS");
    while (0 < impatientQueue.size()) {
      // System.out.println("dequeueing Impatient at " + impatientQueue.size());
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
