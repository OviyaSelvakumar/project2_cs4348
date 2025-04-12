package project2_cs4348;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Customer extends Thread {

    //initialize variables
    int custIdNum;
    int tellerIdNum = -1;
    String transaction = " ";
    //semaphore to limit bank entry to 2 customers at a time
    static Semaphore doorEntrySem = new Semaphore(2);
    //boolean to move customer from line to teller
    boolean readyForTeller = false;
    //random to decide deposit or withdrawal
    Random random = new Random();

    //constructor 
    public Customer(int xIdNumd) {
        custIdNum = xIdNumd;
        //randomly decide transaction
        if (random.nextInt(2) == 0) {
            transaction = "withdrawal";
        } else {
            transaction = "deposit";
        }
    }
    
    //getter for customer id 
    public int getCustIdNum() {
        return custIdNum;
    }
    
    //getter for teller id 
    public int getTellerIdNum() {
        return tellerIdNum;
    }
    
    //setter for teller id 
    public void setTellerId(int xIdNum) {
        tellerIdNum = xIdNum;
    }
    
    //getter for transaction type
    public String getTransaction() {
        return transaction;
    }
    
    //check if customer is ready to be served
    public boolean isReadyForTeller() {
        return readyForTeller;
    }

    public void run() {
        //customer decides on transaction type and goes to bank
        System.out.println("Customer " + custIdNum + " []: wants to perform a " + transaction + " transaction.");
        System.out.println("Customer " + custIdNum + " []: going to the bank.");
        
        try {
            //wait if door is at capacity (2 = max))
            doorEntrySem.acquire();
            
            //customer has now entered the bank
            System.out.println("Customer " + custIdNum + " []: entering the bank.");
            //release once customer is inside
            doorEntrySem.release();
            //add customer to the wait line
            System.out.println("Customer " + custIdNum + " []: getting in line.");
            Teller.sendCustomerToTeller(this);
            
            //wait until customer is matched with a teller
            synchronized (this) {
                while (tellerIdNum == -1) {
                    try {
                        this.wait(50);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                
                //now customer selects and approaches the teller
                System.out.println("Customer " + custIdNum + " [Teller " + tellerIdNum + "]: selects a teller.");
                System.out.println("Customer " + custIdNum + " [Teller " + tellerIdNum + "]: introduces self to teller.");
                readyForTeller = true;
                this.notify();
                
                //customer matches with teller and waits for teller to respond/ask for transaction type
                try {
                    this.wait(50);
                } catch (InterruptedException e) {
                }
                
                //customer tells teller their desired transaction type
                System.out.println("Customer " + custIdNum + " [Teller " + tellerIdNum + "]: asks for a " + transaction + " transaction.");
                this.notify(); 
                
                //wait until transaction is over
                try {
                    this.wait(50);
                } catch (InterruptedException e) {
                }
                //leaves teller when transaction is over
                System.out.println("Customer " + custIdNum + " [Teller " + tellerIdNum + "]: leaves teller.");
                this.notify();
            }
            
            //customer leaves bank
            System.out.println("Customer " + custIdNum + " []: going to the door.");
            System.out.println("Customer " + custIdNum + " []: leaves the bank.");
            
        } catch (InterruptedException e) {
        }
    }
}
