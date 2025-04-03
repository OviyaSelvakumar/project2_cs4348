package project2_cs4348;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Customer extends Thread {
    //initialize variables
    int custIdNum;
    int tellerIdNum = -1;
    static Customer localCust = null;
    static String transaction = " ";
    static Semaphore bankSem = new Semaphore(2);
    //random to decide deposit or withdrawl
    Random random = new Random();
    
    // Constructor to initialize the Customer with an ID and a random transaction type
    public Customer(int xIdNumd, Teller teller) {
        custIdNum = xIdNumd;
        tellerIdNum = teller.getTellerId();  //assign teller
        localCust = this; 
        if(random.nextInt(2) == 0){
            transaction = "withdrawal";
        }
        else{
            transaction = "deposit";
        }
    }

    //getter for custoemr's id num
    public int getCustIdNum() {
        return custIdNum;
    }

    //getter for teller's id 
    public Integer getTellerIdNum() {
        return tellerIdNum;
    }

    //setter for teller's id 
    public void setTellerId(int xIdNum) {
        tellerIdNum = xIdNum;
    }

    //getter for customer
    public static Customer getCustomer() {
        return localCust;
    }

    //getter for transaction type
    public String getTransaction() {
        return transaction;
    }

    public void run() {
        //customer behavior
       try {
        bankSem.acquire();
            System.out.println("Customer " + custIdNum + " []: wants to perform a " + transaction + " transaction.");
            Teller.sendCustomerToTeller(this);
                //while (tellerIdNum < 0) {
                    Thread.sleep(99);
                //}
       } catch (Exception e) {
       }
       bankSem.release();
        System.out.println("Customer " + custIdNum + " []: going to the bank.");
        System.out.println("Customer " + custIdNum + " []: entering the bank.");
        System.out.println("Customer " + custIdNum + " []: getting in line.");
         //synch teller with cust when ready for service
         synchronized (this) {
            System.out.println("Customer " + custIdNum + " []: selecting a teller.");
            this.notify();  //customer is ready to be served
        }
        System.out.println("Customer " + custIdNum + " [Teller " + tellerIdNum + "]: selects a teller.");
        synchronized (this) {
            System.out.println("Customer " + custIdNum + " [Teller " + tellerIdNum + "]: introduces self to teller.");
            this.notify();  //customer is ready to be served
        }
        System.out.println("Customer " + custIdNum + " [Teller " + tellerIdNum + "]: asks for a " + transaction + " transaction.");
        System.out.println("Customer " + custIdNum + " [Teller " + tellerIdNum + "]: leaves teller.");
        System.out.println("Customer " + custIdNum + " []: going to the door.");
        System.out.println("Customer " + custIdNum + " []: leaves the bank.");
       
    }
}
    
