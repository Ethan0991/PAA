package ProjetDebat;


import java.util.Scanner;

import ProjetDebat.debat.*;
import ProjetDebat.file.*;
import ProjetDebat.graphique.*;

/**
 * Classe principal permettant de tester la création d'un débat à partir d'un fichier ou automatiquement 
 *
 * @author Ethan & Lathan
 */
public class MainDebat {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		char choix ='0' ;
		boolean affichageGraphique = false;
		do {
			try {

				System.out.print("Interface Graphique ? (Y/N)\n--> : ");
				choix = scanner.next().charAt(0);
			} catch (IllegalArgumentException e) {
				System.out.println("\nErreur, veuillez entrer Y ou N.");
			}
		} while (choix != 'Y' && choix != 'N');
		
		if (args.length>0) {
			System.out.println("Le graphe a été généré à partir du fichier entré en paramètre :\n");
			System.out.println(args[0]+"\n");
			FichierDebat fd = new FichierDebat(args[0]);
			fd.lireFichier();
			fd.creerGrapheFichier();
			Debat debat = new Debat(fd.getGrapheArg());
			debat.afficheGraphe();
			if (choix == 'Y') {
				affichageGraphique = true;
			}
			debat.affichageMenuRechercheSolution(affichageGraphique);
			
			if (choix == 'Y') {
				DebatGraphique.launchGraphique(args, debat);

			}
			
		}
		else {
			
			int nbArg = Debat.demanderNombreArgument(scanner);
			System.out.println("Vous avez indiqué qu'il y'a "  + nbArg+ " arguments à entrer.\n");	
			Debat debat = new Debat(nbArg);
			System.out.println("Le graphe est :\n");
			debat.afficheGraphe();
			
			debat.affichageMenuContradiction(scanner);
			debat.affichageMenuSolution();
		}
		scanner.close();

	}

}
