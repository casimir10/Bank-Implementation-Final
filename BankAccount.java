
public abstract class BankAccount {

	protected int accountnumber;
	protected double balance;

	public BankAccount() {

	}

	// Two Param Constructor
	public BankAccount(int accnum, double bal) {
		accountnumber = accnum;
		balance = bal;
	}

	// CopyContructor
	public BankAccount(BankAccount acc) {
		accountnumber = acc.accountnumber;
		balance = acc.balance;
	}

	public abstract double getBalance();

	public abstract void makeDeposit(double n) throws NegativeAmountEntered;

	public abstract void makeWithdrawal(double n) throws NegativeAmountEntered, InsufficientFunds;

}