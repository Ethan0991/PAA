package ProjetDebat.file;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ProjetDebat.debat.Debat;

class FichierDebatTest {
	
	private static Debat debatFile;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@ParameterizedTest
	@ValueSource(strings = {"./src/ProjetDebat/test2.txt","./src/ProjetDebat/test.txt","./src/ProjetDebat/test3.txt"})
	void testFichierDebat(String nomFichier) {
		FichierDebat fd = new FichierDebat(nomFichier);
		fd.lireFichier();
		try {
			fd.creerGrapheFichier();
		} catch (Exception e) {
			
		}
		debatFile = new Debat(fd.getGrapheArg());
	}

}
