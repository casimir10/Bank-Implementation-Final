import java.io.*;
import java.util.*;

public class FinalExam {

	public static void main(String[] args)
			throws IOException, AccountNotFound, NegativeAmountEntered, InsufficientFunds {

		// Create ArrayList of Savings Accounts
		ArrayList<SavingsAccount> database = new ArrayList<>();
		// Initialize Output,Input, Scanner
		PrintWriter out = new PrintWriter("myOutput.txt");
		File test = new File("mytest.txt");
		Scanner key = new Scanner(test);

		printMenu();// Print Menu

		boolean notdone = true;
		char choice;

		readAccts(database);// Read Accounts from File to Database
		printAccts(database, out);// Print Initial Database

		do {
			choice = key.next().charAt(0);
			switch (choice) {
			case 'Q':
			case 'q':
				notdone = false;
				break;
			case 'B':
			case 'b':
				getBalance(database, out, key);
				break;
			case 'D':
			case 'd':
				makeDeposit(database, out, key);
				break;
			case 'W':
			case 'w':
				makeWithdrawal(database, out, key);
				break;
			case 'I':
			case 'i':
				addInterest(database, out, key);
				break;
			default:
				out.println("ERROR: Entered an Invalid Value");
				out.println("The Given " + choice + " Is not a Valid Character");
				out.println();
				break;
			}
		} while (notdone);

		out.println();
		printAccts(database, out);// Print Post Database

		key.close();// Close Scanner
		out.close();// Close outputfile
	}// END MAIN

	public static void readAccts(ArrayList<SavingsAccount> bank) throws IOException {
		String line;

		File input = new File("myaccts.txt");
		Scanner in = new Scanner(input);

		while (in.hasNext()) {
			line = in.nextLine();
			String[] tokens = line.split(" ");

			SavingsAccount newaccount = new SavingsAccount(Integer.parseInt(tokens[0]), Double.parseDouble(tokens[1]));
			bank.add(newaccount);
		}
		in.close();
	}

	// PRINT ACCOUNTS METHOD
	// Input: Savingaccount array, outputfile
	// Process: For loop to run through all account objects
	// Output: Prints all accounts to file
	public static void printAccts(ArrayList<SavingsAccount> database, PrintWriter out) throws IOException {
		out.printf("%5s %10s\n", "Account#", "Balance");
		for (int i = 0; i < database.size(); i++) {
			out.printf("%5d %10.2f\n", database.get(i).getAcctNumber(), database.get(i).getBalance());
		}
	}

	// MENU METHOD
	// Output: Prints the complete menu to Screen
	public static void printMenu() {
		System.out.println("Q - Quit");
		System.out.println("B - Balance");
		System.out.println("D - Deposit");
		System.out.println("W - Withdraw");
		System.out.println("I - Add Interest");
	}

	// FIND ACCOUNT METHOD
	// Input: Acct#,SavingsAccount arraylist,outputfile
	// Process: For loop runs through array to match account number
	// Check for exceptions-throw if caught
	// Output: Returns index if found, -1 if not
	public static int findAcct(ArrayList<SavingsAccount> database, int reqaccount, PrintWriter out)
			throws AccountNotFound, IOException {
		int index = -1;
		for (int i = 0; i < database.size(); i++) {

			if (database.get(i).getAcctNumber() == reqaccount)
				index = i;
		}

		try {
			if (index == -1)
				throw new AccountNotFound(index);
		} catch (AccountNotFound e) {
			out.println(e.getMessage());
		}

		return index;
	}

	// BALANCE METHOD
	// Input: Savingsaccount array, outputfile,scanner
	// Process: call to find accounts method to match account
	// Output:Not found: throws exception
	// If found- Prints Receipt of requested accounts balance
	public static void getBalance(ArrayList<SavingsAccount> database, PrintWriter out, Scanner in)
			throws IOException, AccountNotFound {

		int wantedacct = in.nextInt();
		int index = findAcct(database, wantedacct, out);

		try {
			if (index == -1) {
				out.println("Transaction: Balance");
				throw new AccountNotFound(wantedacct);
			} else {
				out.println("Transaction: Balance");
				out.println(database.get(index).getBalance());
			}

		} catch (AccountNotFound e) {
			out.println(e.getMessage());
		}
		out.println();
	}

