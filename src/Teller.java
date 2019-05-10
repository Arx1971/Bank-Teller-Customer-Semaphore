import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Teller {

	private Semaphore TellerSemaphore;
	private Random_Int_Mean random_int_mean;
	private Bank bank;
	private static List<Integer> averagewaitingtime;

	public Teller(Bank bank) {
		this.bank = bank;
		TellerSemaphore = new Semaphore(bank.getNumberOfTellers(), true);
		random_int_mean = new Random_Int_Mean();
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

	private synchronized int servicetime() {
		return random_int_mean.random_int(bank.getMeanServiceTime() / 10) * 1000;
	}

	public static double getAveragewaitingtime() {
		int sum = 0;
		for (int i = 0; i < averagewaitingtime.size(); i++)
			sum += averagewaitingtime.get(i);

		return (sum * 0.001) / averagewaitingtime.size();
	}

}