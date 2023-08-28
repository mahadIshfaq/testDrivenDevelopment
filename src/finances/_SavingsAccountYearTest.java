package finances;
import static org.junit.Assert.*;
import org.junit.*;

public class _SavingsAccountYearTest {
@Test
public void startingBalance() {
	SavingsAccountYear account = new SavingsAccountYear(10000,10);
	assertEquals(10000,account.startingBalance());
}


@Test
public void endingBalance() {
	SavingsAccountYear account = new SavingsAccountYear(10000,10);
	assertEquals(11000,account.endingBalance());
}

@Test
public void nextYearsStartingBalanceShouldEqualThisYearEndingBalance() {
	SavingsAccountYear thisYear = new SavingsAccountYear(10000,10);
	assertEquals(thisYear.endingBalance(),thisYear.nextYear().startingBalance());

}

@Test

public void nextYearsInterestRateEqualsThisYearsInterestRate() {
	SavingsAccountYear thisYear=new SavingsAccountYear(10000,10);
	assertEquals(thisYear.interestRate(),thisYear.nextYear().interestRate());
	
}

}
