package ProjetDebat.debat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ProjetDebat.graphe.Argument;
import ProjetDebat.graphe.Graphe;

/**
 * Classe permettant la vérification d'une solution potentielle
 * Contient en paramètre tous les ensembles nécessaires à cette vérification
 *
 * @author Ethan & Lathan
 */
public class VerifSolution {

	private Set <Argument> argContreditsParSp ;
	private Set<Argument> solutionPotentielle ;
	private Graphe grapheArg ;
	private boolean supressStdout ;


    /**
    * permet de créer un objet VerifSolution à partir d'une liste de solution potentielle
    *                                          et d'un graphe passé en paramètre
    *
    *  @param solutionPotentielle est une liste d'argument contenant les solutions à tester
    *  @param grapheArg est le graphe d'argument du débat
    *
    */
	public VerifSolution(Set<Argument> solutionPotentielle, Graphe grapheArg) {
		
		
	    this.solutionPotentielle = solutionPotentielle ;
		this.grapheArg = grapheArg ;
		argContreditsParSp = new HashSet<Argument>();
		supressStdout = false;
	}
	
    /**
    * permet de créer un objet VerifSolution à partir d'une liste de solution potentielle,d'un graphe     passé
    *                                                   et d'un booléen paramètre supressStdout
    *
    * @param solutionPotentielle est une liste d'argument contenant les solutions à tester
    * @param grapheArg est le graphe d'argument du débat
    * @param supressStdout est un booléan qui va servir à vérifer si il existe une contracdiction interne
    */
	public VerifSolution(Set<Argument> solutionPotentielle, Graphe grapheArg,boolean supressStdout) {
		
		
	    this.solutionPotentielle = solutionPotentielle ;
		this.grapheArg = grapheArg ;
		argContreditsParSp = new HashSet<Argument>();
		this.supressStdout = supressStdout;
	}

	/**
	 * Vérifie si solution potentielle est une solution admissible
	 * 
	 * @return true si la solution vérifie les 2 conditions, faux sinon
	 */
	
	public boolean verifSolutionAdmissible() {
		
	
		
		// 1ere condition : pas de contradiction interne
		boolean pasContradictionInterne;
		pasContradictionInterne = verifContradictionInterne();
		
		if (pasContradictionInterne == false) {
			return false;
		}
		// 2e condition : chaque argument contredit de E est défendu par un élement de E
		//Donc si arg de solutionPotentielle est dans allArgContredits, l'argument qui le contredit doit être dans argContreditParSp 
		boolean argDefendus;
		argDefendus = verifArgDefendu();
		
		return argDefendus;
	}
	
    /**
    * permet de vérifer si il existe une contraction interne pour chaque argument de la liste des solutions potentielles
    *
    * @return true en l'abscence de contraction interne
    * @return false en présence de contraction interne
    */
	private boolean verifContradictionInterne() {
		
		for (Argument arg : solutionPotentielle) {
			if (grapheArg.getGraphe().get(arg)!=null) {
				for (Argument argContredit : grapheArg.getGraphe().get(arg)) {
					if (solutionPotentielle.contains(argContredit)) {
						if (!supressStdout) {
							System.out.println("\nLa solution n'est pas admissible car contradiction"
									+ " interne :\n\n\t"+arg+" contredit :\t"+argContredit+"\n\t");
						}
						return false;
					}
					argContreditsParSp.add(argContredit); // on construit ensemblecontredits pour la 2e condition dans le cas ou la 1ere condition est vraie
				}
			}
			
		}
		return true;

	}
	
    /**
    * permet de vérifer si pour chaque argument de la liste des solutions potentielles,
    *                            il n'exite aucun autre arguments contredisant l'argument
    *
    * @return true si l'argument de la solution potentielle n'est contredit par aucun des autres arguments
    *                    ou si cet argument est bien défendu contre les autres arguments
    * @return false si l'argument de la solution potentielle est contredit par un autre argument
    */
	private boolean verifArgDefendu() {
		Set <Argument> allArgumentsContredits = new HashSet<Argument>();
		Set <Argument> allArgumentsExceptSolutionPotentielle = new HashSet<Argument>();
		
		for (Argument argument : grapheArg.getGraphe().keySet()) {
			if (!solutionPotentielle.contains(argument)) {
				allArgumentsExceptSolutionPotentielle.add(argument);
			}
		}
		
		for (Argument arg : grapheArg.getGraphe().keySet()) {		// On construit l'ensemble de tous les arguments contredits 	
				if (grapheArg.getGraphe().get(arg)!=null) {
					for (Argument arg2 : grapheArg.getGraphe().get(arg)) {  // (c-a-d l'ensemble des valeurs du graphe)	
						allArgumentsContredits.add(arg2);
					}
				}
		}
		
		for (Argument arg : solutionPotentielle) {
			if (allArgumentsContredits.contains(arg)) { // c-a-d si l'argument est contredit par un autre 
				for (Argument argExceptSp : allArgumentsExceptSolutionPotentielle) {
					if (grapheArg.getGraphe().get(argExceptSp)!=null) {
						if (grapheArg.getGraphe().get(argExceptSp).contains(arg)) {
							if (!argContreditsParSp.contains(argExceptSp)) {	//un argument se défend en contredisant l'argument qui le contredit
								if (!supressStdout) {
									System.out.println("La solution potentielle n'est pas admissible car aucun argument "
											+ "ne défend l'"+arg+" contredit par "+argExceptSp);
								}
								
								return false;
							}
						}
					}
					
				}
														
			}
		}
		return true;
	}
	
	
    /**
    * permet de changer l'attribut solutionPotentitelle par argument passé en paramètre
    *
    */
	public void setSolutionPotentielle(Set<Argument> solutionPotentielle) {
		this.solutionPotentielle = solutionPotentielle;
	}

    /**
    * permet de vérifer si pour chaque argument de la liste des solutions admissibles,
    *                            il remplit les critèrres pour être une solution préférée
    * @return true si la liste contient des solutions préférées
    * @return false si si la liste ne contient pas des solutions préférées
    */
	public boolean verifSolutionPrefere(List<HashSet<Argument>> listeSolutionAdmissible) {

		List<HashSet<Argument>> listeSolutionAdmissibleExceptSolPot = new ArrayList<HashSet<Argument>>(listeSolutionAdmissible);
		listeSolutionAdmissibleExceptSolPot.remove(solutionPotentielle);
		int cpt = 0;
		
		for (HashSet<Argument> solutionAdmissible : listeSolutionAdmissibleExceptSolPot) {
			cpt =0;
			for (Argument argument : solutionPotentielle) {
				if (solutionAdmissible.contains(argument)) {
					cpt ++ ;
				}
				if (cpt == solutionPotentielle.size()) {
					return false ;
				}
			}
		}
		return true ;
		
	}
	
}
