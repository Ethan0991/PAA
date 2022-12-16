package ProjetDebat.debat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Classe permettant de tester le fichier ExceptionDebat.java
 *
 * @author Ethan & Lathan
 */
class ExceptionDebatTest {
	
	private ExceptionDebat expD;
	
	@BeforeAll
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
