public class Assignment2 {
	
	public static void main(String[] args) throws InterruptedException {

		Bank bank = new Bank(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]),
				Integer.parseInt(args[3]));

		System.out.println("Mean inter-arrival time: " + Integer.parseInt(args[0]));
		System.out.println("Mean service time: " + Integer.parseInt(args[1]));
		System.out.println("Number of tellers: " + Integer.parseInt(args[2]));
		System.out.println("Length of simulation: " + Integer.parseInt(args[3]) + "\n");

		Thread simulator = new Thread(new Customer(bank));
		simulator.start();
		
		simulator.join();

	}
}