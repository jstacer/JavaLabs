package lab2;

public class Account {

	//properties
	private int AcctNo;
	private String owner;
	private double balance;
	
	//default constructor
	public Account(){
	}
	
	//3 arg constructor
	public Account(int an, String o, double b){
		AcctNo = an;
		owner = o;
		balance = b;
	}
	
	//setAcctNo()
	public void setAcctNo(int an){
		AcctNo = an;
	}
	
	//getAcctNo()
	public int getAcctNo(){
		return AcctNo;
	}
	
	//setOwner()
	public void setOwner(String o){
		owner = o;
	}
	
	//getOwner()
	public String getOwner(){
		return owner;
	}
	
	//setBalance()
	public void setBalance(double b){
		balance = b;
	}
	
	//getBalance()
	public double getBalance(){
		return balance;
	}
	
	//deposit()
	public void deposit(double d){
		balance += d;
	}
	
	//withdraw()
	public void withdraw(double w) throws InsufficientFundsException {
		if(w > balance)
			throw new InsufficientFundsException("You have insufficient funds for this transaction.");
		balance -= w;
	}
	
	//display()
	public void display(){
		System.out.println("Account number: " + AcctNo + ", Owner: " + owner + ", Balance: " + balance);
	}
	
	public static void main(String[] args) {
		Account a1;
		a1 = new Account(2222, "Frank", 500);
		a1.deposit(100.00);
		try{
			a1.withdraw(900.00);
		}catch(InsufficientFundsException ife) {
			System.out.println(ife);
		}finally{
			a1.display();
		}
	}

} //end class
