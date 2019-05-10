import java.util.ArrayList;
import java.util.List;

public class CustomerGenerator implements Runnable {

	private static int customerCounter;
	private List<Thread> customerThreads;
	private Driver driver;
	private Teller teller;
	private Random_Int_Mean random_int_mean;
	public static long startTime;
	public int meanInterArrivalTime;

	public CustomerGenerator(Driver driver) {
		this.driver = driver;
		startTime = System.currentTimeMillis();
		this.teller = new Teller(driver);
		customerThreads = new ArrayList<>();
		random_int_mean = new Random_Int_Mean();
		customerCounter = 0;
		meanInterArrivalTime = 0;
	}

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(arrivalTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Thread customerThread = new Thread(new Customer(++customerCounter, teller));
			customerThreads.add(customerThread);
			customerThread.start();

			if (driver.getlengthOfSimulation() < (System.currentTimeMillis() - startTime) / 100)
				break;

		}

		while (!customerThreads.isEmpty()) {
			for (int i = 0; i < customerThreads.size(); i++) {
				Thread thread = customerThreads.get(i);
				if (thread.isAlive()) {
					continue;
				} else {
					try {
						thread.join();
						customerThreads.remove(i);
						break;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("\nSimulation terminated after " + customerCounter + " customers served");
		System.out.println("Average waiting time = " + Teller.getAveragewaitingtime());

	}

	public static int getStartTime() {
		return (int) ((System.currentTimeMillis() - startTime) / 100);
	}

	private int arrivalTime() {
		return random_int_mean.random_int(driver.getmeanInterArrivalTime() / 10) * 1000;
	}

}
