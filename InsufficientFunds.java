
public class InsufficientFunds extends Exception {

	// Error thrown when transaction is greater than account balance
	public InsufficientFunds(int account) {
		super("Error: Insufficient Funds in Given Account " + account);
	}
}
