package project2_cs4348;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Teller extends Thread {

    //int to track each teller --> max 3
    int tellerIdNum;
    //create local customer variable
    Customer currCustomer = null;
    //semaphores for manager and safe access
    static Semaphore managerSem = new Semaphore(1);
    static Semaphore safeSem = new Semaphore(2);
    //synchronize customer side
    static Object notifyCustomer = new Object();
    //put waiting customers here
    static List<Customer> customerWaitLine = new ArrayList<>();


    public Teller(int xIdNum) {
        tellerIdNum = xIdNum;
    }

    //getter for tellerIdNum
    public int getTellerId() {
        return tellerIdNum;
    }

    public static void sendCustomerToTeller(Customer customer) {
        synchronized (notifyCustomer) {
            customerWaitLine.add(customer);
            notifyCustomer.notifyAll();
        }
    }

    //main run function for teller's actions for entire day
    public void run() {
        //start by announcing they are ready
        System.out.println("Teller " + tellerIdNum + " []:  ready to serve.");
        //teller's behavior
        while (true) {
            currCustomer = Customer.getCustomer();
            //synch local customer and make sure it runs only when it is ready to enter bank
            synchronized (currCustomer) {
                try {
                    //wait for custoemr to notify teller
                    currCustomer.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("Teller " + tellerIdNum + " [Customer " + currCustomer.getCustIdNum() + "]: serving a customer.");
            System.out.println("Teller " + tellerIdNum + " [Customer " + currCustomer.getCustIdNum() + "]: asks for transaction type.");
            currCustomer.setTellerId(tellerIdNum);

            if (currCustomer.getTransaction().equals("withdrawal")) {
                    System.out.println("Teller " + tellerIdNum + " [Customer " + currCustomer.getCustIdNum() + "]: going to manager.");
                    try {
                    managerSem.acquire();
                    Thread.sleep((long) (Math.random() * 30));
                } catch (InterruptedException e) { }
                System.out.println("Teller " + tellerIdNum + " [Customer " + currCustomer.getCustIdNum() + "]: getting manager's permission.");
                System.out.println("Teller " + tellerIdNum + " [Customer " + currCustomer.getCustIdNum() + "]: got manager's permission."); 
                managerSem.release();
            }
            
            System.out.println("Teller " + tellerIdNum + " [Customer " + currCustomer.getCustIdNum() + "]: going to safe.");
            System.out.println("Teller " + tellerIdNum + " [Customer " + currCustomer.getCustIdNum() + "]: entering safe.");
            try {
                safeSem.acquire();
                Thread.sleep((long) (Math.random() * 30));
                System.out.println("Teller " + tellerIdNum + " [Customer " + currCustomer.getCustIdNum() + "]: leaving safe.");
            } catch (InterruptedException e) { }
            safeSem.release();
            
            System.out.println("Teller " + tellerIdNum + " [Customer " + currCustomer.getCustIdNum() + "]: finishing " + currCustomer.getTransaction() + " transaction.");
            System.out.println("Teller " + tellerIdNum + " [Customer " + currCustomer.getCustIdNum() + "]: waiting for customer to leave.");
     
        }
        
    }
}
