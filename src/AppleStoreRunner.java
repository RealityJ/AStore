public class AppleStoreRunner {
	   public static void main(String [] args) {

	        //maximum size of queue
	        int qCapacity = Integer.parseInt(args[0]);

	        //number of simulation hours
	        int simHours = Integer.parseInt(args[1]);

	        //average number of customers per hour
	        int custPerHour = Integer.parseInt(args[2]);
	        
	        AppleStore myStore = new AppleStore(qCapacity, simHours, custPerHour);

	        //Run simulation
	        myStore.simulation();
	        myStore.displayAcceptedCustomers();
	        myStore.displayServedCustomers();
	        myStore.displayWaitingCustomers();
	        myStore.displayTurnAwayCustomers();
	        
	    }
}