import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Teller {

	private Semaphore TellerSemaphore;
	private Random_Int_Mean random_int_mean;
	private Driver driver;
	private static List<Integer> averagewaitingtime;

	public Teller(Driver driver) {
		this.driver = driver;
		TellerSemaphore = new Semaphore(driver.getNumberOfTellers(), true);
		random_int_mean = new Random_Int_Mean();
		averagewaitingtime = new ArrayList<Integer>();
	}

	public void serveCustomer(Customer customer) {
		try {

			TellerSemaphore.acquire();

			customer.printMessage("starts being served");

			try {
				int waittime = servicetime();
				Thread.sleep(waittime);
				averagewaitingtime.add(waittime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			TellerSemaphore.release();
		}

	}

	private synchronized int servicetime() {
		return random_int_mean.random_int(driver.getMeanServiceTime() / 10) * 1000;
	}

	public static double getAveragewaitingtime() {
		int sum = 0;
		for (int i = 0; i < averagewaitingtime.size(); i++)
			sum += averagewaitingtime.get(i);

		return (sum * 0.001) / averagewaitingtime.size();

	}

}
