package TestUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import file.ExceptionFileDebat;

class ExceptionFileDebatTest {
	
	private ExceptionFileDebat expFD;

	@BeforeAll
	void beforeAll(){
		expFD= new ExceptionFileDebat("Erreur, dans votre classe Fichier Débat");
	}
	
	@Test
	void testExceptionFileDebat() {
		assertEquals(false,expFD.equals(null));
	}

	@Test
	void testGetMessage() {
		assertEquals("Erreur, dans votre classe Fichier Débat",expFD.getMessage());
	}

}
