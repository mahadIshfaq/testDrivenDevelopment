package finances;

public class SavingsAccountYear {
	
	private int startingBalance = 0;
	private int interestRate = 0;
	private int capitalGainsAmount = 0;
	private int startingPrincipal= 0;
	private int  totalWithdrawnExceptCapitalGainsTax= 0;
	
	
	public SavingsAccountYear(int startingBalance,int startingPrincipal, int interestRate) {
		this.startingBalance=startingBalance;
		this.startingPrincipal= startingPrincipal;
		this.capitalGainsAmount= startingBalance-startingPrincipal;
		this.interestRate=interestRate;
	}

	public SavingsAccountYear nextYear(int capitalGainsTaxRate ) {
		return  new SavingsAccountYear(this.endingBalance(capitalGainsTaxRate),0,interestRate);
		
	}

	public int endingBalance(int capitalGainsTaxRate) {
		 int modifiedStart= startingBalance-totalWithdrawnExceptCapitalGainsTax()- capitalGainsTaxIncurred(capitalGainsTaxRate);
		return modifiedStart+interestEarned(capitalGainsTaxRate);
	}
	
	public int startingBalance() {
		return startingBalance;
	}

	public int interestRate() {
		
		return interestRate;
	}

	public void withdraw(int amount) {
		this.totalWithdrawnExceptCapitalGainsTax+=amount;
	}

	public int startingPrincipal() {
		
		return startingBalance-capitalGainsAmount;
	}
	
	public int totalWithdrawn(int capitalGainsTax) {
		// TODO Auto-generated method stub
		return totalWithdrawnExceptCapitalGainsTax()+ capitalGainsTaxIncurred(capitalGainsTax);
	}

	public int endingPrincipal() {
		int result= startingPrincipal()-totalWithdrawnExceptCapitalGainsTax();
		return Math.max(0, result);
	}

	public int totalWithdrawnExceptCapitalGainsTax() {
		
		return this.totalWithdrawnExceptCapitalGainsTax;
	}

	public int capitalGainsWithdrawn() {
	
		int result= -1 * (startingPrincipal()- totalWithdrawnExceptCapitalGainsTax());
		return Math.max(0, result);
	}

	public int capitalGainsTaxIncurred(int taxRate) {
		
		double dblTaxRate=taxRate/100.0;
		double dblCapGains=capitalGainsWithdrawn();		
		return (int)((dblCapGains/(1-dblTaxRate))-dblCapGains);
	}

	public int startingCapitalGains() {
	
		return startingBalance-startingPrincipal;
	}

	public int endingCapitalGains() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int interestEarned(int capitalGainsTaxRate) {
		// TODO Auto-generated method stub
		return	(startingBalance-totalWithdrawnExceptCapitalGainsTax()- capitalGainsTaxIncurred(capitalGainsTaxRate)) * interestRate/100;
	}




}