	// DEPOSIT METHOD
	// Input:Savingsaccounts, outputfile,scanner
	// Process: Call to find account,getbalance,makedeposit
	// Output: If account not found/incorrect input amount-exception
	// Print Reciept and increase balance if no error
	public static void makeDeposit(ArrayList<SavingsAccount> database, PrintWriter out, Scanner in)
			throws IOException, AccountNotFound, NegativeAmountEntered {

		int wantedacct = in.nextInt();
		int index = findAcct(database, wantedacct, out);
		double amount = in.nextDouble();

		try {
			if (index == -1) {
				out.println("Transaction: Deposit");
				throw new AccountNotFound(wantedacct);
			} else if (amount < 0) {
				out.println("Transaction: Deposit");
				throw new NegativeAmountEntered();
			} else {
				out.println("Transaction: Deposit");

				out.println("Pre-Trans Balance: " + database.get(index).getBalance());
				out.println("Amount: " + amount);
				database.get(index).makeDeposit(amount);
				;
				out.println("Post-Trans Balance: " + database.get(index).getBalance());

			}

		} catch (AccountNotFound e) {
			out.println(e.getMessage());
		} catch (NegativeAmountEntered e) {
			out.println(e.getMessage());
		}

		out.println();
	}

	// WITHDRAW METHOD
	// Input: Savingsaccounts, outputfile,Scanner
	// Process: Call to find account,getbalance,makewithdraw
	// Output: If account not found/less than required balance-exceptions
	// Print Reciept and decrease balance if no error
	public static void makeWithdrawal(ArrayList<SavingsAccount> database, PrintWriter out, Scanner in)
			throws AccountNotFound, NegativeAmountEntered, InsufficientFunds, IOException {

		int wantedacct = in.nextInt();
		int index = findAcct(database, wantedacct, out);
		double amount = in.nextDouble();

		try {
			if (index == -1) {
				out.println("Transaction: Withdraw");
				throw new AccountNotFound(wantedacct);
			} else if (amount < 0) {
				out.println("Transaction: Deposit");
				throw new NegativeAmountEntered();
			} else if (amount > database.get(index).getBalance()) {
				throw new InsufficientFunds(database.get(index).getAcctNumber());
			} else {
				out.println("Transaction: Deposit");
				out.println("Pre-Trans Balance: " + database.get(index).getBalance());
				out.println("Amount: " + amount);
				database.get(index).makeWithdrawal(amount);
				;
				out.println("Post-Trans Balance: " + database.get(index).getBalance());
			}

		} catch (AccountNotFound e) {
			out.println(e.getMessage());
		} catch (NegativeAmountEntered e) {
			out.println(e.getMessage());
		} catch (InsufficientFunds e) {
			out.println(e.getMessage());
		}
		out.println();
	}

	// INTERFACE METHOD
	// Input: Savingsaccounts, outputfile,scanner
	// Process: Call to find account,getbalance,addinterest
	// Output: If account not found/less than required balance-exception
	// Print Reciept and add interest
	public static void addInterest(ArrayList<SavingsAccount> database, PrintWriter out, Scanner in)
			throws AccountNotFound, IOException {

		int wantedacct = in.nextInt();
		int index = findAcct(database, wantedacct, out);
		double inter = in.nextDouble();

		try {
			if (index == -1) {
				out.println("Transaction: Interest");
				throw new AccountNotFound(wantedacct);
			} else {
				out.println("Pre-Trans Balance: " + database.get(index).getBalance());
				out.println("Interest: " + inter);
				out.println("Creditied: " + (database.get(index).getBalance() * inter));
				database.get(index).addInterest(inter);
				;
				out.println("Post-Trans Balance: " + database.get(index).getBalance());
			}

		} catch (AccountNotFound e) {
			out.println(e.getMessage());
		}
		out.println();
	}
}
