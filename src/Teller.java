import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Teller implements Runnable {

	private Semaphore tellerSemaphore;
	private Random_Int_Mean randomIntMean;
	private Bank bank;
	private static List<Integer> averageWaitingTime;

	public Teller(Bank bank) {
		this.bank = bank;
		tellerSemaphore = new Semaphore(bank.getNumberOfTellers(), true);
		randomIntMean = new Random_Int_Mean();
		averageWaitingTime = new ArrayList<Integer>();
	}

	public synchronized void tellerSimulator(Bank bank) throws InterruptedException {
		tellerSemaphore.acquire();
		bank.display("starts being served");
		int waittime = servicetime();
		Thread.sleep(waittime);
		averageWaitingTime.add(waittime);
		tellerSemaphore.release();
	}

	@Override
	public void run() {
		try {
			tellerSimulator(bank);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private synchronized int servicetime() {
		return randomIntMean.random_int(bank.getMeanServiceTime() / 10) * 1000;
	}

	public synchronized static double getAveragewaitingtime() {
		int sum = 0;
		for (int i = 0; i < averageWaitingTime.size(); i++)
			sum += averageWaitingTime.get(i);

		return (sum * 0.001) / averageWaitingTime.size();
	}

}