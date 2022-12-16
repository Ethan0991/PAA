package ProjetDebat.debat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import ProjetDebat.graphe.*;
import ProjetDebat.solution.*;
import up.mi.jgm.td1corrige.ExceptionMenu;


/**
 * Classe qui permet la construction d'un débat
 *
 * @author Ethan & Lathan
 */
public class Debat {

	
	private List<Argument> listArguments ;
	private Graphe grapheArg ;
	private Set<Argument> solutionPotentielle ;
	private int nbArg=0;
	private RechercheSolution rs ;
	private Set<Argument> solutionAdmissble,solutionPrefere;
	private String cheminFichier = "";
	private Set<Argument> derniereSolution = null ;
	

    /**
    * Construit un débat en fonction d'un graphe passé en paramètre
    * Version 1:le nom des arguments provient de du graphe passé en paramètre
    *
    * @param grapheArg est un graphe à partir duquel nous allons construire un débat
    */
	public Debat(Graphe grapheArg) {
		
		this.listArguments = new ArrayList<Argument>(grapheArg.getGraphe().keySet());
		Collections.sort(listArguments);
		this.grapheArg = grapheArg;
		nbArg = listArguments.size();
		solutionPotentielle = new HashSet<Argument>();
	}
	
    // Dans le cas ou on initialise A1,A2,AN automatiquement
    /**
    * Construit un débat en fonction d'un nombre en paramètre
    * Version 2:    le nom des arguments est choisi automatiquement A1,A2,...,AN
    *
    * @param nbArg est un nombre correspondant à l'ensemble des arguments utlisés dans ce débat
    */
    public Debat(int nbArg) {
		
		this.nbArg=nbArg;
		listArguments = new ArrayList<Argument>();
		grapheArg = new Graphe();
		solutionPotentielle = new HashSet<Argument>();
		for (int i = 0; i < nbArg; i++) {
			listArguments.add(new Argument("A"+(i+1)));
			grapheArg.ajouterNoeud(listArguments.get(i));
		}
	 
		
	}
	
	/** Demande à utlisateur d'entrer nombre d'arguments
	 * 
	 * @return nbArg est un nombre correspondant à l'ensemble des arguments utlisés dans ce débat
	 */
	public static int demanderNombreArgument(Scanner sc) {
		
		System.out.print("Entrez le nombre d'arguments : \n--> : ");
		int nbArg = 0;
		boolean condition = true;
		do {
			try {
				nbArg = sc.nextInt();
				if (nbArg<=0) {
					throw new ExceptionDebat("\nEntrez un nombre d'arguments supérieur ou égale à 1.\n--> : ");
				}
				condition = false;
				
			}catch (ExceptionDebat e) {
				sc.nextLine();
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
				System.out.print("Erreur, entrez un entier\n--> :");
				sc.nextLine();
			}
		} while (condition);
		
		return nbArg;
	}
	
	/**
	 * Affichage du 1er menu permettant de construire le débat
	 */
	
	public void affichageMenuContradiction(Scanner sc) {
		
		
		int choix =0 ;

			do {
				try {
					
			
					Menu.menuContradiction();
					System.out.print("\n--> : ");
					choix = sc.nextInt();
					sc.nextLine();

					
				
					if (choix  != 1 && choix!=2) {
						throw new ExceptionMenu("Veuillez entrer un choix correct\n");
						
					}

				
					switch (choix) {
						case 1:
							ajouterContradiction(sc);
							break;
						case 2:
							break;
						}
					
				} catch (ExceptionMenu e) {
					System.out.println(e.getMessage());
				}
				  catch (InputMismatchException e) {
					System.out.println("Erreur, veuillez entrer un entier\n");
					sc.nextLine();
				}
				
				
			
			} while (choix != 2);
				
		
	}

	/**
	 * Permet à l'utilisateur de choisir d'insérer une contraction dans le débat
     *
     *@param sc un objet de type scanner pour recevoir les input de l'utilisateur
	 */
	public void ajouterContradiction(Scanner sc) {

		int choix = -1;
		boolean sortie = false;
		Argument A1;
		Argument A2;
		
		do {
			try {
				
				System.out.println("\nEntrez l'argument qui va contredire :\n");
				afficheArguments(this.listArguments);
				System.out.print("\n\n--> : ");
				choix = sc.nextInt();

				A1 = listArguments.get(choix-1);
				
				System.out.print("\nEntrez l'argument qui est contredit :\n");
				afficheArguments(this.listArguments);
				System.out.print("\n\n--> : ");
				choix = sc.nextInt();
				A2 = listArguments.get(choix-1);
				if (grapheArg.getGraphe().get(A1)!=null) {
					if (grapheArg.getGraphe().get(A1).contains(A2) ) {
						sortie = true;
						throw new ExceptionDebat("\nErreur, cette contradiction a déjà été entrée");
					}
				}
				
				if(A2==A1) {
					throw new ExceptionDebat("\nUn argument ne peut pas se contredire lui même,recommencez.\n");
				}
				else {
					grapheArg.ajouterArc(A1, A2);
					sortie = true;
				}
				
			} catch (ExceptionDebat e) {
				sc.nextLine();
				System.out.println(e.getMessage());
			}
			catch (IllegalArgumentException e) {
				sc.nextLine();
				System.out.println("\nVeuillez entrer un entier.\n");
			}
		} while (!sortie);
		afficheGraphe();
	}

