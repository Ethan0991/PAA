package ProjetDebat.graphe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArgumentTest {

	private static Argument arg;
	private static Argument arg2;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		arg = new Argument("A");
		arg2 = new Argument("B");
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testToString_1() {
		assertEquals("Argument A",arg.toString());
	}
	@Test
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
		assertNotEquals(4,arg.getNum());
	}
	
	@Test
	void testGetNum_2() {
		assertNotEquals(6,arg.getNum());
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
