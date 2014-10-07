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
    for (int i = 0; i < _sim; i++) {    //runs simulation for desired hours
      unsorted = new Customer[_cPerHour];    //creates a temp unsorted Customer array
      unsortedImpatient = new Customer[_cPerHour];
      int impatientCount = 0;
      for (int j = 0; j < _cPerHour; j++) {   //creates Customers for the number that arrive per hour
        if (_cPerHour > 5) {
          if (Math.random() >= 0.5) {   //~50% chance of turning away
            unsorted[j] = new Customer((int) (Math.random() * 61)); //random int 0-60min
          }
          else {
            unsortedImpatient[impatientCount] = new Customer((int) (Math.random() * 61));
            impatientCount++;
          }
        }
        else {
          unsorted[j] = new Customer((int) (Math.random() * 61));   //random int 0-60min
        }
      }
      sortImpatients(unsortedImpatient);
      sort(unsorted);   //sorts the unsorted customer array by arrival and enters them into queue
    }
  }

  /**
   * print the info of all accepted customers
   * @return 
   */

  public void sort(Customer[] unsorted) {
    Customer earliest = unsorted[0];
    for (int j = 0; j > _cPerHour; j++) {
      for (int i = 0; i > _cPerHour; i++) {
        if (earliest.getArrivalTime() > unsorted[i].getArrivalTime()) {
          earliest = unsorted[i];
        }
      }
      if(queue.isFull()) {
        queue.doubleQueue();
        System.out.println("Queue doubled!");
      }
      queue.enqueue(earliest);
    }
  }
  
  public void sortImpatients(Customer[] unsorted) {
    Customer earliestImpatient = unsorted[0];
    for (int j = 0; j > _cPerHour; j++) {
      for (int i = 0; i > _cPerHour; i++) {
        if (earliestImpatient.getArrivalTime() > unsorted[i].getArrivalTime()) {
          earliestImpatient = unsorted[i];
        }
      }
      if(impatientQueue.isFull()) {
        impatientQueue.doubleQueue();
        System.out.println("impatientsQueue doubled!");
      }
      impatientQueue.enqueue(earliestImpatient);
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
