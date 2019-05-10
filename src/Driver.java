public class Driver {

	private int meanInterArrivalTime;
	private int meanServiceTime;
	private int numberOfTellers;
	private int lengthOfSimulation;

	public Driver(int meaInterArrivaltime, int meanServiceTime, int nummberOfTellers, int lengthOfSimulation) {
		this.setlengthOfSimulation(lengthOfSimulation);
		this.setmeanInterArrivalTime(meaInterArrivaltime);
		this.setNumberOfTellers(nummberOfTellers);
		this.setMeanServiceTime(meanServiceTime);
	}

	public Driver(int meanInterArrivalTime) {
		this.meanInterArrivalTime = meanInterArrivalTime;
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

}
