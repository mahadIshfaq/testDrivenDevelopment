package finances;
import static org.junit.Assert.*;
import org.junit.*;

public class _SavingsAccountTest {
	
@Test


public void depositAndWithdrawal() {

	SavingsAccount account = new SavingsAccount();
	account.deposit(100);
	assertEquals(100,account.balance());
	assertEquals("after deposit", 100, account.balance());
	account.withdraw(50);
	assertEquals("after withdraw", 50, account.balance());
	
}


public void nagitiveBalanceIsJustFine() {
	SavingsAccount account = new SavingsAccount();
	account.withdraw(75);
	assertEquals(-75,account.balance());
		 
}

@Test
public void nextYear() {
	SavingsAccount account = new SavingsAccount();
	account.deposit(10000);
	SavingsAccount nextYear=account.nextYear(10);
	assertEquals(11000,nextYear.balance());
	
}



}
