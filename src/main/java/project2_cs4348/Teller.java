package project2_cs4348;


public class Teller extends Thread {
    //int to track each teller --> max 3
    int tellerIdNum;
    
    public Teller(int xIdNum) {
        tellerIdNum = xIdNum;
    }
    
    //getter for tellerIdNum
    public int getTellerId() {
        return tellerIdNum;
    }
    
    //main run function for teller's actions for entire day
    public void run() {
        //start by announcing they are ready
        System.out.println("Teller " + tellerIdNum + " is ready to serve.");
        
        //create local customer variable
        Customer currCustomer = Customer.getCustomer();
        //synch local customer and make sure it runs only when it is ready to enter bank
        synchronized (currCustomer) {
            try {
                //wait for custoemr to notify teller
                currCustomer.wait();
            } catch (InterruptedException e) { }
        }

        //teller's behavior
        System.out.println("Teller " + tellerIdNum + " is now serving Customer " + currCustomer.getCustIdNum());
        System.out.println("Teller " + tellerIdNum + " completes the transaction and notifies Customer " + currCustomer.getCustIdNum() + ".");
    }
}