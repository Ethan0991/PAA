package ProjetDebat.graphe;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class GrapheTest {
	
	private Argument arg;
	private Argument arg2;
	private Argument arg3;
	private Argument arg4;
	private Argument arg5;
	ArrayList<Argument>listArguments;
	private Graphe gphe;
	private Graphe gphe2;
	
	
	@BeforeAll
	void beforeAll(){
		arg = new Argument("A");
		arg2 = new Argument("B");
		arg3 = new Argument("C");
		arg4 = new Argument("D");
	
		listArguments = new ArrayList<Argument>();
		
		listArguments.add(arg);
		listArguments.add(arg2);
		listArguments.add(arg3);
		listArguments.add(arg4);
		
		//graphe fait avec une liste d'arguments en paramètre
		gphe = new Graphe(listArguments);
		//graphe fait sans paramètre
		gphe2 = new Graphe();
		
		gphe2.ajouterNoeud(arg);
		gphe2.ajouterArc(arg, arg2);
		gphe2.ajouterArc(arg, arg3);
		
	}
	@Test
	void testGrapheListOfArgument() {
		assertEquals(true,gphe.getGraphe().containsKey(arg));
		assertEquals(true,gphe.getGraphe().containsKey(arg2));
		assertEquals(true,gphe.getGraphe().containsKey(arg3));
		assertEquals(true,gphe.getGraphe().containsKey(arg4));
		assertEquals(false,gphe.getGraphe().containsKey(arg5));
	}

	@Test
	void testAjouterNoeud() {
		assertEquals(true,gphe2.getGraphe().containsKey(arg));
		assertEquals(false,gphe2.getGraphe().containsKey(arg2));
	}

	@Test
	void testAjouterArc() {
		assertEquals(true,gphe2.getGraphe().get(arg).contains(arg2));
		assertEquals(true,gphe2.getGraphe().get(arg).contains(arg3));
		assertEquals(false,gphe2.getGraphe().get(arg).contains(arg4));
	}

	@Test
	void testTrouverArgNom_1() {
		assertEquals(arg,gphe.trouverArgNom("A"));
	}
	@Test
	void testTrouverArgNom_2() {
		assertEquals(null,gphe.trouverArgNom("X"));
	}


	@Test
	void testToString() {
		assertEquals("\n"+ "L'Argument A, contredit les arguments suivants : \n"+"\n\t Argument B\n"+"\n\t Argument C\n",gphe2.toString());
	}


}