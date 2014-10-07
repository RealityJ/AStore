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
    for (int i = 0; i < _sim; i++) {    // runs simulation for desired hours
      System.out.println(i + ":hour");
      ArrayList<Customer> unsorted = new ArrayList<Customer>();    // creates a temp unsorted Customer array
      ArrayList<Customer> unsortedImpatient = new ArrayList<Customer>();
      int impatientCount = 0;
      int patientCount = 0;
      for (int j = 0; j < _cPerHour; j++) {   // creates Customers for the number that arrive per hour
        if (j >= 5) {
//          System.out.println("more then 5 people per hour");
          if (Math.random() >= 0.5) {   // ~50% chance of turning away
            unsorted.add(new Customer((int) (Math.random() * 61))); // random int 0-60min
            System.out.println(unsorted.get(patientCount).getArrivalTime() + " was patient");
            patientCount++;
          }
          else {
            unsortedImpatient.add(new Customer((int) (Math.random() * 61)));
            System.out.println(unsortedImpatient.get(impatientCount).getArrivalTime() + " was NOT patient");
            impatientCount++;
          }
        }
        else {
          unsorted.add(new Customer((int) (Math.random() * 61)));   // random int 0-60min
          System.out.println(unsorted.get(patientCount).getArrivalTime());
          patientCount++;
        }
      }
      if (!unsortedImpatient.isEmpty()) {
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

  public void sort(ArrayList<Customer> unsorted2) {
    System.out.println("sorting the patient customers");
    Customer[] sorted = new Customer[unsorted2.size()];
    int earliestPos = 0;
    for (int j = 0; j < unsorted2.size(); j++) {
      Customer earliest = null;
      for (int i = 0; i < unsorted2.size(); i++) {
        if (unsorted2.get(i) != null && (earliest == null || earliest.getArrivalTime() > unsorted2.get(i).getArrivalTime())) {
          earliest = unsorted2.get(i);
          earliestPos = i;
        }
      }
      sorted[j] = earliest;
      unsorted2.set(earliestPos,null);
      System.out.println(sorted[j].getArrivalTime());
    }
    for (int i = 0; i < sorted.length; i++) {
      queue.enqueue(sorted[i]);
      System.out.println("pushed customer at " + sorted[i].getArrivalTime() + " to the queue");
    }
  }

  public void sortImpatients(ArrayList<Customer> unsortedImpatient2) {
    System.out.println("sorting the IMPATIENT customers");
    Customer[] sorted = new Customer[unsortedImpatient2.size()];
    int earliestPos = 0;
    for (int j = 0; j < unsortedImpatient2.size(); j++) {
      Customer earliest = null;
      for (int i = 0; i < unsortedImpatient2.size(); i++) {
        if (unsortedImpatient2.get(i) != null && (earliest == null || earliest.getArrivalTime() > unsortedImpatient2.get(i).getArrivalTime())) {
          earliest = unsortedImpatient2.get(i);
          earliestPos = i;
        }
      }
      sorted[j] = earliest;
      unsortedImpatient2.set(earliestPos,null);
      System.out.println(sorted[j].getArrivalTime());
    }
    for (int i = 0; i < sorted.length; i++) {
      queue.enqueue(sorted[i]);
      System.out.println("pushed customer at " + sorted[i].getArrivalTime() + " to the IMPATIENT queue");
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
