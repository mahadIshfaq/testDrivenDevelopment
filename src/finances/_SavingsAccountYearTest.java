package finances;
import static org.junit.Assert.*;
import org.junit.*;

public class _SavingsAccountYearTest {
@Test
public void startingBalanceMatchesConstructor() {
	assertEquals(10000,newAccount().startingBalance());
}




@Test
public void endingBalanceMatchesConstructor() {
	assertEquals(11000,newAccount().endingBalance());
}

@Test

public void InterestRateMatchesConstructor() {
	assertEquals(10,newAccount().interestRate());
}

@Test
public void nextYearsStartingBalanceEqualsThisYearEndingBalance() {
	SavingsAccountYear thisYear =newAccount();
	assertEquals(thisYear.endingBalance(),thisYear.nextYear().startingBalance());

}

@Test

public void nextYearsInterestRateEqualsThisYearsInterestRate() {
	SavingsAccountYear thisYear=newAccount();
	assertEquals(thisYear.interestRate(),thisYear.nextYear().interestRate());
	
}

@Test
public void WithdrawingFundsOccursAtTheBeginningOfTheYear() {
	SavingsAccountYear year= newAccount();
	year.withdraw(1000);
	assertEquals(9900,year.endingBalance());
}

@Test
public void withdrawingMoreThanPrincipalIncursCapitalGainsTax() {
	 SavingsAccountYear year= new SavingsAccountYear(10000,7000,10);
	 year.withdraw(3000);
	 assertEquals(7700,year.endingBalance());
	 year.withdraw(5000);
	 assertEquals(2000 + 200 - (1250),year.endingBalance());
}

private SavingsAccountYear newAccount() {
	SavingsAccountYear account = new SavingsAccountYear(10000,10);
	return account;
}
}
