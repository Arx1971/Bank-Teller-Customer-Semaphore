import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Teller implements Runnable{

	private Semaphore TellerSemaphore;
	private Random_Int_Mean randomIntMean;
	private Bank bank;
	private static List<Integer> averagewaitingtime;

	public Teller(Bank bank) {
		this.bank = bank;
		TellerSemaphore = new Semaphore(bank.getNumberOfTellers(), true);
		randomIntMean = new Random_Int_Mean();
		averagewaitingtime = new ArrayList<Integer>();
	}

	public void tellerSimulator(Bank bank) throws InterruptedException {
		TellerSemaphore.acquire();
		bank.printMessage("starts being served");
		int waittime = servicetime();
		Thread.sleep(waittime);
		averagewaitingtime.add(waittime);
		TellerSemaphore.release();
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

	public static double getAveragewaitingtime() {
		int sum = 0;
		for (int i = 0; i < averagewaitingtime.size(); i++)
			sum += averagewaitingtime.get(i);

		return (sum * 0.001) / averagewaitingtime.size();
	}

}