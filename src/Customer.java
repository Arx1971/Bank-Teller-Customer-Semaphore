import java.util.LinkedList;
import java.util.Queue;

public class Customer implements Runnable {

	private static int customerCounter;
	private Queue<Thread> customersQueue;
	private Bank bank;
	private Teller teller;
	private Random_Int_Mean random_int_mean;
	public static long startTime;

	public Customer(Bank bank) {
		this.bank = bank;
		startTime = System.currentTimeMillis();
		this.teller = new Teller(bank);
		customersQueue = new LinkedList<Thread>();
		random_int_mean = new Random_Int_Mean();
		customerCounter = 0;
	}

	public static int getStartTime() {
		return (int) ((System.currentTimeMillis() - startTime) / 100);
	}

	private int arrivalTime() {
		return random_int_mean.random_int(bank.getmeanInterArrivalTime() / 10) * 1000;
	}

	public void customerSimulator() throws InterruptedException {
		while (true) {
			try {
				Thread.sleep(arrivalTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Thread customerThread = new Thread(new Bank(++customerCounter, teller));
			customersQueue.add(customerThread);
			customerThread.start();

			if (bank.getlengthOfSimulation() < (System.currentTimeMillis() - startTime) / 100)
				break;

		}

		while (!customersQueue.isEmpty()) {
			while (true) {
				Thread thread = customersQueue.peek();
				if (thread.isAlive()) {
					continue;
				} else {
					thread.join();
					customersQueue.remove();
					break;
				}
			}
		}

		System.out.println("\nSimulation terminated after " + customerCounter + " customers served");
		System.out.printf("Average waiting time = %.2f", Teller.getAveragewaitingtime());
	}

	@Override
	public void run() {
		try {
			customerSimulator();
		} catch (Exception e) {

		}
	}
}
