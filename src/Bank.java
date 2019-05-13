public class Bank implements Runnable {

	private int custID;
	private Teller teller;
	private int meanInterArrivalTime;
	private int meanServiceTime;
	private int numberOfTellers;
	private int lengthOfSimulation;

	public Bank(int custID, Teller teller) {
		this.custID = custID;
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

	public void display(String str) {
		System.out.println("At Time \t" + Customer.getStartTime() + ", Customer \t" + custID + " " + str);
	}

	@Override
	public void run() {
		display("arrives in line");
		try {
			teller.tellerSimulator(new Bank(custID, teller));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		display("leaves the bank");
	}
}
