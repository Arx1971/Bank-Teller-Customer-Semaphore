# Bank Teller Simulator

One interesting class of applications of concurrent programming is simulation, in which a program simulates a
real-world system by using individual threads to simulate its individual components. For this project, we will be
simulating a bank, having some number of tellers. Each customer arrives at the bank, waits in a single line until
a teller becomes free, and then goes to that teller to transact his/her business, leaving the bank when his/her
business is done. In this case, a thread simulates each individual customer.
The major parameters of this system are: the number of tellers, the rate at which new customers arrive (specified
as an average interval between arrivals), and the average time it takes to service a customer.
Clearly:
1. If (average inter-arrival time) >> (average service time) / (# of tellers) then customers will seldom stand
in line, but tellers will often not be productively employed.
2. If (average inter-arrival time) < (average service time) / (# of tellers) then the waiting line will grow
without limit and customers will become very angry.
3. If (average inter-arrival time) = (average service time) / (# of tellers) then the system will eventually reach
a steady state with a fairly constant waiting line length.
The value for each of these three parameters supplied to the assignment’s program should be chosen to satisfy
number 3 above so the tellers are productive and the customers don’t become angry. Therefore, to test your
program, choose values for two of the parameters in the equation of number 3 and then compute the value of the
third parameter. You can generate several different sets of values for the three parameters to test your program.
For this project, you write a program that simulates this system. At startup, your program will accept the three
parameters mentioned above plus the length of time to run the simulation. All times will be entered in seconds.
To avoid having to run the program for an entire day, we will simulate ten seconds of "simulated world" time by
one second of actual program run time.
The simulation will proceed as follows:
1. One thread will be responsible for creating new customers at random intervals, such that the average time
between new customers is equal to the specified parameter. It will exist throughout the simulation.
2. Created when a customer arrives, a unique thread simulates each customer, works its way through the
bank, and then terminates.
3. A general semaphore that uses a FIFO queue will simulate the waiting line.
4. The semaphore initializes to the number of tellers. If there are n tellers, then the first n customers doing
an acquire() on this semaphore will be able to proceed without delay.
5. When each customer thread enters the bank, he/she will do a acquire() on this semaphore. When he/she
finishes being served, he/she will do a release(), allowing another customer to pass the semaphore and use
the teller.

# Customer Thread Insturction
Get next available customer number.
 Report arrival and save arrival time.
 Get in line (i.e., wait on semaphore.)
 Done waiting; calculate waiting time.
 Report starting to be served.
 Sleep() a random amount of time, based on service time mean.
 Report leaving the bank.
 Update global statistics.
 Exit.

# Sample Input: 
The output of a sample run of the program:
Mean inter-arrival time: 10
Mean service time: 30
Number of tellers: 3
Length of simulation: 70

# Sample Output

At Time 	10, Customer 	1 arrives in line\
At Time 	10, Customer 	1 starts being served
At Time 	20, Customer 	2 arrives in line
At Time 	20, Customer 	1 leaves the bank
At Time 	20, Customer 	2 starts being served
At Time 	30, Customer 	3 arrives in line
At Time 	30, Customer 	3 starts being served
At Time 	40, Customer 	2 leaves the bank
At Time 	40, Customer 	4 arrives in line
At Time 	40, Customer 	4 starts being served
At Time 	60, Customer 	4 leaves the bank
At Time 	60, Customer 	5 arrives in line
At Time 	60, Customer 	5 starts being served
At Time 	70, Customer 	3 leaves the bank
At Time 	70, Customer 	6 arrives in line
At Time 	70, Customer 	6 starts being served
At Time 	80, Customer 	7 arrives in line
At Time 	80, Customer 	7 starts being served
At Time 	90, Customer 	6 leaves the bank
At Time 	100, Customer 	7 leaves the bank
At Time 	150, Customer 	5 leaves the bank

Simulation terminated after 7 customers served
Average waiting time = 3.14



