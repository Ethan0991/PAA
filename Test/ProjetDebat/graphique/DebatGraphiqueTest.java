package ProjetDebat.graphique;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ProjetDebat.debat.Debat;
import ProjetDebat.file.FichierDebat;

class DebatGraphiqueTest {

	private static Debat debatFile;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		FichierDebat fd = new FichierDebat("./src/ProjetDebat/test.txt");
		fd.lireFichier();
		fd.creerGrapheFichier();
		debatFile = new Debat(fd.getGrapheArg());
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testLaunchGraphique() {
		
		DebatGraphique.launchGraphique(debatFile);
	}

}
