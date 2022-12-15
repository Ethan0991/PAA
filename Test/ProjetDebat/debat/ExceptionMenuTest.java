package TestUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import debat.ExceptionMenu;


class ExceptionMenuTest {

	private ExceptionMenu expM;
	
	@BeforeEach
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
