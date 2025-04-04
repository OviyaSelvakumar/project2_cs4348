March 27, 2025 - 10:08 PM

Pushing repository to Github.

---------------------------------------------------------------------------
April 1, 2025 - 11:08 PM

I am now rewatching the project overview lecture to best understand the project and its requirements. My notes, thoughts, and gameplan are below:

Bank:
- has 3 tellers
- serves 50 customers per day
- opens when all tellers are free
- closes when all 50 are completed and gone

Customer:
- cannot enter unless bank is open (bank open boolean?)
- can withdraw or deposit (tells teller)
- can go to a free teller to be served
- waits if there are no free tellers
- leaves once complete


Teller:
- goes into safe to get/put money (2 at a time max)
- get manager approcal for withdrawl
- call next when complete

Manager:
- approve withdrawal for teller
- talk to one teller at a time

OVERALL
- use threads and semaphores
---> safe and manager will need semaphores
---> use to ensure transaction is complete before customer leaves

Finer details are in the Project Info Sheet. As per the first suggestion, I am goign to try to make one customer enter the bank and talk/work with one teller + manager

---------------------------------------------------------------------------
April 2, 2025 - 1:28 PM

I worked a little bit into the night but made little progress. Today, however, I was able to get the synchronization and notification between the teller and customer working. I was previously having some trouble with communication between the teller and customer, specifically ordering the behavior as needed. However, I think it now works!

The code, as of right now, works for basic communication and a few needed behaviors between one teller and customer. For the rest, I will first work on completing the fully needed functionalities for one teller and customer. This will include more semaphores as well to ensure the manager, safe, and door work as needed.
---------------------------------------------------------------------------
April 2, 2025 - 11:08 PM

I am going to do the full behavior for the teller and customer. As this will include creating the semaphores, I will be adding those first and now.
---------------------------------------------------------------------------
April 2, 2025 - 11:48 PM

All teller and customer steps/tasks were added and semaphores were created.
---------------------------------------------------------------------------
April 3, 2025 - 12:35 AM

The functions are added and in place and running. The threads are not running in order as needed as the communication seems to be missing. All customer tasks are running first with teller tasks following. This will be fixed tomorrow.
---------------------------------------------------------------------------
April 3, 2025 - 3:48 PM

I will be starting to fix the communication between the teller and customer. I think the synchronization commands are in the incorrect places.
---------------------------------------------------------------------------
April 3, 2025 - 6:12 PM

I have been able to order and match communication until the teller asks for the transaction type. I will continue working to complete this. Once this is completed, I will add more customers and one last semaphore to ensure on 2 people can enter at a time.
---------------------------------------------------------------------------
April 3, 2025 - 9:28 PM

The programming is working correctly and completely for one customer and one teller. I will build on this to allow for 50 customers and 3 tellers later by including restrictions on entry and any other necessary changes.
---------------------------------------------------------------------------
