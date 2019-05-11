
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Customer implements Runnable {

	private static int customerCounter = 0;
	private static BlockingQueue<Thread> customersQueue = new LinkedBlockingQueue<Thread>();
	private static long startTime = System.currentTimeMillis();
	
	private Bank bank;
	private Teller teller;
	private Random_Int_Mean randomIntMean;
	

	public Customer(Bank bank) {
		this.bank = bank;
		teller = new Teller(bank);
		randomIntMean = new Random_Int_Mean();
	}

	public static int getStartTime() {
		return (int) ((System.currentTimeMillis() - startTime) / 100);
	}

	private int arrivalTime() {
		return randomIntMean.random_int(bank.getmeanInterArrivalTime() / 10) * 1000;
	}

	public void customerSimulator() throws InterruptedException {
		while (true) {
			Thread.sleep(arrivalTime());

			Thread thread = new Thread(new Bank(++customerCounter, teller));
			customersQueue.add(thread);
			thread.start();

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
					customersQueue.poll();
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