	/**
	 *	Permet l'affichage du graphe associé au débat
	 */
	public void afficheGraphe() {
		System.out.println(grapheArg.toString());
	}
	
	/**
	 *	Dresse la liste des arguments
     *
	 *@param listArguments liste des arguments du débat 
	 */
	public void afficheArguments(List <Argument> listArguments) {
		
		int cpt = 1;
		for (Argument arg : listArguments) {
			System.out.println("("+cpt +") "+ arg);
			cpt++;
		}
	}
	
	/**
	 *	Menu qui va permettre de construire la solution admissible du débat et de vérifier si celle-ci est correcte
	 */
	public void affichageMenuSolution(Scanner sc) {

		VerifSolution verif = new VerifSolution(solutionPotentielle, grapheArg);
		int choix =0 ;

			do {
				try {
					
			
					Menu.menuSolution(solutionPotentielle);
					System.out.print("\n--> : ");
					choix = sc.nextInt();
				
					if (choix<1 || choix>4) {
						throw new ExceptionMenu("Veuillez entrer un choix correct\n");
					}

				
					switch (choix) {
						case 1:
							ajouterArgument(sc);
							break;
						case 2:
							retirerArgument(sc);
							break;
						
						case 3:
							System.out.println("\n\n Vérification de la solution potentielle :\n\n\t" +solutionPotentielle+"\n");
							if (verif.verifSolutionAdmissible()) {
								System.out.println("\nLa solution est admissible.");
							}
							break;
						
						case 4:
							System.out.println("\nLa solution potentielle est : "+solutionPotentielle);
							if (verif.verifSolutionAdmissible()) {
								System.out.println("\nLa solution est admissible.");
							}
							System.out.println("\nFin du programme");
							break;
						}
					
				} catch (ExceptionMenu e) {
					sc.nextLine();
					
					System.out.println(e.getMessage());
				}
				  catch (InputMismatchException e) {
					System.out.println("Erreur, veuillez entrer un entier\n");
					sc.nextLine();
				}
				
			
			} while (choix != 4);
				//sc.close();
	}

