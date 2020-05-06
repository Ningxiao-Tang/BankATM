# BankATM
OOD final project
Date: 5/6/2020
Team #9

## Contributers
Elyse Kaczmarek
BU ID: 

Ningxiao Tang
BU ID:

Eunice Choi
BU ID: U75265203


## How to run
javac Main.java
java Main

## Files 

### Database 
BankData - 

### Backend/OOD files

Main - Driver class that starts the program

AccountTypes - Abstract class that represents any type of account that can hold money for a customer

	CheckingAccount - An account type that represents a checking account
	SavingsAccount - An account type that represents a savings account
	SecurityAccount - An account type that represents a security account

User - Class that represents a user of the bank ATM
	
	Customer - A user that represents a customer of the bank ATM
	Manager - A user that represents the admin/manager of the bank ATM (only one manager)

Currency - Class representing of currency, it can hold 3 different currency types and a value representing the amount of money it holds
CurrencyType - Enum file that contains three different types of currencies 
Transaction - Class that represents the different kinds of transactions a bank account can make.

	Withdraw- Subclass of transaction that represents a withdrawal of money from an ATM
	Deposit- Subclass of transaction that represents a deposit of money from an ATM
	Transfer- Subclass of transaction that represents a transfer of money from an ATM

Stock - Class representing the stocks of a bank ATM, which can be manipulated by the Manager
Loan - Class representing the loans of a bank ATM

### GUI classes

BuyStockView: The view for customer to buy stocks
CreateAccountMenu: The view for customer to create an account
CustomerInfoView: The view for manager to check all customers
CustomerView: The view of logged in customers
DepositView: The view for customer to make a deposit to the selected account
LoanView: The view for customer to make a loan
LoginMenu: Main menu of BankATM app
ManagerStockView: The view for manager to edit stock attributes
ManagerView: The view of logged in bank manager
RegisterView: View for customer to register in the Bank
ReportView: View for bank manager to check daily report
SellStock: View for customer to sell their stocks
SignInview: The view for users to sign in to the BankATM
StockListView: Frame for all stocks, managers can view all stocks, customers can view their bought stocks
TransactionsView: View of customer’s transactions
TransferView: View for customer to make a transfer from selected account to another account in BankATM system.
WithdrawView: The view for customer to withdraw from the selected account
