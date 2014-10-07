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
    customerArrivalSimulation();
  }

  public void customerArrivalSimulation() {
    System.out.println("_sim=" + _sim);
    for (int i = 0; i < _sim; i++) {    // runs simulation for desired hours
      System.out.println(i + ":hour");
      unsorted = new Customer[_cPerHour];    // creates a temp unsorted Customer array
      unsortedImpatient = new Customer[_cPerHour];
      int impatientCount = 0;
      for (int j = 0; j < _cPerHour; j++) {   // creates Customers for the number that arrive per hour
        if (_cPerHour > 5) {
          System.out.println("more then 5 people per hour");
          if (Math.random() >= 0.5) {   // ~50% chance of turning away
            unsorted[j - impatientCount] = new Customer((int) (Math.random() * 61)); // random int 0-60min
            System.out.println(unsorted[j - impatientCount].getArrivalTime());
          }
          else {
            unsortedImpatient[impatientCount] = new Customer((int) (Math.random() * 61));
            impatientCount++;
            System.out.println(unsortedImpatient[impatientCount].getArrivalTime());
          }
        }
        else {
          unsorted[j] = new Customer((int) (Math.random() * 61));   // random int 0-60min
          System.out.println(unsorted[j].getArrivalTime());
        }
      }
      if (unsortedImpatient[0] != null) {
        sortImpatients(unsortedImpatient);
      }
      sort(unsorted);   // sorts the unsorted customer array by arrival and enters them into queue
    }
  }

  /**
   * print the info of all accepted customers
   * 
   * @return
   */

  public void sort(Customer[] unsorted) {
    System.out.println("sorting the patient customers");
    Customer[] sorted = new Customer[unsorted.length];
    int earliestPos = 0;
    for(int j = 0; j < unsorted.length; j++) {
      Customer earliest = null;
      for(int i = 0; i < unsorted.length; i++) {
        if(unsorted[i] != null && (earliest == null || earliest.getArrivalTime() > unsorted[i].getArrivalTime())) {
          earliest = unsorted[i];
          earliestPos = i;
        }
      }
      sorted[j] = earliest;
      unsorted[earliestPos] = null;
      System.out.println(sorted[j].getArrivalTime());
    }
    for(int i = 0; i < sorted.length; i++) {
      queue.enqueue(sorted[i]);
      System.out.println("pushed customer at " + sorted[i].getArrivalTime() + " to the queue");
    }
  }

  public void sortImpatients(Customer[] unsorted) {
    System.out.println("sorting the patient customers");
    Customer[] sorted = new Customer[unsorted.length];
    int earliestPos = 0;
    for(int j = 0; j < unsorted.length; j++) {
      Customer earliest = null;
      for(int i = 0; i < unsorted.length; i++) {
        if(unsorted[i] != null && (earliest == null || earliest.getArrivalTime() > unsorted[i].getArrivalTime())) {
          earliest = unsorted[i];
          earliestPos = i;
        }
      }
      sorted[j] = earliest;
      unsorted[earliestPos] = null;
      System.out.println(sorted[j].getArrivalTime());
    }
    for(int i = 0; i < sorted.length; i++) {
      impatientQueue.enqueue(sorted[i]);
    }
  }

  public void displayAcceptedCustomers() {

  }

  /**
   * print the info of all served customers
   */

  public void displayServedCustomers() {

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
