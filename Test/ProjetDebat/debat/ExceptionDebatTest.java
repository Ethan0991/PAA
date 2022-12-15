package TestUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import debat.ExceptionDebat;

class ExceptionDebatTest {
	
	private ExceptionDebat expD;
	
	@BeforeEach
	void beforeAll(){
		expD= new ExceptionDebat("Erreur, dans votre classe Debat");
	}
	

	@Test
	void testExceptionDebat() {
		assertEquals(false,expD.equals(null));
		
	}

	@Test
	void testGetMessage() {
		assertEquals("Erreur, dans votre classe Debat",expD.getMessage());
		
	}

}
