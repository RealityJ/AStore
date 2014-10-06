
public class Customer {
	int _timeForCustomer;
	int _arrivalTime;


	public Customer(){
		_arrivalTime = 0;
		_timeForCustomer = 0;
	}
	public Customer(int timeArrived){
		_arrivalTime = timeArrived;
		_timeForCustomer = (int)(Math.random()*3+1);
	}

	public int getArrivalTime(){
		return _arrivalTime;
	}

	public int getTimeForCustomer(){
		return _timeForCustomer;
	}

	public void setArrivalTime(int arrivalTime){
		_arrivalTime = arrivalTime;
	}

	public void setTimeForCustomer(int timeForCustomer){
		_timeForCustomer = timeForCustomer;
	}
}
