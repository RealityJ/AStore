
class AppleStore {
	final int _minToHourConversion = 60;
	int _capacity;
	int _sim;
	int _cPerHour;
	Customer _customer;
	int _convertedHoursToMin = 0;
	AppleQueue _queue;
	int _customersNeededForThisHour;
	Customer[] _customerLine;
	Customer[] _hourCustomers;



	public AppleStore(int qCapacity, int simHours, int custPerHour){
		_capacity = qCapacity;
		_sim = simHours;
		_cPerHour = custPerHour;
		_convertedHoursToMin = simHours*_minToHourConversion;
		_queue = new AppleQueue(_capacity);
		_customersNeededForThisHour = _minToHourConversion/_cPerHour;
		_customerLine = new Customer[simHours*custPerHour];
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
		for(int i = 0; i < _convertedHoursToMin; i++ ){
			if(_customersNeededForThisHour == 0 && i%60 == 0){
				_customersNeededForThisHour= _cPerHour;
			}else if(_customersNeededForThisHour != 0 && i%60 != 0){
				_customer = new Customer((int)(Math.random()*60)*100);
				_customerLine[i] = _customer;
				_customersNeededForThisHour--;
			}else;
		}
		
		for(int i = 0; i < _customerLine.length; i++){
			for(int j = 0; j < _customerLine.length; j++){
				if(_customerLine[i].getArrivalTime() > _customerLine[j+1].getArrivalTime()){
				Customer placeholder = _customerLine[i];
				_customerLine[i] = _customerLine[j+1]; 
				_customerLine[j+1] = placeholder;
				}
			}
		}
		for(int i = 0; i < _customerLine.length; i++){
			System.out.print(" ," + _customerLine[i].getArrivalTime());
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
		return _cPerHour;
	}
}

