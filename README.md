Hi!

The code consists of 3 files: Customer.java, Teller.java, Simulation.java. Please run Simulation.java as it is used to run all three files and the program as a whole.

Simulation.java: 
- This creates the customers and tellers, with the use of loops, to simulate the bank. Once all 50 customers are served, this file will close the bank.
- Through the tellerCount and customerCount, the number of people in the bank can be edited.

Customer.java: 
- This holds the creation of a customer's communication/interactions with a teller and their overall behavior. 
- This also holds a semaphore (doorEntrySem) to make sure that more than 2 people cannot enter the bank at the same time.
- Also, the transaction type of the customer is decided randomly here.

Teller.java:  
- This holds the creation of the teller's communication/interactions with a customer, manager, and their overall behavior.
- This holds a semaphore (safeSem) to make sure that more than 2 tellers cannot enter the safe at the same time.
- This also holds a semaphore (managerSem) to make sure only one person is asking for the manager's permission at a time.
- There is a wait line for customers created through an Array List, and this stores customers who are waiting in line for a teller inside the bank.

*There is a gap in my commits. This is due to travel and being unable to properly commit or upload code during the last weekend/monday.*
