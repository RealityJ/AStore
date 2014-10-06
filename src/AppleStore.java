
class AppleStore {
	final int _MinToHourConversion = 60;
	int _capacity;
	int _sim;
	int _cperHour;
	Customer _customer;
	int _convertedHoursToMin = 0;
	AppleQueue _queue;



	public AppleStore(int qCapacity, int simHours, int custPerHour){
		_capacity = qCapacity;
		_sim = simHours;
		_cperHour = custPerHour;
		_convertedHoursToMin = simHours*_MinToHourConversion;
		_queue = new AppleQueue(_capacity);
	}

	/**
	 * This methods performs a simulation of a store operation using a 
	 * queue and prints the statistics.
	 * For every minute, the simulator 1) checks if there are new customers 
	 * arriving; 2) adds the new customer into the waiting
	 * line or else records the customer who chooses to leave; 3) continues 
	 * to help the current customer if the current customer is not finished 
	 * yet, or else get the next person in the waiting line. 
	 * The simulator starts at minute 0, and repeats every minute until 
	 * it finishes the requested simulation time.
	 */
	
	public void simulation(){
		//creates the array to hold the customers
		Customer[] _customerHolder = new Customer[this.getSimHours()*this.getCPerHour()];
		Customer[] _leftHolder = new Customer[this.getSimHours()*this.getCPerHour()];
		
		if(_queue.size() < this.getSimHours()*this.getCPerHour()){
			_queue.doubleQueue();
		}

		//loop that will create as many customers as needed by the simulation
		for(int i = 0; i < (this.getSimHours()*this.getCPerHour()); i++){
			//the new customers being created at random times
			Customer _customerToBePlaced = new Customer((int)(Math.random()*100));
			if(i > 5 && (int)Math.random() == 1){
			_leftHolder[i] = _customerToBePlaced;	
			}else{
			//customers being placed into the array
			_customerHolder[i] = _customerToBePlaced;
			}
		}	
		//bubble sort by the arrival time in the array 
		//TODO: Check if this is correct
		for(int i = 0; i < _customerHolder.length; i++){
			for( int j = 0; j < _customerHolder.length;j++){
				if(_customerHolder[i].getArrivalTime() < _customerHolder[j].getArrivalTime() && _customerHolder[i].getArrivalTime() != 0 && _customerHolder[j].getArrivalTime() != 0){
					Customer placeholder = _customerHolder[i];
					_customerHolder[i] = _customerHolder[j];
					_customerHolder[j] = placeholder;
				}
			}
		}
		for(int i = 0; i < _customerHolder.length-1; i++){
			System.out.print(_customerHolder[i].getArrivalTime() + ", ");
			System.out.println(_customerHolder[i].getTimeForCustomer() + ", ");
			if(_leftHolder[i] != null){
				System.out.println("Customer left " + _leftHolder[i].getTimeForCustomer() + ", ");
			}
		}
		//queuing the customers into the line
		for(int i = 0; i <= _customerHolder.length; i++){
			_queue.enqueue(_customerHolder[i]);
		}



	}

	/**
	 * print the info of all accepted customers
	 */
	public void displayAcceptedCustomers(){

	}
	/**
	 * print the info of all served customers
	 */

	public void displayServedCustomers(){

	}
	/**
	 * print the info of all waiting customers
	 */
	public void displayWaitingCustomers(){

	}
	/**
	 * print the info of all turned away customers
	 */
	public void displayTurnAwayCustomers(){

	}

	public int getCapacity(){
		return _capacity;
	}
	public int getSimHours(){
		return _sim;
	}
	public int getCPerHour(){
		return _cperHour;
	}
}

