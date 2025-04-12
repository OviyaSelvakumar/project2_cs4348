package project2_cs4348;

public class Simulation {
    public static void main(String[] args) { 
        int tellerCount = 3;
        int customerCount = 50; 

        //create tellers
        Teller[] tellersArray = new Teller[tellerCount];
        for (int i = 0; i < tellerCount; i++) {
            tellersArray[i] = new Teller(i);
            // start teller
            tellersArray[i].start();
        }

        //create customers
        Customer[] customersArray = new Customer[customerCount];
        for (int i = 0; i < customerCount; i++) {
            customersArray[i] = new Customer(i);
            // start customer
            customersArray[i].start();
        }

        //wait for all customers to finish
        for (Customer customer : customersArray) {
            try {
                customer.join();
            } catch (InterruptedException e) { }
        }

        //close bank after all customers are done
        System.out.println("All customers served. Bank is now closed.");
        Teller.closeBank();
        
        // wait for all tellers to finish
        for (Teller teller : tellersArray) {
            try {
                teller.join();
            } catch (InterruptedException e) { }
        }
    }
}