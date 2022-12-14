package ProjetDebat.debat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ProjetDebat.file.FichierDebat;
import up.mi.jgm.util.UtilMath;



class DebatTest {
	
	private static Debat debatNbArg;
	private static Debat debatFile;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		FichierDebat fd = new FichierDebat("./src/ProjetDebat/test.txt");
		fd.lireFichier();
		fd.creerGrapheFichier();
		debatFile = new Debat(fd.getGrapheArg());
		debatNbArg = new Debat(3);
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	//@Test
	@ParameterizedTest
	@ValueSource(ints = {6,9,1000})
	void testDemanderNbArgument(int nbTest) {
		Scanner scanner = new Scanner(Integer.toString(nbTest));
		Debat.demanderNombreArgument(scanner);
		
	}
	@ParameterizedTest
	@ValueSource(ints = {0,-1,-2193})
	void testWrongValue (int value) {
		Scanner scanner = new Scanner(Integer.toString(value));
		assertThrows(Exception.class, () -> { Debat.demanderNombreArgument(scanner);})  ;
	}
	

	@ParameterizedTest
	@ValueSource(ints = {2})
	void testAffichageContradiction(int choix) {
		
		Scanner scanner1 = new Scanner(Integer.toString(choix));
		Scanner scanner = new Scanner(System.in);
		debatNbArg.affichageMenuContradiction(scanner);
	}
//	@Test
//	void testAjouterContradiction() {
//		Scanner scanner1 = new Scanner("1");
//		Scanner scanner2 = new Scanner("2");
//		debatNbArg.ajouterContradiction(scanner1);
//	}

}
