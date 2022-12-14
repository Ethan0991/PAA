package up.mi.jgm.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UtilMathTest {

	@Test
	void sumTest() {
		assertEquals(3, UtilMath.somme3(1, 1, 1));
	}
	@Test
	void factTest() {
		assertEquals(2, UtilMath.fact(0));
		
	}
	@Test
	void testNegativeValue ( ) {
		assertThrows(IllegalArgumentException.class, () -> { UtilMath.factrecur(-1);})  ;
	}

}
