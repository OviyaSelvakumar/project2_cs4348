package project2_cs4348;

public class Simulation {
    public static void main(String[] args) {        
        //create teller 1
        Teller teller = new Teller(1);
        //start teller 1
        teller.start();
        
        //create customer 1
        Customer customer = new Customer(1, teller);
        //start customer 1
        customer.start();
        
        //use try-catch to attempt to run teller and customer
        try {
            teller.join();  
            customer.join();
        } catch (InterruptedException e) { }
        
        //simulation completed --> close bank
        System.out.println("All customers served. Bank is now closed.");
    }
}




