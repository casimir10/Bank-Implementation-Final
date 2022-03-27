
public class AccountNotFound extends Exception {
	// Error that is thrown when account not in database
	public AccountNotFound(int account) {
		super("Error: The Given Account not Found in the Database " + account);
	}
}
