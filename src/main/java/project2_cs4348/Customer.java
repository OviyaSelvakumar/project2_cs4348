package project2_cs4348;

public class Customer extends Thread {
    //initialize variables
    int custIdNum;
    int tellerIdNum = -1;
    static Customer localCust = null;
    
    // Constructor to initialize the Customer with an ID and a random transaction type
    public Customer(int xIdNumd, Teller teller) {
        custIdNum = xIdNumd;
        tellerIdNum = teller.getTellerId();  //assign teller
        localCust = this;  
    }

    //getter for custoemr's id num
    public int getCustIdNum() {
        return custIdNum;
    }

    //getter for teller's id 
    public Integer getTellerIdNum() {
        return tellerIdNum;
    }

    //getter for customer
    public static Customer getCustomer() {
        return localCust;
    }

    public void run() {
        //customer behavior
        System.out.println("Customer " + custIdNum + " wants to perform a transaction.");
        System.out.println("Customer " + custIdNum + " enters the bank.");
        System.out.println("Customer " + custIdNum + " enters the line.");

        //synch teller with cust when ready for service
        synchronized (this) {
            System.out.println("Customer " + custIdNum + " goes and introduces itself to Teller " + tellerIdNum + ".");
            this.notify();  //customer is ready to be served
        }
    }
}