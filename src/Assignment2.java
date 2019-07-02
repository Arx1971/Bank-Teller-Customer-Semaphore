/**
 * @ Adnan Rahin
 *
 *
 **/

public class Assignment2 {

	public static void main(String[] args) throws InterruptedException {

		Bank bank = new Bank(10, 30, 3, 70);

		System.out.println("Mean inter-arrival time: " + 10);
		System.out.println("Mean service time: " + 30);
		System.out.println("Number of tellers: " + 3);
		System.out.println("Length of simulation: " + 70);
		System.out.println();

		Thread simulator = new Thread(new Customer(bank));
		simulator.start();
		simulator.join();
	}
}