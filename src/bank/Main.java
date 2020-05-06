/*
	Main class is the driver of this program 
*/
package bank;
// import src.GUI.LoginMenu;

import Database.BankData;

public class Main {

	public static void main(String[] args){
		BankData db = new BankData();
		new GUI.LoginMenu(db);
  }
}
