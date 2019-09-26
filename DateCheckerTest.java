import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DateCheckerTest {

	@Test
	void testIsValidDate() {
		DateChecker dateChecker = new DateChecker();
		
		assertEquals(true, dateChecker.isValidDate("09/26/2019"));
		assertEquals(false, dateChecker.isValidDate("09/32/2019"));
	}

}