    /**
     * Méthode affichant le menu de la recherche de solution et qui permet d'afficher une solution admissible, une solution préféree,
     * sauvegarder un fichier ou quitter le programme
     *
     *@param boolean affichageGraphique indique si l'utilisateur a choisit l'affichage graphique
     */
	public void affichageMenuRechercheSolution(Scanner sc,boolean affichageGraphique) {

		this.rs = new RechercheSolution(grapheArg);
		rs.construireListeCombinaisons();
		rs.construireListeSolutionAdmissible();
		rs.construireListeSolutionPrefere();
		
		int choix =0 ;
		boolean choix_possible = false;
		int cptArg =0;
		if (affichageGraphique) {
			sc.close();
			return ;
		}
		
		
			do {
				try {
					
			
					Menu.menuRechercheSolution();
					System.out.print("\n--> : ");
					choix = sc.nextInt();
					
				
					if (choix<1 || choix>4) {
						throw new ExceptionMenu("\nVeuillez entrer un choix correct\n");
					}

				
					switch (choix) {
						case 1:
							System.out.println("\nUne solution admissible est :\n");
							solutionAdmissble = rs.getAdmissible() ;
							
							for (Argument argument : solutionAdmissble) {
								if (cptArg>0) {
									System.out.print(",");
								}
								System.out.print(argument.toString().substring(9, argument.toString().length()));
								cptArg++;
							}
							cptArg=0;
							System.out.println();
							derniereSolution = solutionAdmissble;
							choix_possible = true;
							break;
						case 2:
							System.out.println("\nUne solution préferee est :\n");
							solutionPrefere = rs.getPreferee() ;
							for (Argument argument : solutionPrefere) {
								if (cptArg>0) {
									System.out.print(",");
								}
								System.out.print(argument.toString().substring(9, argument.toString().length()));
								cptArg++;
							}
							cptArg=0;
							choix_possible =true;
							derniereSolution = solutionPrefere;
							System.out.println();

							break;
						
						case 3:
							if (!choix_possible) {
								throw new ExceptionMenu("\nErreur, veuillez d'abord chercher une solution admissible ou préféree.\n");
							}
							System.out.print("Veuillez entrez le chemin du fichier dans lequel sauvegarder la solution (entrez"
									+ " uniquement le nom si vous ne voulez pas un fichier spécifique) :\n--> : ");
							
							sc.nextLine();
							cheminFichier = sc.nextLine();
							File fichierSauvegarde = new File(cheminFichier);
							try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichierSauvegarde))) {
								
								for (Argument argument : derniereSolution) {
									if (cptArg>0) {
										bw.write(",");
									}
									bw.write(argument.toString().substring(9, argument.toString().length()));
									cptArg++;
								}
							System.out.println("Fichier créé : "+cheminFichier);
								
							}catch (FileNotFoundException e) {
								System.out.print("Fichier non trouvé");
							}
							catch (IOException e) {
								System.out.print("Erreur IOException");
							}
							
							break;
						
						case 4:		
							System.out.println("\nFin du programme.");
							return;
						}
					
				} catch (ExceptionMenu e) {
					sc.nextLine();
					
					System.out.println(e.getMessage());
				}
				  catch (InputMismatchException e) {
					System.out.println("Erreur, veuillez entrer un entier \n");
					sc.nextLine();
				}
				
			
			} while (choix != 4);
				sc.close();
	}


	/**
	 * Permet l'ajout d'un argument dans la liste de solutionPotentielle
	 * 
	 * @param sc un objet de type scanner pour recevoir les input de l'utilisateur
	 */
	
	public void ajouterArgument(Scanner sc) {
		if (solutionPotentielle.size()<nbArg) {

			int choix = 0;
			Argument A;
			boolean sortie = false;
			do {
				try {
					
					System.out.print("\nChoisissez l'argument à ajouter à la solution :\n");
					afficheArguments(this.listArguments);
					System.out.print("\n\n--> : ");
					choix = sc.nextInt();
					A = listArguments.get(choix-1);
					if (solutionPotentielle.contains(A)) {
						System.out.println("\nL'argument est déjà dans la solution proposée.");
						break;
					}
					solutionPotentielle.add(A);
					sortie = true;
					
				}
				catch (IllegalArgumentException e) {
					sc.nextLine();
					System.out.println("\nErreur, Veuillez entrer un entier.\n");
				}
				catch (IndexOutOfBoundsException e) {
					sc.nextLine();
					System.out.println("\nVeuillez entrer un entier positif.");
				}
				catch (InputMismatchException e) {
					sc.nextLine();
					System.out.println("\nErreur, Veuillez entrer un entier.\n");
				}
			} while (!sortie);
		} else {
			System.out.println("\nImpossible d'ajouter un autre argument car l'ensemble de la solution potentielle est complet");
		}
	}
	
	public List<Argument> getListArguments() {
		return listArguments;
	}

	/**
	 * permet à l'utlisateur de choisir de retirer un argument de la solution potentielle
	 * 
	 * @param sc un objet de type scanner pour recevoir les input de l'utilisateur
	 */
	public void retirerArgument(Scanner sc) {
		if (solutionPotentielle.size()>0) {
			
		
			int choix = 0;
			Argument A = null;
			boolean sortie = false;
			do {
				try {
					
					System.out.print("\nChoisissez l'argument à retirer de la solution :\n\n");
					List<Argument> listArgSp = new ArrayList<Argument>();
					
					for (Argument argument : solutionPotentielle) {
						listArgSp.add(argument);
					}
					Collections.sort(listArgSp);
					afficheArguments(listArgSp);
					System.out.print("\n\n--> : ");
					choix = sc.nextInt(); 
					A = listArgSp.get(choix-1);
					if (choix > solutionPotentielle.size() || choix<=0 ) {
						throw new ExceptionDebat("\nEntrez un choix correct.\n");
					}
					
					solutionPotentielle.remove(A);
					sortie = true;
					
				}
				catch (ExceptionDebat e) {
					System.out.println(e.getMessage());
					
				}
				catch (IllegalArgumentException e) {
					System.out.println("entree");
					sc.nextLine();
					System.out.println("\nVeuillez entrer un entier.");
					System.out.println("sortie : "+sortie);
				}
				catch (IndexOutOfBoundsException e) {
					sc.nextLine();
					System.out.println("\nVeuillez entrer un entier positif.");
				}
				catch (InputMismatchException e) {
					sc.nextLine();
					System.out.println("\nErreur, Veuillez entrer un entier.\n");
				}
				
			} while (!sortie);
		}
		else {
			System.out.println("\nImpossible de retirer car la solution potentielle est vide");
		}

	
	}


	public RechercheSolution getRs() {
		return rs;
	}
	
	
	
	
}
