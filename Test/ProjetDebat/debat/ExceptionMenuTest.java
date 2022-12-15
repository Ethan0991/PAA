package ProjetDebat.debat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class ExceptionMenuTest {

	private ExceptionMenu expM;
	
	@BeforeAll
	void beforeAll(){
		expM= new ExceptionMenu("Erreur, dans votre classe Menu");
	}
	
	@Test
	void testExceptionMenu() {
		assertEquals(false,expM.equals(null));
		
	}

	@Test
	void testGetMessage() {
		assertEquals("Erreur, dans votre classe Menu",expM.getMessage());
	}


}