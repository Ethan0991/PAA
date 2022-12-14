package up.mi.jgm.td3.exo1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class DicoT9Test {

	private static MonDicoT9 dico ;
	private static MonDicoT9 dico2 ;
	private static List<String> list;
	private static List<String> list2;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dico = new MonDicoT9 () ;
		dico2 = new MonDicoT9 () ;
		
	}

	@BeforeEach
	void setUp() throws Exception {
		dico.enregistrer ("bonjour") ;
		dico.enregistrer ("bonne") ;
		dico.enregistrer ("comme") ;
		list = new ArrayList<String>();
		list2 = new ArrayList<String>();
		
		list.add("bonne");list.add("comme");
		list2.add("bonjour");
	}

	@ParameterizedTest
	//@ValueSource(strings = {"26663","2665687"})
	@CsvSource ({ "26663", "2665687" })
	
	void dicoT9Test(String code) {

		//assertEquals(list, dico.recuperer(code));
		System.out.println(code);
		assertEquals(list2, dico.recuperer(code)); ;
		

	}

}
