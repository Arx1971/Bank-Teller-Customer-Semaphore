public class Bank implements Runnable {
	
	private int id;
	private Teller teller;
	private int meanInterArrivalTime;
	private int meanServiceTime;
	private int numberOfTellers;
	private int lengthOfSimulation;

	public Bank(int id, Teller teller) {
		this.id = id;
		this.teller = teller;
	}

	public Bank(int meaInterArrivaltime, int meanServiceTime, int nummberOfTellers, int lengthOfSimulation) {
		this.setlengthOfSimulation(lengthOfSimulation);
		this.setmeanInterArrivalTime(meaInterArrivaltime);
		this.setNumberOfTellers(nummberOfTellers);
		this.setMeanServiceTime(meanServiceTime);
	}

	public int getNumberOfTellers() {
		return numberOfTellers;
	}

	public int getmeanInterArrivalTime() {
		return meanInterArrivalTime;
	}

	public int getMeanServiceTime() {
		return meanServiceTime;
	}

	public int getlengthOfSimulation() {
		return lengthOfSimulation;
	}

	public void setNumberOfTellers(int numberOfTellers) {
		this.numberOfTellers = numberOfTellers;
	}

	public void setmeanInterArrivalTime(int meanInterArrivalTime) {
		this.meanInterArrivalTime = meanInterArrivalTime;
	}

	public void setMeanServiceTime(int meanServiceTime) {
		this.meanServiceTime = meanServiceTime;
	}

	public void setlengthOfSimulation(int lengthOfSimulation) {
		this.lengthOfSimulation = lengthOfSimulation;
	}

	public void printMessage(String message) {
		System.out.println("At Time \t" + Customer.getStartTime() + ", Customer \t" + id + " " + message);
	}

	@Override
	public void run() {
		printMessage("arrives in line");
		try {
			teller.tellerSimulator(new Bank(id, teller));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		printMessage("leaves the bank");
	}
}
