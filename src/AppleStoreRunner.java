public class AppleStoreRunner {
     public static void main(String [] args) {

          //maximum size of queue
          int qCapacity = Integer.parseInt(args[0]);
//          System.out.println("Capacity = " + qCapacity);

          //number of simulation hours
          int simHours = Integer.parseInt(args[1]);
//          System.out.println("simulating hours = " + simHours);

          //average number of customers per hour
          int custPerHour = Integer.parseInt(args[2]);
//          System.out.println("custPerHour = " + custPerHour);
          
          AppleStore myStore = new AppleStore(qCapacity, simHours, custPerHour);

          //Run simulation
          myStore.simulation();
//          System.out.println("running simulation");
          myStore.displayAcceptedCustomers();
//          System.out.println("displaying Accepted Customers");
          myStore.displayServedCustomers();
//          System.out.println("displaying Served Customers");
          myStore.displayWaitingCustomers();
//          System.out.println("displaying waiting customers");
          myStore.displayTurnAwayCustomers();
//          System.out.println("displaying thoes who left");
          
      }
}