# Bank-Implementation-Final

PURPOSE: Implmement the necessary systems to simulate transactions on a savings account that can
process interest utilizing a combination of Classes, Methods and interfaces. (MAY BE NON-FUNCTIONAL)

UI : 

Console Displayed Menu,Text Based Input and Output also done via Console. A txt file is created to display
all existing contents of the ATM folling the end of a session.

FEATURE LIST:

Every Savings Account has access to the following queries:

- Withdrawals 
- Deposits
- Balance Checks
- Adding Interest

The following exceptions were implemented:

- Exception thrown when account is not found in Database
- Exception thrown when an existing account has an insufficient balance for a given transaction
- Exception thrown when a negative value is entered during query


This was an exercise in utilizing a modern Java programming approach to implementing a very early iteration of a savings account. 

The following techniques were used:

- Seperating functions into repsective objects which communicate
- Using an interface for shared methods
- Extending the Exception class to create new exceptions to suit your needs
- Following the Open-Closed Model (Savings Account is an Extentsion of the unmodified Bank Account class)
- Overriding inherited methods
- Taking advantage of Abstraction
- Using copy contructors 
- Implmementing switch cases to call methods
- Printing to txt files
