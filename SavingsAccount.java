public class SavingsAccount extends BankAccount implements Interest {

	// Two Parameter Constructor
	public SavingsAccount(int acctnum, double bal) {
		super(acctnum, bal);
	}

	// Copy Constructor
	public SavingsAccount(SavingsAccount acc) {
		accountnumber = acc.accountnumber;
		balance = acc.balance;
	}

	// returms account#
	public int getAcctNumber() {
		return accountnumber;
	}

	// Processes interest to given savingsaccount
	@Override
	public void addInterest(double rate) {

		balance += (balance *= rate);
	}

	// returns balance of given account
	@Override
	public double getBalance() {

		return balance;
	}

	// Processes deposit to given account
	// throws NegAmount exception if <0
	@Override
	public void makeDeposit(double n) throws NegativeAmountEntered {

		if (n < 0) {
			throw new NegativeAmountEntered();
		} else {
			balance += n;
		}

	}

	// Processes withdrawal to given account
	// throws NegAmount exception if <0
	// throws InSuffic exception if bal < amount
	@Override
	public void makeWithdrawal(double n) throws NegativeAmountEntered, InsufficientFunds {

		if (n < 0) {
			throw new NegativeAmountEntered();
		} else if (balance < n) {
			throw new InsufficientFunds(accountnumber);
		} else {
			balance -= n;
		}
	}
}