package TestUnitaires;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import graphe.Argument;

/**
 * Classe permettant de tester le fichier Argument.java
 *
 * @author Ethan & Lathan
 */
class ArgumentTest {
	

	private Argument arg;
	private Argument arg2;
	
	@BeforeEach
	void beforeAll(){
	arg = new Argument("A");
	arg2 = new Argument("B");
	}

	@Test
	void testToString_1() {
		assertEquals("Argument A",arg.toString());
	}
	void testToString_2() {
		assertEquals("Argument B",arg2.toString());
	}

	@Test
	void testCompareTo_1() {
		assertEquals(-1,arg.compareTo(arg2));
	}
	@Test
	void testCompareTo_2() {
		assertEquals(1,arg2.compareTo(arg));
	}

	@Test
	void testGetNum_1() {
		assertEquals(4,arg.getNum());
	}
	
	@Test
	void testGetNum_2() {
		assertEquals(6,arg.getNum());
	}


	@Test
	void testGetNom_1() {
		assertEquals("A",arg.getNom());
	}
	@Test
	void testGetNom_2() {
		assertEquals("B",arg2.getNom());
	}

}
