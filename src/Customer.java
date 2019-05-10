
public class Customer implements Runnable {

	private int id;
	private Teller teller;

	public Customer(int id, Teller teller) {
		this.id = id;
		this.teller = teller;
	}

	public void printMessage(String message) {
		System.out.println("At Time \t" + CustomerGenerator.getStartTime() + ", Customer \t" + id + " " + message);
	}

	@Override
	public void run() {
		printMessage("arrives in line");
		teller.serveCustomer(this);
		printMessage("leaves the bank");
	}
}