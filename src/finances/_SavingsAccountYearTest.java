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
	assertEquals(11000,newAccount().endingBalance(25));
}

@Test

public void InterestRateMatchesConstructor() {
	assertEquals(10,newAccount().interestRate());
}

@Test
public void startingCapitalGainsIsStartingBalanceMinusStartingPrincipal() {
	SavingsAccountYear year= new SavingsAccountYear(10000,3000,10);
	 assertEquals(7000,year.startingCapitalGains());
}

@Test
public void nextYearsStartingBalanceEqualsThisYearEndingBalance() {
	SavingsAccountYear thisYear =newAccount();
	assertEquals(thisYear.endingBalance(25),thisYear.nextYear(25).startingBalance());

}

@Test

public void nextYearsInterestRateEqualsThisYearsInterestRate() {
	SavingsAccountYear thisYear=newAccount();
	assertEquals(thisYear.interestRate(),thisYear.nextYear(25).interestRate());
	
}



@Test

public void multipleWithdrawalsInAYearAreTotaled() {
	SavingsAccountYear year= newAccount();
	year.withdraw(1000);
	year.withdraw(2000);
	assertEquals(3000,year.totalWithdrawn());
	
}

@Test
public void withdrawingMoreThanPrincipalTakesFromCapitalGains() {
	SavingsAccountYear year = new SavingsAccountYear(10000,3000,10);
//	assertEquals("starting principal",3000,year.startingPrincipal());
	year.withdraw(1000);
	assertEquals(0,year.capitalGainsWithdrawn());
	year.withdraw(3000);
	assertEquals(1000,year.capitalGainsWithdrawn());
}
@Test
public void startingPrincipalMatchConstructor() {
	SavingsAccountYear year= new SavingsAccountYear(10000,3000,10);
	 assertEquals(3000,year.startingPrincipal());
}

@Test
public void endingPrincipalConsiderWithdrawls() {
	SavingsAccountYear year= new SavingsAccountYear(10000,3000,10);
//	 assertEquals("starting principal",3000,year.startingPrincipal());
	 year.withdraw(2000);
	 assertEquals("ending principal",1000, year.endingPrincipal());
}
@Test
public void endingPrincipalNeverGoesBelowZero() {
	SavingsAccountYear year= new SavingsAccountYear(10000,3000,10);
//	 assertEquals("starting principal",3000,year.startingPrincipal());
	 year.withdraw(4000);
	 assertEquals("ending principal",0, year.endingPrincipal());
}
@Test

public void capitalGainsTaxesDonotEarnIntereset() {
	SavingsAccountYear year= new SavingsAccountYear(10000,0,10);
	year.withdraw(1000);
	assertEquals("capital gains withdrawn",1000,year.capitalGainsWithdrawn());
	assertEquals("capital gains withdrawn",333,year.capitalGainsTaxIncurred(25));
//	assertEquals("total withdrawn",1333,year.totalWithdrawnIncludingCapitalGains(25));
	assertEquals("interest earned",866,year.interestEarned(25));
	
}

@Test
public void interestEarnedIsStartingBalanceCombinedWithInterestRate() {
	
	SavingsAccountYear year= new SavingsAccountYear(10000,3000,10);
	 assertEquals(1000, year.interestEarned(25));
}

@Test
public void WithdrawingFundsDonotEarnInterest() {
	SavingsAccountYear year= newAccount();
	year.withdraw(1000);
	assertEquals(900,year.interestEarned(25));
}
@Test
public void totalWithdrawnincludingCapitalGains() {
	SavingsAccountYear year= new SavingsAccountYear(10000,0,10);
	year.withdraw(1000);
	assertEquals("capital gains withdrawn",333,year.capitalGainsTaxIncurred(25));
	assertEquals("total withdrawn",1333,year.totalWithdrawnIncludingCapitalGainsTaxWithdrawn(25));
}


@Test
@Ignore
public void endingCapitalGainsIncludesInterestEarned() {
	SavingsAccountYear year= new SavingsAccountYear(10000,3000,10);
	 assertEquals(7000,year.startingCapitalGains());
	 assertEquals(4000,year.endingCapitalGains());
}

@Test
public void capitalGainTaxIncurred_NeedsToCoverCapitalGainsWithdrawn_AND_theAdditionalCapitalGainsWithdrawnToPayCapitalGainsTax( ) {
	SavingsAccountYear year = new SavingsAccountYear(10000,3000,10);
	year.withdraw(5000);
	assertEquals(2000,year.capitalGainsWithdrawn());
	assertEquals(666,year.capitalGainsTaxIncurred(25)); 
}

@Test
public void capitalGainsTaxIsIncludedInEndingBalance() {
	SavingsAccountYear year = new SavingsAccountYear(10000,3000,10);
	int amountWithdrawn=5000;
	year.withdraw(amountWithdrawn);
	int expectedCapitalGainsTax=666;
	assertEquals(expectedCapitalGainsTax,year.capitalGainsTaxIncurred(25));
	int expectedStartingBalanaceAfterWithdawals=10000-amountWithdrawn - expectedCapitalGainsTax; 
	assertEquals((int)(expectedStartingBalanaceAfterWithdawals * 1.10),year.endingBalance(25));
}
private SavingsAccountYear newAccount() {
	SavingsAccountYear account = new SavingsAccountYear(10000,10000, 10);
	return account;
}
}
