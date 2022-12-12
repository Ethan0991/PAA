package ProjetDebat.debat;
import java.util.Set;

import ProjetDebat.graphe.Argument;


/**
 * Classe contenant l'ensemble des différents menus proposant des options à l'utilisateur
 *
 * @author Ethan & Lathan
 */
public class Menu {

    /**
    * Affiche le menu des solutions  qui propose 4 options
    *
    */
	public static void menuSolution(Set<Argument> solutionPotentielle) {
		System.out.println("\n\nLa solution potentielle est : \t"+solutionPotentielle+"\n");
		System.out.println("\nQuelle operation souhaitez-vous effectuer ?\n");
		System.out.println("1) Ajouter un argument");
		System.out.println("2) Retirer un argument");
		System.out.println("3) Vérifier la solution");
		System.out.println("4) Fin");
		
	}
	
    /**
     * Affiche le menu des contractions qui propose 2 options
     *
    */
	public static void menuContradiction() {
		System.out.println("\nQuelle operation souhaitez-vous effectuer ?\n");
		System.out.println("1) Ajouter une contradiction");
		System.out.println("2) Quitter");
	}
	
    /**
    * Affiche le menu de recherche des solutions qui propose 4 options
    *
    */
	public static void menuRechercheSolution() {
		System.out.println("\nQuelle operation souhaitez-vous effectuer ?\n");
		System.out.println("1) Chercher une solution admissible");
		System.out.println("2) Chercher une solution préférée");
		System.out.println("3) Sauvegarder la solution");
		System.out.println("4) Fin");
	}
	
}
