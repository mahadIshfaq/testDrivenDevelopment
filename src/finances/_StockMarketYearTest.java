package finances;
import static org.junit.Assert.*;
import org.junit.*;

public class _StockMarketYearTest {
	
private static final int INTEREST_RATE = 10;
private static final int STARTING_PRINCIPAL = 3000;
private static final int STARTING_BALANCE = 10000;
@Test
public void startingValues() {
	StockMarketYear year= new StockMarketYear(10000,3000,10);
		assertEquals("starting balance",STARTING_BALANCE,year.startingBalance());
	 assertEquals("starting principal",STARTING_PRINCIPAL,year.startingPrincipal());
	assertEquals("interest Rate principal",INTEREST_RATE,year.interestRate());
	assertEquals("total withdrawn default",0,year.totalWithdrawn(25));
}


@Test
public void endingBalance() {
	StockMarketYear year= new StockMarketYear(10000,3000,10);
	assertEquals("ending balance includes interest",11000,year.endingBalance(25));
	year.withdraw(1000);
	assertEquals("ending balance includes withdrawals",9900,year.endingBalance(25));
	year.withdraw(3000);
	assertEquals("ending balance includes capital gains tax withdrawals",6233,year.endingBalance(25));
}
@Test
public void nextYearStartingValuesMatchesThisYearEndingValues() {
	StockMarketYear thisYear= new StockMarketYear(10000,3000,10);
	StockMarketYear nextYear = thisYear.nextYear(25);
	assertEquals("starting Balance",thisYear.endingBalance(25),nextYear.startingBalance());
	assertEquals("starting principal",thisYear.endingPrincipal(),nextYear.startingPrincipal());
	assertEquals("interest",thisYear.interestRate(),nextYear.interestRate());

}

@Test
public void nextYearsStartingPrincipalEqualsThisYearsEndingPrincipal() {
	StockMarketYear thisYear = newYear();
	assertEquals(thisYear.endingPrincipal(),thisYear.nextYear(25).startingPrincipal());
}


private StockMarketYear newYear() {
	return new StockMarketYear(STARTING_BALANCE,STARTING_PRINCIPAL, INTEREST_RATE);
	
}
@Test
public void capitalGainsTax() {
	StockMarketYear year= new StockMarketYear(10000,3000,10);
	year.withdraw(4000);
	assertEquals("capital gains tax on withdrawals to cover capital gains",333,year.capitalGainsTaxIncurred(25));
	assertEquals("total withdrawn",4333,year.totalWithdrawn(25));
}
@Test
public void interestEarned() {
	
	StockMarketYear year= new StockMarketYear(10000,3000,10);
	 assertEquals("basic interest earned",1000, year.interestEarned(25));
	 year.withdraw(2000);
	 assertEquals("withdrawals don't earn interest ",800, year.interestEarned(25));
	 year.withdraw(2000);
	 assertEquals("capital gains tax withdrawns don't earn interest",566,year.interestEarned(25));
}

@Test
public void endingPrincipal() {
	StockMarketYear year= new StockMarketYear(10000,3000,10);
	 year.withdraw(1000);
	 assertEquals("ending principal consider withdrawals",2000, year.endingPrincipal());
	 year.withdraw(500);
	 assertEquals("ending principal consider total multiple withdrawals",1500, year.endingPrincipal());
	 year.withdraw(3000);
	 assertEquals("ending principal never goes below zero",0, year.endingPrincipal());
}
@Test
public void WithdrawingFundsDonotEarnInterest() {
	StockMarketYear year = newYear();
	year.withdraw(1000);
	assertEquals(900,year.interestEarned(25));
}

}
