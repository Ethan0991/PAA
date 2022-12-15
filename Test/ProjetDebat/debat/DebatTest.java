package ProjetDebat.debat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ProjetDebat.file.FichierDebat;



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


	@ParameterizedTest
	@ValueSource(ints = {6,9,1000,0,-1,-2193})
	void testDemanderNbArgument(int nbTest) {
		Scanner scanner = new Scanner(Integer.toString(nbTest));
		if (nbTest<=0) {
			assertThrows(Exception.class, () -> { Debat.demanderNombreArgument(scanner);})  ;
		}
		else {
			Debat.demanderNombreArgument(scanner);
		}
		
		
	}

	@ParameterizedTest
	@ValueSource(strings = {"-1\n1\n\n2\n3\n\n2\n","-1\ne\n\n1\n3\n\n3\n3\n1\n2\n"})
	void testAffichageMenuContradiction(String inputs) {
		

		Scanner scanner = new Scanner(inputs);
		debatNbArg.affichageMenuContradiction(scanner);
	}
	
	void testAfficheGraphe() {
		debatFile.afficheGraphe();
	}
	void testAfficheArguments() {
		debatFile.afficheArguments(debatFile.getListArguments());
		debatNbArg.afficheArguments(debatNbArg.getListArguments());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"-1\n1\n\n2\n3\n\n2\n\n4\n3\n1\n3\n1\n\n1\n4,d\nBonjour\n-0.2\n4"})
	void testAffichageMenuSolution(String inputs) {
		

		Scanner scanner = new Scanner(inputs);
		debatNbArg.affichageMenuSolution(scanner);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"1\n1\n1\n1\n1\n1\n1\n2\\n2\n2\n2\n2\n2\n2\n3\nTestSave\n4,d\nBonjour\n-0.2\n4\n4 "})
	void testAffichageMenuRechercheSolution(String inputs) {

		Scanner scanner = new Scanner(inputs);
		debatFile.affichageMenuRechercheSolution(scanner,false);
		debatFile.getRs();
	}

}
